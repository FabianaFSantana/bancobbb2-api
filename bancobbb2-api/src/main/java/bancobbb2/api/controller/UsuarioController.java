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

import bancobbb2.api.model.Usuario;
import bancobbb2.api.repository.UsuarioRepository;
import bancobbb2.api.service.ContaCorrenteService;
import bancobbb2.api.service.ContaPoupancaService;
import bancobbb2.api.service.ContaSalarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ContaCorrenteService contaCorrenteService;

    @Autowired
    private ContaPoupancaService contaPoupancaService;

    @Autowired
    private ContaSalarioService contaSalarioService;


    @PostMapping
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.status(HttpStatus.CREATED)
        .body(usuarioRepository.save(usuario));
    }

    @PostMapping("/{idUsuario}/associarUsuarioContaCorrente/{idCc}")
    public ResponseEntity<String> associarUsuarioContaCorrente(@PathVariable("idUsuario") Long idUsuario,
    @PathVariable("idCc") Long idCc) {

        try { //usado para envolver um bloco de código onde excessões podem ocorrer
            contaCorrenteService.associarContaCorrenteUsuario(idUsuario, idCc);
            return ResponseEntity.ok("Usuário associado à conta corrente!");
        } catch (Exception e) { //Para tratar a excessão
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Erro ao associar usuário à conta" + e.getMessage());
        }
    }

    @PostMapping("/{idUsuario}/associarUsuarioContaPoupanca/{idCp}")
    public ResponseEntity<String> associarUsuarioPoupanca(@PathVariable("idUsuario") Long idUsuario,
    @PathVariable("idCp") Long idCp) {

        try {
            contaPoupancaService.associarContaPoupancaUsuario(idUsuario, idCp);
            return ResponseEntity.status(HttpStatus.OK)
            .body("Usuário associado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Erro ao associar usuário à conta!" + e.getMessage());
        }

    }

    @PostMapping("/{idUsuario}/associarUsuarioContaSalario/{idCs}")
    public ResponseEntity<String> associarContaSalarioUsuario(@PathVariable("idUsuario") Long idUsuario,
    @PathVariable("idCs") Long idCs) {

        try {
            contaSalarioService.associarContaSalarioUsuario(idUsuario, idCs);
            return ResponseEntity.status(HttpStatus.OK)
            .body("Usuário associado à conta com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Erro ao associar usuário à conta!" + e.getMessage());
        }
    }



     @GetMapping
    public ResponseEntity<List<Usuario>> exibirListaDeUsuarios() {
        return ResponseEntity.status(HttpStatus.OK)
        .body(usuarioRepository.findAll());
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<Usuario> buscarUsuarioPeloId(@PathVariable("idUsuario") Long idUsuario) {
        Optional<Usuario> usuarOptional = usuarioRepository.findById(idUsuario);

        if (usuarOptional.isPresent()) {
            Usuario usuarioEncontrado = usuarOptional.get();
            return ResponseEntity.status(HttpStatus.OK)
            .body(usuarioEncontrado);
        } else {
            return ResponseEntity.notFound().build();
            
        }
    }

    @GetMapping("cpf/{cpf}")
    public ResponseEntity<Usuario> buscarUsuarioPeloCpf(@PathVariable("cpf") String cpf) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByPessoaUsuarioCpf(cpf);
        
        if (usuarioOptional.isPresent()) {
            Usuario usuarioEncontrado = usuarioOptional.get();

            return ResponseEntity.status(HttpStatus.OK)
            .body(usuarioEncontrado);
        } else {
            return null;
        }
    }

   

     @PutMapping("/{idUsuario}")
    public ResponseEntity<Usuario> atualizarDadosDoUsuario(@PathVariable("idUsuario") Long idUsuario,
    @RequestBody Usuario usuario) {
        Optional<Usuario> usuarOptional = usuarioRepository.findById(idUsuario);

        if (usuarOptional.isPresent()) {
            Usuario usuarioEncontrado = usuarOptional.get();

            usuarioEncontrado.getPessoaUsuario().setNome(usuario.getPessoaUsuario().getNome());
            usuarioEncontrado.getPessoaUsuario().setSobrenome(usuario.getPessoaUsuario().getSobrenome());
            usuarioEncontrado.getPessoaUsuario().setDataDeNascimento(usuario.getPessoaUsuario().getDataDeNascimento());
            usuarioEncontrado.getPessoaUsuario().setCpf(usuario.getPessoaUsuario().getCpf());
            usuarioEncontrado.getPessoaUsuario().setRg(usuario.getPessoaUsuario().getRg());
            usuarioEncontrado.getPessoaUsuario().setEmail(usuario.getPessoaUsuario().getEmail());
            usuarioEncontrado.getEnderecoUsuario().setCep(usuario.getEnderecoUsuario().getCep());
            usuarioEncontrado.getEnderecoUsuario().setLogradouro(usuario.getEnderecoUsuario().getLogradouro());
            usuarioEncontrado.getEnderecoUsuario().setBairro(usuario.getEnderecoUsuario().getBairro());
            usuarioEncontrado.getEnderecoUsuario().setCidade(usuario.getEnderecoUsuario().getCidade());
            usuarioEncontrado.getEnderecoUsuario().setUf(usuario.getEnderecoUsuario().getUf());
            usuarioEncontrado.getEnderecoUsuario().setDdd(usuario.getEnderecoUsuario().getDdd());
            usuarioEncontrado.getEnderecoUsuario().setTelefone(usuario.getEnderecoUsuario().getTelefone());
            usuarioEncontrado.getEnderecoUsuario().setCelular(usuario.getEnderecoUsuario().getCelular());
            
            return ResponseEntity.status(HttpStatus.OK)
            .body(usuarioRepository.save(usuarioEncontrado));
        } else {
            return ResponseEntity.notFound().build();
        }

    }

     @DeleteMapping("/{idUsuario}")
    public ResponseEntity<String> excluirUsuario(@PathVariable("idUsuario") Long idUsuario) {
        usuarioRepository.deleteById(idUsuario);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
        .body("Usuário excluído com sucesso");
    }





    
}
