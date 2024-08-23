package br.com.security.domain.repository;

import br.com.security.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    Optional <Usuario> findByLogin(String login);
}
