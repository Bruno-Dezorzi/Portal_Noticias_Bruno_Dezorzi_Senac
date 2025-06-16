package br.com.brunodezorzi.api.noticiario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.brunodezorzi.api.noticiario.model.Publicidade;

@Repository
public interface PublicidadeRepository extends JpaRepository<Publicidade, Integer> {
       List<Publicidade> findByPosicao_NomeIgnoreCase(String nome);
}