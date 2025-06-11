package br.com.brunodezorzi.api.noticiario.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.brunodezorzi.api.noticiario.model.Publicidade;
import br.com.brunodezorzi.api.noticiario.model.Posicao;
import br.com.brunodezorzi.api.noticiario.model.Categoria;
import br.com.brunodezorzi.api.noticiario.repository.PublicidadeRepository;
import br.com.brunodezorzi.api.noticiario.repository.PosicaoRepository;
import br.com.brunodezorzi.api.noticiario.repository.CategoriaRepository;

@Service
@Transactional
public class PublicidadeService {

    @Autowired
    private PublicidadeRepository publicidadeRepository;
    
    @Autowired
    private PosicaoRepository posicaoRepository;
    
    @Autowired
    private CategoriaRepository categoriaRepository;

    // ===== MÉTODOS BÁSICOS CRUD =====
    
    public List<Publicidade> buscarTodas() {
        return publicidadeRepository.findAll();
    }
    
    public Optional<Publicidade> buscarPorId(Integer id) {
        return publicidadeRepository.findById(id);
    }
    
    public Publicidade salvar(Publicidade publicidade) {
        if (publicidade.getId() == null) {
            // Nova publicidade - definir valores padrão
            if (publicidade.getVisualizacoes() == null) {
                publicidade.setVisualizacoes(0);
            }
            if (publicidade.getClicks() == null) {
                publicidade.setClicks(0);
            }
            if (publicidade.getPrioridade() == null) {
                publicidade.setPrioridade(1);
            }
            if (publicidade.getTipoMidia() == null) {
                publicidade.setTipoMidia("IMAGE");
            }
        }
        return publicidadeRepository.save(publicidade);
    }
    
    public void deletar(Integer id) {
        publicidadeRepository.deleteById(id);
    }
    
    public boolean existePorId(Integer id) {
        return publicidadeRepository.existsById(id);
    }

    // ===== MÉTODOS DE NEGÓCIO =====
    
    /**
     * Busca anúncios ativos e vigentes para exibição
     */
    public List<Publicidade> buscarParaExibicao() {
        return publicidadeRepository.buscarAtivasEVigentes(LocalDate.now());
    }
    
    /**
     * Busca anúncios para uma posição específica
     */
    public List<Publicidade> buscarPorPosicao(Integer posicaoId) {
        Optional<Posicao> posicao = posicaoRepository.findById(posicaoId);
        if (posicao.isPresent()) {
            return publicidadeRepository.buscarPorPosicaoAtivasEVigentes(
                posicao.get(), LocalDate.now());
        }
        return List.of();
    }
    
    /**
     * Busca anúncios para uma categoria específica
     */
    public List<Publicidade> buscarPorCategoria(Integer categoriaId) {
        Optional<Categoria> categoria = categoriaRepository.findById(categoriaId);
        if (categoria.isPresent()) {
            return publicidadeRepository.buscarPorCategoriaAtivasEVigentes(
                categoria.get(), LocalDate.now());
        }
        return List.of();
    }
    
    /**
     * Busca anúncios para múltiplas categorias
     */
    public List<Publicidade> buscarPorCategorias(List<Integer> categoriaIds) {
        List<Categoria> categorias = categoriaRepository.findAllById(categoriaIds);
        if (!categorias.isEmpty()) {
            return publicidadeRepository.buscarPorCategoriasAtivasEVigentes(
                categorias, LocalDate.now());
        }
        return List.of();
    }
    
    /**
     * Busca o primeiro anúncio disponível para uma posição
     */
    public Optional<Publicidade> buscarPrimeiroPorPosicao(Integer posicaoId) {
        Optional<Posicao> posicao = posicaoRepository.findById(posicaoId);
        if (posicao.isPresent()) {
            return publicidadeRepository.buscarPrimeiraPorPosicaoAtivaEVigente(
                posicao.get(), LocalDate.now());
        }
        return Optional.empty();
    }
    
    /**
     * Registra uma visualização do anúncio
     */
    public void registrarVisualizacao(Integer publicidadeId) {
        Optional<Publicidade> publicidade = publicidadeRepository.findById(publicidadeId);
        if (publicidade.isPresent()) {
            Publicidade p = publicidade.get();
            p.incrementarVisualizacoes();
            publicidadeRepository.save(p);
        }
    }
    
    /**
     * Registra um click no anúncio
     */
    public void registrarClick(Integer publicidadeId) {
        Optional<Publicidade> publicidade = publicidadeRepository.findById(publicidadeId);
        if (publicidade.isPresent()) {
            Publicidade p = publicidade.get();
            p.incrementarClicks();
            publicidadeRepository.save(p);
        }
    }
    
    /**
     * Ativa ou desativa uma publicidade
     */
    public boolean alterarStatus(Integer publicidadeId, boolean ativo) {
        Optional<Publicidade> publicidade = publicidadeRepository.findById(publicidadeId);
        if (publicidade.isPresent()) {
            Publicidade p = publicidade.get();
            p.setAtivo(ativo);
            publicidadeRepository.save(p);
            return true;
        }
        return false;
    }
    
    /**
     * Busca anúncios que expiram nos próximos dias
     */
    public List<Publicidade> buscarQueExpiramEm(int dias) {
        LocalDate hoje = LocalDate.now();
        LocalDate dataLimite = hoje.plusDays(dias);
        return publicidadeRepository.buscarQueExpiramEntre(hoje, dataLimite);
    }
    
    /**
     * Busca anúncios por tipo de mídia
     */
    public List<Publicidade> buscarPorTipoMidia(String tipoMidia) {
        return publicidadeRepository.buscarPorTipoMidia(tipoMidia, LocalDate.now());
    }
    
    /**
     * Busca anúncios mais visualizados
     */
    public List<Publicidade> buscarMaisVisualizados(int limite) {
        Pageable pageable = PageRequest.of(0, limite);
        return publicidadeRepository.buscarMaisVisualizados();
    }
    
    /**
     * Busca anúncios mais clicados
     */
    public List<Publicidade> buscarMaisClicados(int limite) {
        Pageable pageable = PageRequest.of(0, limite);
        return publicidadeRepository.buscarMaisClicados();
    }
    
    /**
     * Conta anúncios ativos por posição
     */
    public Long contarAtivosPorPosicao(Integer posicaoId) {
        Optional<Posicao> posicao = posicaoRepository.findById(posicaoId);
        if (posicao.isPresent()) {
            return publicidadeRepository.contarAtivasPorPosicao(posicao.get(), LocalDate.now());
        }
        return 0L;
    }
    
    /**
     * Gera relatório de performance por posição
     */
    public List<Object[]> gerarRelatorioPerformance() {
        return publicidadeRepository.relatorioPerformancePorPosicao();
    }
    
    /**
     * Valida se uma publicidade pode ser salva
     */
    public boolean validarPublicidade(Publicidade publicidade) {
        // Validações básicas
        if (publicidade.getTitulo() == null || publicidade.getTitulo().trim().isEmpty()) {
            return false;
        }
        
        if (publicidade.getDataInicio() == null || publicidade.getDataFim() == null) {
            return false;
        }
        
        if (publicidade.getDataInicio().isAfter(publicidade.getDataFim())) {
            return false;
        }
        
        if (publicidade.getPosicao() == null) {
            return false;
        }
        
        return true;
    }
    
    /**
     * Busca publicidades por período
     */
    public List<Publicidade> buscarPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        return publicidadeRepository.findByDataInicioLessThanEqualAndDataFimGreaterThanEqual(
            dataFim, dataInicio);
    }
    
    /**
     * Estende o período de uma publicidade
     */
    public boolean estenderPeriodo(Integer publicidadeId, LocalDate novaDataFim) {
        Optional<Publicidade> publicidade = publicidadeRepository.findById(publicidadeId);
        if (publicidade.isPresent()) {
            Publicidade p = publicidade.get();
            if (novaDataFim.isAfter(p.getDataFim())) {
                p.setDataFim(novaDataFim);
                publicidadeRepository.save(p);
                return true;
            }
        }
        return false;
    }
    
    /**
     * Altera a prioridade de uma publicidade
     */
    public boolean alterarPrioridade(Integer publicidadeId, Integer novaPrioridade) {
        Optional<Publicidade> publicidade = publicidadeRepository.findById(publicidadeId);
        if (publicidade.isPresent()) {
            Publicidade p = publicidade.get();
            p.setPrioridade(novaPrioridade);
            publicidadeRepository.save(p);
            return true;
        }
        return false;
    }
    
    /**
     * Calcula CTR (Click Through Rate) de uma publicidade
     */
    public double calcularCTR(Integer publicidadeId) {
        Optional<Publicidade> publicidade = publicidadeRepository.findById(publicidadeId);
        if (publicidade.isPresent()) {
            Publicidade p = publicidade.get();
            if (p.getVisualizacoes() > 0) {
                return (double) p.getClicks() / p.getVisualizacoes() * 100;
            }
        }
        return 0.0;
    }
    
    /**
     * Busca publicidades ativas ordenadas por prioridade
     */
    public List<Publicidade> buscarAtivasOrdenadas() {
        return publicidadeRepository.findByAtivoTrueOrderByPrioridadeDesc();
    }
    
    /**
     * Duplica uma publicidade existente
     */
    public Publicidade duplicarPublicidade(Integer publicidadeId) {
        Optional<Publicidade> original = publicidadeRepository.findById(publicidadeId);
        if (original.isPresent()) {
            Publicidade p = original.get();
            Publicidade nova = new Publicidade();
            
            nova.setTitulo(p.getTitulo() + " - Cópia");
            nova.setImagemUrl(p.getImagemUrl());
            nova.setLinkDestino(p.getLinkDestino());
            nova.setDataInicio(LocalDate.now());
            nova.setDataFim(p.getDataFim());
            nova.setAtivo(false); // Começa inativa
            nova.setPosicao(p.getPosicao());
            nova.setCategorias(p.getCategorias());
            nova.setTipoMidia(p.getTipoMidia());
            nova.setPrioridade(p.getPrioridade());
            nova.setVisualizacoes(0);
            nova.setClicks(0);
            
            return publicidadeRepository.save(nova);
        }
        return null;
    }
}