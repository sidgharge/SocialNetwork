package com.network.social.user.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserBeansConfig {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
