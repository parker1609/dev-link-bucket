package com.codemcd.myarchive.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "LINKS")
public class Link {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name = "URI")
    private String uri;

    @Column(name = "TITLE")
    private String title;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "LINK_ID")
    private List<Tag> tags = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE")
    private LinkType type;

    protected Link() {}

    public Link(String uri, String title, List<Tag> tags, LinkType type) {
        this.uri = uri;
        this.title = title;
        this.tags = tags;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public String getUri() {
        return uri;
    }

    public String getTitle() {
        return title;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public LinkType getType() {
        return type;
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
