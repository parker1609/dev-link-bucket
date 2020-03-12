package com.codemcd.myarchive.domain;

import com.codemcd.myarchive.domain.exception.NotFoundLinkTypeException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@RequiredArgsConstructor
@Getter
public enum LinkType {
    ARTICLE("글"),
    VIDEO("영상"),
    SLIDE("슬라이드");

    private final String linkType;

    public static LinkType of(String type) {
        return Stream.of(LinkType.values())
                .filter(linkType -> linkType.getLinkType().equals(type))
                .findAny()
                .orElseThrow(NotFoundLinkTypeException::new)
                ;
    }
}
