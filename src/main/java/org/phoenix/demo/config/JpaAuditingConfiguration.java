package org.phoenix.demo.config;


import java.util.Optional;
import org.phoenix.demo.security.utils.SecurityUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaAuditingConfiguration {

  @Bean
  public AuditorAware<String> auditorProvider() {
    return () -> Optional.of(SecurityUtils.getCurrentUsername().orElse("SYSTEM"));
  }

}
