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
        esportes.setCategoriaPaiId(null);
        esportes = categoriaRepository.save(esportes);

        // Categoria: Lazer e Cultura
        Categoria lazerCultura = new Categoria();
        lazerCultura.setNome("Lazer e Cultura");
        lazerCultura.setDescricao("Entretenimento e cultura");
        lazerCultura.setDestaque(true);
        lazerCultura.setCategoriaPaiId(null);
        lazerCultura = categoriaRepository.save(lazerCultura);

        // Categoria: Política
        Categoria politica = new Categoria();
        politica.setNome("Política");
        politica.setDescricao("Notícias políticas nacionais");
        politica.setDestaque(true);
        politica.setCategoriaPaiId(null);
        politica = categoriaRepository.save(politica);

        // Categoria: Internacional
        Categoria internacional = new Categoria();
        internacional.setNome("Internacional");
        internacional.setDescricao("Notícias internacionais");
        internacional.setDestaque(true);
        internacional.setCategoriaPaiId(null);
        internacional = categoriaRepository.save(internacional);

        // Categoria: Economia
        Categoria economia = new Categoria();
        economia.setNome("Economia");
        economia.setDescricao("Notícias econômicas");
        economia.setDestaque(true);
        economia.setCategoriaPaiId(null);
        economia = categoriaRepository.save(economia);

        // ==== SUBCATEGORIAS ====

        // Subcategorias de Esportes
        Categoria futebol = new Categoria();
        futebol.setNome("Futebol");
        futebol.setDescricao("Notícias sobre futebol");
        futebol.setDestaque(false);
        futebol.setCategoriaPaiId(esportes.getId());
        categoriaRepository.save(futebol);

        Categoria basquete = new Categoria();
        basquete.setNome("Basquete");
        basquete.setDescricao("Notícias sobre basquete");
        basquete.setDestaque(false);
        basquete.setCategoriaPaiId(esportes.getId());
        categoriaRepository.save(basquete);

        Categoria volei = new Categoria();
        volei.setNome("Vôlei");
        volei.setDescricao("Notícias sobre vôlei");
        volei.setDestaque(false);
        volei.setCategoriaPaiId(esportes.getId());
        categoriaRepository.save(volei);

        // Subcategorias de Lazer e Cultura
        Categoria cinema = new Categoria();
        cinema.setNome("Cinema");
        cinema.setDescricao("Notícias sobre cinema");
        cinema.setDestaque(false);
        cinema.setCategoriaPaiId(lazerCultura.getId());
        categoriaRepository.save(cinema);

        Categoria teatro = new Categoria();
        teatro.setNome("Teatro");
        teatro.setDescricao("Notícias sobre teatro");
        teatro.setDestaque(false);
        teatro.setCategoriaPaiId(lazerCultura.getId());
        categoriaRepository.save(teatro);

        Categoria musica = new Categoria();
        musica.setNome("Música");
        musica.setDescricao("Notícias sobre música");
        musica.setDestaque(false);
        musica.setCategoriaPaiId(lazerCultura.getId());
        categoriaRepository.save(musica);

        // Subcategorias de Política
        Categoria governo = new Categoria();
        governo.setNome("Governo");
        governo.setDescricao("Notícias do governo");
        governo.setDestaque(false);
        governo.setCategoriaPaiId(politica.getId());
        categoriaRepository.save(governo);

        Categoria eleicoes = new Categoria();
        eleicoes.setNome("Eleições");
        eleicoes.setDescricao("Notícias eleitorais");
        eleicoes.setDestaque(false);
        eleicoes.setCategoriaPaiId(politica.getId());
        categoriaRepository.save(eleicoes);

        Categoria congresso = new Categoria();
        congresso.setNome("Congresso");
        congresso.setDescricao("Notícias do congresso");
        congresso.setDestaque(false);
        congresso.setCategoriaPaiId(politica.getId());
        categoriaRepository.save(congresso);

        // Subcategorias de Internacional
        Categoria diplomacia = new Categoria();
        diplomacia.setNome("Diplomacia");
        diplomacia.setDescricao("Notícias diplomáticas");
        diplomacia.setDestaque(false);
        diplomacia.setCategoriaPaiId(internacional.getId());
        categoriaRepository.save(diplomacia);

        Categoria conflitos = new Categoria();
        conflitos.setNome("Conflitos");
        conflitos.setDescricao("Conflitos internacionais");
        conflitos.setDestaque(false);
        conflitos.setCategoriaPaiId(internacional.getId());
        categoriaRepository.save(conflitos);

        Categoria eventosGlobais = new Categoria();
        eventosGlobais.setNome("Eventos Globais");
        eventosGlobais.setDescricao("Eventos mundiais");
        eventosGlobais.setDestaque(false);
        eventosGlobais.setCategoriaPaiId(internacional.getId());
        categoriaRepository.save(eventosGlobais);

        // Subcategorias de Economia
        Categoria mercadoFinanceiro = new Categoria();
        mercadoFinanceiro.setNome("Mercado Financeiro");
        mercadoFinanceiro.setDescricao("Notícias do mercado");
        mercadoFinanceiro.setDestaque(false);
        mercadoFinanceiro.setCategoriaPaiId(economia.getId());
        categoriaRepository.save(mercadoFinanceiro);

        Categoria emprego = new Categoria();
        emprego.setNome("Emprego");
        emprego.setDescricao("Notícias sobre emprego");
        emprego.setDestaque(false);
        emprego.setCategoriaPaiId(economia.getId());
        categoriaRepository.save(emprego);

        Categoria inflacao = new Categoria();
        inflacao.setNome("Inflação");
        inflacao.setDescricao("Notícias sobre inflação");
        inflacao.setDestaque(false);
        inflacao.setCategoriaPaiId(economia.getId());
        categoriaRepository.save(inflacao);

        // ==== PESSOAS ====
        
        // Pessoas para Usuários
        Pessoa pessoaJoao = new Pessoa();
        pessoaJoao.setNome("João Silva");
        pessoaJoao.setEmail("joao@email.com");
        pessoaJoao = pessoaRepository.save(pessoaJoao);

        Pessoa pessoaMaria = new Pessoa();
        pessoaMaria.setNome("Maria Oliveira");
        pessoaMaria.setEmail("maria@email.com");
        pessoaMaria = pessoaRepository.save(pessoaMaria);

        Pessoa pessoaCarlos = new Pessoa();
        pessoaCarlos.setNome("Carlos Souza");
        pessoaCarlos.setEmail("carlos@email.com");
        pessoaCarlos = pessoaRepository.save(pessoaCarlos);

        // Pessoas para Autores
        Pessoa pessoaFernanda = new Pessoa();
        pessoaFernanda.setNome("Fernanda Costa");
        pessoaFernanda.setEmail("fernanda@email.com");
        pessoaFernanda = pessoaRepository.save(pessoaFernanda);

        Pessoa pessoaRicardo = new Pessoa();
        pessoaRicardo.setNome("Ricardo Lima");
        pessoaRicardo.setEmail("ricardo@email.com");
        pessoaRicardo = pessoaRepository.save(pessoaRicardo);

        Pessoa pessoaAna = new Pessoa();
        pessoaAna.setNome("Ana Beatriz");
        pessoaAna.setEmail("ana@email.com");
        pessoaAna = pessoaRepository.save(pessoaAna);

        Pessoa pessoaMarcos = new Pessoa();
        pessoaMarcos.setNome("Marcos Pereira");
        pessoaMarcos.setEmail("marcos@email.com");
        pessoaMarcos = pessoaRepository.save(pessoaMarcos);

        Pessoa pessoaLucia = new Pessoa();
        pessoaLucia.setNome("Lúcia Mendes");
        pessoaLucia.setEmail("lucia@email.com");
        pessoaLucia = pessoaRepository.save(pessoaLucia);

        // ==== USUÁRIOS ====
        
        Usuario usuario1 = new Usuario();
        usuario1.setLogin("joaos");
        usuario1.setSenha("123456");
        usuario1.setPessoaId(pessoaJoao.getId());
        usuarioRepository.save(usuario1);

        Usuario usuario2 = new Usuario();
        usuario2.setLogin("mariao");
        usuario2.setSenha("senha123");
        usuario2.setPessoaId(pessoaMaria.getId());
        usuarioRepository.save(usuario2);

        Usuario usuario3 = new Usuario();
        usuario3.setLogin("carloss");
        usuario3.setSenha("abc123");
        usuario3.setPessoaId(pessoaCarlos.getId());
        usuarioRepository.save(usuario3);

        // ==== AUTORES ====
        
        Autor autor1 = new Autor();
        autor1.setBiografia("Jornalista premiado em geopolítica.");
        autor1.setPessoaId(pessoaFernanda.getId());
        autor1 = autorRepository.save(autor1);

        Autor autor2 = new Autor();
        autor2.setBiografia("Especialista em economia internacional.");
        autor2.setPessoaId(pessoaRicardo.getId());
        autor2 = autorRepository.save(autor2);

        Autor autor3 = new Autor();
        autor3.setBiografia("Escreve sobre esportes e lazer há 10 anos.");
        autor3.setPessoaId(pessoaAna.getId());
        autor3 = autorRepository.save(autor3);

        Autor autor4 = new Autor();
        autor4.setBiografia("Analista político com 15 anos de experiência.");
        autor4.setPessoaId(pessoaMarcos.getId());
        autor4 = autorRepository.save(autor4);

        Autor autor5 = new Autor();
        autor5.setBiografia("Repórter internacional com vasta experiência.");
        autor5.setPessoaId(pessoaLucia.getId());
        autor5 = autorRepository.save(autor5);

        // ==== POSIÇÕES PARA PUBLICIDADE ====
        
        Posicao posicaoTopo = new Posicao("Top");
        posicaoTopo = posicaoRepository.save(posicaoTopo);

        Posicao posicaoDireita = new Posicao("Right");
        posicaoDireita = posicaoRepository.save(posicaoDireita);

        Posicao posicaoRodape = new Posicao("Down");
        posicaoRodape = posicaoRepository.save(posicaoRodape);

        Posicao posicaoEsquerda = new Posicao("Left");
        posicaoEsquerda = posicaoRepository.save(posicaoEsquerda);

        // ==== NOTÍCIAS ====
        
        Lorem lorem = LoremIpsum.getInstance();

        // Notícias com conteúdo completo
        Noticia noticiaEsportes = new Noticia();
        noticiaEsportes.setTitulo(lorem.getTitle(3));
        noticiaEsportes.setCorpo(lorem.getParagraphs(3, 5));
        noticiaEsportes.setDataPublicacao(LocalDate.now());
        noticiaEsportes.setImagem_url("https://picsum.photos/800/400?random=1");
        noticiaEsportes.setCategoriaId(esportes.getId());
        noticiaEsportes.setAutorId(autor3.getId());
        noticiaEsportes.setDestaque(true);
        noticiaRepository.save(noticiaEsportes);

        Noticia noticiaLazer = new Noticia();
        noticiaLazer.setTitulo(lorem.getTitle(3));
        noticiaLazer.setCorpo(lorem.getParagraphs(3, 5));
        noticiaLazer.setDataPublicacao(LocalDate.now().minusDays(1));
        noticiaLazer.setImagem_url("https://picsum.photos/800/400?random=2");
        noticiaLazer.setCategoriaId(lazerCultura.getId());
        noticiaLazer.setAutorId(autor3.getId());
        noticiaLazer.setDestaque(false);
        noticiaRepository.save(noticiaLazer);

        Noticia noticiaPolitica = new Noticia();
        noticiaPolitica.setTitulo(lorem.getTitle(3));
        noticiaPolitica.setCorpo(lorem.getParagraphs(3, 5));
        noticiaPolitica.setDataPublicacao(LocalDate.now().minusDays(2));
        noticiaPolitica.setImagem_url("https://picsum.photos/800/400?random=3");
        noticiaPolitica.setCategoriaId(politica.getId());
        noticiaPolitica.setAutorId(autor4.getId());
        noticiaPolitica.setDestaque(true);
        noticiaRepository.save(noticiaPolitica);

        Noticia noticiaInternacional = new Noticia();
        noticiaInternacional.setTitulo(lorem.getTitle(3));
        noticiaInternacional.setCorpo(lorem.getParagraphs(3, 5));
        noticiaInternacional.setDataPublicacao(LocalDate.now().minusDays(3));
        noticiaInternacional.setImagem_url("https://picsum.photos/800/400?random=4");
        noticiaInternacional.setCategoriaId(internacional.getId());
        noticiaInternacional.setAutorId(autor5.getId());
        noticiaInternacional.setDestaque(false);
        noticiaRepository.save(noticiaInternacional);

        Noticia noticiaEconomia = new Noticia();
        noticiaEconomia.setTitulo(lorem.getTitle(3));
        noticiaEconomia.setCorpo(lorem.getParagraphs(3, 5));
        noticiaEconomia.setDataPublicacao(LocalDate.now().minusDays(4));
        noticiaEconomia.setImagem_url("https://picsum.photos/800/400?random=5");
        noticiaEconomia.setCategoriaId(economia.getId());
        noticiaEconomia.setAutorId(autor2.getId());
        noticiaEconomia.setDestaque(true);
        noticiaRepository.save(noticiaEconomia);

        // Notícias das subcategorias
        Noticia noticiaFutebol = new Noticia();
        noticiaFutebol.setTitulo(lorem.getTitle(3));
        noticiaFutebol.setCorpo(lorem.getParagraphs(2, 4));
        noticiaFutebol.setDataPublicacao(LocalDate.now().minusDays(1));
        noticiaFutebol.setImagem_url("https://picsum.photos/800/400?random=6");
        noticiaFutebol.setCategoriaId(futebol.getId());
        noticiaFutebol.setAutorId(autor3.getId());
        noticiaFutebol.setDestaque(false);
        noticiaRepository.save(noticiaFutebol);

        Noticia noticiaCinema = new Noticia();
        noticiaCinema.setTitulo(lorem.getTitle(3));
        noticiaCinema.setCorpo(lorem.getParagraphs(2, 4));
        noticiaCinema.setDataPublicacao(LocalDate.now().minusDays(2));
        noticiaCinema.setImagem_url("https://picsum.photos/800/400?random=7");
        noticiaCinema.setCategoriaId(cinema.getId());
        noticiaCinema.setAutorId(autor3.getId());
        noticiaCinema.setDestaque(true);
        noticiaRepository.save(noticiaCinema);

        // ==== PUBLICIDADES - 5 TOP E 5 DOWN ====
        
        // Array com as categorias para distribuir as publicidades
        Categoria[] categorias = {esportes, lazerCultura, politica, internacional, economia};
        
        // 5 Publicidades RIGHT
        for (int i = 1; i <= 10; i++) {
            Publicidade pubRight = new Publicidade();
            pubRight.setTitulo("Publicidade RIGHT " + i);
            pubRight.setImagemUrl("https://picsum.photos/728/90?random=right" + i);
            pubRight.setLinkDestino("https://exemplo.com/publicidade-right-" + i);
            pubRight.setDataInicio(LocalDate.now().minusDays(i));
            pubRight.setDataFim(LocalDate.now().plusDays(30 - i));
            pubRight.setAtivo(true);
            pubRight.setPrioridade(i <= 2); // As duas primeiras têm prioridade
            pubRight.setPosicaoId(posicaoDireita.getId());
            pubRight.setCategoriaId(categorias[(i - 1) % categorias.length].getId());
            publicidadeRepository.save(pubRight);
        }
        
        // 5 Publicidades LEFT
        for (int i = 1; i <= 10; i++) {
            Publicidade pubLeft = new Publicidade();
            pubLeft.setTitulo("Publicidade Left " + i);
            pubLeft.setImagemUrl("https://picsum.photos/728/90?random=left" + i);
            pubLeft.setLinkDestino("https://exemplo.com/publicidade-left-" + i);
            pubLeft.setDataInicio(LocalDate.now().minusDays(i + 2));
            pubLeft.setDataFim(LocalDate.now().plusDays(25 - i));
            pubLeft.setAtivo(true);
            pubLeft.setPrioridade(i <= 2); // As duas primeiras têm prioridade
            pubLeft.setPosicaoId(posicaoEsquerda.getId());
            pubLeft.setCategoriaId(categorias[(i - 1) % categorias.length].getId());
            publicidadeRepository.save(pubLeft);
        }

        System.out.println("✅ Data fixture executado com sucesso!");
        System.out.println("📊 Dados criados:");
        System.out.println("   - Categorias: " + categoriaRepository.count());
        System.out.println("   - Pessoas: " + pessoaRepository.count());
        System.out.println("   - Usuários: " + usuarioRepository.count());
        System.out.println("   - Autores: " + autorRepository.count());
        System.out.println("   - Notícias: " + noticiaRepository.count());
        System.out.println("   - Posições: " + posicaoRepository.count());
        System.out.println("   - Publicidades: " + publicidadeRepository.count());
        System.out.println("   - Publicidades LEFT: 10");
        System.out.println("   - Publicidades RIGHT: 10");
    }
}