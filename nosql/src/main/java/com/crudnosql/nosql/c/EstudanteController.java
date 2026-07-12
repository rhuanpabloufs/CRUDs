package com.crudnosql.nosql.c;
import java.util.List;
import javax.management.RuntimeErrorException;
import org.springframework.stereotype.Service;
import com.crudnosql.nosql.objects.Estudante;
import com.crudnosql.nosql.objects.Vinculo;
import com.crudnosql.nosql.r.EstudanteRepo;
import com.crudnosql.nosql.r.UsuarioRepo;
import com.crudnosql.nosql.r.VinculoRepo;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Service
public class EstudanteController {
    private final EstudanteRepo eRepo;
    public List<Estudante> getAll(){return eRepo.findAll();}
    public Estudante getByCpf(String cpf){return eRepo.findByCpf(cpf);}
    public Estudante getByMat(String m){return eRepo.findById(m).orElse(null);}
    public List<Estudante> getbyYear(int ano_ingresso){return eRepo.findByAnoIngresso(ano_ingresso);}
    public void addStudent(String cpf, String mat_estudante, double mc, int ano_ingresso, UsuarioRepo u) throws Exception{
        if(eRepo.existsById(mat_estudante) || eRepo.existsByCpf(cpf)){
            throw new Exception("cuida papai");
        } else if(u.existsById(cpf)){
            Estudante e = new Estudante(cpf, mat_estudante, mc, ano_ingresso);
            eRepo.save(e);
        }
    }
    public void uptadeMC(double mc, String mat){
        if(!eRepo.existsById(mat)){
            throw new RuntimeErrorException(new Error("Jamaixxxx"));
        }
        Estudante e = getByMat(mat);
        eRepo.save(e);
    }
    public void deleteByMat_Estudante(String mat, VinculoRepo v){
        if(eRepo.existsById(mat)){
            eRepo.deleteById(mat);
        } 
        if(v.existsByMatEstudante(mat)){
            Vinculo v1 = v.findByMatEstudante(mat);
            v1.setMatEstudante(null);
            v.save(v1);
        }
    }
    public void deleteYear(int ano){
        eRepo.deleteByAnoIngresso(ano);
    }
}
