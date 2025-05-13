package br.com.brunodezorzi.API.noticiario.repository;

import br.com.brunodezorzi.API.noticiario.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long> {}
