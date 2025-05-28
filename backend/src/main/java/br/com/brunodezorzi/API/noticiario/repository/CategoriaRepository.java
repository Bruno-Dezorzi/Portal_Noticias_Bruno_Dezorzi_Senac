package br.com.brunodezorzi.api.noticiario.repository;

import br.com.brunodezorzi.api.noticiario.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository
  extends JpaRepository<Categoria, Integer> {}
