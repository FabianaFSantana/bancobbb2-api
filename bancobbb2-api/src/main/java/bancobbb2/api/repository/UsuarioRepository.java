package bancobbb2.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import bancobbb2.api.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("SELECT u FROM usuario u WHERE u.pessoaUsuario.cpf = :cpf")
    Optional<Usuario> findByPessoaUsuarioCpf(@Param("cpf") String cpf);
    
}
