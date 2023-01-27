package com.elephant.dreamhi.security.oauth;

import com.elephant.dreamhi.model.entity.User;
import com.elephant.dreamhi.model.entity.UserProducerRelation;
import com.elephant.dreamhi.model.statics.ProviderType;
import com.elephant.dreamhi.model.statics.UserRole;
import com.elephant.dreamhi.repository.UserProducerRelationRepository;
import com.elephant.dreamhi.repository.UserRepository;
import com.elephant.dreamhi.security.PrincipalDetails;
import com.elephant.dreamhi.security.oauth.provider.OAuth2UserInfo;
import com.elephant.dreamhi.security.oauth.provider.OAuth2UserInfoFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PrincipalOAuth2UserService extends DefaultOAuth2UserService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final UserProducerRelationRepository userProducerRelationRepository;

    /**
     * 인증 서버로부터 Access Token을 받아 서버로부터 필요한 데이터 조회한 결과[userRequest] 이용해 회원가입/로그인 수행 메소드
     *
     * @param userRequest : 인증 서버로부터 받은 사용자 정보
     * @return 우리 서버에서 로직 수행 후 로그인 성공한 Principal 반환 -> SecurityContext에 저장하는데 사용됨
     * @throws OAuth2AuthenticationException
     */
    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("PrincipalOAuth2UserService : userRequest => ", userRequest);
        OAuth2User oAuth2User = super.loadUser(userRequest);
        log.info("oAuth2Useruser => {}\t Attr : {}", oAuth2User, oAuth2User.getAttributes());
        log.info("registrationId = {}", userRequest.getClientRegistration().getRegistrationId());
        log.info("usernameAttributeName = {}", userRequest.getClientRegistration()
                                                          .getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName());

        OAuth2UserInfo userInfo = getOAuth2UserInfo(userRequest, oAuth2User);

        return process(oAuth2User, userInfo);
    }

    /**
     * 회원가입 or 로그인 수행 메소드
     *
     * @param oAuth2User
     * @param userInfo   : 인증 서버로부터 받은 사용자 정보
     * @return 권한 정보 담긴 Principal
     */
    private PrincipalDetails process(OAuth2User oAuth2User, OAuth2UserInfo userInfo) {
        Optional<User> user = userRepository.findByEmail(userInfo.getEmail());

        boolean isNew = false;
        User userEntity;

        if (user.isPresent()) {
            userEntity = user.get();
            // 존재한다면 수정 로직 추가 가능
        } else {
            isNew = true;
            userEntity = registerUser(userInfo);
        }

        List<GrantedAuthority> authorities = getAuthorities(userEntity);
        log.info("id : {}, name : {}, email : {}, phone : {}, role : {} ,authorities : {}", userEntity.getId()
                , userEntity.getName(), userEntity.getEmail(), userEntity.getPhone(), userEntity.getRole(), authorities);

        return PrincipalDetails.builder()
                               .id(userEntity.getId())
                               .email(userEntity.getEmail())
                               .password(userEntity.getPassword())
                               .authorities(authorities)
                               .attributes(oAuth2User.getAttributes())
                               .isNew(isNew)
                               .build();
    }

    /**
     * 해당 유저의 모든 role 조회 후 Authorities 생성 메소드
     *
     * @param userEntity : userEntitu
     * @return Authorities
     */
    private List<GrantedAuthority> getAuthorities(User userEntity) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<UserProducerRelation> userProducerRelations = userProducerRelationRepository.findAllByUser_Id(userEntity.getId());
        log.info("UserProducerRelations : {} ", userProducerRelations.size());
        userProducerRelations.forEach(u -> {
            log.info("userProdRelation => {}, {}, {}", u.getId(), u.getPosition(), u.getRole());
        });
        authorities.add(new SimpleGrantedAuthority(String.valueOf(userEntity.getRole())));
        userProducerRelations.forEach(u -> {
            authorities.add(
                    new SimpleGrantedAuthority(u.getRole() + "_" + String.valueOf(u.getId()))
            );
        });
        return authorities;
    }

    /**
     * UserInfoFactory를 이용해 provider에 맞는 UserInfo 객체 가져오기.
     *
     * @param userRequest
     * @param oAuth2User
     * @return provider에 맞는 UserInfo 객체
     */
    private static OAuth2UserInfo getOAuth2UserInfo(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {
        // provider type
        ProviderType providerType =
                ProviderType.valueOf(userRequest.getClientRegistration().getRegistrationId().toUpperCase());
        // get userInfo from Factory
        return OAuth2UserInfoFactory.getOAuth2UserInfo(providerType, oAuth2User.getAttributes());
    }

    /**
     * 회원가입 메소드
     *
     * @param userInfo : 소셜 로그인한 UserInfo
     * @return 생성 후 User 객체
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    User registerUser(OAuth2UserInfo userInfo) {
        log.info("없으니까 새로 가입!!");
        return userRepository.save(
                User.builder()
                    .email(userInfo.getEmail())
                    .name(userInfo.getName())
                    .password(passwordEncoder.encode(UUID.randomUUID().toString()))
                    .phone(userInfo.getPhone() != null ? userInfo.getPhone() : null)
                    .role(UserRole.ROLE_USER)
                    .build()
        );
    }

}