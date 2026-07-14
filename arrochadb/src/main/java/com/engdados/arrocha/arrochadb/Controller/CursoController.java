package com.engdados.arrocha.arrochadb.Controller;

import com.engdados.arrocha.arrochadb.Entities.Curso;
import com.engdados.arrocha.arrochadb.Services.CursoService;

import jakarta.validation.Valid;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    private CursoService cursoService; // a ideia é ele pegar os metodos do Service de Curso
    
    @PostMapping
    public Curso inserir(@Valid @RequestBody Curso curso){
        return cursoService.inserir(curso);
    }

    @PutMapping("/{idCurso}")
    public Curso atualizar(@PathVariable("idCurso") Integer idCurso, @RequestBody Curso mudou){
        return cursoService.atualizar(idCurso, mudou);
    }

    @GetMapping
    public List<Curso> listarTodos(){
        return cursoService.listarTodos();
    }

     @GetMapping("/{idCurso}")
    public Curso buscarPorId(@PathVariable("idCurso") Integer idCurso){
        return cursoService.buscarPorId(idCurso);
    }

    @DeleteMapping("/{idCurso}")
    public ResponseEntity<Curso> deletar(@PathVariable("idCurso") Integer idCurso){ // não informava nada quando erad deletado, agora retorna o objeto
        Curso deletado = cursoService.buscarPorId(idCurso);
        cursoService.deletar(idCurso);
        return ResponseEntity.status(200).body(deletado);
    }
  
}
