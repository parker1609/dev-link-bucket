package com.codemcd.myarchive.service.dto;

import java.util.List;

public class LinkResponseDto {
    private Long id;
    private String uri;
    private String title;
    private List<String> tags;
    private String type;

    public LinkResponseDto(Long id, String uri, String title, List<String> tags, String type) {
        this.id = id;
        this.uri = uri;
        this.title = title;
        this.tags = tags;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public String getUri() {
        return uri;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getTags() {
        return tags;
    }

    public String getType() {
        return type;
    }
}
