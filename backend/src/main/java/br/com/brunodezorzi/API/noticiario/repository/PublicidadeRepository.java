package br.com.brunodezorzi.api.noticiario.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.brunodezorzi.api.noticiario.model.Categoria;
import br.com.brunodezorzi.api.noticiario.model.Posicao;
import br.com.brunodezorzi.api.noticiario.model.Publicidade;

@Repository
public interface PublicidadeRepository extends JpaRepository<Publicidade, Integer> {

    // ===== QUERY DERIVADAS =====
    
    List<Publicidade> findByPosicao_NomeIgnoreCase(String nome);

    // Buscar por status ativo
    List<Publicidade> findByAtivoTrue();
    List<Publicidade> findByAtivoFalse();
    List<Publicidade> findByAtivo(Boolean ativo);
    
    // Buscar por posição
    List<Publicidade> findByPosicao(Posicao posicao);
    List<Publicidade> findByPosicaoId(Integer posicaoId);
    
    // Buscar por categoria
    List<Publicidade> findByCategoriasContaining(Categoria categoria);
    
    // Buscar por data
    List<Publicidade> findByDataInicioLessThanEqualAndDataFimGreaterThanEqual(
        LocalDate dataInicio, LocalDate dataFim);
    
    // Buscar ativos ordenados por prioridade
    List<Publicidade> findByAtivoTrueOrderByPrioridadeDesc();
    
    // Buscar por posição e ativo, ordenado por prioridade
    List<Publicidade> findByPosicaoAndAtivoTrueOrderByPrioridadeDesc(Posicao posicao);

    // ===== QUERIES JPQL =====
    
    // Buscar anúncios ativos e vigentes
    @Query("SELECT p FROM Publicidade p WHERE p.ativo = true " +
           "AND :dataAtual BETWEEN p.dataInicio AND p.dataFim")
    List<Publicidade> buscarAtivasEVigentes(@Param("dataAtual") LocalDate dataAtual);
    
    // Buscar anúncios por posição, ativos e vigentes, ordenados por prioridade
    @Query("SELECT p FROM Publicidade p WHERE p.ativo = true " +
           "AND :dataAtual BETWEEN p.dataInicio AND p.dataFim " +
           "AND p.posicao = :posicao " +
           "ORDER BY p.prioridade DESC")
    List<Publicidade> buscarPorPosicaoAtivasEVigentes(
        @Param("posicao") Posicao posicao, 
        @Param("dataAtual") LocalDate dataAtual);
    
    // Buscar anúncios por categoria, ativos e vigentes
    @Query("SELECT p FROM Publicidade p WHERE p.categoria = :categoria AND p.ativa = true AND :data BETWEEN p.inicio AND p.fim")
       List<Publicidade> buscarPorCategoriaAtivasEVigentes(@Param("categoria") Categoria categoria, @Param("data") LocalDate data);
    
    // Buscar anúncios por múltiplas categorias
    @Query("SELECT DISTINCT p FROM Publicidade p JOIN p.categorias c " +
           "WHERE p.ativo = true " +
           "AND :dataAtual BETWEEN p.dataInicio AND p.dataFim " +
           "AND c IN :categorias " +
           "ORDER BY p.prioridade DESC")
    List<Publicidade> buscarPorCategoriasAtivasEVigentes(
        @Param("categorias") List<Categoria> categorias, 
        @Param("dataAtual") LocalDate dataAtual);
    
    // Buscar anúncios mais visualizados
    @Query("SELECT p FROM Publicidade p WHERE p.ativo = true " +
           "ORDER BY p.visualizacoes DESC")
    List<Publicidade> buscarMaisVisualizados();
    
    // Buscar anúncios com mais clicks
    @Query("SELECT p FROM Publicidade p WHERE p.ativo = true " +
           "ORDER BY p.clicks DESC")
    List<Publicidade> buscarMaisClicados();
    
    // Buscar anúncios por tipo de mídia
    @Query("SELECT p FROM Publicidade p WHERE p.ativo = true " +
           "AND p.tipoMidia = :tipoMidia " +
           "AND :dataAtual BETWEEN p.dataInicio AND p.dataFim " +
           "ORDER BY p.prioridade DESC")
    List<Publicidade> buscarPorTipoMidia(
        @Param("tipoMidia") String tipoMidia, 
        @Param("dataAtual") LocalDate dataAtual);

    // ===== QUERIES SQL NATIVAS =====
    
    // Buscar anúncios ativos e vigentes (SQL nativo)
    @Query(value = "SELECT * FROM publicidade p " +
                   "WHERE p.ativo = true " +
                   "AND CURRENT_DATE BETWEEN p.data_inicio AND p.data_fim " +
                   "ORDER BY p.prioridade DESC", 
           nativeQuery = true)
    List<Publicidade> buscarAtivasEVigentesSQL();
    
    // Relatório de performance por posição
    @Query(value = "SELECT pos.nome as posicao_nome, " +
                   "COUNT(p.id) as total_anuncios, " +
                   "SUM(p.visualizacoes) as total_visualizacoes, " +
                   "SUM(p.clicks) as total_clicks, " +
                   "CASE WHEN SUM(p.visualizacoes) > 0 " +
                   "THEN ROUND((SUM(p.clicks)::numeric / SUM(p.visualizacoes) * 100), 2) " +
                   "ELSE 0 END as ctr_percentual " +
                   "FROM publicidade p " +
                   "INNER JOIN posicao pos ON p.id_posicao = pos.id " +
                   "WHERE p.ativo = true " +
                   "GROUP BY pos.id, pos.nome " +
                   "ORDER BY total_clicks DESC", 
           nativeQuery = true)
    List<Object[]> relatorioPerformancePorPosicao();

    // ===== MÉTODOS ESPECÍFICOS =====
    
    // Buscar uma única publicidade ativa e vigente por posição
    @Query("SELECT p FROM Publicidade p WHERE p.ativo = true " +
           "AND :dataAtual BETWEEN p.dataInicio AND p.dataFim " +
           "AND p.posicao = :posicao " +
           "ORDER BY p.prioridade DESC")
    Optional<Publicidade> buscarPrimeiraPorPosicaoAtivaEVigente(
        @Param("posicao") Posicao posicao, 
        @Param("dataAtual") LocalDate dataAtual);
    
    // Contar anúncios ativos por posição
    @Query("SELECT COUNT(p) FROM Publicidade p WHERE p.ativo = true " +
           "AND :dataAtual BETWEEN p.dataInicio AND p.dataFim " +
           "AND p.posicao = :posicao")
    Long contarAtivasPorPosicao(
        @Param("posicao") Posicao posicao, 
        @Param("dataAtual") LocalDate dataAtual);
    
    // Buscar anúncios que expiram em X dias
    @Query("SELECT p FROM Publicidade p WHERE p.ativo = true " +
           "AND p.dataFim BETWEEN :dataInicio AND :dataFim")
    List<Publicidade> buscarQueExpiramEntre(
        @Param("dataInicio") LocalDate dataInicio, 
        @Param("dataFim") LocalDate dataFim);
}