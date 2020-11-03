package com.harshith.urlshortner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlShortnerService {

  @Autowired
  UrlShortenedRepository urlShortenedRepository;


  public UrlShortenedEntity save(String actualUrl) {

    UrlShortenedEntity entity = new UrlShortenedEntity();
    entity.setActualUrl(actualUrl);

    return urlShortenedRepository.save(entity);
  }

}
