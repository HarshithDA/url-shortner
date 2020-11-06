package com.harshith.urlshortner.resource;

import java.net.URISyntaxException;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.harshith.urlshortner.factory.UrlShortnerFactory;
import com.harshith.urlshortner.service.UrlShortnerServiceException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


/**
 * This resource handles the URL Redirect Operations for the shortened URLs
 */

@Api(value = "URL Redirect Operations", tags = "URL Redirect APIs")
@RestController
@RequestMapping("/v1/redirect")
public class UrlRedirectResource {


  @Autowired
  private UrlShortnerFactory urlShortnerFactory;

  @ApiOperation(value = "Redirect to Original URL with URI Response Approach",
      response = Response.class)
  @GetMapping(value = "/response")
  public Response redirectToOriginalUrlResponse(
      @ApiParam("Shortened Url") @RequestParam(value = "shortened_url",
          required = true) String shortenedUrl)
      throws UrlShortnerServiceException, URISyntaxException {
    return urlShortnerFactory.redirectToOriginalUrl(shortenedUrl);
  }

  @ApiOperation(value = "Redirect to Original URL with URI Response Approach",
      response = Response.class)
  @GetMapping(value = "/response-v2")
  public Response redirectToOriginalUrlResponseV2(
      @ApiParam("Shortened Url") @RequestParam(value = "shortened_url",
          required = true) String shortenedUrl)
      throws UrlShortnerServiceException, URISyntaxException {
    return urlShortnerFactory.redirectToOriginalUrlV2(shortenedUrl);
  }


}
