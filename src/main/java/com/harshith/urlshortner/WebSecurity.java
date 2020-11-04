package com.harshith.urlshortner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurity extends WebSecurityConfigurerAdapter {

  @Value("${application.basic.auth.user}")
  String basicAuthUser;

  @Value("${application.basic.auth.password}")
  String basicAuthPassword;


  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable().authorizeRequests().anyRequest().authenticated().and().httpBasic();
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

    // {noop} : NoOpPasswordEncoder
    auth.inMemoryAuthentication().withUser(basicAuthUser).password("{noop}" + basicAuthPassword)
        .roles("USER");
  }
}
