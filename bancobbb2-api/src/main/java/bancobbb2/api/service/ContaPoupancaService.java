package bancobbb2.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bancobbb2.api.dto.SaqueDto;
import bancobbb2.api.model.ContaPoupanca;
import bancobbb2.api.model.Funcionario;
import bancobbb2.api.model.Usuario;
import bancobbb2.api.repository.ContaPoupancaRepository;
import bancobbb2.api.repository.FuncionarioRepository;
import bancobbb2.api.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ContaPoupancaService {

    
    private final ContaPoupancaRepository contaPoupancaRepository;

    @Autowired
    public ContaPoupancaService(ContaPoupancaRepository contaPoupancaRepository) {
        this.contaPoupancaRepository = contaPoupancaRepository;
    }

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    //Método para associar Conta Poupança a Usuario
    public void associarContaPoupancaUsuario(Long idUsuario, Long idCp) {

        Optional<Usuario> usuarOptional = usuarioRepository.findById(idUsuario);
        Optional<ContaPoupanca>contaOptional = contaPoupancaRepository.findById(idCp);

        if (usuarOptional.isPresent() && contaOptional.isPresent()) {
            Usuario usuario = usuarOptional.get();
            ContaPoupanca contaPoupanca = contaOptional.get();

            //associando:
            usuario.setContaPoupanca(contaPoupanca);
            usuarioRepository.save(usuario);
            
        } else {
            throw new EntityNotFoundException("Usuario não encontrado!");
        }
    }

    //Método para associar Conta Poupança à Funcionário
    public void associarFuncContaPoupanca(Long idFuncionario, Long idCp) {
        Optional<Funcionario> funcOptional = funcionarioRepository.findById(idFuncionario);
        Optional<ContaPoupanca> contaOptional = contaPoupancaRepository.findById(idCp);

        if (funcOptional.isPresent() && contaOptional.isPresent()) {
            Funcionario funcionario = funcOptional.get();
            ContaPoupanca contaPoupanca = contaOptional.get();

            funcionario.setContaPoupanca(contaPoupanca);
            funcionarioRepository.save(funcionario);
        } else {
            throw new EntityNotFoundException("Funcionário não foi encontrado.");
        }
    }

    public Double exibirSaldoCp(Long idCp) {
        Optional<ContaPoupanca> contaOptional = contaPoupancaRepository.findById(idCp);

        if (contaOptional.isPresent()) {
            ContaPoupanca conta = contaOptional.get();
            return conta.getSaldoCp();
        } else {
            throw new EntityNotFoundException("Conta não encontrada.");
        }
    }

    public Double sacarValorCp(Long idCp, SaqueDto valorDto) {
        Optional<ContaPoupanca> contaOptional = contaPoupancaRepository.findById(idCp);

        if (contaOptional.isPresent()) {
            ContaPoupanca contaPoupanca = contaOptional.get();

            Double novoSaldo = contaPoupanca.getSaldoCp() - valorDto.getValorSaque();
            contaPoupanca.setSaldoCp(novoSaldo);
            contaPoupancaRepository.save(contaPoupanca);
            return novoSaldo;
            
        } else {
            return null;
        }
    }
    



    
    
}
