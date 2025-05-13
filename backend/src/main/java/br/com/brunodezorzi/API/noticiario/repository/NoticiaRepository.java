package br.com.brunodezorzi.API.noticiario.repository;

import br.com.brunodezorzi.API.noticiario.model.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticiaRepository extends JpaRepository<Noticia, Long> {}
