package com.justindodson.familybucks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class FamilyBucksApplication {

	public static void main(String[] args) {
		SpringApplication.run(FamilyBucksApplication.class, args);
	}

}
