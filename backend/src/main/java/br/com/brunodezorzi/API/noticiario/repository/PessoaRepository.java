package br.com.brunodezorzi.API.noticiario.repository;

import br.com.brunodezorzi.API.noticiario.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {}
