package com.elephant.dreamhi.service;

import com.elephant.dreamhi.exception.NotFoundException;
import com.elephant.dreamhi.model.dto.ProducerInfoResponseDto;
import com.elephant.dreamhi.model.dto.ProducerListResponseDto;
import com.elephant.dreamhi.model.dto.ProducerMemberDto;
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
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ProducerService {

    private final ProducerRepository producerRepository;
    private final FollowRepository followRepository;
    private final UserProducerRelationRepository userProducerRelationRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long createProducer(String name, Long userId) throws DuplicateKeyException, NotFoundException {
        producerRepository.findByName(name)
                          .ifPresent(t -> {
                              throw new DuplicateKeyException("중복된 제작사 이름입니다.");
                          });

        final Producer producer = Producer.builder()
                                          .name(name)
                                          .description("제작사 소개글을 입력해주세요")
                                          .picture(Picture.builder()
                                                          .url("base url")
                                                          .build())
                                          .build();

        producerRepository.save(producer);

        final User user = userRepository.findById(userId)
                                        .orElseThrow(() -> new NotFoundException("존재하지 않는 유저입니다."));

        final UserProducerRelation relation = UserProducerRelation.builder()
                                                                  .position("STAFF")
                                                                  .role(ProducerRole.EDITOR)
                                                                  .build();
        relation.setUser(user);
        relation.setProducer(producer);

        userProducerRelationRepository.save(relation);

        return producer.getId();
    }

    @Transactional
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

    public ProducerInfoResponseDto getProducerInfoById(Long producerId, Long userId) throws NotFoundException {

        final ProducerInfoResponseDto responseDto = producerRepository.findById(producerId)
                                                                      .map(ProducerInfoResponseDto::new)
                                                                      .orElseThrow(() -> new NotFoundException("존재하지 않는 제작사입니다."));

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

    public List<ProducerMemberDto> findMembersByProducerId(Long producerId) {
        return producerRepository.findMembersByProducerId(producerId)
                                 .stream()
                                 .map(ProducerMemberDto::new)
                                 .collect(Collectors.toList());
    }

    @Transactional
    public void addProducerMember(Long producerId, Long userId, ProducerMemberDto member) throws RuntimeException {
        final User user = userRepository.findById(userId).orElseThrow();
        final Producer producer = producerRepository.findById(producerId).orElseThrow();

        // 이미 제작진에 포함 되어있음
        userProducerRelationRepository.findByProducer_IdAndUser_Id(producerId, userId)
                                      .ifPresent(m -> {
                                                     throw new RuntimeException();
                                                 }
                                      );

        final UserProducerRelation newMember = UserProducerRelation.builder()
                                                                   .position(member.getPosition())
                                                                   .build();
        newMember.setUser(user);
        newMember.setProducer(producer);

        userProducerRelationRepository.save(newMember);

    }

    @Transactional
    public void deleteProducerMember(Long producerId, Long userId) {
        log.info("producerId=[{}], userId=[{}]", producerId, userId);
        userProducerRelationRepository.deleteByProducer_IdAndUser_Id(producerId, userId);
    }

    @Transactional
    public void modifyProducerMemberInfo(Long producerId, Long userId, ProducerMemberDto producerMemberDto) {
        final UserProducerRelation userProducerRelation = userProducerRelationRepository.findByProducer_IdAndUser_Id(producerId, userId)
                                                                                        .orElseThrow();
        userProducerRelation.changePosition(producerMemberDto.getPosition());
    }

}
