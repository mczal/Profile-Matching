package com.spk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class ProfileMatchingApplication extends SpringBootServletInitializer {

  public static void main(String[] args) {
    SpringApplication.run(ProfileMatchingApplication.class, args);
  }

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(ProfileMatchingApplication.class);
  }
}
