package com.crudnosql.nosql.c;
import java.time.LocalDateTime;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Service;
import com.crudnosql.nosql.objects.Vinculo;
import com.crudnosql.nosql.r.CursoRepo;
import com.crudnosql.nosql.r.EstudanteRepo;
import com.crudnosql.nosql.r.VinculoRepo;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class VinculoService {
    private final VinculoRepo vRepo;
    public Vinculo getID(String id){
        return vRepo.findById(id).orElse(null);
    }
    public Vinculo getMat(String mat){
        return vRepo.findByMatEstudante(mat);
    }
    public List<Vinculo> getCurso(String idCurso){
        return vRepo.findByCurso(idCurso);
    }
    public void addVinculo(String mat_estudante, String curso, LocalDateTime dataEntrada, LocalDateTime dataSaida, String status, CursoRepo c, EstudanteRepo e){
        if(vRepo.existsByMatEstudante(mat_estudante)){
            throw new RuntimeErrorException(null);
        } else if(c.existsById(curso) && e.existsById(mat_estudante)){
            Vinculo v = new Vinculo(null, mat_estudante, curso, dataEntrada, status, dataSaida);
            vRepo.save(v);
        }
    }
    public void updateVinculoCurso(String matEstudante, String novoCurso) {
        Vinculo v = vRepo.findByMatEstudante(matEstudante);
        if (v != null) {
            v.setCurso(novoCurso);
            vRepo.save(v);
        }
    }

    public void updateVinculoDataEntrada(String matEstudante, LocalDateTime novaData) {
        Vinculo v = vRepo.findByMatEstudante(matEstudante);
        if (v != null) {
            v.setDataEntrada(novaData);
            vRepo.save(v);
        }
    }

    public void updateVinculoStatus(String matEstudante, String novoStatus) {
        Vinculo v = vRepo.findByMatEstudante(matEstudante);
        if (v != null) {
            v.setStatus(novoStatus);
            vRepo.save(v);
        } else {
            System.err.println("ta nulo");
        }
    }

    public void updateVinculoDataSaida(String matEstudante, LocalDateTime novaDataSaida) {
        Vinculo v = vRepo.findByMatEstudante(matEstudante);
        if (v != null) {
            v.setDataSaida(novaDataSaida);
            vRepo.save(v);
        } 
    }
    public void deleteCurso(String curso){
        vRepo.deleteByCurso(curso);
    }
    public void deleteByMat_Estudante(String m){
        vRepo.deleteByMatEstudante(m);
    }
}

