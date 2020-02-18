package com.codemcd.myarchive.service;

import com.codemcd.myarchive.domain.Tag;
import com.codemcd.myarchive.domain.TagRepository;
import org.springframework.stereotype.Service;

@Service
public class TagService {
    private TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public Tag createOrGet(String tagName) {
        return tagRepository.findByName(tagName)
                .orElse(tagRepository.save(new Tag(tagName)))
                ;
    }
}
