package com.elephant.dreamhi.security;


import com.elephant.dreamhi.model.entity.User;
//import com.elephant.dreamhi.model.entity.UserProducerRelation;
//import com.elephant.dreamhi.repository.UserProducerRelationRepository;
import com.elephant.dreamhi.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

//    private final UserProducerRelationRepository userProducerRelationRepository;

    /**
     * - authenticate 실행 시 수행되는 로직 - role 들을 조회해 Authorities 정의
     *
     * @param email : 로그인 시도한 이메일
     * @return UserDetails -> authenticate() 수행하고 Authentication을 만드는데 사용된다.
     * @throws UsernameNotFoundException : 회원 정보 없다면 Exception 발생
     */
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("[" + UserDetailsService.class.getName() + "] " + email + " 유저 정보가 없습니다.")
        );
//        List<UserProducerRelation> userProducerRelations = userProducerRelationRepository.findAllByUser_Id(user.getId());

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(String.valueOf(user.getRole())));
//        userProducerRelations.forEach(u -> {
//            authorities.add(
//                    new SimpleGrantedAuthority(u.getRole() + "_" + String.valueOf(u.getId()))
//            );
//        });
        return PrincipalDetails.builder()
                               .id(user.getId())
                               .email(user.getEmail())
                               .password(user.getPassword())
                               .authorities(authorities)
                               .build();
    }

}