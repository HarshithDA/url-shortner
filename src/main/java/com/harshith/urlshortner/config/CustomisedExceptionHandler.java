package com.harshith.urlshortner.config;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.harshith.urlshortner.service.UrlShortnerServiceException;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class CustomisedExceptionHandler extends ResponseEntityExceptionHandler {


  public static final String STATUS_ERROR = "error";
  public static final String INVALID_PAYLOAD_ERROR =
      "Could not parse the input payload. Kindly make sure you send a valid payload.";
  public static final String INVALID_METHOD_REQUEST =
      "The requested API/URL do not exists. Kindly check the API URL or API method.";
  public static final String INVALID_PATH_TYPE_ERROR = "Invalid path variable type";
  public static final String INVALID_PATH = "Invalid path : ";


  @Override
  public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {
    log.warn(ex.getMessage(), ex);
    return new ResponseEntity<>(new ErrorDetailsResponse(STATUS_ERROR, INVALID_PAYLOAD_ERROR),
        status);
  }

  @Override
  public ResponseEntity<Object> handleHttpRequestMethodNotSupported(
      HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status,
      WebRequest request) {

    log.warn(ex.getMessage(), ex);

    return new ResponseEntity<>(new ErrorDetailsResponse(STATUS_ERROR, INVALID_METHOD_REQUEST),
        status);
  }

  @Override
  protected ResponseEntity<Object> handleMissingServletRequestPart(
      MissingServletRequestPartException ex, HttpHeaders headers, HttpStatus status,
      WebRequest request) {
    log.warn(ex.getMessage(), ex);
    return new ResponseEntity<>(new ErrorDetailsResponse(STATUS_ERROR, ex.getMessage()), status);


  }


  /**
   * This is the implementation to handle all bad types in the request
   */
  @Override
  public ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
      HttpStatus status, WebRequest request) {

    log.warn(ex.getMessage(), ex);
    String requestUri = ((ServletWebRequest) request).getRequest().getRequestURL().toString();

    try {
      requestUri = URLDecoder.decode(requestUri, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      // Ignore
    }

    return new ResponseEntity<>(
        new ErrorDetailsResponse(new Date(), INVALID_PATH_TYPE_ERROR, INVALID_PATH + requestUri),
        HttpStatus.BAD_REQUEST);
  }

  /**
   * Generic exception handling block for UrlShortnerServiceException
   * 
   * @param ex
   * @param request
   * @return
   */
  @ExceptionHandler(value = {UrlShortnerServiceException.class})
  protected ResponseEntity<ErrorDetailsResponse> urlShortnerServiceExceptionHandler(
      UrlShortnerServiceException ex, WebRequest request) {
    log.warn(ex.getMessage(), ex);
    if (ex.getErrorCode() > 0) {
      return getErrorDetailResponse(ex, HttpStatus.BAD_REQUEST, ex.getErrorCode());
    } else {
      return getErrorDetailResponse(ex, HttpStatus.BAD_REQUEST);
    }
  }

  @ExceptionHandler(value = {org.springframework.security.access.AccessDeniedException.class})
  public ResponseEntity<ErrorDetailsResponse> springSecurityExceptionHandler(Exception ex,
      WebRequest request) {
    log.warn(ex.getMessage(), ex);
    return getErrorDetailResponse(ex, HttpStatus.UNAUTHORIZED);
  }


  /**
   * Any unhandled exception will be caught here
   * 
   * @param e
   * @param request
   * @return
   */
  @ExceptionHandler(value = {Exception.class})
  public ResponseEntity<ErrorDetailsResponse> handleGenericException(Exception e,
      WebRequest request) {
    log.error(e.getMessage(), e);
    return getErrorDetailResponse("URL Shortner Service Failure!",
        HttpStatus.INTERNAL_SERVER_ERROR);
  }



  private ResponseEntity<ErrorDetailsResponse> getErrorDetailResponse(String message,
      HttpStatus httpStatus) {
    return new ResponseEntity<>(new ErrorDetailsResponse(new Date(), STATUS_ERROR, message),
        httpStatus);
  }


  /**
   * Creates the Object to be sent back as a failure response
   * 
   * @param exception
   * @param httpStatus
   * @return
   */
  private ResponseEntity<ErrorDetailsResponse> getErrorDetailResponse(final Exception exception,
      final HttpStatus httpStatus) {
    return new ResponseEntity<>(
        new ErrorDetailsResponse(new Date(), STATUS_ERROR, getRootCause(exception)), httpStatus);
  }



  /**
   * Creates the Object to be sent back as a failure response
   * 
   * @param exception
   * @param httpStatus
   * @return
   */
  private ResponseEntity<ErrorDetailsResponse> getErrorDetailResponse(final Exception exception,
      final HttpStatus httpStatus, int errorCode) {

    return new ResponseEntity<>(
        new ErrorDetailsResponse(new Date(), STATUS_ERROR, getRootCause(exception), errorCode),
        httpStatus);
  }

  private String getRootCause(Throwable e) {
    Throwable cause = null;
    Throwable result = e;

    while (null != (cause = result.getCause()) && (result != cause)) {
      result = cause;
    }
    if (result.getLocalizedMessage() == null) {
      return "URL Shortner Service Failure!";
    }
    return result.getLocalizedMessage();
  }


}
