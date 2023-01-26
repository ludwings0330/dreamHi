package com.elephant.dreamhi.controller;

import com.elephant.dreamhi.service.ProducerService;
import com.elephant.dreamhi.utils.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProducerController {

    private final ProducerService producerService;

    @PostMapping("/api/producers")
    public ResponseEntity<?> createProducer() {
        Long id = producerService.createProducer();
        return Response.ok();
    }

}
