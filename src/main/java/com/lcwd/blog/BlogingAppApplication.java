package com.lcwd.blog;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;


import com.lcwd.blog.entity.User;

@SpringBootApplication
public class BlogingAppApplication {


	public static void main(String[] args) {
		SpringApplication.run(BlogingAppApplication.class, args);

		System.out.println("this is master branch code");

	}


	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}




