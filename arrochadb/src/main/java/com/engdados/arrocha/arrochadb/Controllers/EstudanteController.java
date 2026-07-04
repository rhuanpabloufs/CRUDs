package com.engdados.arrocha.arrochadb.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.*;

import com.engdados.arrocha.arrochadb.Entities.Estudante;
import com.engdados.arrocha.arrochadb.Services.EstudanteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/estudante")
public class EstudanteController {

    @Autowired
    private EstudanteService estudanteSe;

    @PostMapping
    public Estudante inserir(@Valid @RequestBody Estudante estudante){
        return estudanteSe.insereEstudante(estudante);
    }

    @PutMapping("/{matricula}")
    public Estudante atualizar(@PathVariable String matricula, @Valid @RequestBody Estudante alterado){
        return estudanteSe.updEstudante(matricula, alterado);
    }

    @GetMapping
    public List<Estudante> buscaTodos(){
        return estudanteSe.buscaTdEstudantes();
    }

    @GetMapping("/{matricula}")
    public Estudante busca(@PathVariable String matricula){
        return estudanteSe.buscaEstudante(matricula);
    }

    @DeleteMapping("/{matricula}")
    public void remover(@PathVariable String matricula){
        estudanteSe.rmEstudante(matricula);
    }

}