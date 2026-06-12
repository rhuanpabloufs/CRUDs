package com.engdados.arrocha.arrochadb.Services;

import java.util.List;
import java.time.LocalDate;
import com.engdados.arrocha.arrochadb.Entities.Vinculo;
import com.engdados.arrocha.arrochadb.Entities.Status;
import com.engdados.arrocha.arrochadb.Repositories.VinculoRepository;
import com.engdados.arrocha.arrochadb.Entities.Estudante;
import com.engdados.arrocha.arrochadb.Entities.Curso;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class VinculoService {
    private final VinculoRepository vinculoDosCara;
        public void botaVinculo(Estudante estudante, Curso curso, LocalDate dataEntrada, Status status, LocalDate dataSaida) throws Exception{
        if(curso == null || estudante == null){
            throw new Exception("Henrique, porra! Fique de boa, eu sou sua!");
        }
        Vinculo otoVinculo = new Vinculo();
        otoVinculo.setEstudante(estudante);
        otoVinculo.setCurso(curso);
        otoVinculo.setDataEntrada(dataEntrada);
        otoVinculo.setStatus(status);
        otoVinculo.setDataSaida(dataSaida);
        vinculoDosCara.save(otoVinculo);
    }
    public List<Vinculo> macroCaca(){ return vinculoDosCara.findAll();}
    public Vinculo cacaId(Long id){ 
        return vinculoDosCara.findById(id).orElse(null);
    }
    public void vinculoAtualisado(Long id, Estudante nEstudante, Curso nCurso, LocalDate nDataEntrada, Status nStatus, LocalDate nDataSaida) throws Exception{
        Vinculo vinculoAtual = cacaId(id);
        if (vinculoAtual == null){
            throw new Exception("Peraí, bixo! Could you tell me again?!");
        }
        vinculoAtual.setEstudante(nEstudante);
        vinculoAtual.setCurso(nCurso);
        vinculoAtual.setDataEntrada(nDataEntrada);
        vinculoAtual.setStatus(nStatus);
        vinculoAtual.setDataSaida(nDataSaida);
        vinculoDosCara.save(vinculoAtual);
    }
    public void neutrofilo(Long id){
        if(vinculoDosCara.existsById(id)){
            vinculoDosCara.deleteById(id);
        }
    }
    
}
