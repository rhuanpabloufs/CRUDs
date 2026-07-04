package com.engdados.arrocha.arrochadb.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.engdados.arrocha.arrochadb.Entities.Estudante;
import com.engdados.arrocha.arrochadb.Repositories.EstudanteRepository;
import com.engdados.arrocha.arrochadb.Repositories.UsuarioRepository;
import java.util.*;

import jakarta.transaction.Transactional;

@Service
public class EstudanteService {
    
    @Autowired
    private EstudanteRepository estudanteRepo;

    @Autowired// nao pode 2
    private UsuarioRepository usuario;

    @Transactional
    public Estudante insereEstudante(Estudante linha){ // Inserção de uma linha
        if (estudanteRepo.existsById(linha.getMat_Estudante())){
            throw new RuntimeException("Estudante já cadastrado");
        }
        if (linha.getUsuario() != null && !usuario.existsById(linha.getUsuario().getCpf())){
            throw new RuntimeException("Estudante não encontrou usuário correspondente");
        }
        return estudanteRepo.save(linha);
    }

    public List<Estudante> buscaTdEstudantes(){ // Leitura Geral
        return estudanteRepo.findAll();
    }

    public Estudante buscaEstudante(String mat){
        return estudanteRepo.findById(mat).orElseThrow(() -> new RuntimeException("Estudante não encontrado"));
    }


    @Transactional
    public Estudante updEstudante(String matricula, Estudante alterado){ // atualizar uma linha
        Estudante existente = estudanteRepo.findById(matricula)
            .orElseThrow(() -> new RuntimeException("Erro: Usuário com matricula " + matricula + " não encontrado."));
        
        if(alterado.getMC() != null){
            existente.setMC(alterado.getMC());
        } 
        if(alterado.getAno_ingresso() != 0){ 
            existente.setAno_ingresso(alterado.getAno_ingresso());
        }
        if(alterado.getUsuario() != null){
            if(alterado.getUsuario().getCpf() == null){
                existente.setUsuario(null); // permite setar null
            }
            else if(!estudanteRepo.existsByUsuarioCpf(alterado.getUsuario().getCpf()) 
                     && usuario.existsById(alterado.getUsuario().getCpf())){
                existente.setUsuario(alterado.getUsuario()); // troca o usuario inteiro, não só o cpf
            }
        }
        return estudanteRepo.save(existente);
    }
 
    @Transactional
    public void rmEstudante(String mtr){
        if (!estudanteRepo.existsById(mtr)){
            throw new RuntimeException("Estudante inexistente");
        }
        estudanteRepo.deleteById(mtr);
    }

}
