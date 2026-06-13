package com.crudnosql.nosql;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.crudnosql.nosql.c.CursoService;
import com.crudnosql.nosql.c.EstudanteController;
import com.crudnosql.nosql.c.UsuarioController;
import com.crudnosql.nosql.c.VinculoService;
import com.crudnosql.nosql.objects.Vinculo;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@SpringBootApplication
public class NosqlApplication implements CommandLineRunner {
	private final UsuarioController u;
	private final VinculoService v;
	private final EstudanteController e;
	private final CursoService c;
	public static void main(String[] args) {
		SpringApplication.run(NosqlApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}
}
