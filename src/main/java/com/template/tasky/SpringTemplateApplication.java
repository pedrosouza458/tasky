package com.template.tasky;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringTemplateApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();

		SpringApplication.run(SpringTemplateApplication.class, args);
	}

}
