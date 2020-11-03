package com.harshith.urlshortner;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UrlShortenRequestView {

  @NotBlank(message = "Actual URL is mandatory")
  @ApiModelProperty(value = "Actual URL to be Shortened", required = true)
  @Size(min = 1, max = 1000, message = "Actual URL must have at least 1 charaters and at max 1000")
  String actualUrl;


}
