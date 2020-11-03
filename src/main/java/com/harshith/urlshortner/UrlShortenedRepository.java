package com.harshith.urlshortner;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlShortenedRepository extends JpaRepository<UrlShortenedEntity, Long> {



}
