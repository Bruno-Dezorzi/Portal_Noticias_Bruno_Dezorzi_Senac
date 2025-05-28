package br.com.brunodezorzi.api.noticiario.service;

import br.com.brunodezorzi.api.noticiario.model.Autor;
import br.com.brunodezorzi.api.noticiario.model.Categoria;
import br.com.brunodezorzi.api.noticiario.model.Noticia;
import br.com.brunodezorzi.api.noticiario.model.Usuario;
import br.com.brunodezorzi.api.noticiario.repository.AutorRepository;
import br.com.brunodezorzi.api.noticiario.repository.CategoriaRepository;
import br.com.brunodezorzi.api.noticiario.repository.NoticiaRepository;
import br.com.brunodezorzi.api.noticiario.repository.UsuarioRepository;
import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataFixture implements CommandLineRunner {

  @Autowired
  CategoriaRepository categoriaRepository;

  @Autowired
  NoticiaRepository noticiaRepository;

  /*@Autowired
  PessoaRepository pessoaRepository;*/

  @Autowired
  UsuarioRepository usuarioRepository;

  @Autowired
  AutorRepository autorRepository;

  @Override
  public void run(String... args) throws Exception {
    // Categoria: Esportes
    Categoria esportes = new Categoria();
    esportes.setNome("Esportes");
    categoriaRepository.save(esportes);

    categoriaRepository.save(new Categoria("Futebol", esportes));
    categoriaRepository.save(new Categoria("Basquete", esportes));
    categoriaRepository.save(new Categoria("Vôlei", esportes));

    // Categoria: Lazer e Cultura
    Categoria lazerCultura = new Categoria();
    lazerCultura.setNome("Lazer e Cultura");
    categoriaRepository.save(lazerCultura);

    categoriaRepository.save(new Categoria("Cinema", lazerCultura));
    categoriaRepository.save(new Categoria("Teatro", lazerCultura));
    categoriaRepository.save(new Categoria("Música", lazerCultura));

    // Categoria: Política
    Categoria politica = new Categoria();
    politica.setNome("Política");
    categoriaRepository.save(politica);

    categoriaRepository.save(new Categoria("Governo", politica));
    categoriaRepository.save(new Categoria("Eleições", politica));
    categoriaRepository.save(new Categoria("Congresso", politica));

    // Categoria: Internacional
    Categoria internacional = new Categoria();
    internacional.setNome("Internacional");
    categoriaRepository.save(internacional);

    categoriaRepository.save(new Categoria("Diplomacia", internacional));
    categoriaRepository.save(new Categoria("Conflitos", internacional));
    categoriaRepository.save(new Categoria("Eventos Globais", internacional));

    // Categoria: Economia
    Categoria economia = new Categoria();
    economia.setNome("Economia");
    categoriaRepository.save(economia);

    categoriaRepository.save(new Categoria("Mercado Financeiro", economia));
    categoriaRepository.save(new Categoria("Emprego", economia));
    categoriaRepository.save(new Categoria("Inflação", economia));

    // ==== USUÁRIOS ====
    Usuario usuario1 = new Usuario(
      "João Silva",
      "joao@email.com",
      "joaos",
      "123456"
    );
    Usuario usuario2 = new Usuario(
      "Maria Oliveira",
      "maria@email.com",
      "mariao",
      "senha123"
    );
    Usuario usuario3 = new Usuario(
      "Carlos Souza",
      "carlos@email.com",
      "carloss",
      "abc123"
    );

    usuarioRepository.save(usuario1);
    usuarioRepository.save(usuario2);
    usuarioRepository.save(usuario3);

    // ==== AUTORES ====
    Autor autor1 = new Autor("Jornalista premiado em geopolítica.");
    autor1.setNome("Fernanda Costa");
    autor1.setEmail("fernanda@email.com");

    Autor autor2 = new Autor("Especialista em economia internacional.");
    autor2.setNome("Ricardo Lima");
    autor2.setEmail("ricardo@email.com");

    Autor autor3 = new Autor("Escreve sobre esportes e lazer há 10 anos.");
    autor3.setNome("Ana Beatriz");
    autor3.setEmail("ana@email.com");

    Autor autor4 = new Autor("Analista político com 15 anos de experiência.");
    autor4.setNome("Marcos Pereira");
    autor4.setEmail("marcos@email.com");

    Autor autor5 = new Autor("Repórter internacional com vasta experiência.");
    autor5.setNome("Lúcia Mendes");
    autor5.setEmail("lucia@email.com");

    autorRepository.save(autor1);
    autorRepository.save(autor2);
    autorRepository.save(autor3);
    autorRepository.save(autor4);
    autorRepository.save(autor5);

    Lorem lorem = LoremIpsum.getInstance();

    // Criar notícias usando o construtor com todos os campos
    Noticia noticiaEsportes = new Noticia(
      lorem.getTitle(3),
      LocalDate.now(),
      esportes,
      autor3 // Ana Beatriz, que escreve sobre esportes e lazer
    );
    noticiaRepository.save(noticiaEsportes);

    Noticia noticiaLazer = new Noticia(
      lorem.getTitle(3),
      LocalDate.now(),
      lazerCultura,
      autor3
    );
    noticiaRepository.save(noticiaLazer);

    Noticia noticiaPolitica = new Noticia(
      lorem.getTitle(3),
      LocalDate.now(),
      politica,
      autor4
    );
    noticiaRepository.save(noticiaPolitica);

    Noticia noticiaInternacional = new Noticia(
      lorem.getTitle(3),
      LocalDate.now(),
      internacional,
      autor5
    );
    noticiaRepository.save(noticiaInternacional);

    Noticia noticiaEconomia = new Noticia(
      lorem.getTitle(3),
      LocalDate.now(),
      economia,
      autor2 // Ricardo Lima, especialista em economia internacional
    );
    noticiaRepository.save(noticiaEconomia);
  }
}
