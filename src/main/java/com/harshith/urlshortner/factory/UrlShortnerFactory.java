package com.harshith.urlshortner.factory;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
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

  @Autowired
  UrlShortnerService urlShortnerService;

  public String getShortenedUrl(String originalUrl) throws UrlShortnerServiceException {

    if (originalUrl == null || originalUrl.isBlank()) {
      throw new UrlShortnerServiceException(
          "Original URL cannot be null or empty, please provide a valid URL");
    }

    return urlShortnerService.getShortenedUrl(originalUrl);
  }


  public String getShortenedUrlByUniqueDate(String originalUrl) throws UrlShortnerServiceException {

    if (originalUrl == null || originalUrl.isBlank()) {
      throw new UrlShortnerServiceException(
          "Original URL cannot be null or empty, please provide a valid URL");
    }

    return urlShortnerService.getShortenedUrlByUniqueDate(originalUrl);
  }


  public UrlShortenResponseView getOriginalUrl(String shortenedUrl)
      throws UrlShortnerServiceException {

    if (shortenedUrl == null || shortenedUrl.isBlank()) {
      throw new UrlShortnerServiceException(
          "Shortened URL cannot be null or empty, please provide a valid Short URL");
    }

    return urlShortnerService.getOriginalUrl(shortenedUrl);
  }


  public List<UrlShortenResponseView> getOriginalUrlList(int pageNumber, int pageSize,
      SortByColumnEnum sortBy, SortOrderEnum sortByOrder) {
    return urlShortnerService.getOriginalUrlList(pageNumber, pageSize, sortBy, sortByOrder);
  }


  public Response redirectToOriginalUrl(String shortenedUrl)
      throws UrlShortnerServiceException, URISyntaxException {

    if (shortenedUrl == null || shortenedUrl.isBlank()) {
      throw new UrlShortnerServiceException(
          "Shortened URL cannot be null or empty, please provide a valid Short URL");
    }

    String originalUrl = urlShortnerService.getOriginalUrl(shortenedUrl).getOriginalUrl();

    URI uri = new URI(originalUrl);

    return Response.status(Status.MOVED_PERMANENTLY).location(uri).build();
    // return Response.temporaryRedirect(uri).build();

  }


  public ModelAndView redirectToOriginalUrlNew(String shortenedUrl,
      HttpServletResponse httpServletResponse)
      throws UrlShortnerServiceException, URISyntaxException {

    if (shortenedUrl == null || shortenedUrl.isBlank()) {
      throw new UrlShortnerServiceException(
          "Shortened URL cannot be null or empty, please provide a valid Short URL");
    }

    String originalUrl = urlShortnerService.getOriginalUrl(shortenedUrl).getOriginalUrl();

    // httpServletResponse.setHeader("Location", originalUrl);
    // httpServletResponse.setStatus(302);

    return new ModelAndView("redirect:" + originalUrl);


  }

}
