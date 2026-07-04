package com.engdados.arrocha.arrochadb.Controllers;

import com.engdados.arrocha.arrochadb.Entities.Curso;
import com.engdados.arrocha.arrochadb.Services.CursoService;

import jakarta.validation.Valid;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cursos")
public class CursoController {
    
    @Autowired
    private CursoService cursoSe; // a ideia é ele pegar os metodos do Service de Curso
    
    @PostMapping
    public Curso inserir(@Valid @RequestBody Curso butarCurso){
        return cursoSe.insereCurso(butarCurso);
    }

    @PutMapping("/{idCurso}")
    public Curso atualizar(@PathVariable("idCurso") Long idCurso, @Valid @RequestBody Curso mudou){
        return cursoSe.updCurso(idCurso, mudou);
    }

    @GetMapping
    public List<Curso> buscaTodos(){
        return cursoSe.buscaTdCursos();
    }

    @DeleteMapping("/{idCurso}")
    public void remover(@PathVariable("idCurso") Long idCurso){
        cursoSe.rmCurso(idCurso);
    }


        
}
