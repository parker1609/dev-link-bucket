package com.codemcd.myarchive.service;

import com.codemcd.myarchive.domain.Link;
import com.codemcd.myarchive.domain.Tag;
import com.codemcd.myarchive.domain.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TagService {
    private final TagRepository tagRepository;

    @Transactional
    public List<Tag> convertToTags(List<String> tags) {
        return tags.stream()
                .map(this::createOrGet)
                .collect(Collectors.toList())
                ;
    }

    @Transactional(readOnly = true)
    public List<Link> findLinksByTagNameInTheLatestOrder(String tagName) {
        Tag tag = tagRepository.findByName(tagName)
                .orElseThrow(() -> new IllegalArgumentException("해당 이름의 Tag가 존재하지 않습니다. name: " + tagName));
        tag.sortLinksOrderByUpdatedTimeDesc();

        return tag.getLinks();
    }

    private Tag createOrGet(String tagName) {
        return tagRepository.findByName(tagName)
                .orElseGet(() -> tagRepository.save(new Tag(tagName)))
                ;
    }
}
