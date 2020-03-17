package com.codemcd.myarchive.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LinkRepository extends JpaRepository<Link, Long> {
    List<Link> findAllByOrderByUpdatedAtDesc();
}
