package br.com.brunodezorzi.api.noticiario.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;

import br.com.brunodezorzi.api.noticiario.model.Autor;
import br.com.brunodezorzi.api.noticiario.model.Categoria;
import br.com.brunodezorzi.api.noticiario.model.Noticia;
import br.com.brunodezorzi.api.noticiario.model.Pessoa;
import br.com.brunodezorzi.api.noticiario.model.Posicao;
import br.com.brunodezorzi.api.noticiario.model.Publicidade;
import br.com.brunodezorzi.api.noticiario.model.Usuario;
import br.com.brunodezorzi.api.noticiario.repository.AutorRepository;
import br.com.brunodezorzi.api.noticiario.repository.CategoriaRepository;
import br.com.brunodezorzi.api.noticiario.repository.NoticiaRepository;
import br.com.brunodezorzi.api.noticiario.repository.PessoaRepository;
import br.com.brunodezorzi.api.noticiario.repository.PosicaoRepository;
import br.com.brunodezorzi.api.noticiario.repository.PublicidadeRepository;
import br.com.brunodezorzi.api.noticiario.repository.UsuarioRepository;

@Component
public class DataFixture implements CommandLineRunner {

    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    NoticiaRepository noticiaRepository;

    @Autowired
    PessoaRepository pessoaRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    AutorRepository autorRepository;

    @Autowired
    PosicaoRepository posicaoRepository;

    @Autowired
    PublicidadeRepository publicidadeRepository;

    @Override
    public void run(String... args) throws Exception {
        
        // ==== CATEGORIAS PRINCIPAIS ====
        
        // Categoria: Esportes
        Categoria esportes = new Categoria();
        esportes.setNome("Esportes");
        esportes.setDescricao("Notícias sobre esportes em geral");
        esportes.setDestaque(true);
        categoriaRepository.save(esportes);

        // Subcategorias de Esportes
        categoriaRepository.save(new Categoria("Futebol", "Notícias sobre futebol", esportes, false));
        categoriaRepository.save(new Categoria("Basquete", "Notícias sobre basquete", esportes, false));
        categoriaRepository.save(new Categoria("Vôlei", "Notícias sobre vôlei", esportes, false));

        // Categoria: Lazer e Cultura
        Categoria lazerCultura = new Categoria();
        lazerCultura.setNome("Lazer e Cultura");
        lazerCultura.setDescricao("Entretenimento e cultura");
        lazerCultura.setDestaque(true);
        categoriaRepository.save(lazerCultura);

        // Subcategorias de Lazer e Cultura
        categoriaRepository.save(new Categoria("Cinema", "Notícias sobre cinema", lazerCultura, false));
        categoriaRepository.save(new Categoria("Teatro", "Notícias sobre teatro", lazerCultura, false));
        categoriaRepository.save(new Categoria("Música", "Notícias sobre música", lazerCultura, false));

        // Categoria: Política
        Categoria politica = new Categoria();
        politica.setNome("Política");
        politica.setDescricao("Notícias políticas nacionais");
        politica.setDestaque(true);
        categoriaRepository.save(politica);

        // Subcategorias de Política
        categoriaRepository.save(new Categoria("Governo", "Notícias do governo", politica, false));
        categoriaRepository.save(new Categoria("Eleições", "Notícias eleitorais", politica, false));
        categoriaRepository.save(new Categoria("Congresso", "Notícias do congresso", politica, false));

        // Categoria: Internacional
        Categoria internacional = new Categoria();
        internacional.setNome("Internacional");
        internacional.setDescricao("Notícias internacionais");
        internacional.setDestaque(true);
        categoriaRepository.save(internacional);

        // Subcategorias de Internacional
        categoriaRepository.save(new Categoria("Diplomacia", "Notícias diplomáticas", internacional, false));
        categoriaRepository.save(new Categoria("Conflitos", "Conflitos internacionais", internacional, false));
        categoriaRepository.save(new Categoria("Eventos Globais", "Eventos mundiais", internacional, false));

        // Categoria: Economia
        Categoria economia = new Categoria();
        economia.setNome("Economia");
        economia.setDescricao("Notícias econômicas");
        economia.setDestaque(true);
        categoriaRepository.save(economia);

        // Subcategorias de Economia
        categoriaRepository.save(new Categoria("Mercado Financeiro", "Notícias do mercado", economia, false));
        categoriaRepository.save(new Categoria("Emprego", "Notícias sobre emprego", economia, false));
        categoriaRepository.save(new Categoria("Inflação", "Notícias sobre inflação", economia, false));

        // ==== PESSOAS ====
        
        // Pessoas para Usuários
        Pessoa pessoaJoao = new Pessoa();
        pessoaJoao.setNome("João Silva");
        pessoaJoao.setEmail("joao@email.com");
        pessoaRepository.save(pessoaJoao);

        Pessoa pessoaMaria = new Pessoa();
        pessoaMaria.setNome("Maria Oliveira");
        pessoaMaria.setEmail("maria@email.com");
        pessoaRepository.save(pessoaMaria);

        Pessoa pessoaCarlos = new Pessoa();
        pessoaCarlos.setNome("Carlos Souza");
        pessoaCarlos.setEmail("carlos@email.com");
        pessoaRepository.save(pessoaCarlos);

        // Pessoas para Autores
        Pessoa pessoaFernanda = new Pessoa();
        pessoaFernanda.setNome("Fernanda Costa");
        pessoaFernanda.setEmail("fernanda@email.com");
        pessoaRepository.save(pessoaFernanda);

        Pessoa pessoaRicardo = new Pessoa();
        pessoaRicardo.setNome("Ricardo Lima");
        pessoaRicardo.setEmail("ricardo@email.com");
        pessoaRepository.save(pessoaRicardo);

        Pessoa pessoaAna = new Pessoa();
        pessoaAna.setNome("Ana Beatriz");
        pessoaAna.setEmail("ana@email.com");
        pessoaRepository.save(pessoaAna);

        Pessoa pessoaMarcos = new Pessoa();
        pessoaMarcos.setNome("Marcos Pereira");
        pessoaMarcos.setEmail("marcos@email.com");
        pessoaRepository.save(pessoaMarcos);

        Pessoa pessoaLucia = new Pessoa();
        pessoaLucia.setNome("Lúcia Mendes");
        pessoaLucia.setEmail("lucia@email.com");
        pessoaRepository.save(pessoaLucia);

        // ==== USUÁRIOS ====
        
        Usuario usuario1 = new Usuario();
        usuario1.setLogin("joaos");
        usuario1.setSenha("123456");
        usuario1.setPessoa(pessoaJoao);
        usuarioRepository.save(usuario1);

        Usuario usuario2 = new Usuario();
        usuario2.setLogin("mariao");
        usuario2.setSenha("senha123");
        usuario2.setPessoa(pessoaMaria);
        usuarioRepository.save(usuario2);

        Usuario usuario3 = new Usuario();
        usuario3.setLogin("carloss");
        usuario3.setSenha("abc123");
        usuario3.setPessoa(pessoaCarlos);
        usuarioRepository.save(usuario3);

        // ==== AUTORES ====
        
        Autor autor1 = new Autor();
        autor1.setBiografia("Jornalista premiado em geopolítica.");
        autor1.setPessoa(pessoaFernanda);
        autorRepository.save(autor1);

        Autor autor2 = new Autor();
        autor2.setBiografia("Especialista em economia internacional.");
        autor2.setPessoa(pessoaRicardo);
        autorRepository.save(autor2);

        Autor autor3 = new Autor();
        autor3.setBiografia("Escreve sobre esportes e lazer há 10 anos.");
        autor3.setPessoa(pessoaAna);
        autorRepository.save(autor3);

        Autor autor4 = new Autor();
        autor4.setBiografia("Analista político com 15 anos de experiência.");
        autor4.setPessoa(pessoaMarcos);
        autorRepository.save(autor4);

        Autor autor5 = new Autor();
        autor5.setBiografia("Repórter internacional com vasta experiência.");
        autor5.setPessoa(pessoaLucia);
        autorRepository.save(autor5);

        // ==== POSIÇÕES PARA PUBLICIDADE ====
        
        Posicao posicaoTopo = new Posicao("Topo da Página");
        posicaoRepository.save(posicaoTopo);

        Posicao posicaoLateral = new Posicao("Lateral Direita");
        posicaoRepository.save(posicaoLateral);

        Posicao posicaoRodape = new Posicao("Rodapé");
        posicaoRepository.save(posicaoRodape);

        Posicao posicaoMeio = new Posicao("Meio do Conteúdo");
        posicaoRepository.save(posicaoMeio);

        // ==== NOTÍCIAS ====
        
        Lorem lorem = LoremIpsum.getInstance();

        // Notícias com conteúdo completo
        Noticia noticiaEsportes = new Noticia();
        noticiaEsportes.setTitulo(lorem.getTitle(3));
        noticiaEsportes.setCorpo(lorem.getParagraphs(3, 5));
        noticiaEsportes.setDataPublicacao(LocalDate.now());
        noticiaEsportes.setImagem_url("https://picsum.photos/800/400?random=1");
        noticiaEsportes.setCategoria(esportes);
        noticiaEsportes.setAutor(autor3);
        noticiaEsportes.setDestaque(true);
        noticiaRepository.save(noticiaEsportes);

        Noticia noticiaLazer = new Noticia();
        noticiaLazer.setTitulo(lorem.getTitle(3));
        noticiaLazer.setCorpo(lorem.getParagraphs(3, 5));
        noticiaLazer.setDataPublicacao(LocalDate.now().minusDays(1));
        noticiaLazer.setImagem_url("https://picsum.photos/800/400?random=2");
        noticiaLazer.setCategoria(lazerCultura);
        noticiaLazer.setAutor(autor3);
        noticiaLazer.setDestaque(false);
        noticiaRepository.save(noticiaLazer);

        Noticia noticiaPolitica = new Noticia();
        noticiaPolitica.setTitulo(lorem.getTitle(3));
        noticiaPolitica.setCorpo(lorem.getParagraphs(3, 5));
        noticiaPolitica.setDataPublicacao(LocalDate.now().minusDays(2));
        noticiaPolitica.setImagem_url("https://picsum.photos/800/400?random=3");
        noticiaPolitica.setCategoria(politica);
        noticiaPolitica.setAutor(autor4);
        noticiaPolitica.setDestaque(true);
        noticiaRepository.save(noticiaPolitica);

        Noticia noticiaInternacional = new Noticia();
        noticiaInternacional.setTitulo(lorem.getTitle(3));
        noticiaInternacional.setCorpo(lorem.getParagraphs(3, 5));
        noticiaInternacional.setDataPublicacao(LocalDate.now().minusDays(3));
        noticiaInternacional.setImagem_url("https://picsum.photos/800/400?random=4");
        noticiaInternacional.setCategoria(internacional);
        noticiaInternacional.setAutor(autor5);
        noticiaInternacional.setDestaque(false);
        noticiaRepository.save(noticiaInternacional);

        Noticia noticiaEconomia = new Noticia();
        noticiaEconomia.setTitulo(lorem.getTitle(3));
        noticiaEconomia.setCorpo(lorem.getParagraphs(3, 5));
        noticiaEconomia.setDataPublicacao(LocalDate.now().minusDays(4));
        noticiaEconomia.setImagem_url("https://picsum.photos/800/400?random=5");
        noticiaEconomia.setCategoria(economia);
        noticiaEconomia.setAutor(autor2);
        noticiaEconomia.setDestaque(true);
        noticiaRepository.save(noticiaEconomia);

        // ==== PUBLICIDADES ====
        
        Publicidade pub1 = new Publicidade(
            "Anúncio Esportivo - Equipamentos de Futebol",
            "https://picsum.photos/728/90?random=ad1",
            "https://exemplo.com/equipamentos-futebol",
            LocalDate.now().minusDays(10),
            LocalDate.now().plusDays(20),
            true,
            posicaoTopo,
            esportes
        );
        publicidadeRepository.save(pub1);

        Publicidade pub2 = new Publicidade(
            "Festival de Cinema 2025",
            "https://picsum.photos/300/250?random=ad2",
            "https://exemplo.com/festival-cinema",
            LocalDate.now().minusDays(5),
            LocalDate.now().plusDays(30),
            true,
            posicaoLateral,
            lazerCultura
        );
        publicidadeRepository.save(pub2);

        Publicidade pub3 = new Publicidade(
            "Consultoria Econômica - Investimentos",
            "https://picsum.photos/728/90?random=ad3",
            "https://exemplo.com/consultoria-economica",
            LocalDate.now(),
            LocalDate.now().plusDays(15),
            true,
            posicaoMeio,
            economia
        );
        publicidadeRepository.save(pub3);

        Publicidade pub4 = new Publicidade(
            "Curso de Política Internacional",
            "https://picsum.photos/300/250?random=ad4",
            "https://exemplo.com/curso-politica",
            LocalDate.now().minusDays(2),
            LocalDate.now().plusDays(25),
            false, // Anúncio inativo
            posicaoRodape,
            internacional
        );
        publicidadeRepository.save(pub4);

        System.out.println("✅ Data fixture executado com sucesso!");
        System.out.println("📊 Dados criados:");
        System.out.println("   - Categorias: " + categoriaRepository.count());
        System.out.println("   - Pessoas: " + pessoaRepository.count());
        System.out.println("   - Usuários: " + usuarioRepository.count());
        System.out.println("   - Autores: " + autorRepository.count());
        System.out.println("   - Notícias: " + noticiaRepository.count());
        System.out.println("   - Posições: " + posicaoRepository.count());
        System.out.println("   - Publicidades: " + publicidadeRepository.count());
    }
}