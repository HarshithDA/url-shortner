package com.harshith.urlshortner.view;

import java.time.Instant;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * View to be presented to users which maps with UrlShortenedEntity
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UrlShortenResponseView {

  private Long urlId;

  private String originalUrl;

  private Long uniqueIdByDate;

  private Instant createdDate;

}
