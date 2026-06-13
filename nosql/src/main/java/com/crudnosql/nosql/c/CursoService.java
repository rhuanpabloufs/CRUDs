package com.crudnosql.nosql.c;

import java.util.List;
import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Service;
import com.crudnosql.nosql.objects.Curso;
import com.crudnosql.nosql.r.CursoRepo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CursoService {

    private final CursoRepo cursoRepo;

    public Curso getID(String id) {
        return cursoRepo.findById(id).orElse(null);
    }

    public Curso getNome(String nome) {
        return cursoRepo.findByNome(nome);
    }

    public List<Curso> getAllCursos() {
        return cursoRepo.findAll();
    }

    public void addCurso(String nome, String grau, String turno, String campus, String nivel) {
        Curso c = getNome(nome);
        if((c == null || (c.getNome() == nome && c.getTurno() == turno && c.getCampus() == campus))){
            throw new RuntimeErrorException(null);
        }
        Curso c1 = new Curso(null, nome, grau, turno, campus, nivel);
        cursoRepo.save(c1);
    }

    public void updateCursoNome(String id, String novoNome) {
        Curso c = cursoRepo.findById(id).orElse(null);
        if (c != null) {
            c.setNome(novoNome);
            cursoRepo.save(c);
        }
    }

    public void updateCursoGrau(String id, String novoGrau) {
        Curso c = cursoRepo.findById(id).orElse(null);
        if (c != null) {
            c.setGrau(novoGrau);
            cursoRepo.save(c);
        }
    }

    public void updateCursoTurno(String id, String novoTurno) {
        Curso c = cursoRepo.findById(id).orElse(null);
        if (c != null) {
            c.setTurno(novoTurno);
            cursoRepo.save(c);
        }
    }

    public void updateCursoCampus(String id, String novoCampus) {
        Curso c = cursoRepo.findById(id).orElse(null);
        if (c != null) {
            c.setCampus(novoCampus);
            cursoRepo.save(c);
        }
    }

    public void updateCursoNivel(String id, String novoNivel) {
        Curso c = cursoRepo.findById(id).orElse(null);
        if (c != null) {
            c.setNivel(novoNivel);
            cursoRepo.save(c);
        }
    }

    public void deleteById(String id) {
        cursoRepo.deleteById(id);
    }

    public void deleteByNome(String nome) {
        cursoRepo.deleteByNome(nome);
    }
}