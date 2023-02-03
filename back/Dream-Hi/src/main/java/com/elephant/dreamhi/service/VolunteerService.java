package com.elephant.dreamhi.service;

import com.elephant.dreamhi.model.dto.VolunteerApplyRequestDto;
import com.elephant.dreamhi.repository.VolunteerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class VolunteerService {

    private final AuthService authService;
    private final VolunteerRepository volunteerRepository;

    public void userApplyOnAnnouncement(Long id, VolunteerApplyRequestDto requestDto) {
        if (authService.isAnonymous(id)) {
            throw new AccessDeniedException("익명 사용자는 지원할 수 없습니다.");
        }

        // 캐스팅을 돌면서 volunteer 엔티티를 만들고 저장한다. (한번에)
    }

}
