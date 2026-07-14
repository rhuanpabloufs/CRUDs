package com.engdados.arrocha.arrochadb.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.engdados.arrocha.arrochadb.Entities.Usuario;
import com.engdados.arrocha.arrochadb.Repositories.UsuarioRepository;
import com.engdados.arrocha.arrochadb.Exceptions.*;

import java.util.*;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario inserir(Usuario linha){ // Inserção de uma linha
        if (usuarioRepository.existsById(linha.getCpf())){
            throw new DadosDuplicado("CPF já cadastrado");
        }
        return usuarioRepository.save(linha);
    }

    public List<Usuario> listarTodos(){ // Leitura Geral
        return usuarioRepository.findAll();
    }

    public Usuario buscarUsuario(Long id){
        return usuarioRepository.findById(id).orElseThrow(() -> new NotFound("Usúario inexistente"));
    }

    @Transactional
    public Usuario atualizar(Long cpf, Usuario alterado){ // ataulizar uma linha
        Usuario existente = buscarUsuario(cpf);

        if(alterado.getData_nascimento() != null){
            existente.setData_nascimento(alterado.getData_nascimento());
        } 
        if(alterado.getNome() != null && !alterado.getNome().isBlank()){ 
            existente.setNome(alterado.getNome());
        }
        if(alterado.getTelefone() != null && !alterado.getTelefone().isEmpty()){

            for (String e : alterado.getTelefone()){
                if(!existente.getTelefone().contains(e))
                    existente.getTelefone().add(e);
            }
        }
        if(alterado.getSenha() != null &&  !alterado.getSenha().isBlank()){
            existente.setSenha(alterado.getSenha());
        }
        if(alterado.getEmail() != null && !alterado.getEmail().isEmpty()){
           
            for (String e : alterado.getEmail()){
                if(!existente.getEmail().contains(e))
                    existente.getEmail().add(e);
            }
        
        }
        if(alterado.getLogin() != null &&  !alterado.getLogin().isBlank()){
            if(!existente.getLogin().equals(alterado.getLogin()) && usuarioRepository.existsBylogin(alterado.getLogin())){
                throw new DadosDuplicado("Login já utilizado");
            }
            else{
                existente.setLogin(alterado.getLogin());
            }
        }
        return usuarioRepository.save(existente);
    }
 
    @Transactional
    public void deletar(Long cpf){
        if (!usuarioRepository.existsById(cpf)){
            throw new NotFound("CPF inexistente");
        }
        usuarioRepository.deleteById(cpf);
    }

}
