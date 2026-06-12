package com.engdados.arrocha.arrochadb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import com.engdados.arrocha.arrochadb.Services.*;
import com.engdados.arrocha.arrochadb.Entities.Grau;
import com.engdados.arrocha.arrochadb.Entities.Turno;
import com.engdados.arrocha.arrochadb.Entities.Nivel;
import com.engdados.arrocha.arrochadb.Entities.Estudante;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@SpringBootApplication
public class ArrochadbApplication implements CommandLineRunner {

	private final CursoService cursoService;
	private final VinculoService vinculoService;

	@Override
	public void run(String... args) throws Exception{
		System.out.println("HORA DE ARROCHAR, BB!");
		try{
			cursoService.botaCurso("Engenharia de Sexo", Grau.BACHARELADO, Turno.NOTURNO, "São Cristóvão", Nivel.Graduação);
			System.out.println("Curso arrochado com sucesso!");

			System.out.println("Cursos gulosos: ");
			cursoService.macroCaca().forEach(curso -> {
				System.out.println(curso.getIdCurso() + curso.getNome());

			});
		} catch (Exception e){
			System.err.println("Um dia frio, pica quer xibiu : " + e.getMessage());
		}
	}
	public static void main(String[] args) {
		SpringApplication.run(ArrochadbApplication.class, args);
	}

}
