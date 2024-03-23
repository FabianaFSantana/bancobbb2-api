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

    //Para acessar funcionário pelo id
    @GetMapping("/{idFuncionario}")
    public ResponseEntity<Optional<Funcionario>> buscarFuncionarioPeloId(@PathVariable("idFuncionario") Long idFuncionario) {
        return ResponseEntity.status(HttpStatus.OK)
        .body(funcionarioRepository.findById(idFuncionario));
    }

    //Para acessar funcionário pelo cpf
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Funcionario> buscarFuncionarioPeloCpf(@PathVariable("cpf") String cpf) {
        Optional<Funcionario> funcionarioOptional = funcionarioRepository.findByPessoaCpf(cpf);
        
        if (funcionarioOptional.isPresent()) {

            Funcionario funcionario = funcionarioOptional.get();

            return ResponseEntity.status(HttpStatus.OK)
            .body(funcionario);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();      
        
    }

     //Para atualizar funcionário:
    @PutMapping("/{idFuncionario}")
    public ResponseEntity<Funcionario> atualizarFuncionario(@PathVariable("idFuncionario") Long idFuncionario, @RequestBody Funcionario funcionario) {
        Optional<Funcionario> funcionarOptional = funcionarioRepository.findById(idFuncionario);

        if (funcionarOptional.isPresent()) {
            Funcionario funcionarioAtual = funcionarOptional.get();

            funcionarioAtual.getPessoaFuncionario().setNome(funcionario.getPessoaFuncionario().getNome());
            funcionarioAtual.getPessoaFuncionario().setSobrenome(funcionario.getPessoaFuncionario().getSobrenome());
            funcionarioAtual.getPessoaFuncionario().setDataDeNascimento(funcionario.getPessoaFuncionario().getDataDeNascimento());
            funcionarioAtual.getPessoaFuncionario().setCpf(funcionario.getPessoaFuncionario().getCpf());
            funcionarioAtual.getPessoaFuncionario().setRg(funcionario.getPessoaFuncionario().getRg());
            funcionarioAtual.getPessoaFuncionario().setEmail(funcionario.getPessoaFuncionario().getEmail());
            funcionarioAtual.getEnderecoFuncionario().setCep(funcionario.getEnderecoFuncionario().getCep());
            funcionarioAtual.getEnderecoFuncionario().setLogradouro(funcionario.getEnderecoFuncionario().getLogradouro());
            funcionarioAtual.getEnderecoFuncionario().setBairro(funcionario.getEnderecoFuncionario().getBairro());
            funcionarioAtual.getEnderecoFuncionario().setCidade(funcionario.getEnderecoFuncionario().getCidade());
            funcionarioAtual.getEnderecoFuncionario().setUf(funcionario.getEnderecoFuncionario().getUf());
            funcionarioAtual.getEnderecoFuncionario().setDdd(funcionario.getEnderecoFuncionario().getDdd());
            funcionarioAtual.getEnderecoFuncionario().setTelefone(funcionario.getEnderecoFuncionario().getTelefone());
            funcionarioAtual.getEnderecoFuncionario().setCelular(funcionario.getEnderecoFuncionario().getCelular());
            funcionarioAtual.setSetor(funcionario.getSetor());
            funcionarioAtual.setFuncao(funcionario.getFuncao());

            return ResponseEntity.status(HttpStatus.OK)
            .body(funcionarioRepository.save(funcionarioAtual));
            
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    //Para excluir um funcionário
    @DeleteMapping("/{idFuncionario}")
    public ResponseEntity<String> deletarFuncionario(@PathVariable("idFuncionario") Long idFuncioario) {
        funcionarioRepository.deleteById(idFuncioario);
        
        return ResponseEntity.status(HttpStatus.OK)
        .body("Funcionário excluído com sucesso!");
    }


    
}
