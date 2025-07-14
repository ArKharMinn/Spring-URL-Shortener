package com.url.url_shortener.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.url.url_shortener.Models.Link;

@Repository
public interface LinkRepo extends JpaRepository<Link, Long> {
    Link findByShortUrl(String shortUrl);
}
