package br.com.brunodezorzi.api.noticiario.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "Noticia", schema = "public")
public class Noticia {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "titulo")
  private String titulo;

  @Column(name = "data_publicacao")
  private LocalDate dataPublicacao;

  @ManyToOne
  @JoinColumn(name = "id_categoria")
  private Categoria categoria;

  @ManyToOne
  @JoinColumn(name = "autor_id") // nome da coluna no banco
  private Autor autor;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public LocalDate getDataPublicacao() {
    return dataPublicacao;
  }

  public void setDataPublicacao(LocalDate dataPublicacao) {
    this.dataPublicacao = dataPublicacao;
  }

  public Categoria getCategoria() {
    return categoria;
  }

  public void setCategoria(Categoria categoria) {
    this.categoria = categoria;
  }

  public Autor getAutor() {
    return autor;
  }

  public void setAutor(Autor autor) {
    this.autor = autor;
  }

  public Noticia() {}

  public Noticia(
    String titulo,
    LocalDate dataPublicacao,
    Categoria categoria,
    Autor autor
  ) {
    this.titulo = titulo;
    this.dataPublicacao = dataPublicacao;
    this.categoria = categoria;
    this.autor = autor;
  }
}
