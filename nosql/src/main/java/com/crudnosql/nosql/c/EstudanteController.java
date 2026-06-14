package com.crudnosql.nosql.c;
import java.util.List;
import javax.management.RuntimeErrorException;
import org.springframework.stereotype.Service;
import com.crudnosql.nosql.objects.Estudante;
import com.crudnosql.nosql.r.EstudanteRepo;
import com.crudnosql.nosql.r.UsuarioRepo;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Service
public class EstudanteController {
    private final EstudanteRepo eRepo;
    public List<Estudante> getAll(){return eRepo.findAll();}
    public Estudante getByCpf(String cpf){return eRepo.findById(cpf).orElse(null);}
    public Estudante getByMat(String m){return eRepo.findByMatEstudante(m);}
    public List<Estudante> getbyYear(int ano_ingresso){return eRepo.findByAnoIngresso(ano_ingresso);}
    public void addStudent(String cpf, String mat_estudante, double mc, int ano_ingresso, UsuarioRepo u) throws Exception{
        if(eRepo.existsById(cpf) || eRepo.existsByMatEstudante(mat_estudante)){
            throw new Exception("cuida papai");
        } else if(u.existsById(cpf)){
            Estudante e = new Estudante(cpf, mat_estudante, mc, ano_ingresso);
            eRepo.save(e);
        }
    }
    public void updateMat_Estudante(String cpf,String mat){
        if(eRepo.existsByMatEstudante(mat) || !eRepo.existsById(cpf)){
            throw new RuntimeErrorException(new Error("cuida papai"));
        }
        Estudante e = getByCpf(cpf);
        e.setMatEstudante(mat);
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
        eRepo.deleteByAnoIngresso(ano);
    }
}
