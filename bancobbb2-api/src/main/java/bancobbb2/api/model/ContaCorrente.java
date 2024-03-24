package bancobbb2.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "contaCorrente")
public class ContaCorrente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCc;

    @Column(nullable = false, unique = true)
    private int numeroCc;

    @Column(nullable = false)
    private String senhaCc;

    @Column(nullable = false)
    private double saldoCc;

    @Column(nullable = false)
    private double limiteChequeEspecial;
}
