package com.engdados.arrocha.arrochadb.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.engdados.arrocha.arrochadb.Entities.Estudante;
import com.engdados.arrocha.arrochadb.Exceptions.*;
import com.engdados.arrocha.arrochadb.Repositories.EstudanteRepository;
import com.engdados.arrocha.arrochadb.Repositories.UsuarioRepository;
import java.util.*;

import jakarta.transaction.Transactional;

@Service
public class EstudanteService {
    
    @Autowired
    private EstudanteRepository estudanteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public Estudante inserir(Estudante linha){ // Inserção de uma linha
        if (estudanteRepository.existsById(linha.getMat_estudante())){
            throw new DadosDuplicado("Estudante já cadastrado");
        }
        if (linha.getUsuario() != null && !usuarioRepository.existsById(linha.getUsuario().getCpf())){
            throw new NotFound("Estudante não encontrou usuário correspondente");
        }
        return estudanteRepository.save(linha);
    }

    public List<Estudante> listarTodos(){ // Leitura Geral
        return estudanteRepository.findAll();
    }

    public Estudante buscarEstudante(String mat){
        return estudanteRepository.findById(mat).orElseThrow(() -> new NotFound("Estudante não encontrado"));
    }


    @Transactional
    public Estudante atualizar(String matricula, Estudante alterado){ // ataulizar uma linha
        Estudante existente = estudanteRepository.findById(matricula)
            .orElseThrow(() -> new NotFound("Usuário com matricula não encontrado."));
        
        if(alterado.getMC() != null){
            existente.setMC(alterado.getMC());
        } 
        if(alterado.getAno_ingresso() != 0){ 
            existente.setAno_ingresso(alterado.getAno_ingresso());
        }
        if(alterado.getUsuario() != null){
            if(alterado.getUsuario().getCpf() == null){
                existente.setUsuario(null); 
            }
            else if(!estudanteRepository.existsByUsuarioCpf(alterado.getUsuario().getCpf()) && usuarioRepository.existsById(alterado.getUsuario().getCpf())){
                existente.setUsuario(alterado.getUsuario()); 
            }
            else{
                throw new NotFound("Cpf de estudante não encontrou usúario correspondente ou já está sendo usado");
            }
        }
        return estudanteRepository.save(existente);
    }
 
    @Transactional
    public void deletar(String mtr){
        if (!estudanteRepository.existsById(mtr)){
            throw new NotFound("Estudante inexistente");
        }
        estudanteRepository.deleteById(mtr);
    }

}
