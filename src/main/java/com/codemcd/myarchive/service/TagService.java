package com.codemcd.myarchive.service;

import com.codemcd.myarchive.domain.Tag;
import com.codemcd.myarchive.domain.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TagService {
    private final TagRepository tagRepository;

    public Tag createOrGet(String tagName) {
        return tagRepository.findByName(tagName)
                .orElse(tagRepository.save(new Tag(tagName)))
                ;
    }
}
