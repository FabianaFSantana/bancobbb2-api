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

import bancobbb2.api.dto.DepositoDto;
import bancobbb2.api.model.ContaCorrente;
import bancobbb2.api.repository.ContaCorrenteRepository;
import bancobbb2.api.service.ContaCorrenteService;

@RestController
@RequestMapping("/contaCorrente")
public class ContaCorrenteController {

    @Autowired
    private ContaCorrenteRepository contaCorrenteRepository;

    @Autowired
    private ContaCorrenteService contaCorrenteService;

    @PostMapping
    public ResponseEntity<ContaCorrente> cadastrarContaCorrente(@RequestBody ContaCorrente contaCorrente) {
        return ResponseEntity.status(HttpStatus.CREATED)
        .body(contaCorrenteRepository.save(contaCorrente));
    }

    @PostMapping("/depositarValor/{idCc}")
    public ResponseEntity<String> depositarValorContaCorrente(@PathVariable("idCc") Long idCc,
    @RequestBody DepositoDto valorDto) {
       
        contaCorrenteService.depositarValorCc(idCc, valorDto);
        return ResponseEntity.status(HttpStatus.OK)
        .body("Valor depositado com sucesso!");
    }

    @GetMapping
    public ResponseEntity<List<ContaCorrente>> exibirListaDeContas() {
        return ResponseEntity.status(HttpStatus.OK)
        .body(contaCorrenteRepository.findAll());
    }

    @GetMapping("/{idCc}")
    public ResponseEntity<Optional<ContaCorrente>> buscarContaCorrentePeloId(@PathVariable("idCc") Long idCc) {
        return ResponseEntity.status(HttpStatus.OK)
        .body(contaCorrenteRepository.findById(idCc));
    }

    @GetMapping("/saldo/{idCc}")
    public ResponseEntity<Double> exibirSalcoCc(@PathVariable("idCc") Long idCc) {
        Double saldo = contaCorrenteService.exibirSaldo(idCc);
        return ResponseEntity.ok(saldo);
    }

    @PutMapping("/{idCc}")
    public ResponseEntity<ContaCorrente> atualizarDadosDaConta(@PathVariable("idCc") Long idCc,
    @RequestBody ContaCorrente contaCorrente) {
        Optional<ContaCorrente> contaOptional = contaCorrenteRepository.findById(idCc);

        if (contaOptional.isPresent()) {
            ContaCorrente contaCorrEncontrada = contaOptional.get();

            contaCorrEncontrada.setNumeroCc(contaCorrente.getNumeroCc());
            contaCorrEncontrada.setSenhaCc(contaCorrente.getSenhaCc());
            contaCorrEncontrada.setSaldoCc(contaCorrente.getSaldoCc());
            contaCorrEncontrada.setLimiteChequeEspecial(contaCorrente.getLimiteChequeEspecial());

            return ResponseEntity.status(HttpStatus.OK)
            .body(contaCorrenteRepository.save(contaCorrEncontrada));
            
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{idCc}")
    public ResponseEntity<String> cancelarContaCorrente(@PathVariable("idCc") Long idCc) {
        contaCorrenteRepository.deleteById(idCc);
        return ResponseEntity.status(HttpStatus.OK)
        .body("Conta Corrente cancelada.");
    }

    

    
}
