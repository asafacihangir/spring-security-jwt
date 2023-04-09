package org.phoenix.demo.config;

import org.phoenix.demo.base.SimpleBaseRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = {
        "org.phoenix.demo.core.repository",
        "org.phoenix.demo.security.repository",
    },
    repositoryBaseClass = SimpleBaseRepository.class)
@EnableJpaAuditing
public class PersistenceConfiguration {

}