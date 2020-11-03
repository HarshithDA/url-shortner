package com.harshith.urlshortner;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")

public interface UrlShortenedEntityMapper {

  UrlShortenResponseView urlShortenedEntityToDto(UrlShortenedEntity entity);

}
