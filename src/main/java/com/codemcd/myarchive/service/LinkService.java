package com.codemcd.myarchive.service;

import com.codemcd.myarchive.domain.Link;
import com.codemcd.myarchive.domain.LinkRepository;
import com.codemcd.myarchive.domain.LinkType;
import com.codemcd.myarchive.domain.Tag;
import com.codemcd.myarchive.service.dto.LinkDeleteResponseDto;
import com.codemcd.myarchive.service.dto.LinkRequestDto;
import com.codemcd.myarchive.service.dto.LinkResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LinkService {
    private final LinkRepository linkRepository;
    private final TagService tagService;

    @Transactional
    public LinkResponseDto create(LinkRequestDto linkRequest) {
        // TODO: 2020/02/18 uri 중복 검사, 형식 검사, tag 중복 검사
        List<Tag> tags = linkRequest.getTags().stream()
                .map(tagService::createOrGet)
                .collect(Collectors.toList())
                ;

        Link savedLink = linkRepository.save(
                Link.builder()
                        .uri(linkRequest.getUri())
                        .title(linkRequest.getTitle())
                        .tags(tags)
                        .type(LinkType.of(linkRequest.getType()))
                        .build()
                );

        return new LinkResponseDto(savedLink);
    }

    @Transactional
    public LinkResponseDto update(Long linkId, LinkRequestDto linkRequest) {
        Link link = linkRepository.findById(linkId)
                .orElseThrow(() -> new IllegalArgumentException("해당 id의 Link가 존재하지 않습니다. id: " + linkId));

        link.update(linkRequest.toEntity());

        return new LinkResponseDto(link);
    }

    @Transactional
    public LinkDeleteResponseDto delete(Long linkId) {
        Link link = linkRepository.findById(linkId)
                .orElseThrow(() -> new IllegalArgumentException("해당 id의 Link가 존재하지 않습니다. id: " + linkId));

        linkRepository.delete(link);

        return new LinkDeleteResponseDto(link.getId());
    }
}
