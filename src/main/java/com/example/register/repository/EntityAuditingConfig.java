package com.example.register.repository;

import com.example.register.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
@EnableJpaAuditing
@RequiredArgsConstructor
public class EntityAuditingConfig {
	
	private final UserRepository userRepository;
	
	@Bean
	public AuditorAware<User> auditorAware() {

		return () -> {String signedInUser = SecurityContextHolder.getContext().
				getAuthentication().getName();
			return userRepository.findByUsername(signedInUser);};

	}
}
