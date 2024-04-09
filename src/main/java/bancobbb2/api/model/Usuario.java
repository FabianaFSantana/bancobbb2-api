package bancobbb2.api.model;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "usuario")

public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idUsuario;

    @Embedded
    private Pessoa pessoaUsuario;

    @Embedded
    private Endereco enderecoUsuario;

    @OneToOne
    private ContaCorrente contaCorrente;

    @OneToOne
    private ContaPoupanca contaPoupanca;

    @OneToOne
    private ContaSalario contaSalario;

}
