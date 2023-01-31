package com.elephant.dreamhi.service;

import com.elephant.dreamhi.model.entity.Follow;
import com.elephant.dreamhi.model.entity.User;
import com.elephant.dreamhi.model.statics.FollowType;
import com.elephant.dreamhi.repository.AnnouncementRepository;
import com.elephant.dreamhi.repository.FollowRepository;
import com.elephant.dreamhi.repository.ProducerRepository;
import com.elephant.dreamhi.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;

    private final UserRepository userRepository;

    private final ProducerRepository producerRepository;

    private final AnnouncementRepository announcementRepository;

    @Transactional(readOnly = true)
    public Long getFollowerCount(Long id) {
        return followRepository.countByActor_Id(id);
    }

    @Transactional(readOnly = true)
    public Boolean checkFollow(FollowType type, Long id, Long followerId) {
        return followRepository.checkFollow(type, id, followerId).isPresent();
    }

    /**
     * 팔로우 기능 구현 메소드
     *
     * @param type       : follow 할 type
     * @param id         : follow 할 id
     * @param followerId : follow 하는 사용자 id
     * @return true
     * @throws DuplicateKeyException : 이미 해당 대상을 팔로우 중일 때 발생
     * @throws org.springframework.dao.DataIntegrityViolationException : db 제약사항 위반
     */
    @Transactional
    public Boolean addFollow(FollowType type, Long id, Long followerId) throws DuplicateKeyException {
        Optional<Follow> follow = followRepository.checkFollow(type, id, followerId);
        if (follow.isPresent()) {
            throw new DuplicateKeyException("이미 팔로우 중입니다.");
        }

        Follow newFollow = buildFollowEntity(type, id, followerId);

        followRepository.save(newFollow);
        return true;
    }

    @Transactional
    public Boolean removeFollow(FollowType type, Long id, Long followerId) {
        int result = followRepository.deleteFollowWithCondition(type, id, followerId);
        return false;
    }

    /**
     * Follow Entity 생성 메소드
     *
     * @param type       : follow 할 type
     * @param id         : follow 할 id
     * @param followerId : follow 하는 사용자 id
     * @return Follow
     */
    private Follow buildFollowEntity(FollowType type, Long id, Long followerId) {
        Follow newFollow = Follow.builder()
                                 .type(type)
                                 .build();

        User follower = userRepository.getReferenceById(followerId);
        newFollow.setFollower(follower);

        Object relation = findRelation(type, id);

        newFollow.setRelationObject(type, relation);
        return newFollow;
    }

    /**
     * Follow Entity 생성을 위해 필요한 관계 찾기 메소드
     *
     * @param type
     * @param id
     */
    private Object findRelation(FollowType type, Long id) {
        Object proxyRelation = null;
        switch (type) {
            case ACTOR:
                proxyRelation = userRepository.getReferenceById(id);
                break;
            case ANNOUNCEMENT:
                proxyRelation = announcementRepository.getReferenceById(id);
                break;
            case PRODUCER:
                proxyRelation = producerRepository.getReferenceById(id);
                break;
            default:
                break;
        }
        return proxyRelation;
    }

}
