package com.harshith.urlshortner.resource;

import java.util.List;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.harshith.urlshortner.enums.SortByColumnEnum;
import com.harshith.urlshortner.enums.SortOrderEnum;
import com.harshith.urlshortner.factory.UrlShortnerFactory;
import com.harshith.urlshortner.service.UrlShortnerServiceException;
import com.harshith.urlshortner.view.UrlShortenResponseView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


/**
 * This resource fetches the URL Shortner service Statistics/Details.
 */

@Api(value = "URL Shortner service Statistics/Details",
    tags = "URL Shortner Statistics/Details APIs")
@RestController
@RequestMapping("/v1/stats")
public class UrlShortenerStatsResource {

  @Autowired
  private UrlShortnerFactory urlShortnerFactory;


  @ApiOperation(value = "Fetch the Original URL for the Short URL",
      response = UrlShortenResponseView.class)
  @GetMapping(value = "/original")
  public UrlShortenResponseView getOriginalUrl(
      @ApiParam("Shortened Url") @RequestParam(value = "shortened_url",
          required = true) String shortenedUrl)
      throws UrlShortnerServiceException {
    return urlShortnerFactory.getOriginalUrl(shortenedUrl);
  }


  @ApiOperation(value = "Fetch the Original URL for the Short URL",
      response = UrlShortenResponseView.class)
  @GetMapping(value = "/original-v2")
  public UrlShortenResponseView getOriginalUrlV2(
      @ApiParam("Shortened Url") @RequestParam(value = "shortened_url",
          required = true) String shortenedUrl)
      throws UrlShortnerServiceException {
    return urlShortnerFactory.getOriginalUrlV2(shortenedUrl);
  }


  @ApiOperation(value = "Fetch the Original URLs list details",
      response = UrlShortenResponseView.class, responseContainer = "List")
  @GetMapping(value = "/original-list")
  public List<UrlShortenResponseView> getOriginalUrlList(
      @Min(value = 1, message = "page_number must be minimum 1") @RequestParam(
          value = "page_number", defaultValue = "1") int pageNumber,
      @Max(value = 100, message = "page_size can be at max 100") @RequestParam(value = "page_size",
          defaultValue = "20") int pageSize,
      @RequestParam(value = "sort_by", defaultValue = "CREATED_DATE") SortByColumnEnum sortBy,
      @RequestParam(value = "sort_by_order", defaultValue = "DESC") SortOrderEnum sortByOrder)
      throws UrlShortnerServiceException {
    return urlShortnerFactory.getOriginalUrlList(pageNumber, pageSize, sortBy, sortByOrder);
  }


}
