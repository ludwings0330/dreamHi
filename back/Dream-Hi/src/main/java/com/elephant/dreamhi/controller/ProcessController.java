package com.elephant.dreamhi.controller;

import com.elephant.dreamhi.security.PrincipalDetails;
import com.elephant.dreamhi.utils.Response.Body;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/announcements/process")
@RequiredArgsConstructor
public class ProcessController {

    @PostMapping
    @PreAuthorize("@checker.hasEditorAuthority(#user, #producerId)")
    public ResponseEntity<Body> updateProcess(@AuthenticationPrincipal PrincipalDetails user) {
        return null;
    }

}
