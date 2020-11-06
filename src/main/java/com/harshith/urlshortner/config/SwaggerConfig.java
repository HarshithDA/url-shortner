package com.harshith.urlshortner.config;

import static com.google.common.collect.Lists.newArrayList;
import java.util.ArrayList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger basic info when the swagger is loaded. high level info on the application services and
 * other information like contact and license if any.
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

  private static final String VALUE_STRING = "string";
  private static final String HEADER = "header";

  @Bean
  public Docket notificationApi() {

    return new Docket(DocumentationType.SWAGGER_2).select()
        .apis(RequestHandlerSelectors.basePackage("com.harshith")).paths(PathSelectors.any())
        .build().apiInfo(apiInfo()).globalOperationParameters(this.getParameters());
  }


  private ApiInfo apiInfo() {
    return new ApiInfo("URL Shortner Service APIs", "URL Shortner Service APIs", "V1", "",
        new Contact("Harshith D A", "", "h.hegde7@gmail.com"), "", "", new ArrayList<>(0));
  }

  private ArrayList<Parameter> getParameters() {
    ArrayList<Parameter> parameters = newArrayList(new ParameterBuilder().name("accept")
        .description("accept-type").defaultValue("*/*").modelRef(new ModelRef(VALUE_STRING))
        .parameterType(HEADER).required(true).hidden(true).build());

    return parameters;
  }
}
