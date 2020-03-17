package com.codemcd.myarchive.web.controller;

import com.codemcd.myarchive.service.LinksReadService;
import com.codemcd.myarchive.service.dto.LinkResponseDtoList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LinksReadController {
    private final LinksReadService linksReadService;

    @GetMapping("/api/links")
    public ResponseEntity getLinks() {
        LinkResponseDtoList links = linksReadService.getLinks();

        return ResponseEntity.ok(links);
    }

    @GetMapping("/api/tags/{tagName}")
    public ResponseEntity getLinksByTagName(@PathVariable String tagName) {
        LinkResponseDtoList links = linksReadService.getLinksByTagName(tagName);

        return ResponseEntity.ok(links);
    }
}
