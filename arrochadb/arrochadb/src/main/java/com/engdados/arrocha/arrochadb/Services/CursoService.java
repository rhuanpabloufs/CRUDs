package com.engdados.arrocha.arrochadb.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.engdados.arrocha.arrochadb.Entities.Curso;
import com.engdados.arrocha.arrochadb.Repositories.CursoRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CursoService {
    
    @Autowired
    private CursoRepository cursoRepo;

    @Transactional
    public Curso insereCurso(Curso curso) {
        if(cursoRepo.existsByNomeAndTurnoAndCampusAndNivel(
                curso.getNome(), curso.getTurno(), curso.getCampus(), curso.getNivel())){
            throw new RuntimeException("Curso já cadastrado com essa combinação");
        }
        return cursoRepo.save(curso);
    }

    public List<Curso> buscaTdCursos(){return cursoRepo.findAll();} 

    public Curso buscaCurso(Long id){
        return cursoRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Id inexistente"));
    }
    
    @Transactional
    public Curso updCurso(Long id, Curso upCurso) {
        // 1. Busca pelo ID
        Curso cursoAtual = cursoRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Curso não encontrado"));
        
        // 2. Verifica se a nova combinação já existe
        if(cursoRepo.existsByNomeAndTurnoAndCampusAndNivel(
                upCurso.getNome(), upCurso.getTurno(), 
                upCurso.getCampus(), upCurso.getNivel())){
            throw new RuntimeException("Já existe um curso com essa combinação");
        }

   
    cursoAtual.setNome(upCurso.getNome());
    cursoAtual.setGrau(upCurso.getGrau());
    cursoAtual.setTurno(upCurso.getTurno());
    cursoAtual.setCampus(upCurso.getCampus());
    cursoAtual.setNivel(upCurso.getNivel());

    return cursoRepo.save(cursoAtual);
}

@Transactional
public void rmCurso(Long id) {
    if(!cursoRepo.existsById(id)){
        throw new RuntimeException("Curso não encontrado");
    }
    cursoRepo.deleteById(id);
}

}   
