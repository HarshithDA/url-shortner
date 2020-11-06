package com.harshith.urlshortner.service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import com.harshith.urlshortner.entity.UrlShortenedEntity;
import com.harshith.urlshortner.entity.UrlShortenedEntityMapper;
import com.harshith.urlshortner.enums.SortByColumnEnum;
import com.harshith.urlshortner.enums.SortOrderEnum;
import com.harshith.urlshortner.view.UrlShortenResponseView;

@Service
public class UrlShortnerService {

  private static final Logger LOGGER = LoggerFactory.getLogger(UrlShortnerService.class);

  @Autowired
  UrlShortenedRepository urlShortenedRepository;

  @Autowired
  UrlShortenedEntityMapper urlShortenedEntityMapper;

  private static final String CONVERSION_PLACEHOLDER_CHARACTERS =
      "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
  private final char[] placeHolderCharacters = CONVERSION_PLACEHOLDER_CHARACTERS.toCharArray();
  private final int base62Count = placeHolderCharacters.length;


  public String getShortenedUrl(String originalUrl) {

    UrlShortenedEntity entity = urlShortenedRepository.findByOriginalUrl(originalUrl);
    long urlId;

    if (entity == null) {
      entity = new UrlShortenedEntity();
      entity.setOriginalUrl(originalUrl);
      entity.setUniqueIdByDate(new Date().getTime());
      entity.setCreatedDate(Instant.now());
      urlId = urlShortenedRepository.save(entity).getUrlId();
    } else {
      urlId = entity.getUrlId();
    }

    return convertUniqueIdtoShortUrl(urlId);
  }


  public String getShortenedUrlByUniqueDate(String originalUrl) {

    UrlShortenedEntity entity = urlShortenedRepository.findByOriginalUrl(originalUrl);
    long uniqueIdByDate;

    if (entity == null) {
      entity = new UrlShortenedEntity();
      entity.setOriginalUrl(originalUrl);
      entity.setUniqueIdByDate(new Date().getTime());
      entity.setCreatedDate(Instant.now());
      uniqueIdByDate = urlShortenedRepository.save(entity).getUniqueIdByDate();
    } else {
      uniqueIdByDate = entity.getUniqueIdByDate();
    }

    LOGGER.info("uniqueId Generated : " + uniqueIdByDate);

    return convertUniqueIdtoShortUrl(uniqueIdByDate);

  }


  /**
   * Convert Unique ID to base 62 String using the placeholder characters
   */
  private String convertUniqueIdtoShortUrl(long uniqueId) {
    StringBuilder encodedString = new StringBuilder();

    if (uniqueId == 0) {
      return String.valueOf(placeHolderCharacters[0]);
    }

    while (uniqueId > 0) {
      int characterIndex = (int) (uniqueId % base62Count);
      encodedString.append(placeHolderCharacters[characterIndex]);
      uniqueId = uniqueId / base62Count;
    }

    return encodedString.reverse().toString();
  }



  public UrlShortenResponseView getOriginalUrl(String shortenedUrl)
      throws UrlShortnerServiceException {
    long uniqueId = convertShortUrlToUniqueId(shortenedUrl);
    UrlShortenedEntity entity = null;

    Optional<UrlShortenedEntity> optional = urlShortenedRepository.findById(uniqueId);
    if (optional.isPresent()) {
      entity = optional.get();
    } else {
      entity = urlShortenedRepository.findByUniqueIdByDate(uniqueId)
          .orElseThrow(() -> new UrlShortnerServiceException(
              "Short URL Not found, please provide a short URL generated by 'Shorten the URL' API"));
    }

    return urlShortenedEntityMapper.urlShortenedEntityToDto(entity);
  }

  /**
   * Convert base 62 String to Unique ID using the placeholder characters
   */
  private long convertShortUrlToUniqueId(String shortUrl) {

    char[] characters = shortUrl.toCharArray();
    int length = characters.length;

    long uniqueId = 0;
    int charIndex = 1;
    for (int i = 0; i < length; i++) {

      int currentCharIndex = CONVERSION_PLACEHOLDER_CHARACTERS.indexOf(characters[i]);

      // converting base62 to original value by moving one index per character in short URL
      uniqueId = (long) (uniqueId + currentCharIndex * Math.pow(base62Count, length - charIndex));

      charIndex++;
    }

    return uniqueId;
  }



  public List<UrlShortenResponseView> getOriginalUrlList(int pageNumber, int pageSize,
      SortByColumnEnum sortBy, SortOrderEnum sortByOrder) {
    Direction direction = null;

    if (sortByOrder == SortOrderEnum.ASC) {
      direction = Sort.Direction.ASC;
    } else {
      direction = Sort.Direction.DESC;
    }

    PageRequest pageable =
        PageRequest.of(pageNumber - 1, pageSize, direction, sortBy.getTableColumn());

    return urlShortenedEntityMapper
        .urlShortenedEntityListToDtoList(urlShortenedRepository.findAll(pageable).getContent());
  }


}
