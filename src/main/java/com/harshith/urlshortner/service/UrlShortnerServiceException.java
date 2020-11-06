package com.harshith.urlshortner.service;

import lombok.Getter;

/**
 * Application level Exception to handle custom errors as well as Wrapper exception
 *
 */
@Getter
public class UrlShortnerServiceException extends Exception {

  private final int errorCode;

  public UrlShortnerServiceException() {
    super();
    errorCode = 0;
  }

  public UrlShortnerServiceException(String message) {
    super(message);
    errorCode = 0;
  }

  public UrlShortnerServiceException(String message, int errorCode) {
    super(message);
    this.errorCode = errorCode;
  }

}
