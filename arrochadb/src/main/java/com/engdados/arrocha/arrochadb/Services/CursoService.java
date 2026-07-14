package com.engdados.arrocha.arrochadb.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.engdados.arrocha.arrochadb.Entities.Curso;
import com.engdados.arrocha.arrochadb.Repositories.CursoRepository;
import com.engdados.arrocha.arrochadb.Exceptions.*;

import jakarta.transaction.Transactional;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Transactional
    public Curso inserir(Curso curso) {
        if (cursoRepository.existeCombinacao(
                curso.getNome(), curso.getTurno(), curso.getCampus(), curso.getNivel())) {
            throw new DadosDuplicado("Curso já cadastrado com essa combinação");
        }
        cursoRepository.inserirCurso(curso.getNome(), curso.getGrau(), curso.getTurno(), curso.getCampus(), curso.getNivel());
        cursoRepository.sincronizaSerialCurso();

        Integer cursoADD = cursoRepository.ultimoId();

        return buscarPorId(cursoADD);
    }

    public List<Curso> listarTodos() { return cursoRepository.findAll(); }

    public Curso buscarPorId(Integer id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new NotFound("Curso inexistente"));
    }

    @Transactional
    public Curso atualizar(Integer id, Curso atualizar) { 
        Curso cursoAtual = buscarPorId(id);

        String nomeFinal   = atualizar.getNome()   != null ? atualizar.getNome()   : cursoAtual.getNome();
        String grauFinal   = atualizar.getGrau()   != null ? atualizar.getGrau()   : cursoAtual.getGrau();
        String turnoFinal  = atualizar.getTurno()  != null ? atualizar.getTurno()  : cursoAtual.getTurno();
        String campusFinal = atualizar.getCampus() != null ? atualizar.getCampus() : cursoAtual.getCampus();
        String nivelFinal  = atualizar.getNivel()  != null ? atualizar.getNivel()  : cursoAtual.getNivel();

        Integer existenteId = cursoRepository.idDaCombinacao(nomeFinal, turnoFinal, campusFinal, nivelFinal);
        if (existenteId != null && !existenteId.equals(id)) {
            throw new DadosDuplicado("Já existe um curso com essa combinação");
        }

        cursoRepository.atualizarCurso(id, nomeFinal, grauFinal, turnoFinal, campusFinal, nivelFinal);
        return buscarPorId(id);
    }

    @Transactional
    public void deletar(Integer id) {
        if (!cursoRepository.existsById(id)) {
            throw new NotFound("Curso não encontrado");
        }
        cursoRepository.deleteById(id);
    }
}
