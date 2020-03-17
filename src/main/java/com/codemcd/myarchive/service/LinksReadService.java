package com.codemcd.myarchive.service;

import com.codemcd.myarchive.domain.Link;
import com.codemcd.myarchive.service.dto.LinkResponseDto;
import com.codemcd.myarchive.service.dto.LinkResponseDtoList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LinksReadService {
    private final LinkService linkService;
    private final TagService tagService;

    @Transactional(readOnly = true)
    public LinkResponseDtoList getLinks() {
        List<Link> links = linkService.findLinksInTheLatestOrder();

        return new LinkResponseDtoList(links.stream()
                .map(LinkResponseDto::new)
                .collect(Collectors.toList()));
    }

    @Transactional(readOnly = true)
    public LinkResponseDtoList getLinksByTagName(String tagName) {
        List<Link> links = tagService.findLinksByTagNameInTheLatestOrder(tagName);

        return new LinkResponseDtoList(links.stream()
                .map(LinkResponseDto::new)
                .collect(Collectors.toList()));
    }
}
