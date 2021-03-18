package com.openclassroooms.paymybuddy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PaymybuddyApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymybuddyApplication.class, args);


	//ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	//String path = classLoader.getResource("templates/troispoints.png").getPath();
	//System.out.println(path);
	}
}
