package com.lira.security.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class LiraSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(LiraSecurityApplication.class, args);
		
	}
	
	
	
//	@Bean
//	public PasswordEncoder getPasswordEncoder() {
//		BCryptPasswordEncoder geraEncoder = new BCryptPasswordEncoder();
//		return geraEncoder;
//	}
	

}
