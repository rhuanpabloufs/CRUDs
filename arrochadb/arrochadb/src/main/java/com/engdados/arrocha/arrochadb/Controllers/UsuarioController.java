package com.engdados.arrocha.arrochadb.Controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.*;

import com.engdados.arrocha.arrochadb.Entities.Usuario;
import com.engdados.arrocha.arrochadb.Services.UsuarioService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService userSe;

    @PostMapping
    public Usuario inserir(@Valid @RequestBody Usuario inseUsuario){
        return userSe.insereUsuario(inseUsuario);
    }

    @PutMapping("/{cpf}")
    public Usuario atualizar(@PathVariable Long cpf, @Valid @RequestBody Usuario alterado){
        return userSe.updUsuario(cpf, alterado);
    }

    @GetMapping
    public List<Usuario> buscaTodos(){
        return userSe.buscaTdUsuarios();
    }

    @GetMapping("/{cpf}")
    public Usuario busca(@PathVariable Long cpf){
        return userSe.buscaUsuario(cpf);
    }

    @DeleteMapping("/{cpf}")
    public void remover(@PathVariable Long cpf){
        userSe.rmUser(cpf);
    }

}
