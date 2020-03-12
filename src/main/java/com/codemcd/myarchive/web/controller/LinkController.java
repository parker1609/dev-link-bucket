package com.codemcd.myarchive.web.controller;

import com.codemcd.myarchive.service.LinkService;
import com.codemcd.myarchive.service.dto.LinkDeleteResponseDto;
import com.codemcd.myarchive.service.dto.LinkRequestDto;
import com.codemcd.myarchive.service.dto.LinkResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class LinkController {
    private final LinkService linkService;

    @PostMapping("/api/links")
    public ResponseEntity create(@RequestBody @Valid LinkRequestDto linkRequest) {
        LinkResponseDto linkResponse = linkService.create(linkRequest);

        return ResponseEntity.ok(linkResponse);
    }

    @PutMapping("/api/links/{linkId}")
    public ResponseEntity update(@PathVariable Long linkId,
                                 @RequestBody @Valid LinkRequestDto linkRequest) {
        LinkResponseDto updatedLinkResponse = linkService.update(linkId, linkRequest);

        return ResponseEntity.ok(updatedLinkResponse);
    }

    @DeleteMapping("/api/links/{linkId}")
    public ResponseEntity delete(@PathVariable Long linkId) {
        LinkDeleteResponseDto deleteResponse = linkService.delete(linkId);

        return ResponseEntity.ok(deleteResponse);
    }
}
