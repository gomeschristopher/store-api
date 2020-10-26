package com.gomeschristopher.store.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gomeschristopher.store.domain.PaymentBill;
import com.gomeschristopher.store.domain.PaymentCard;

@Configuration
public class JacksonConfig {
	@Bean
	public Jackson2ObjectMapperBuilder objectMapperBuilder() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
			public void configure(ObjectMapper objectMapper) {
				objectMapper.registerSubtypes(PaymentBill.class);
				objectMapper.registerSubtypes(PaymentCard.class);
				super.configure(objectMapper);
			}
		};
		return builder;
	}
}