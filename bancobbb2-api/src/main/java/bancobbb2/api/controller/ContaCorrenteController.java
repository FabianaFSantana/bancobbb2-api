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

import bancobbb2.api.model.ContaCorrente;
import bancobbb2.api.repository.ContaCorrenteRepository;

@RestController
@RequestMapping("/contaCorrente")
public class ContaCorrenteController {

    @Autowired
    private ContaCorrenteRepository contaCorrenteRepository;

    //Para cadastrar uma conta corrente:
    @PostMapping
    public ResponseEntity<ContaCorrente> cadastrarContaCorrente(@RequestBody ContaCorrente contaCorrente) {
        return ResponseEntity.status(HttpStatus.CREATED)
        .body(contaCorrenteRepository.save(contaCorrente));
    }

    //Para exibir lista de todas as contas cadastradas:
    @GetMapping
    public ResponseEntity<List<ContaCorrente>> exibirListaDeContas() {
        return ResponseEntity.status(HttpStatus.OK)
        .body(contaCorrenteRepository.findAll());
    }

    //Para exibir conta corrente pelo id:
    @GetMapping("/{idCc}")
    public ResponseEntity<Optional<ContaCorrente>> buscarContaCorrentePeloId(@PathVariable("idCc") Long idCc) {
        return ResponseEntity.status(HttpStatus.OK)
        .body(contaCorrenteRepository.findById(idCc));
    }

    
}
