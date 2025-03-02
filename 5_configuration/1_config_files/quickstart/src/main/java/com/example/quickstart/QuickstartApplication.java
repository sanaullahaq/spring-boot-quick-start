package com.example.quickstart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuickstartApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuickstartApplication.class, args);
	}

}

/* application.properties handles the server properties for example in which port server will run.
* This can be both in .properties/.yml file.
* The properties file inside test directory will be used for test
* */
