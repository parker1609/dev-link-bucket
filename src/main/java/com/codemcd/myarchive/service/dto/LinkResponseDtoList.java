package com.codemcd.myarchive.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class LinkResponseDtoList {

    @JsonProperty("links")
    private List<LinkResponseDto> linkResponseDtos;

    public LinkResponseDtoList(List<LinkResponseDto> linkResponseDtos) {
        this.linkResponseDtos = linkResponseDtos;
    }
}
