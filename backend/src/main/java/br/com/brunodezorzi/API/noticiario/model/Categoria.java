package br.com.brunodezorzi.api.noticiario.model;

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

  private String nome;

  @ManyToOne
  @JoinColumn(
    name = "categoria_pai_id",
    referencedColumnName = "id",
    nullable = true
  ) // nome da coluna de FK na tabela
  private Categoria categoriaPai;

  public Categoria(String nome, Categoria categoriaPai) {
    this.categoriaPai = categoriaPai;
    this.nome = nome;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Categoria getCategoriaPai() {
    return categoriaPai;
  }

  public void setCategoriaPai(Categoria categoriaPai) {
    this.categoriaPai = categoriaPai;
  }

  public Categoria() {}

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }
}
