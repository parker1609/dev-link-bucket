package com.codemcd.myarchive.service;

import com.codemcd.myarchive.domain.Tag;
import com.codemcd.myarchive.domain.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TagService {
    private final TagRepository tagRepository;

    public List<Tag> convertToTags(List<String> tags) {
        return tags.stream()
                .map(this::createOrGet)
                .collect(Collectors.toList())
                ;
    }

    private Tag createOrGet(String tagName) {
        return tagRepository.findByName(tagName)
                .orElseGet(() -> tagRepository.save(new Tag(tagName)))
                ;
    }
}
