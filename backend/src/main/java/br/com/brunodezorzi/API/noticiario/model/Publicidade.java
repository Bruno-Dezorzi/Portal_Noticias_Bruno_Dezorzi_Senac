package br.com.brunodezorzi.api.noticiario.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Publicidade", schema = "public")
public class Publicidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "imagemUrl")
    private String imagemUrl;

    @Column(name = "linkDestino")
    private String linkDestino;

    @Column(name = "dataInicio")
    private LocalDate dataInicio;

    @Column(name = "dataFim")
    private LocalDate dataFim;

    @Column(name = "ativo")
    private boolean ativo;

    // FIXED: Removed @Column annotation and kept only @ManyToOne with @JoinColumn
    @ManyToOne
    @JoinColumn(name = "id_posicao")
    private Posicao posicao;

    // Additional fields based on requirements
    @Column(name = "visualizacoes", columnDefinition = "INTEGER DEFAULT 0")
    private Integer visualizacoes = 0;

    @Column(name = "clicks", columnDefinition = "INTEGER DEFAULT 0")
    private Integer clicks = 0;

    @Column(name = "tipo_midia")
    private String tipoMidia; // IMAGE, VIDEO, SCRIPT

    @Column(name = "prioridade", columnDefinition = "INTEGER DEFAULT 1")
    private Integer prioridade = 1;

    @ManyToMany
    @JoinTable(
        name = "publicidade_categoria",
        joinColumns = @JoinColumn(name = "publicidade_id"),
        inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )
    private List<Categoria> categorias;

    // Constructors
    public Publicidade() {
    }

    public Publicidade(String titulo, String imagemUrl, String linkDestino, 
                      LocalDate dataInicio, LocalDate dataFim, boolean ativo, 
                      Posicao posicao) {
        this.titulo = titulo;
        this.imagemUrl = imagemUrl;
        this.linkDestino = linkDestino;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.ativo = ativo;
        this.posicao = posicao;
        this.visualizacoes = 0;
        this.clicks = 0;
        this.prioridade = 1;
        this.tipoMidia = "IMAGE";
    }

    // Helper methods
    public boolean isVigente() {
        LocalDate hoje = LocalDate.now();
        return hoje.isAfter(dataInicio.minusDays(1)) && hoje.isBefore(dataFim.plusDays(1));
    }

    public boolean isAtivoEVigente() {
        return ativo && isVigente();
    }

    public void incrementarVisualizacoes() {
        this.visualizacoes++;
    }

    public void incrementarClicks() {
        this.clicks++;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }

    public String getLinkDestino() {
        return linkDestino;
    }

    public void setLinkDestino(String linkDestino) {
        this.linkDestino = linkDestino;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Posicao getPosicao() {
        return posicao;
    }

    public void setPosicao(Posicao posicao) {
        this.posicao = posicao;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public Integer getVisualizacoes() {
        return visualizacoes;
    }

    public void setVisualizacoes(Integer visualizacoes) {
        this.visualizacoes = visualizacoes;
    }

    public Integer getClicks() {
        return clicks;
    }

    public void setClicks(Integer clicks) {
        this.clicks = clicks;
    }

    public String getTipoMidia() {
        return tipoMidia;
    }

    public void setTipoMidia(String tipoMidia) {
        this.tipoMidia = tipoMidia;
    }

    public Integer getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Integer prioridade) {
        this.prioridade = prioridade;
    }
}

/*package br.com.brunodezorzi.api.noticiario.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Publicidade", schema = "public")
public class Publicidade {

@Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "titulo")
  private String titulo;

  @Column(name = "imagemUrl")
  private String imagemUrl;

  @Column(name = "linkDestino")
  private String linkDestino;

  @Column(name = "dataInicio")
  private LocalDate dataInicio;

  @Column(name = "dataFim")
  private LocalDate dataFim;

  @Column(name = "ativo")
  private boolean ativo;

  @ManyToOne
  @JoinColumn(name = "id_posicao")
  @Column(name = "posicao")
  private Posicao posicao;

  @ManyToMany
    @JoinTable(
        name = "publicidade_categoria",
        joinColumns = @JoinColumn(name = "publicidade_id"),
        inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )
    private List<Categoria> categorias;

    public Publicidade() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }

    public String getLinkDestino() {
        return linkDestino;
    }

    public void setLinkDestino(String linkDestino) {
        this.linkDestino = linkDestino;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Posicao getPosicao() {
        return posicao;
    }

    public void setPosicao(Posicao posicao) {
        this.posicao = posicao;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }


    

}
*/