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

import bancobbb2.api.model.ContaPoupanca;
import bancobbb2.api.repository.ContaPoupancaRepository;

@RestController
@RequestMapping("/contaPoupanca")
public class ContaPoupancaController {

    @Autowired
    private ContaPoupancaRepository contaPoupancaRepository;

    @PostMapping
    public ResponseEntity<ContaPoupanca> cadastrarContaPoupanca(@RequestBody ContaPoupanca contaPoupanca) {
        return ResponseEntity.status(HttpStatus.CREATED)
        .body(contaPoupancaRepository.save(contaPoupanca));
    }

    @GetMapping
    public ResponseEntity<List<ContaPoupanca>> exibirListaDeContasPoupanca() {
        return ResponseEntity.status(HttpStatus.OK)
        .body(contaPoupancaRepository.findAll());
    }

    @GetMapping("/{idCp}")
    public ResponseEntity<Optional<ContaPoupanca>> buscarContaPoupancaPeloId(@PathVariable("idCp") Long idCp) {
        return ResponseEntity.status(HttpStatus.OK)
        .body(contaPoupancaRepository.findById(idCp));
    }

    


    
}
