package com.harshith.urlshortner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class UrlShortnerFactory {

  @Autowired
  UrlShortnerService urlShortnerService;

  public String getShortenedUrl(String actualUrl) throws UrlShortnerServiceException {

    if (actualUrl == null || actualUrl.isBlank()) {
      throw new UrlShortnerServiceException(
          "Actual URL cannot be null or empty, please provide a valid URL");
    }

    urlShortnerService.save(actualUrl);

    return "URL Shortened!";
  }

  public String redirectToActualUrl(@PathVariable("shortened_url") String shortenedUrl)
      throws UrlShortnerServiceException {
    return "Actual URL is : ''";
  }

}
