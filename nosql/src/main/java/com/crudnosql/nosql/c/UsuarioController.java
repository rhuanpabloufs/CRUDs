package com.crudnosql.nosql.c;
import com.crudnosql.nosql.objects.Usuario;
import com.crudnosql.nosql.r.UsuarioRepo;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class UsuarioController {
    private final UsuarioRepo usuarioRepo;
    public List<Usuario> fAll(){return usuarioRepo.findAll();
    }
    public Usuario getBYcpf(String cpf) {return usuarioRepo.findById(cpf).orElse(null);}
    public List<Usuario> getByNome(String nome) {return usuarioRepo.findByNome(nome);}
    public void saveUser(String cpf,String nome,LocalDateTime data_nascimento, List<String> email, List<String> telefone,  String login, String senha) throws Exception {
        if(usuarioRepo.existsById(cpf) || usuarioRepo.existsByLogin(login)){
            throw new Exception("Cuida Papai");
        }
        Usuario u = new Usuario(cpf, nome,data_nascimento, email,  telefone, login, senha);
        usuarioRepo.save(u);
    }
    public void updateUserNome(String UserId, String NovoNome){
        Usuario u = getBYcpf(UserId);
        if(u != null) {
            u.setNome(NovoNome);
            usuarioRepo.save(u);
        }
    }
    public void updateUserDataNascimento(String UserId, LocalDateTime NovaDataNascimento){
        Usuario u = getBYcpf(UserId);
        if(u != null){
            u.setData_nascimento(NovaDataNascimento);
            usuarioRepo.save(u);
        }
    }
    public void updateUserEmail(String UserId, List<String> NovosEmails){
        Usuario u = getBYcpf(UserId);
        if(u != null){
            u.setEmail(NovosEmails);
            usuarioRepo.save(u);
        }
    }
    public void updateUserTelefone(String UserId, List<String> NovosTelefones){
        Usuario u = getBYcpf(UserId);
        if(u != null){
            u.setTelefone(NovosTelefones);
            usuarioRepo.save(u);
        }
    }
    public void updateUserLogin(String UserId, String NovoLogin){
        Usuario u = getBYcpf(UserId);
        if(u != null){
            u.setLogin(NovoLogin);
            usuarioRepo.save(u);
        }
    }
    public void updateUserSenha(String UserId, String NovaSenha){
        Usuario u = getBYcpf(UserId);
        if(u != null){
            u.setSenha(NovaSenha);
            usuarioRepo.save(u);
        }
    }
    public void deleteCPF(String cpf){
        if (usuarioRepo.existsById(cpf)) {
        usuarioRepo.deleteById(cpf);
        }
    }
    public void deleteLogin(String login){
        if(usuarioRepo.existsByLogin(login)){
            usuarioRepo.deleteByLogin(login);
        }
    }
}
