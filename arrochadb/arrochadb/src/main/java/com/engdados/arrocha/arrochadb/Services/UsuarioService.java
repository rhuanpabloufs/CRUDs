package com.engdados.arrocha.arrochadb.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.engdados.arrocha.arrochadb.Entities.Usuario;
import com.engdados.arrocha.arrochadb.Repositories.UsuarioRepository;
import java.util.*;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepo;

    @Transactional
    public Usuario insereUsuario(Usuario linha){ // Inserção de uma linha
        if (usuarioRepo.existsById(linha.getCpf())){
            throw new RuntimeException("CPF já cadastrado");
        }
        return usuarioRepo.save(linha);
    }

    public List<Usuario> buscaTdUsuarios(){ // Leitura Geral
        return usuarioRepo.findAll();
    }

    public Usuario buscaUsuario(Long id){
        return usuarioRepo.findById(id).orElse(null);
    }

    @Transactional
    public Usuario updUsuario(Long cpf, Usuario alterado){ // ataulizar uma linha
        Usuario existente = usuarioRepo.findById(cpf)
            .orElseThrow(() -> new RuntimeException("Erro: Usuário com CPF " + cpf + " não encontrado."));
        
        if(alterado.getData_nascimento() != null){
            existente.setData_nascimento(alterado.getData_nascimento());
        } 
        if(alterado.getNome() != null && !alterado.getNome().isBlank()){ // acvaliar nulidade de alterado
            existente.setNome(alterado.getNome());
        }
        if(alterado.getTelefone() != null && alterado.getTelefone().size() > 0){
            Set<String> listaTelefonica = new HashSet<>(alterado.getTelefone());
            existente.getTelefone().addAll(listaTelefonica);
        }
        if(alterado.getSenha() != null &&  !alterado.getSenha().isBlank()){
            existente.setSenha(alterado.getSenha());
        }
        if(alterado.getEmail() != null && alterado.getEmail().size() > 0){
            Set<String> emails = new HashSet<>(alterado.getEmail());
            existente.getEmail().addAll(emails);
        }
        if(alterado.getLogin() != null &&  !alterado.getLogin().isBlank()){
            if(usuarioRepo.existsBylogin(alterado.getLogin())){
                throw new RuntimeException("Login já utilizado");
            }
            else{
                existente.setLogin(alterado.getLogin());
            }
        }
        return usuarioRepo.save(existente);
    }
 
    @Transactional
    public void rmUser(Long cpf){
        if (!usuarioRepo.existsById(cpf)){
            throw new RuntimeException("CPF inexistente");
        }
        usuarioRepo.deleteById(cpf);
    }

}
