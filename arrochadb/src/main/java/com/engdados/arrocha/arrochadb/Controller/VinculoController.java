package com.engdados.arrocha.arrochadb.Controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.engdados.arrocha.arrochadb.Entities.Vinculo;
import com.engdados.arrocha.arrochadb.Services.VinculoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/vinculos")
public class VinculoController {

    @Autowired
    private VinculoService vinculoService;

    @PostMapping
    public Vinculo inserir(@Valid @RequestBody Vinculo vinculaEle){
        return vinculoService.inserir(vinculaEle);
    }

    @PutMapping("/{idVinculo}")
    public Vinculo atualizar(@PathVariable("idVinculo") Integer idVinculo, @RequestBody Vinculo mudado){
        return vinculoService.atualizar(idVinculo, mudado);
    }

    @GetMapping
    public List<Vinculo> listarTodos(){
        return vinculoService.listarTodos();
    }

    @GetMapping("/{idVinculo}")
    public Vinculo buscarPorId(@PathVariable Integer idVinculo){
        return vinculoService.buscarPorId(idVinculo);
    }

    @DeleteMapping("/{idVinculo}")
    public ResponseEntity<Vinculo> deletar(@PathVariable Integer idVinculo){
        Vinculo deletado = vinculoService.buscarPorId(idVinculo);
        vinculoService.deletar(idVinculo);
        return ResponseEntity.status(200).body(deletado);
    }
}
