package com.spk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by Gl552 on 4/24/2017.
 */
@Configuration
@EnableJpaRepositories("com.spk.dao")
@EnableJpaAuditing
public class CommonBeanConfig {

  @Bean
  public AuditingEntityListener createAuditingListener() {
    return new AuditingEntityListener();
  }

}
