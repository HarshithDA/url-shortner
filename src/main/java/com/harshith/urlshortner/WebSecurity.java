package com.harshith.urlshortner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class WebSecurity extends WebSecurityConfigurerAdapter {

  @Value("${application.basic.auth.user}")
  String basicAuthUser;

  @Value("${application.basic.auth.password}")
  String basicAuthPassword;

  @Autowired
  ObjectMapper mapper;


  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().csrf()
        .disable().authorizeRequests().anyRequest().authenticated().and().httpBasic();
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication().withUser(basicAuthUser)
        .password(new BCryptPasswordEncoder().encode(basicAuthPassword)).roles("USER");
  }

  @Bean
  BCryptPasswordEncoder encoder() {
    // strong hashing function to encode the password
    return new BCryptPasswordEncoder();
  }

}
