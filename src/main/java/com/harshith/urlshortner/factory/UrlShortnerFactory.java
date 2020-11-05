package com.harshith.urlshortner.factory;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import com.harshith.urlshortner.enums.SortByColumnEnum;
import com.harshith.urlshortner.enums.SortOrderEnum;
import com.harshith.urlshortner.service.UrlShortnerService;
import com.harshith.urlshortner.service.UrlShortnerServiceException;
import com.harshith.urlshortner.view.UrlShortenResponseView;

@Service
public class UrlShortnerFactory {

  private static final Logger LOGGER = LoggerFactory.getLogger(UrlShortnerFactory.class);

  @Autowired
  UrlShortnerService urlShortnerService;

  public String getShortenedUrl(String originalUrl) throws UrlShortnerServiceException {
    try {
      if (originalUrl == null || originalUrl.isBlank()) {
        throw new UrlShortnerServiceException(
            "Original URL cannot be null or empty, please provide a valid URL");
      }

      return urlShortnerService.getShortenedUrl(originalUrl);
    } catch (UrlShortnerServiceException e) {
      throw e;
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      throw new UrlShortnerServiceException(
          "Failed to Shorten URL! Unexpected Application Error Occured!", 101);
    }
  }


  public String getShortenedUrlByUniqueDate(String originalUrl) throws UrlShortnerServiceException {
    try {
      if (originalUrl == null || originalUrl.isBlank()) {
        throw new UrlShortnerServiceException(
            "Original URL cannot be null or empty, please provide a valid URL");
      }

      return urlShortnerService.getShortenedUrlByUniqueDate(originalUrl);
    } catch (UrlShortnerServiceException e) {
      throw e;
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      throw new UrlShortnerServiceException(
          "Failed to Shorten URL by Date! Unexpected Application Error Occured!", 102);
    }
  }


  public UrlShortenResponseView getOriginalUrl(String shortenedUrl)
      throws UrlShortnerServiceException {
    try {
      if (shortenedUrl == null || shortenedUrl.isBlank()) {
        throw new UrlShortnerServiceException(
            "Shortened URL cannot be null or empty, please provide a valid Short URL");
      }

      return urlShortnerService.getOriginalUrl(shortenedUrl);
    } catch (UrlShortnerServiceException e) {
      throw e;
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      throw new UrlShortnerServiceException(
          "Failed to Fetch Original! Unexpected Application Error Occured!", 103);
    }
  }


  public List<UrlShortenResponseView> getOriginalUrlList(int pageNumber, int pageSize,
      SortByColumnEnum sortBy, SortOrderEnum sortByOrder) throws UrlShortnerServiceException {
    try {
      return urlShortnerService.getOriginalUrlList(pageNumber, pageSize, sortBy, sortByOrder);
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      throw new UrlShortnerServiceException(
          "Failed to Fetch Original URLs List! Unexpected Application Error Occured!", 104);
    }
  }


  public Response redirectToOriginalUrl(String shortenedUrl)
      throws UrlShortnerServiceException, URISyntaxException {
    try {
      if (shortenedUrl == null || shortenedUrl.isBlank()) {
        throw new UrlShortnerServiceException(
            "Shortened URL cannot be null or empty, please provide a valid Short URL");
      }

      String originalUrl = urlShortnerService.getOriginalUrl(shortenedUrl).getOriginalUrl();

      URI uri = new URI(originalUrl);

      return Response.status(Status.MOVED_PERMANENTLY).location(uri).build();
      // return Response.temporaryRedirect(uri).build();
    } catch (UrlShortnerServiceException e) {
      throw e;
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      throw new UrlShortnerServiceException(
          "Failed to Redirect to Original URL! Unexpected Application Error Occured!", 105);
    }
  }


  public ModelAndView redirectToOriginalUrlNew(String shortenedUrl,
      HttpServletResponse httpServletResponse)
      throws UrlShortnerServiceException, URISyntaxException {
    try {
      if (shortenedUrl == null || shortenedUrl.isBlank()) {
        throw new UrlShortnerServiceException(
            "Shortened URL cannot be null or empty, please provide a valid Short URL");
      }

      String originalUrl = urlShortnerService.getOriginalUrl(shortenedUrl).getOriginalUrl();

      // httpServletResponse.setHeader("Location", originalUrl);
      // httpServletResponse.setStatus(302);

      return new ModelAndView("redirect:" + originalUrl);
    } catch (UrlShortnerServiceException e) {
      throw e;
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      throw new UrlShortnerServiceException(
          "Failed to Redirect to Original URL! Unexpected Application Error Occured!", 106);
    }
  }

}
