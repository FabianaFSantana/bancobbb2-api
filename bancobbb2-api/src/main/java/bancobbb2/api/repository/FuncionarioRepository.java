package bancobbb2.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import bancobbb2.api.model.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    @Query("SELECT f FROM funcionario f WHERE f.pessoaFuncionario.cpf = :cpf")
    Optional<Funcionario> findByPessoaCpf(@Param("cpf") String cpf);
    
}
