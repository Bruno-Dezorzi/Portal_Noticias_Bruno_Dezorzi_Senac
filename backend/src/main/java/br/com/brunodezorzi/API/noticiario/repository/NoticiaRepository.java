package br.com.brunodezorzi.api.noticiario.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.brunodezorzi.api.noticiario.model.Noticia;

public interface NoticiaRepository extends JpaRepository<Noticia, Integer> {

    // Busca todas as notícias com destaque = true
    List<Noticia> findByDestaqueTrue();

    // Busca todas as notícias com destaque = false
    List<Noticia> findByDestaqueFalse();

    // Busca notícias com base no valor do destaque (true ou false)
    List<Noticia> findByDestaque(Boolean destaque);

    // Busca opcionalmente uma notícia com destaque = true
    Optional<Noticia> findFirstByDestaqueTrue();

    // Exemplo alternativo com JPQL (não necessário neste caso, mas aqui está para referência)
    @Query("SELECT n FROM Noticia n WHERE n.destaque = true")
    List<Noticia> buscarNoticiasEmDestaqueComQuery();

}
