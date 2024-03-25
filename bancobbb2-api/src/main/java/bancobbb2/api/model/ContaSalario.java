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
@Entity(name = "contaSalario")

public class ContaSalario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCs;

    @Column(nullable = false, unique = true)
    private int numeroCs;

    @Column(nullable = false)
    private String senhaCs;

    @Column(nullable = false)
    private double saldoCs;
}
