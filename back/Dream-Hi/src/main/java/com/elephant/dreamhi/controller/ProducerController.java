package com.elephant.dreamhi.controller;

import com.elephant.dreamhi.service.ProducerService;
import com.elephant.dreamhi.utils.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProducerController {

    private final ProducerService producerService;

    @PostMapping("/api/producers")
    public ResponseEntity<?> createProducer(@RequestParam String name) {
        Long userId = 1L;
        Long id = producerService.createProducer(name, userId);
        return Response.create(HttpStatus.CREATED, "created", id);
    }


}
