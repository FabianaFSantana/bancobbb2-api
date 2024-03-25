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

import bancobbb2.api.model.ContaSalario;
import bancobbb2.api.repository.ContaSalarioRepository;

@RestController
@RequestMapping("/contaSalario")
public class ContaSalarioController {

    @Autowired
    private ContaSalarioRepository contaSalarioRepository;

    @PostMapping
    public ResponseEntity<ContaSalario> criarContaSalario(@RequestBody ContaSalario contaSalario) {
        return ResponseEntity.status(HttpStatus.CREATED)
        .body(contaSalarioRepository.save(contaSalario));
    }

    @GetMapping
    public ResponseEntity<List<ContaSalario>> exibirListaDeContas() {
        return ResponseEntity.status(HttpStatus.OK)
        .body(contaSalarioRepository.findAll());
    }

    @GetMapping("/{idCs}")
    public ResponseEntity<Optional<ContaSalario>> buscarContaPeloId(@PathVariable("idCs") Long idCs) {
        return ResponseEntity.status(HttpStatus.OK)
        .body(contaSalarioRepository.findById(idCs));
    }

    @PutMapping("/{idCs}")
    public ResponseEntity<ContaSalario> atualizarDadosDaConta(@PathVariable("idCs") Long idCs,
    @RequestBody ContaSalario contaSalario) {

        Optional<ContaSalario> contaOptional = contaSalarioRepository.findById(idCs);
        
        if (contaOptional.isPresent()) {
            ContaSalario contaEncontrada = contaOptional.get();

            contaEncontrada.setNumeroCs(contaSalario.getNumeroCs());
            contaEncontrada.setSenhaCs(contaSalario.getSenhaCs());
            contaEncontrada.setSaldoCs(contaSalario.getSaldoCs());

            return ResponseEntity.status(HttpStatus.OK)
            .body(contaSalarioRepository.save(contaEncontrada));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{idCs}")
    public ResponseEntity<String> cancelarContaSalario(@PathVariable("idCs") Long idCs) {
        contaSalarioRepository.deleteById(idCs);
        return ResponseEntity.status(HttpStatus.OK)
        .body("Conta Salario cancelada com sucesso!");
    }


    
}
