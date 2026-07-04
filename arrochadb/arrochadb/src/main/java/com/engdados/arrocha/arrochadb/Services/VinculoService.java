package com.engdados.arrocha.arrochadb.Services;

import java.util.List;
import com.engdados.arrocha.arrochadb.Entities.Vinculo;
import com.engdados.arrocha.arrochadb.Repositories.VinculoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;


@Service
public class VinculoService {

    @Autowired
    private VinculoRepository vinculoRepo;

    @Transactional
    public Vinculo insereVinculo(Vinculo linha) {
        return vinculoRepo.save(linha);
    }

    public List<Vinculo> buscaTdVinculos() { 
        return vinculoRepo.findAll(); 
    }

    public Vinculo buscaVinculo(Long id) {
        return vinculoRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Vínculo não encontrado"));
    }

    @Transactional
    public Vinculo updVinculo(Long id, Vinculo alterado) {
        Vinculo vinculoAtual = buscaVinculo(id);

        if(alterado.getT_estudante() != null) {
            vinculoAtual.setT_estudante(alterado.getT_estudante());
        }
        if(alterado.getT_curso() != null) {
            vinculoAtual.setT_curso(alterado.getT_curso());
        }
        if(alterado.getDataEntrada() != null) {
            vinculoAtual.setDataEntrada(alterado.getDataEntrada());
        }
        if(alterado.getDataSaida() != null) {
            vinculoAtual.setDataSaida(alterado.getDataSaida());
        }
        if(alterado.getStatus() != null) {
            vinculoAtual.setStatus(alterado.getStatus());
        }

        return vinculoRepo.save(vinculoAtual);
    }

    @Transactional
    public void rmVinculo(Long id) {
        if(!vinculoRepo.existsById(id)) {
            throw new RuntimeException("Vínculo não encontrado");
        }
        vinculoRepo.deleteById(id);
    }
}