package com.tiba.tiba;

import com.tiba.tiba.Services.JwtUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
@EnableScheduling  // Enable scheduling here
//@ComponentScan(basePackages = {
//		"com.tiba.tiba",
//		"com.tiba.tiba.Services",
//		"com.tiba.tiba.Controllers",
//		"com.tiba.tiba.Repositories"
//})
//@EntityScan("com.tiba.tiba.Entities")
//@EnableJpaRepositories("com.tiba.tiba.Repositories")
public class TibaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TibaApplication.class, args);
	}


	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public ExecutorService executorService() {
		return Executors.newFixedThreadPool(10);
	}

	@Bean
	public JwtUtil jwtUtil() {
		return new JwtUtil();
	}
}
