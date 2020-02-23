package com.codemcd.myarchive.service;

import com.codemcd.myarchive.domain.Link;
import com.codemcd.myarchive.domain.LinkRepository;
import com.codemcd.myarchive.service.dto.LinkResponseDto;
import com.codemcd.myarchive.service.dto.LinkResponseDtoList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LinksReadService {
    private final LinkRepository linkRepository;

    public LinkResponseDtoList getLinks() {
        List<Link> linksOrderByCreatedAtDesc = linkRepository.findAllByOrderByCreatedAtDesc();

        return new LinkResponseDtoList(linksOrderByCreatedAtDesc.stream()
                .map(LinkResponseDto::new)
                .collect(Collectors.toList()));
    }
}
