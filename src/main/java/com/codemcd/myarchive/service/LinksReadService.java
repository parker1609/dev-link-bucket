package com.codemcd.myarchive.service;

import com.codemcd.myarchive.domain.Link;
import com.codemcd.myarchive.domain.LinkRepository;
import com.codemcd.myarchive.domain.Tag;
import com.codemcd.myarchive.service.dto.LinkResponseDto;
import com.codemcd.myarchive.service.dto.LinkResponseDtoList;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LinksReadService {
    private LinkRepository linkRepository;

    public LinksReadService(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    public LinkResponseDtoList getLinks() {
        List<Link> linksOrderByCreatedAtDesc = linkRepository.findAllByOrderByCreatedAtDesc();

        return new LinkResponseDtoList(linksOrderByCreatedAtDesc.stream()
                .map(link -> new LinkResponseDto(link.getId(), link.getUri(), link.getTitle(),
                        link.getTags().stream().map(Tag::getName).collect(Collectors.toList()),
                        link.getType().getLinkType(), link.getCreatedAt(), link.getUpdatedAt()))
                .collect(Collectors.toList()));
    }
}
