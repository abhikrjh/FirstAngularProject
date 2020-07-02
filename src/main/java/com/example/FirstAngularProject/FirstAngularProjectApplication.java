package com.example.FirstAngularProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = "{com.example.FirstAngularProject}")
public class FirstAngularProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstAngularProjectApplication.class, args);
	}

	
	  @Bean
	   public WebMvcConfigurer corsConfigurer() {
	      return new WebMvcConfigurer() {
	         @Override
	         public void addCorsMappings(CorsRegistry registry) {
	            registry.addMapping("/*").allowedOrigins("http://localhost:4200");
	         }
	      };
	   }
}
 