package com.engdados.arrocha.arrochadb.Controllers;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
    private VinculoService vinculoSe;

    @PostMapping
    public Vinculo inserir(@Valid @RequestBody Vinculo vinculaEle){
        return vinculoSe.insereVinculo(vinculaEle);
    }

    @PutMapping("/{idVinculo}")
    public Vinculo atualizar(@PathVariable("idVinculo") Long idVinculo, @Valid @RequestBody Vinculo mudado){
        return vinculoSe.updVinculo(idVinculo, mudado);
    }

    @GetMapping
    public List<Vinculo> buscaTodos(){
        return vinculoSe.buscaTdVinculos();
    }

    @GetMapping("/{idVinculo}")
    public Vinculo busca(@Valid @PathVariable Long idVinculo){
        return vinculoSe.buscaVinculo(idVinculo);
    }

    @DeleteMapping("/{idVinculo}")
    public void remover(@PathVariable("idVinculo") Long idVinculo){
        vinculoSe.rmVinculo(idVinculo);
    }
}
