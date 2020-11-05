package com.harshith.urlshortner.service;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.harshith.urlshortner.entity.UrlShortenedEntity;

@Repository
public interface UrlShortenedRepository extends JpaRepository<UrlShortenedEntity, Long> {

  UrlShortenedEntity findByOriginalUrl(String originalUrl);

  Optional<UrlShortenedEntity> findByUniqueIdByDate(long uniqueId);

}
