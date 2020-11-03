package com.harshith.urlshortner;

import java.net.URISyntaxException;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
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
  public String getShortenedUrl(@ApiParam("Original Url") @RequestParam(value = "original_url",
      required = true) String originalUrl) throws UrlShortnerServiceException {
    return urlShortnerFactory.getShortenedUrl(originalUrl);
  }


  @ApiOperation(value = "Redirect to actual URL", response = String.class)
  @GetMapping(value = "/original")
  public String getOriginalUrl(@ApiParam("Shortened Url") @RequestParam(value = "shortened_url",
      required = true) String shortenedUrl) throws UrlShortnerServiceException {
    return urlShortnerFactory.getOriginalUrl(shortenedUrl);
  }


  @ApiOperation(value = "Redirect to actual URL", response = String.class)
  @GetMapping(value = "/redirect")
  public Response redirectToOriginalUrl(
      @ApiParam("Shortened Url") @RequestParam(value = "shortened_url",
          required = true) String shortenedUrl)
      throws UrlShortnerServiceException, URISyntaxException {
    return urlShortnerFactory.redirectToOriginalUrl(shortenedUrl);
  }

  @ApiOperation(value = "Redirect to actual URL", response = String.class)
  @GetMapping(value = "/redirectnew")
  public ModelAndView redirectToOriginalUrlNew(
      @ApiParam("Shortened Url") @RequestParam(value = "shortened_url",
          required = true) String shortenedUrl,
      HttpServletResponse httpServletResponse)
      throws UrlShortnerServiceException, URISyntaxException {
    return urlShortnerFactory.redirectToOriginalUrlNew(shortenedUrl, httpServletResponse);
  }

}
