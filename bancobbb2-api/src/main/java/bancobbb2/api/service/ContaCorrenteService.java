package bancobbb2.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bancobbb2.api.model.ContaCorrente;
import bancobbb2.api.model.Funcionario;
import bancobbb2.api.model.Usuario;
import bancobbb2.api.repository.ContaCorrenteRepository;
import bancobbb2.api.repository.FuncionarioRepository;
import bancobbb2.api.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ContaCorrenteService {

    private final ContaCorrenteRepository contaCorrenteRepository;

    @Autowired
    public ContaCorrenteService(ContaCorrenteRepository contaCorrenteRepository) {
        this.contaCorrenteRepository = contaCorrenteRepository;
    }

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    // Método para associar uma conta corrente ao usuário:
    public void associarContaCorrenteUsuario(Long idUsuario, Long idCc) {

        Optional<Usuario> usuOptional = usuarioRepository.findById(idUsuario);
        Optional<ContaCorrente> contaCorrOptional = contaCorrenteRepository.findById(idCc);

        if (usuOptional.isPresent() && contaCorrOptional.isPresent()) {
            Usuario usuario = usuOptional.get();
            ContaCorrente contaCorrente = contaCorrOptional.get();

            // Associando conta a usuário:
            usuario.setContaCorrente(contaCorrente);
            usuarioRepository.save(usuario);
        } else {
            throw new EntityNotFoundException("Usuário noã encontrado!");
        }
    }

    // Método para associar conta corrente a Funcionário
    public void associarContaCorrenteFuncionario(Long idFuncionario, Long idCc) {

        Optional<Funcionario> funcOptional = funcionarioRepository.findById(idFuncionario);
        Optional<ContaCorrente> contOptional = contaCorrenteRepository.findById(idCc);

        if (funcOptional.isPresent() && contOptional.isPresent()) {
            Funcionario funcionario = funcOptional.get();
            ContaCorrente contaCorrente = contOptional.get();

            funcionario.setContaCorrente(contaCorrente);
            funcionarioRepository.save(funcionario);
        } else {
            throw new EntityNotFoundException("Funcionário não econtrado!");
        }
    }

    public Double exibirSaldo(Long idCc) {

        Optional<ContaCorrente> contOptional = contaCorrenteRepository.findById(idCc);

        if (contOptional.isPresent()) {
            ContaCorrente contaCorrente = contOptional.get();
            return contaCorrente.getSaldoCc();
            
        } else {
            throw new EntityNotFoundException("Conta corrente não encontrada!");
        }
    }

}
