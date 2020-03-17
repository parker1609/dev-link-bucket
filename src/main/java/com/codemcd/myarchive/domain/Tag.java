package com.codemcd.myarchive.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "TAGS")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", unique = true)
    private String name;

    @ManyToMany(mappedBy = "tags")
    private List<Link> links = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Builder
    public Tag(String name) {
        this.name = name;
    }

    public void sortLinksOrderByUpdatedTimeDesc() {
        links.sort((link1, link2) -> {
            if (link1.getUpdatedAt().isBefore(link2.getUpdatedAt())) {
                return 1;
            } else {
                return -1;
            }
        });
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tag)) return false;
        Tag tag = (Tag) o;
        return getId().equals(tag.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
