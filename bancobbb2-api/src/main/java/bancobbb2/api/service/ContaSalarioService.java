package bancobbb2.api.service;

import java.lang.module.ResolutionException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scripting.config.LangNamespaceHandler;
import org.springframework.stereotype.Service;

import bancobbb2.api.model.ContaSalario;
import bancobbb2.api.model.Funcionario;
import bancobbb2.api.model.Usuario;
import bancobbb2.api.repository.ContaSalarioRepository;
import bancobbb2.api.repository.FuncionarioRepository;
import bancobbb2.api.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ContaSalarioService {

    private final ContaSalarioRepository contaSalarioRepository;

    @Autowired
    public ContaSalarioService(ContaSalarioRepository contaSalarioRepository) {
        this.contaSalarioRepository = contaSalarioRepository;
    }

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    //Método para associar usuário a conta salário.
    public void associarContaSalarioUsuario(Long idUsuario, Long idCs) {

        Optional<Usuario> usuOptional = usuarioRepository.findById(idUsuario);
        Optional<ContaSalario> contaSalOptional = contaSalarioRepository.findById(idCs);

        if (usuOptional.isPresent() && contaSalOptional.isPresent()) {
            Usuario usuario = usuOptional.get();
            ContaSalario contaSalario = contaSalOptional.get();

            usuario.setContaSalario(contaSalario);
            usuarioRepository.save(usuario);
            
        } else {
            throw new EntityNotFoundException("Usuário não encontrado!");
        }
    }

    //Método para associar funcionário a conta salário.
    public void associarFuncContaSalario(Long idFuncionario, Long idCs) {

        Optional<Funcionario> funcOptional = funcionarioRepository.findById(idFuncionario);
        Optional<ContaSalario> contaOptional = contaSalarioRepository.findById(idCs);

        if (funcOptional.isPresent() && contaOptional.isPresent()) {
            Funcionario funcionario = funcOptional.get();
            ContaSalario contaSalario = contaOptional.get();

            funcionario.setContaSalario(contaSalario);
            funcionarioRepository.save(funcionario);
        }
        throw new EntityNotFoundException("Funcionário não encontrado.");

    }
    
}
