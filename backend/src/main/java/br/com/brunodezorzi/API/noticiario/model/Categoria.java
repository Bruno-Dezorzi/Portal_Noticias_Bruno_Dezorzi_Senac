package br.com.brunodezorzi.API.noticiario.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Categoria {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String descricao;

  @ManyToOne
  @JoinColumn(name = "categoria_pai_id") // nome da coluna de FK na tabela
  private Categoria categoriaPai;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public Categoria getCategoriaPai() {
    return categoriaPai;
  }

  public void setCategoriaPai(Categoria categoriaPai) {
    this.categoriaPai = categoriaPai;
  }

  public Categoria(Long id, String descricao, Categoria categoriaPai) {
    this.id = id;
    this.descricao = descricao;
    this.categoriaPai = categoriaPai;
  }
}
