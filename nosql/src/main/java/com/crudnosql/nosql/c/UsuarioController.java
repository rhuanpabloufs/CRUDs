package com.crudnosql.nosql.c;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crudnosql.nosql.objects.Usuario;
import com.crudnosql.nosql.r.UsuarioRepo;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private UsuarioRepo usuarioRepo;
    @GetMapping
    public List<Usuario> fAll(){
        return usuarioRepo.findAll();
    }
    @GetMapping("/cpf/{cpf}")
    public Usuario getBYcpf(@RequestParam String cpf) {
        return usuarioRepo.findById(cpf).orElse(null);
    }
    
}
