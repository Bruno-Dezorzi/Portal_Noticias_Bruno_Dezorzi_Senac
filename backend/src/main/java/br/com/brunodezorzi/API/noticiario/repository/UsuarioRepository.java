package br.com.brunodezorzi.api.noticiario.repository;

import br.com.brunodezorzi.api.noticiario.model.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
  Optional<Usuario> findByLoginAndSenha(String login, String senha);
}
