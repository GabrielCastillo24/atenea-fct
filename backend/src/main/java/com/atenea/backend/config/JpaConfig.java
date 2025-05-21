package com.atenea.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// Habilita el timestamp automatico en clases anotadas con JPA
@Configuration
@EnableJpaAuditing
public class JpaConfig {
}
