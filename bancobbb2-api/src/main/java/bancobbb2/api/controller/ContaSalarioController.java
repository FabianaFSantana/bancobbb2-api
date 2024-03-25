package bancobbb2.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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

    
    
}
