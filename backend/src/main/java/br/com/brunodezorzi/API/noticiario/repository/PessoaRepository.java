package br.com.brunodezorzi.api.noticiario.repository;

import br.com.brunodezorzi.api.noticiario.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {}
