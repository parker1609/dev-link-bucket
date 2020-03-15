package com.codemcd.myarchive.service.dto;

import com.codemcd.myarchive.domain.Link;
import com.codemcd.myarchive.domain.LinkType;
import com.codemcd.myarchive.domain.Tag;
import com.codemcd.myarchive.service.TagService;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class LinkRequestDto {

    @NotBlank(message = "URI를 입력해주세요.")
    private String uri;

    @NotBlank(message = "제목을 입력해주세요.")
    private String title;

    private List<String> tags;

    @NotBlank(message = "타입을 입력해주세요.")
    private String type;

    @Builder
    public LinkRequestDto(String uri, String title, List<String> tags, String type) {
        this.uri = uri;
        this.title = title;
        this.tags = tags;
        this.type = type;
    }

    public Link toEntity(TagService tagService) {
        List<Tag> tagList = tagService.convertToTags(tags);

        return Link.builder()
                .uri(uri)
                .title(title)
                .tags(tagList)
                .type(LinkType.of(type))
                .build();
    }
}
