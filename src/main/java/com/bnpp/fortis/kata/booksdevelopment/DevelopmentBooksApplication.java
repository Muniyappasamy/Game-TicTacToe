package com.bnpp.fortis.kata.booksdevelopment;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DevelopmentBooksApplication {
	public static void main(String[] args) {
		SpringApplication.run(DevelopmentBooksApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
