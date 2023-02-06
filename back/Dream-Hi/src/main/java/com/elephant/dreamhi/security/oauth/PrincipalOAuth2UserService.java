package com.elephant.dreamhi.security.oauth;

import com.elephant.dreamhi.model.entity.ActorProfile;
import com.elephant.dreamhi.model.entity.User;
//import com.elephant.dreamhi.model.entity.UserProducerRelation;
import com.elephant.dreamhi.model.statics.ProviderType;
import com.elephant.dreamhi.model.statics.UserRole;
import com.elephant.dreamhi.repository.ActorRepository;
//import com.elephant.dreamhi.repository.UserProducerRelationRepository;
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

//    private final UserProducerRelationRepository userProducerRelationRepository;

    private final ActorRepository actorRepository;

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
        OAuth2User oauth2User = super.loadUser(userRequest);
        OAuth2UserInfo userInfo = getOAuth2UserInfo(userRequest, oauth2User);

        return process(oauth2User, userInfo);
    }

    /**
     * 회원가입 or 로그인 수행 메소드
     *
     * @param oauth2User
     * @param userInfo   : 인증 서버로부터 받은 사용자 정보
     * @return 권한 정보 담긴 Principal
     */
    private PrincipalDetails process(OAuth2User oauth2User, OAuth2UserInfo userInfo) {
        Optional<User> user = userRepository.findByEmail(userInfo.getEmail());

        boolean isNew = false;
        User userEntity;

        if (user.isPresent()) {
            log.info("[{}] {} 유저 소셜 로그인 요청", PrincipalOAuth2UserService.class.getName(), userInfo.getEmail());
            userEntity = user.get();
            // 존재한다면 수정 로직 추가 가능
        } else {
            isNew = true;
            userEntity = registerUser(userInfo);
            registerActorProfile(userEntity);
        }

        List<GrantedAuthority> authorities = getAuthorities(userEntity);

        return PrincipalDetails.builder()
                               .id(userEntity.getId())
                               .email(userEntity.getEmail())
                               .password(userEntity.getPassword())
                               .authorities(authorities)
                               .attributes(oauth2User.getAttributes())
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
//        List<UserProducerRelation> userProducerRelations = userProducerRelationRepository.findAllByUser_Id(userEntity.getId());
        authorities.add(new SimpleGrantedAuthority(String.valueOf(userEntity.getRole())));
//        userProducerRelations.forEach(u -> {
//            authorities.add(
//                    new SimpleGrantedAuthority(u.getRole() + "_" + String.valueOf(u.getId()))
//            );
//        });
        return authorities;
    }

    /**
     * UserInfoFactory를 이용해 provider에 맞는 UserInfo 객체 가져오기.
     *
     * @param userRequest
     * @param oauth2User
     * @return provider에 맞는 UserInfo 객체
     */
    private static OAuth2UserInfo getOAuth2UserInfo(OAuth2UserRequest userRequest, OAuth2User oauth2User) {
        // provider type
        ProviderType providerType =
                ProviderType.valueOf(userRequest.getClientRegistration().getRegistrationId().toUpperCase());
        // get userInfo from Factory
        return OAuth2UserInfoFactory.getOAuth2UserInfo(providerType, oauth2User.getAttributes());
    }

    /**
     * 회원가입 메소드
     *
     * @param userInfo : 소셜 로그인한 UserInfo
     * @return 생성 후 User 객체
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    User registerUser(OAuth2UserInfo userInfo) {
        log.info("[{}] {} 유저 신규 회원 가입 절차 진행", PrincipalOAuth2UserService.class.getName(), userInfo.getEmail());
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

    /**
     * 회원가입 시 자동으로 ActorProfile 생성
     *
     * @param user : 회원가입한 user
     * @return 생성 후 ActorProfile 객체
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    ActorProfile registerActorProfile(User user) {
        return actorRepository.save(
                ActorProfile.builder()
                        .user(user)
                        .title("연기하는 배우 " + user.getName() + " 입니다")
                            .build()
        );
    }

}
