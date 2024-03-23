package bancobbb2.api.model;

import bancobbb2.api.constant.TipoDeConta;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoDeConta tipoDeConta;

}
