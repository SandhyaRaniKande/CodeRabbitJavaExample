package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	/**
	 * Application entry point; boots the Spring Boot application.
	 *
	 * Starts the application by calling SpringApplication.run with this class and the provided
	 * command-line arguments. Any runtime exceptions thrown by the framework will propagate.
	 *
	 * @param args command-line arguments passed to the application
	 */
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
