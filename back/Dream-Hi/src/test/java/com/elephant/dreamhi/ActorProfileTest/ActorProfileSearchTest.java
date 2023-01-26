package com.elephant.dreamhi.ActorProfileTest;

import com.elephant.dreamhi.model.dto.ActorSearchCondition;
import com.elephant.dreamhi.repository.ActorRepository;
import com.elephant.dreamhi.service.ActorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

@SpringBootTest
public class ActorProfileSearchTest {

    @Autowired
    private ActorService actorService;

    @Autowired
    private ActorRepository actorRepository;

    @Test
    public void search() throws Exception {
        final ActorSearchCondition condition = new ActorSearchCondition();
        final PageRequest of = PageRequest.of(0, 8);

        actorRepository.findActorSimpleProfiles(condition, of);
    }

}
