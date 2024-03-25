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

    @PutMapping("/{idCp}")
    public ResponseEntity<ContaPoupanca> atualizarDadosDaConta(@PathVariable("idCp") Long idCp,
    @RequestBody ContaPoupanca contaPoupanca) {
        Optional<ContaPoupanca> contaOptional = contaPoupancaRepository.findById(idCp);

        if (contaOptional.isPresent()) {
            ContaPoupanca contaEncontrada = contaOptional.get();

            contaEncontrada.setNumeroCp(contaPoupanca.getNumeroCp());
            contaEncontrada.setSenhaCp(contaPoupanca.getSenhaCp());
            contaEncontrada.setSaldoCp(contaPoupanca.getSaldoCp());

            return ResponseEntity.status(HttpStatus.OK)
            .body(contaPoupancaRepository.save(contaEncontrada));
            
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/idCp")
    public ResponseEntity<String> cancelarContaPoupanca(@PathVariable("idCp") Long idCp) {
        contaPoupancaRepository.deleteById(idCp);
        return ResponseEntity.status(HttpStatus.OK)
        .body("Conta cancelada com sucesso!");
    }




    
}
