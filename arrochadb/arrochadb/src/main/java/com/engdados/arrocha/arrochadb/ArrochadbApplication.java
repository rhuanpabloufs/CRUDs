package com.engdados.arrocha.arrochadb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@SpringBootApplication
public class ArrochadbApplication implements CommandLineRunner {


	@Override
	public void run(String... args) throws Exception{
		System.out.println("Hibernate iniciado");
	}
	public static void main(String[] args) {
		SpringApplication.run(ArrochadbApplication.class, args);
	}

}
