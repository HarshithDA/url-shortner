package com.harshith.urlshortner.entity;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import com.harshith.urlshortner.view.UrlShortenResponseView;

/**
 * Mapper class to map the fields of Entity to Views using mapstruct
 *
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")

public interface UrlShortenedEntityMapper {

  UrlShortenResponseView urlShortenedEntityToDto(UrlShortenedEntity entity);

  List<UrlShortenResponseView> urlShortenedEntityListToDtoList(List<UrlShortenedEntity> entity);

}
