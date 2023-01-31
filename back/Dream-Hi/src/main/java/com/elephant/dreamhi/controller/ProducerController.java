package com.elephant.dreamhi.controller;

import com.elephant.dreamhi.model.dto.ProducerInfoResponseDto;
import com.elephant.dreamhi.model.dto.ProducerListResponseDto;
import com.elephant.dreamhi.model.dto.ProducerMemberDto;
import com.elephant.dreamhi.model.dto.ProducerSearchCondition;
import com.elephant.dreamhi.model.dto.ProducerUpdateRequestDto;
import com.elephant.dreamhi.service.ProducerService;
import com.elephant.dreamhi.utils.Response;
import com.elephant.dreamhi.utils.Response.Body;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProducerController {

    private final ProducerService producerService;

    @GetMapping("/api/producers")
    public ResponseEntity<Body> getAllProducers(@RequestBody ProducerSearchCondition condition,
                                                @PageableDefault Pageable pageable) {
        condition.setUserId(1L);
        Page<ProducerListResponseDto> result = producerService.searchProducersByCondition(condition, pageable);
        return Response.create(HttpStatus.OK, "ok", result);
    }

    @GetMapping("/api/producers/{producerId}")
    public ResponseEntity<Body> getProducerInfoById(@PathVariable Long producerId) {
        Long userId = 1L;
        ProducerInfoResponseDto responseDto = producerService.getProducerInfoById(producerId, userId);
        return Response.create(HttpStatus.OK, "ok", responseDto);
    }

    @PutMapping("/api/producers/{producerId}")
    public ResponseEntity<Body> updateProducerInfo(@PathVariable Long producerId,
                                                   @RequestBody ProducerUpdateRequestDto producerDto) {
        producerService.updateProducerInfo(producerId, producerDto);
        return Response.ok();
    }

    @PostMapping("/api/producers")
    public ResponseEntity<Body> createProducer(@RequestParam String name) {
        Long userId = 1L;
        Long id = producerService.createProducer(name, userId);
        return Response.create(HttpStatus.CREATED, "created", id);
    }

    @DeleteMapping("/api/producers/{producerId}")
    public ResponseEntity<Body> deleteProducer(@PathVariable Long producerId) {
        // 해당 제작사에 대한 권한이 존재하는지 확인 이후 삭제
        producerService.deleteProducer(producerId);

        return Response.create(HttpStatus.OK, "deleted");
    }

    @GetMapping("/api/producers/{producerId}/users")
    public ResponseEntity<Body> findMembersByProducerId(@PathVariable Long producerId) {
        List<ProducerMemberDto> result = producerService.findMembersByProducerId(producerId);
        return Response.create(HttpStatus.OK, "ok", result);
    }

    @PostMapping("/api/producers/{producerId}/users/{userId}")
    public ResponseEntity<Body> addProducerMember(@PathVariable Long producerId,
                                                  @PathVariable Long userId,
                                                  @RequestBody ProducerMemberDto member) throws Exception {
        producerService.addProducerMember(producerId, userId, member);
        return Response.ok();
    }

    @DeleteMapping("/api/producers/{producerId}/users/{userId}")
    public ResponseEntity<Body> deleteProducerMember(@PathVariable Long producerId,
                                                     @PathVariable Long userId) {
        producerService.deleteProducerMember(producerId, userId);
        return Response.ok();
    }

    @PutMapping("/api/producers/{producerId}/users/{userId}")
    public ResponseEntity<Body> modifyProducerMemberInfo(@PathVariable Long producerId,
                                                         @PathVariable Long userId,
                                                         @RequestBody ProducerMemberDto requestDto) {
        producerService.modifyProducerMemberInfo(producerId, userId, requestDto);
        return Response.ok();
    }

}
