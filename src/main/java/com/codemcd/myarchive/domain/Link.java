package com.codemcd.myarchive.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "LINKS")
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "URI", nullable = false)
    private String uri;

    @Column(name = "TITLE")
    private String title;

    @ManyToMany
    @JoinTable(name = "LINK_TAG_CONN",
            joinColumns = @JoinColumn(name = "LINK_ID"),
            inverseJoinColumns = @JoinColumn(name = "TAG_ID"))
    private List<Tag> tags = new ArrayList<>();

    @Convert(converter = LinkTypeConverter.class)
    @Column(name = "TYPE")
    private LinkType type;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Builder
    public Link(String uri, String title, List<Tag> tags, LinkType type) {
        this.uri = uri;
        this.title = title;
        this.tags = tags;
        this.type = type;
    }

    public void update(Link another) {
        this.uri = another.uri;
        this.title = another.title;
        this.tags = another.tags;
        this.type = another.type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Link)) return false;
        Link link = (Link) o;
        return getId().equals(link.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
