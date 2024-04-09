package bancobbb2.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bancobbb2.api.model.Banco;
import bancobbb2.api.model.Funcionario;
import bancobbb2.api.model.Usuario;
import bancobbb2.api.repository.BancoRepository;
import bancobbb2.api.repository.FuncionarioRepository;
import bancobbb2.api.repository.UsuarioRepository;

@RestController
@RequestMapping("/banco")
public class BancoController {

    @Autowired
    private BancoRepository bancoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Para cadastrar o banco
    @PostMapping
    public ResponseEntity<Banco> cadastrarBanco(@RequestBody Banco banco) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bancoRepository.save(banco));
    }

    //Para adicionar funcionário em uma lista do banco:
    @PostMapping("/{idBanco}/adicionarFuncionario/{idFuncionario}")
    public ResponseEntity<Banco> adicionarFuncionarioNaLista(@PathVariable("idBanco") Long idBanco,
    @PathVariable("idFuncionario") Long idFuncionario) {
        
        Optional<Banco> bancOptional = bancoRepository.findById(idBanco);
        if (bancOptional.isPresent()) {
            Banco bancoEncontrado = bancOptional.get();

           Optional<Funcionario> funcOptional = funcionarioRepository.findById(idFuncionario);
           if (funcOptional.isPresent()) {
            Funcionario funcEncontrado = funcOptional.get();

            List<Funcionario> funcionarios = bancoEncontrado.getFuncionarios();
            funcionarios.add(funcEncontrado);

            bancoRepository.save(bancoEncontrado);
            return ResponseEntity.ok(bancoEncontrado);

           } else {
            return ResponseEntity.notFound().build();
           }
            
        }
        return ResponseEntity.notFound().build();
    }

    //Método para adicionar Usuario a lista de usuarios do banco:
    @PostMapping("/{idBanco}/adicionarUsuario/{idUsuario}")
    public ResponseEntity<Banco> adicionarUsuarioNaLista(@PathVariable("idBanco") Long idBanco,
    @PathVariable("idUsuario") Long idUsuario) {

        Optional<Banco> bancOptional = bancoRepository.findById(idBanco);
        if (bancOptional.isPresent()) {
            Banco bancoEncontrado = bancOptional.get();

            Optional<Usuario> usuarOptional = usuarioRepository.findById(idUsuario);
            if (usuarOptional.isPresent()) {
                Usuario usuarioEncont = usuarOptional.get();

                List<Usuario> usuarios = bancoEncontrado.getUsuarios();
                usuarios.add(usuarioEncont);

                bancoRepository.save(bancoEncontrado);
                return ResponseEntity.ok(bancoEncontrado);
                
            } else {
                return ResponseEntity.notFound().build();
            }
            
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Banco>> exibirListaDeBancos() {
        return ResponseEntity.status(HttpStatus.OK)
        .body(bancoRepository.findAll());
    }

    
    // Para Localizar o banco pelo id
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Banco>> localizarBancoPeloId(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(bancoRepository.findById(id));
    }

    //Para atualizar dados do banco:
    @PutMapping("/{id}")
    public ResponseEntity<Banco> atualizarDadosDoBanco(@PathVariable("id") Long id,
    @RequestBody Banco banco) {
        Optional<Banco> bancOptional = bancoRepository.findById(id);

        if (bancOptional.isPresent()) {
            Banco bancoAtualizado = bancOptional.get();

            bancoAtualizado.setAgencia(banco.getAgencia());
            bancoAtualizado.getEnderecoBanco().setCep(banco.getEnderecoBanco().getCep());
            bancoAtualizado.getEnderecoBanco().setLogradouro(banco.getEnderecoBanco().getLogradouro());
            bancoAtualizado.getEnderecoBanco().setBairro(banco.getEnderecoBanco().getBairro());
            bancoAtualizado.getEnderecoBanco().setCidade(banco.getEnderecoBanco().getCidade());
            bancoAtualizado.getEnderecoBanco().setUf(banco.getEnderecoBanco().getUf());
            bancoAtualizado.getEnderecoBanco().setDdd(banco.getEnderecoBanco().getDdd());
            bancoAtualizado.getEnderecoBanco().setTelefone(banco.getEnderecoBanco().getTelefone());
            bancoAtualizado.getEnderecoBanco().setCelular(banco.getEnderecoBanco().getCelular());

            return ResponseEntity.status(HttpStatus.OK)
            .body(bancoRepository.save(bancoAtualizado));
            
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Método para deletar banco:
    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirBanco(@PathVariable("id") Long id) {
        bancoRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
        .body("Banco excluído com sucesso!");

    }

    //Método para remover funcionário da lista do Banco:
    @DeleteMapping("/{idBanco}/removerFuncionario/{idFuncionario}") 
    public ResponseEntity<String> removerFuncionarioDaLista(@PathVariable("idBanco") Long idBanco,
    @PathVariable("idFuncionario") Long idFuncionario) {

        Optional<Banco> bancOptional = bancoRepository.findById(idBanco);
        if (bancOptional.isPresent()) {
            Banco bancoEncontrado = bancOptional.get();

            Optional<Funcionario> funcOptional = funcionarioRepository.findById(idFuncionario);
            if (funcOptional.isPresent()) {
                Funcionario funcEncontrado = funcOptional.get();

                List<Funcionario> funcionarios = bancoEncontrado.getFuncionarios();
                funcionarios.remove(funcEncontrado);

                bancoRepository.save(bancoEncontrado);
                return ResponseEntity.status(HttpStatus.OK)
                .body("Funcionário removido da lista do Banco.");
                
            } else {
                return ResponseEntity.notFound().build();
            }
            
        }
        return ResponseEntity.notFound().build();
    }

    //Método para remover usuário da lista de usuarios:
    @DeleteMapping("{idBanco}/removerUsuario/{idUsuario}")
    public ResponseEntity<String> removerUsuarioDaLista(@PathVariable("idBanco") Long idBanco,
    @PathVariable("idUsuario") Long idUsuario) {
        
        Optional<Banco> bancOptional = bancoRepository.findById(idBanco);
        if (bancOptional.isPresent()) {
            Banco bancoEncont = bancOptional.get();

            Optional<Usuario> usuOptional = usuarioRepository.findById(idUsuario);
            if (usuOptional.isPresent()) {
                Usuario usuarEncontrado = usuOptional.get();

                List<Usuario> usuarios = bancoEncont.getUsuarios();
                usuarios.remove(usuarEncontrado);

                bancoRepository.save(bancoEncont);
                return ResponseEntity.status(HttpStatus.OK)
                .body("Usuário removido com sucesso!");
                
            } else {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.notFound().build();
    }


    
}
