package com.codemcd.myarchive.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class LinkResponseDtoList {

    @JsonProperty("links")
    private List<LinkResponseDto> linkResponseDtos;

    private LinkResponseDtoList() {}

    public LinkResponseDtoList(List<LinkResponseDto> linkResponseDtos) {
        this.linkResponseDtos = linkResponseDtos;
    }

    public List<LinkResponseDto> getLinkResponseDtos() {
        return linkResponseDtos;
    }
}
