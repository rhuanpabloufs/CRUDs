package com.engdados.arrocha.arrochadb.Services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.engdados.arrocha.arrochadb.Entities.Curso;
import com.engdados.arrocha.arrochadb.Entities.Turno;
import com.engdados.arrocha.arrochadb.Entities.Nivel;
import com.engdados.arrocha.arrochadb.Entities.Grau;
import com.engdados.arrocha.arrochadb.Repositories.CursoRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CursoService {
    private final CursoRepository cursoBala;
    void botaCurso(String nome, Grau grau, Turno turno, String campus, Nivel nivel) throws Exception{
        if (cursoBala.existsByNome(nome)){
            throw new Exception("Tira que eu vou cagar");
        }
        Curso otoCurso = new Curso();
        otoCurso.setNome(nome);
        otoCurso.setGrau(grau);
        otoCurso.setTurno(turno);
        otoCurso.setCampus(campus);
        otoCurso.setNivel(nivel);

        cursoBala.save(otoCurso);
    }
    List<Curso> macroCaca(){return cursoBala.findAll();} // macroCaca = caçar tudo no repo
    Curso cacaId(Long id){return cursoBala.findById(id).orElse(null);}
    
    void upCurso(Long id, String nNome, Grau nGrau, Turno nTurno, String nCampus, Nivel nNivel) throws Exception{
        Curso cursoAtual = cacaId(id);
        if (cursoAtual == null){
            throw new Exception("Cabrunco tá oco");            
        }
        cursoAtual.setNome(nNome);
        cursoAtual.setGrau(nGrau);
        cursoAtual.setTurno(nTurno);
        cursoAtual.setCampus(nCampus);
        cursoAtual.setNivel(nNivel);
        cursoBala.save(cursoAtual);
    }
    void neutrofilo(Long id){
        if (cursoBala.existsById(id)){
            cursoBala.deleteById(id);
        }
    }

}   
