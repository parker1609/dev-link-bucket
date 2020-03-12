package com.codemcd.myarchive.web.controller;

import com.codemcd.myarchive.service.LinkService;
import com.codemcd.myarchive.service.dto.LinkRequestDto;
import com.codemcd.myarchive.service.dto.LinkResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class LinkController {
    private final LinkService linkService;

    @PostMapping("/api/links")
    public ResponseEntity create(@RequestBody @Valid LinkRequestDto linkRequestDto) {
        LinkResponseDto linkResponseDto = linkService.create(linkRequestDto);

        return ResponseEntity.ok(linkResponseDto);
    }
}
