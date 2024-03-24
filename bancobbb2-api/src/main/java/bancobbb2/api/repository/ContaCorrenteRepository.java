package bancobbb2.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bancobbb2.api.model.ContaCorrente;

@Repository
public interface ContaCorrenteRepository extends JpaRepository<ContaCorrente, Long> {
    
}
