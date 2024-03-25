package bancobbb2.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bancobbb2.api.model.ContaPoupanca;
import bancobbb2.api.model.Usuario;
import bancobbb2.api.repository.ContaPoupancaRepository;
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

    //Método para associar Conta Pupança a Usuario
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

    
    
}
