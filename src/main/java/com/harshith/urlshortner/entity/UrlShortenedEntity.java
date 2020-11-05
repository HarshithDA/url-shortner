package com.harshith.urlshortner.entity;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "T_URL_SHORTENED")
@Data
@NoArgsConstructor
public class UrlShortenedEntity implements Serializable {

  private static final long serialVersionUID = 1159536781632649314L;

  @Id
  @SequenceGenerator(name = "url_id_seq", sequenceName = "url_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "url_id_seq")
  @Column(name = "url_id")
  private Long urlId;

  @Column(name = "original_url")
  private String originalUrl;

  @Column(name = "unique_id_by_date")
  private Long uniqueIdByDate;

  @Column(name = "created_date")
  private Instant createdDate;

}
