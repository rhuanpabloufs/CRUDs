package com.crudnosql.nosql.c;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crudnosql.nosql.objects.Estudante;
import com.crudnosql.nosql.r.EstudanteRepo;

@Service
public class EstudanteController {
    @Autowired
    private EstudanteRepo eRepo;
    public List<Estudante> getAll(){return eRepo.findAll();}
    public Estudante getByCpf(String cpf){return eRepo.findById(cpf).orElse(null);}
    public Estudante getByMat(String m){return eRepo.getByMat_estudante(m);}
    public List<Estudante> getbyYear(int ano_ingresso){return eRepo.getByAno_ingresso(ano_ingresso);}
    public void addStudent(String cpf, String mat_estudante, double mc, int ano_ingresso) throws Exception{
        if(eRepo.existsById(cpf) || eRepo.existsByMat_estudante(mat_estudante)){
            throw new Exception("cuida papai");
        } 
        Estudante e = new Estudante(cpf, mat_estudante, mc, ano_ingresso);
        eRepo.save(e);
    }
    public void updateMat_Estudante(String cpf,String mat){
        if(eRepo.existsByMat_estudante(mat) || !eRepo.existsById(cpf)){
            throw new RuntimeErrorException(new Error("cuida papai"));
        }
        Estudante e = getByCpf(cpf);
        e.setMat_estudante(mat);
        eRepo.save(e);
    }
    public void uptadeMC(double mc, String cpf){
        if(!eRepo.existsById(cpf)){
            throw new RuntimeErrorException(new Error("Jamaixxxx"));
        }
        Estudante e = getByCpf(cpf);
        eRepo.save(e);
    }
    public void DeleteCPF(String cpf){
        if(eRepo.existsById(cpf)){
            eRepo.deleteById(cpf);
        }
    }
    public void deleteYear(int ano){
        eRepo.deleteByAno_ingresso(ano);
    }
}
