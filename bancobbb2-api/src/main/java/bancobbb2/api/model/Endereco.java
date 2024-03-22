package bancobbb2.api.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Endereco {
    
    private String cep;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String uf;
    private String ddd;
    private String telefone;
    private String celular;

}
