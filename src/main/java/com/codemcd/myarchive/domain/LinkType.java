package com.codemcd.myarchive.domain;

import java.util.stream.Stream;

public enum LinkType {
    ARTICLE("글"),
    VIDEO("영상"),
    SLIDE("슬라이드");

    private String linkType;

    LinkType(String linkType) {
        this.linkType = linkType;
    }

    public static LinkType of(String type) {
        return Stream.of(LinkType.values())
                .filter(linkType -> linkType.getLinkType().equals(type))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 타입입니다."))
                ;
    }

    public String getLinkType() {
        return linkType;
    }
}
