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
@Entity(name = "contaPoupanca")

public class ContaPoupanca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCp;

    @Column(nullable = false, unique = true)
    private int numeroCp;

    @Column(nullable = false)
    private String senhaCp;

    @Column(nullable = false)
    private double saldoCp;

}
