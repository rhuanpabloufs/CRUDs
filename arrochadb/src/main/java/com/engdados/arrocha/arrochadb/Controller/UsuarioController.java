package com.engdados.arrocha.arrochadb.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    private UsuarioService usuarioService;

    @PostMapping
    public Usuario inserir(@Valid @RequestBody Usuario usuario){
        return usuarioService.inserir(usuario);
    }

    @PutMapping("/{cpf}")
    public Usuario update(@PathVariable Long cpf, @RequestBody Usuario alterado){
        return usuarioService.atualizar(cpf, alterado);
    }

    @GetMapping
    public List<Usuario> listAll(){
        return usuarioService.listarTodos();
    }

    @GetMapping("/{cpf}")
    public Usuario buscarPorId(@PathVariable Long cpf){
        return usuarioService.buscarUsuario(cpf);
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Usuario> deletar(@PathVariable Long cpf){ // não informava nada quando erad deletado, agora retorna o objeto
        Usuario deletado = usuarioService.buscarUsuario(cpf);
        usuarioService.deletar(cpf);
        return ResponseEntity.status(200).body(deletado);
    }

}
