package com.harshith.urlshortner;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlShortenedRepository extends JpaRepository<UrlShortenedEntity, Long> {

  UrlShortenedEntity findByOriginalUrl(String originalUrl);

  Optional<UrlShortenedEntity> findByUniqueIdByDate(long uniqueId);

}
