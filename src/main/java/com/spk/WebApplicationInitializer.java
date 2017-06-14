package com.spk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext;
import org.springframework.cloud.Cloud;
import org.springframework.cloud.CloudException;
import org.springframework.cloud.CloudFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * Created by mczal on 6/14/17.
 */
public class WebApplicationInitializer implements
    ApplicationContextInitializer<AnnotationConfigEmbeddedWebApplicationContext> {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Override
  public void initialize(
      AnnotationConfigEmbeddedWebApplicationContext applicationContext) {
    Cloud cloud = getCloud();
    ConfigurableEnvironment appEnvironment = applicationContext.getEnvironment();
    if (cloud != null) {
      appEnvironment.setActiveProfiles("cloud");
      logger.info("Cloud profile activated");
    } else {
      appEnvironment.setActiveProfiles("qa");
      logger.info("Qa profile activated");
    }
  }

  private Cloud getCloud() {
    try {
      CloudFactory cloudFactory = new CloudFactory();
      return cloudFactory.getCloud();
    } catch (CloudException ce) {
      return null;
    }
  }
}
