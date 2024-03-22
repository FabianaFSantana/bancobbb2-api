package bancobbb2.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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



    
}
