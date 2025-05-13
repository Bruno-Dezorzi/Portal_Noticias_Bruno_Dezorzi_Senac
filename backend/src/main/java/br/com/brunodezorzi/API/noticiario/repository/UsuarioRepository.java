package br.com.brunodezorzi.API.noticiario.repository;

import br.com.brunodezorzi.API.noticiario.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {}
