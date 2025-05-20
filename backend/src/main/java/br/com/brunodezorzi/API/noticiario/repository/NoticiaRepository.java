package br.com.brunodezorzi.api.noticiario.repository;

import br.com.brunodezorzi.api.noticiario.model.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticiaRepository extends JpaRepository<Noticia, Long> {}
