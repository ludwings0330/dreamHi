package com.elephant.dreamhi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@EnableAspectJAutoProxy
@SpringBootApplication
public class DreamHiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DreamHiApplication.class, args);
	}

}
