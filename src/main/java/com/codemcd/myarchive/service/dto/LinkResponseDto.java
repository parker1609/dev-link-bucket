package com.codemcd.myarchive.service.dto;

import com.codemcd.myarchive.domain.Link;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class LinkResponseDto {
    private Long id;
    private String uri;
    private String title;
    private List<String> tags;
    private String type;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public LinkResponseDto(Link entity) {
        this.id = entity.getId();
        this.uri = entity.getUri();
        this.title = entity.getTitle();
        this.tags = entity.getTags().stream().map(tag->tag.getName()).collect(Collectors.toList());
        this.type = entity.getType().getLinkType();
        this.createdAt = entity.getCreatedAt();
        this.updatedAt = entity.getUpdatedAt();
    }
}
