package com.elephant.dreamhi.controller;

import com.elephant.dreamhi.model.dto.ProcessSaveDto;
import com.elephant.dreamhi.security.PrincipalDetails;
import com.elephant.dreamhi.service.ProcessService;
import com.elephant.dreamhi.utils.Response;
import com.elephant.dreamhi.utils.Response.Body;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/announcements/process")
@RequiredArgsConstructor
public class ProcessController {

    private final ProcessService processService;

    @PostMapping
    @PreAuthorize("@checker.hasEditorAuthority(#user, #processSaveDto.producerId)")
    public ResponseEntity<Body> saveProcess(@RequestBody @Valid ProcessSaveDto processSaveDto, @AuthenticationPrincipal PrincipalDetails user) {
        processService.saveProcess(processSaveDto);
        return Response.created();
    }

}
