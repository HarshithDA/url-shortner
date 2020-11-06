package com.harshith.urlshortner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;
import com.harshith.urlshortner.config.UrlShortnerJpaConfig;
import com.harshith.urlshortner.entity.UrlShortenedEntityMapper;
import com.harshith.urlshortner.factory.UrlShortnerFactory;
import com.harshith.urlshortner.service.UrlShortenedRepository;
import com.harshith.urlshortner.service.UrlShortnerService;
import com.harshith.urlshortner.service.UrlShortnerServiceException;

@ContextConfiguration(classes = {UrlShortnerJpaConfig.class},
    loader = AnnotationConfigContextLoader.class)
@Transactional
@SpringBootTest
class UrlShortnerApplicationTests {

  @InjectMocks
  UrlShortnerFactory urlShortnerFactory;

  @Mock
  UrlShortnerService urlShortnerService;

  @Mock
  UrlShortenedRepository urlShortenedRepository;

  @Mock
  UrlShortenedEntityMapper urlShortenedEntityMapper;

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
  }



  @Test
  public void shortenUrlTestNew() throws UrlShortnerServiceException {
    String originalUrl =
        "https://news.google.com/topics/CAAqJggKIiBDQkFTRWdvSUwyMHZNRGRqTVhZU0FtVnVHZ0pKVGlnQVAB?hl=en-IN&gl=IN&ceid=IN%3Aen";
    String shortUrl = urlShortnerFactory.getShortenedUrl(originalUrl);
    // assertNotNull(shortUrl);
    verify(urlShortnerService, times(1)).getShortenedUrl(any(String.class));
  }


  @Test
  public void fetchOriginalUrlTest() throws UrlShortnerServiceException {
    String originalUrl = "https://news.google.com/topstories";
    urlShortnerFactory.getOriginalUrl("b");
    // assertEquals(originalUrlFromMock, originalUrl);
    verify(urlShortnerService, times(1)).getOriginalUrl(any(String.class));
  }


}
