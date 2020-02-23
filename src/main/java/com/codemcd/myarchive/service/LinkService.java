package com.codemcd.myarchive.service;

import com.codemcd.myarchive.domain.Link;
import com.codemcd.myarchive.domain.LinkRepository;
import com.codemcd.myarchive.domain.LinkType;
import com.codemcd.myarchive.domain.Tag;
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
    public LinkResponseDto create(LinkRequestDto linkRequestDto) {
        // TODO: 2020/02/18 uri 중복 검사, 형식 검사, tag 중복 검사
        List<Tag> tags = linkRequestDto.getTags().stream()
                .map(tagService::createOrGet)
                .collect(Collectors.toList())
                ;

        Link savedLink = linkRepository.save(new Link(
                linkRequestDto.getUri(),
                linkRequestDto.getTitle(),
                tags,
                LinkType.of(linkRequestDto.getType())));

        return new LinkResponseDto(savedLink);
    }
}
