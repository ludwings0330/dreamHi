package com.elephant.dreamhi.service;

import com.elephant.dreamhi.model.dto.PictureDto;
import com.elephant.dreamhi.model.dto.UserSimpleDto;
import com.elephant.dreamhi.model.entity.User;
import com.elephant.dreamhi.repository.ActorRepository;
import com.elephant.dreamhi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    /**
     * User 기본 정보 조회 메소드
     *
     * @param id
     * @return UserDetailDto
     * @throws UsernameNotFoundException : id 조회 결과 없을 경우 발생
     */
    @Override
    public UserSimpleDto findUserSimple(Long id) throws UsernameNotFoundException {
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(id + "번은 존재하지 않는 유저입니다."));
        return new UserSimpleDto(user);
    }

    @Override
    @Transactional
    public void updateMainProfile(Long id, PictureDto pictureDto) throws UsernameNotFoundException {
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(id + "번은 존재하지 않는 유저입니다."));

        user.setMainProfile(pictureDto);
    }

}
