package bancobbb2.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bancobbb2.api.dto.DepositoDto;
import bancobbb2.api.dto.SaqueDto;
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


    //Método para associar Funcionario à conta salário.
    public void associarContaSalarioFuncionario(Long idFuncionario, Long idCs) {

        Optional<Funcionario> funcOptional = funcionarioRepository.findById(idFuncionario);
        Optional<ContaSalario> contaSalOptional = contaSalarioRepository.findById(idCs);

        if (funcOptional.isPresent() && contaSalOptional.isPresent()) {
            Funcionario funcionario = funcOptional.get();
            ContaSalario contaSalario = contaSalOptional.get();

            funcionario.setContaSalario(contaSalario);
            funcionarioRepository.save(funcionario);
            
        } else {
            throw new EntityNotFoundException("Usuário não encontrado!");
        }
    }

    public Double exbirSaldo(Long idCs) {
        Optional<ContaSalario> contaOptional = contaSalarioRepository.findById(idCs);

        if (contaOptional.isPresent()) {
            ContaSalario conta = contaOptional.get();
           return conta.getSaldoCs();
        } else {
            throw new  EntityNotFoundException("Conta não encontrada!");
        }
    
    }

    public Double sacarValor(Long idCs, SaqueDto valorDto) {
        Optional<ContaSalario> contaOptional = contaSalarioRepository.findById(idCs);

        if (contaOptional.isPresent()) {
            ContaSalario contaSalario = contaOptional.get();

            Double novoSaldo = contaSalario.getSaldoCs() - valorDto.getValorSaque();
            contaSalario.setSaldoCs(novoSaldo);
            contaSalarioRepository.save(contaSalario);
            return novoSaldo;
            
        } else {
            throw new EntityNotFoundException("Conta não encontrada.");
        }
    }

    public void depositarValorCs(Long idCs, DepositoDto valorDto) {
        Optional<ContaSalario> contaOptional = contaSalarioRepository.findById(idCs);

        if (contaOptional.isPresent()) {
            ContaSalario contaSalario = contaOptional.get();

            Double novoSaldo = contaSalario.getSaldoCs() + valorDto.getValorDeposito();
            contaSalario.setSaldoCs(novoSaldo);
            contaSalarioRepository.save(contaSalario);
            
        }
    }
    
}
