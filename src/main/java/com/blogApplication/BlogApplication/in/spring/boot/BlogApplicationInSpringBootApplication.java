package com.blogApplication.BlogApplication.in.spring.boot;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BlogApplicationInSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogApplicationInSpringBootApplication.class, args);
	}

	@Bean
	  public ModelMapper modelMapper(){
		return new ModelMapper();
	  }

}
