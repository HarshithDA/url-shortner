package com.harshith.urlshortner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.Instant;
import java.util.Date;
import javax.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;
import com.harshith.urlshortner.config.UrlShortnerJpaConfig;
import com.harshith.urlshortner.entity.UrlShortenedEntity;
import com.harshith.urlshortner.service.UrlShortenedRepository;

@ContextConfiguration(classes = {UrlShortnerJpaConfig.class},
    loader = AnnotationConfigContextLoader.class)
@Transactional
@SpringBootTest
class UrlShortnerApplicationTests {

  @Resource
  private UrlShortenedRepository urlShortenedRepository;

  @Test
  public void givenStudent_whenSave_thenGetOk() {
    UrlShortenedEntity entity = new UrlShortenedEntity(100L, "https://news.google.com/topstories",
        new Date().getTime(), Instant.now());
    Long urlId = urlShortenedRepository.save(entity).getUrlId();

    UrlShortenedEntity entitySaved = urlShortenedRepository.findById(urlId).get();
    assertEquals("https://news.google.com/topstories", entitySaved.getOriginalUrl());
  }

}
