package com.elephant.dreamhi.service;

import com.elephant.dreamhi.model.entity.Producer;
import com.elephant.dreamhi.model.entity.User;
import com.elephant.dreamhi.model.entity.UserProducerRelation;
import com.elephant.dreamhi.model.statics.ProducerRole;
import com.elephant.dreamhi.repository.ProducerRepository;
import com.elephant.dreamhi.repository.UserProducerRelationRepository;
import com.elephant.dreamhi.repository.UserRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProducerService {

    private final ProducerRepository producerRepository;
    private final UserProducerRelationRepository userProducerRelationRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long createProducer() {
        Long userId = 1L;

        // producer 엔티티만듬
        // producer 와 멤버 연결
        final Producer producer = Producer.builder()
                                          .name("test")
                                          .description("제작사 소개글을 입력해주세요".getBytes())
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

}
