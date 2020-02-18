package com.codemcd.myarchive.service.dto;

import java.time.LocalDateTime;
import java.util.List;

public class LinkResponseDto {
    private Long id;
    private String uri;
    private String title;
    private List<String> tags;
    private String type;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private LinkResponseDto() {}

    public LinkResponseDto(Long id, String uri, String title, List<String> tags, String type, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.uri = uri;
        this.title = title;
        this.tags = tags;
        this.type = type;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
