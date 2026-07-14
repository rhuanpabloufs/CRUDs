package com.engdados.arrocha.arrochadb.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    private EstudanteService estudanteService;

    @PostMapping
    public Estudante inserir(@Valid @RequestBody Estudante estudante){
        return estudanteService.inserir(estudante);
    }

    @PutMapping("/{matricula}")
    public Estudante update(@PathVariable String matricula, @RequestBody Estudante alterado){
        return estudanteService.atualizar(matricula, alterado);
    }

    @GetMapping
    public List<Estudante> listAll(){
        return estudanteService.listarTodos();
    }

    @GetMapping("/{matricula}")
    public Estudante buscarEstudante(@PathVariable String matricula){
        return estudanteService.buscarEstudante(matricula);
    }

    @DeleteMapping("/{matricula}")
    public ResponseEntity<Estudante> deletar(@PathVariable String matricula){
        Estudante deletado = estudanteService.buscarEstudante(matricula);
        estudanteService.deletar(matricula);
        return ResponseEntity.status(200).body(deletado);
    }

}