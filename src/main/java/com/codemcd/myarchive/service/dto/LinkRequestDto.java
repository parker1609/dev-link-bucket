package com.codemcd.myarchive.service.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class LinkRequestDto {
    private String uri;
    private String title;
    private List<String> tags;
    private String type;

    @Builder
    public LinkRequestDto(String uri, String title, List<String> tags, String type) {
        this.uri = uri;
        this.title = title;
        this.tags = tags;
        this.type = type;
    }
}
