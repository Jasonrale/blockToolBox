package com.web3.blockToolBox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class blockToolBoxApplication {

	public static void main(String[] args) {
		SpringApplication.run(blockToolBoxApplication.class, args);
	}

}
