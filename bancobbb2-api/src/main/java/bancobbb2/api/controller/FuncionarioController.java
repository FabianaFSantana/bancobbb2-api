package bancobbb2.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bancobbb2.api.model.Funcionario;
import bancobbb2.api.repository.FuncionarioRepository;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;
    
    //Para cadastrar um funcionario
    @PostMapping
    public ResponseEntity<Funcionario> cadastrarFuncionario(@RequestBody Funcionario funcionario) {
        return ResponseEntity.status(HttpStatus.CREATED)
        .body(funcionarioRepository.save(funcionario));

    }

    //Para Acessar uma lista de funcionarios
    @GetMapping
    public ResponseEntity<List<Funcionario>> exibirListaDeFunconarios() {
        return ResponseEntity.status(HttpStatus.OK)
        .body(funcionarioRepository.findAll());
    }
    
}
