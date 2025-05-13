package br.com.brunodezorzi.API.noticiario.repository;

import br.com.brunodezorzi.API.noticiario.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {}
