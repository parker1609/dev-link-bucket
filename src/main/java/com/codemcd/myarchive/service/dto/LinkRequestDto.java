package com.codemcd.myarchive.service.dto;

import java.util.List;

public class LinkRequestDto {
    private String uri;
    private String title;
    private List<String> tags;
    private String type;

    private LinkRequestDto() {}

    public LinkRequestDto(String uri, String title, List<String> tags, String type) {
        this.uri = uri;
        this.title = title;
        this.tags = tags;
        this.type = type;
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
