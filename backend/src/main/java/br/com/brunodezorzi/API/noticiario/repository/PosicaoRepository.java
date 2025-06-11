package br.com.brunodezorzi.api.noticiario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.brunodezorzi.api.noticiario.model.Posicao;

public interface PosicaoRepository extends JpaRepository<Posicao, Integer> {}
