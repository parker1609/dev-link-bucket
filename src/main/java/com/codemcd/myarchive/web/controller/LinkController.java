package com.codemcd.myarchive.web.controller;

import com.codemcd.myarchive.service.LinkService;
import com.codemcd.myarchive.service.dto.LinkRequestDto;
import com.codemcd.myarchive.service.dto.LinkResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LinkController {
    private LinkService linkService;

    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @PostMapping("/api/links")
    public ResponseEntity create(@RequestBody LinkRequestDto linkRequestDto) {
        LinkResponseDto linkResponseDto = linkService.create(linkRequestDto);

        return ResponseEntity.ok(linkResponseDto);
    }
}
