package com.harshith.urlshortner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


/**
 * This resource handles the photo related operations for Legacy SSO based approach.
 */

@Api(value = "Profile Photo Service Operations")
@RestController
@RequestMapping("/v1")
public class UrlShortnerResource {


  @Autowired
  private UrlShortnerFactory urlShortnerFactory;


  @ApiOperation(value = "Shorten the URL", response = String.class)
  @GetMapping(path = "/shorten")
  public String getShortenedUrl(
      @ApiParam("Actual Url") @RequestParam(value = "actual_url", required = true) String actualUrl)
      throws UrlShortnerServiceException {
    return urlShortnerFactory.getShortenedUrl(actualUrl);
  }


  @ApiOperation(value = "Redirect to actual URL", response = String.class)
  @GetMapping(value = "/redirect")
  public String redirectToActualUrl(
      @ApiParam("Shortened Url") @RequestParam(value = "shortened_url",
          required = true) String shortenedUrl)
      throws UrlShortnerServiceException {
    return urlShortnerFactory.redirectToActualUrl(shortenedUrl);
  }

}
