package br.com.brunodezorzi.api.noticiario.repository;

import br.com.brunodezorzi.api.noticiario.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long> {}
