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
import bancobbb2.api.repository.BancoRepository;

@RestController
@RequestMapping("/banco")
public class BancoController {

    @Autowired
    private BancoRepository bancoRepository;

    // Para cadastrar o banco
    @PostMapping
    public ResponseEntity<Banco> cadastrarBanco(@RequestBody Banco banco) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bancoRepository.save(banco));
    }

    //Para exibir uma lista de bancos:
     // Para listar os bancos cadastrados
    @GetMapping
    public ResponseEntity<List<Banco>> listarBancos() {
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


    
}
