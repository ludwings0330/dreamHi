package com.elephant.dreamhi.security.jwt;

import com.elephant.dreamhi.model.dto.JwtResponse;
import com.elephant.dreamhi.security.PrincipalDetails;
import com.elephant.dreamhi.model.dto.TokenDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Slf4j
@Component
public class TokenProvider implements InitializingBean {

    private static final String AUTHORITIES_CLAIM_KEY = "auth";

    private static final String EMAIL_CLAIM_KEY = "email";

    private final String header;

    private final String secret;

    private final Long accessTokenValidityInMilliSeconds;

    private final Long refreshTokenValidityInMilliSeconds;

    private Key key;


    public TokenProvider(
            @Value("${jwt.header}") String header,
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.accesstoken-validity-in-seconds}") Long accessTokenValidityInMilliSeconds,
            @Value("${jwt.refreshtoken-validity-in-seconds}") Long refreshTokenValidityInMilliSeconds
    ) {
        this.header = header;
        this.secret = secret;
        this.accessTokenValidityInMilliSeconds = accessTokenValidityInMilliSeconds;
        this.refreshTokenValidityInMilliSeconds = refreshTokenValidityInMilliSeconds;
    }

    /**
     * 초기 설정 한 번만 진행하기 위한 메소드
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Access Token 생성 메소드
     *
     * @param authentication : SecurityContext에 담긴 Authentication 객체 정보
     */
    public String createAccessToken(Authentication authentication) {
        return createToken(authentication, this.accessTokenValidityInMilliSeconds);
    }

    /**
     * Refresh Token 생성 메소드
     *
     * @param authentication : SecurityContext에 담긴 Authentication 객체 정보
     */
    public String createRefreshToken(Authentication authentication) {
        return createToken(authentication, this.refreshTokenValidityInMilliSeconds);
    }

    /**
     * 토큰 유효성 검사 메소드
     *
     * @param token : Token 문자열
     * @return 유효하다면 true 반환 그렇지 않다면 false 반환
     */
    public boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            log.info("만료된 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            log.info("잘못된 JWT 토큰입니다.");
        }

        return false;
    }

    /**
     * Request의 Header로부터 Token 추출 메소드
     *
     * @param request : ServletRequest
     * @return Bearer Token : request의 header에 Auhtorization : Bearer {token} 정보를 통해 Token 추출
     */
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(header);

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }

        return null;
    }

    /**
     * Token으로부터 Authentication 정보 생성
     *
     * @param token : Token 문자열
     * @return Token에 불변 데이터 userId, email, authorities를 담아두고 이를 활용해 Authentication을 만들어 반환
     */
    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token).getBody();
        Collection<? extends GrantedAuthority> authorities = getAuthorities(claims);

        PrincipalDetails principal = PrincipalDetails.builder()
                                                     .id(Long.parseLong(claims.getSubject()))
                                                     .email((String) claims.get(EMAIL_CLAIM_KEY))
                                                     .authorities(authorities)
                                                     .build();

        // Authentication => Principal, Credential, Authorities
        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    /**
     * 토큰 생성 메소드
     *
     * @param authentication : SecurityContext에 담긴 Authentication 객체 정보
     * @param validityTime   : 토큰의 만료시간
     * @return JWT을 생성 후 반환
     */
    private String createToken(Authentication authentication, long validityTime) {
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        String authorities = getAuthorities(principalDetails);
        Date expirationTime = new Date(System.currentTimeMillis() + validityTime);

        return Jwts.builder()
                   .setSubject(String.valueOf(principalDetails.getId()))
                   .claim(AUTHORITIES_CLAIM_KEY, authorities)
                   .claim(EMAIL_CLAIM_KEY, principalDetails.getEmail())
                   .setExpiration(expirationTime)
                   .signWith(this.key, SignatureAlgorithm.HS512)
                   .compact();
    }

    /**
     * Principal(접근 주체)를 통해 권한 정보를 얻는 메소드
     *
     * @param principalDetails : 현재 접근중인 주체 [UserDetails의 구현체]
     * @return authorities의 정보를 token에 담기위해 String 형태로 반환합니다.
     */
    private String getAuthorities(PrincipalDetails principalDetails) {
        return principalDetails.getAuthorities()
                               .stream()
                               .map(GrantedAuthority::getAuthority)
                               .collect(Collectors.joining(","));
    }

    /**
     * 토큰으로부터 Authorities 정보 얻는 메소드
     *
     * @param claims : Token의 Payload 의 데이터 단위 -> id, email, authorities 포함
     * @return Authorities 반환 [Collection 형태로 반환]
     */
    private Collection<? extends GrantedAuthority> getAuthorities(Claims claims) {
        String[] authorities = claims.get(AUTHORITIES_CLAIM_KEY)
                                     .toString()
                                     .split(",");

        return Arrays.stream(authorities)
                     .map(SimpleGrantedAuthority::new)
                     .collect(Collectors.toList());
    }

    /**
     * Token parsing 하여 유효성 검증 수행
     *
     * @param token : JWT
     * @throws SecurityException
     * @throws MalformedJwtException
     * @throws ExpiredJwtException
     * @throws UnsupportedJwtException
     * @throws IllegalArgumentException
     */
    private Jws<Claims> getClaims(String token)
            throws SecurityException, MalformedJwtException, ExpiredJwtException, UnsupportedJwtException, IllegalArgumentException {
        return Jwts.parserBuilder()
                   .setSigningKey(this.key)
                   .build()
                   .parseClaimsJws(token);
    }

}
