package com.harshith.urlshortner.config;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.xml.bind.annotation.XmlRootElement;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@XmlRootElement(name = "errors")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
public class ErrorDetailsResponse {
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
  private Date time;
  private String status;
  private Integer errorCode;
  private Set<String> messages;

  public ErrorDetailsResponse(String status, String message) {
    super();
    this.time = new Date();
    this.status = status;
    this.messages = new HashSet<>(1);
    this.messages.add(message);
  }

  public ErrorDetailsResponse(Date time, String status, String message) {
    super();
    this.time = time;
    this.status = status;
    this.messages = new HashSet<>(1);
    this.messages.add(message);
  }

  public ErrorDetailsResponse(Date time, String status, String message, int errorCode) {
    this(time, status, message);
    this.errorCode = errorCode;
  }


}
