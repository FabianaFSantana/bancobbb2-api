package bancobbb2.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping
    public ResponseEntity<ContaCorrente> cadastrarContaCorrente(@RequestBody ContaCorrente contaCorrente) {
        return ResponseEntity.status(HttpStatus.CREATED)
        .body(contaCorrenteRepository.save(contaCorrente));
    }
    
}
