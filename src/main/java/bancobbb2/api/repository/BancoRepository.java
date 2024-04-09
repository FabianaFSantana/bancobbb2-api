package bancobbb2.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bancobbb2.api.model.Banco;

@Repository
public interface BancoRepository extends JpaRepository<Banco, Long> {
    
}
