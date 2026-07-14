package com.engdados.arrocha.arrochadb.Services;

import java.util.List;
import com.engdados.arrocha.arrochadb.Entities.Vinculo;
import com.engdados.arrocha.arrochadb.Repositories.CursoRepository;
import com.engdados.arrocha.arrochadb.Repositories.EstudanteRepository;
import com.engdados.arrocha.arrochadb.Repositories.VinculoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import com.engdados.arrocha.arrochadb.Exceptions.*;

@Service
public class VinculoService {

    @Autowired
    private VinculoRepository vinculoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private EstudanteRepository estudanteRepository;

    @Transactional
    public Vinculo inserir(Vinculo linha) {
        if (linha.getCurso() == null || linha.getEstudante() == null) {
            throw new NotFound("Falta id do estudante ou matrícula");
        }
        if (linha.getEstudante().getMat_estudante() == null || linha.getCurso().getIdCurso() == null) {
            throw new NotFound("Falta id do estudante ou matrícula");
        }
        if (!estudanteRepository.existsById(linha.getEstudante().getMat_estudante()) || !cursoRepository.existsById(linha.getCurso().getIdCurso())) {
            throw new DataIntegrityViolationException("Vinculo não possue curso ou estudante correspondente");
        }

        vinculoRepository.inserirVinculo(linha.getEstudante().getMat_estudante(), linha.getCurso().getIdCurso(), linha.getDataEntrada(), linha.getStatus(), linha.getDataSaida());
        
        Integer vinculoADD = vinculoRepository.ultimoId();
        
        return buscarPorId(vinculoADD);
    }

    public List<Vinculo> listarTodos() {
        return vinculoRepository.findAll();
    }

    public Vinculo buscarPorId(Integer id) {
        return vinculoRepository.findById(id)
                .orElseThrow(() -> new NotFound("Vínculo não encontrado"));
    }

    @Transactional
    public Vinculo atualizar(Integer id, Vinculo alterado) {
         Vinculo vinculoAtual = buscarPorId(id);

         Vinculo temp = new Vinculo();
         temp.setEstudante(vinculoAtual.getEstudante());
         temp.setCurso(vinculoAtual.getCurso());
         temp.setDataEntrada(vinculoAtual.getDataEntrada());
         temp.setDataSaida(vinculoAtual.getDataSaida());
         temp.setStatus(vinculoAtual.getStatus()); 

        if(alterado.getEstudante() != null) { // dirty check do hibernate fudendo tudo, o find by retona a referencia do original no banco. qualquer set nele faz um update oculto
            temp.setEstudante(alterado.getEstudante());
        }
        if(alterado.getCurso() != null) {
            if(cursoRepository.existsById(alterado.getCursoId()))
                temp.setCurso(alterado.getCurso());
        }
        if(alterado.getDataEntrada() != null) {
            temp.setDataEntrada(alterado.getDataEntrada());
        }
        if(alterado.getDataSaida() != null) {
            temp.setDataSaida(alterado.getDataSaida());
        }
        if(alterado.getStatus() != null) {
            temp.setStatus(alterado.getStatus());
        }

        vinculoRepository.atualizarVinculo(id, temp.getEstudante().getMat_estudante(), temp.getCurso().getIdCurso(), temp.getDataEntrada(), temp.getStatus(), temp.getDataSaida());
        return buscarPorId(id);
    }

    @Transactional
    public void deletar(Integer id) {
        if (!vinculoRepository.existsById(id)) {
            throw new NotFound("Vínculo não encontrado");
        }
        vinculoRepository.deleteById(id);
    }
}
