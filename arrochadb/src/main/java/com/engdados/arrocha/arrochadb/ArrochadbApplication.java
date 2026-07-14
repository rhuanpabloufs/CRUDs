package com.engdados.arrocha.arrochadb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import com.engdados.arrocha.arrochadb.Repositories.CursoRepository;
import com.engdados.arrocha.arrochadb.Repositories.VinculoRepository;

@SpringBootApplication
public class ArrochadbApplication implements CommandLineRunner {

	@Autowired
	private CursoRepository reset;
	@Autowired
	private VinculoRepository resetar;

	@Override
	public void run(String... args) throws Exception{
		reset.sincronizaSerialCurso();
		resetar.sincronizaSerialVinculo();
	}
	public static void main(String[] args) {
		SpringApplication.run(ArrochadbApplication.class, args);
	}

}
