package com.elephant.dreamhi.service;

import com.elephant.dreamhi.model.dto.ProducerInfoResponseDto;
import com.elephant.dreamhi.model.dto.ProducerListResponseDto;
import com.elephant.dreamhi.model.dto.ProducerSearchCondition;
import com.elephant.dreamhi.model.dto.ProducerUpdateRequestDto;
import com.elephant.dreamhi.model.entity.Picture;
import com.elephant.dreamhi.model.entity.Producer;
import com.elephant.dreamhi.model.entity.User;
import com.elephant.dreamhi.model.entity.UserProducerRelation;
import com.elephant.dreamhi.model.statics.ProducerRole;
import com.elephant.dreamhi.repository.FollowRepository;
import com.elephant.dreamhi.repository.ProducerRepository;
import com.elephant.dreamhi.repository.UserProducerRelationRepository;
import com.elephant.dreamhi.repository.UserRepository;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProducerService {

    private final ProducerRepository producerRepository;
    private final FollowRepository followRepository;
    private final UserProducerRelationRepository userProducerRelationRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long createProducer(String name, Long userId) {
        // producer 엔티티만듬
        // producer 와 멤버 연결
        final Producer producer = Producer.builder()
                                          .name(name)
                                          .description("제작사 소개글을 입력해주세요")
                                          .picture(Picture.builder()
                                                          .url("base url")
                                                          .build())
                                          .build();
        producerRepository.save(producer);

        final Optional<User> findUser = userRepository.findById(userId);

        final User user = findUser.orElseThrow();

        final UserProducerRelation relation = UserProducerRelation.builder()
                                                                  .position("STAFF")
                                                                  .role(ProducerRole.EDITOR)
                                                                  .build();
        relation.setUser(user);
        relation.setProducer(producer);

        userProducerRelationRepository.save(relation);

        return producer.getId();
    }

    public void deleteProducer(Long producerId) {
        producerRepository.deleteById(producerId);
    }

    @Transactional
    public void updateProducerInfo(Long producerId, ProducerUpdateRequestDto producerDto) {
        final Optional<Producer> byId = producerRepository.findById(producerId);
        final Producer producer = byId.orElseThrow();

        log.info("producer : {}, {}, {}", producer.getId(), producer.getDescription(), producer.getPicture().getUrl());
        log.info("producer Dto : {}", producerDto);
        producer.updateInfo(producerDto);
    }

    public ProducerInfoResponseDto getProducerInfoById(Long producerId, Long userId) {

        ProducerInfoResponseDto responseDto = new ProducerInfoResponseDto();

        final Producer producer = producerRepository.findById(producerId).orElseThrow();
        responseDto.setInfo(producer);

        followRepository.findByProducer_IdAndFollower_Id(producerId, userId)
                        .ifPresent(follow -> responseDto.setIsFollow(true));

        return responseDto;
    }

    public Page<ProducerListResponseDto> searchProducersByCondition(ProducerSearchCondition condition,
                                                                    Pageable pageable) {

        final Page<ProducerListResponseDto> producers = producerRepository.findProducersByCondition(condition, pageable);

        Set<Long> producerFollowInfo = new HashSet<>(followRepository.findProducerIdByFollowerId(condition.getUserId()));

        producers.getContent()
                 .forEach(item -> item.setIsFollow(producerFollowInfo.contains(item.getId())));

        return producers;
    }

}
