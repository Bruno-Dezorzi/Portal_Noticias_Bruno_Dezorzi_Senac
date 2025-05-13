package br.com.brunodezorzi.API.noticiario.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Usuario extends Pessoa {

  @Id
  private Long id;

  private String nome;
  private String email;
  private String login;
  private String senha;

  @ManyToOne
  @JoinColumn(name = "pessoa")
  private Pessoa pessoa;

  @Override
  public Long getId() {
    return id;
  }

  @Override
  public void setId(Long id) {
    this.id = id;
  }

  @Override
  public String getNome() {
    return nome;
  }

  @Override
  public void setNome(String nome) {
    this.nome = nome;
  }

  @Override
  public String getEmail() {
    return email;
  }

  @Override
  public void setEmail(String email) {
    this.email = email;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public Pessoa getPessoa() {
    return pessoa;
  }

  public void setPessoa(Pessoa pessoa) {
    this.pessoa = pessoa;
  }

  public Usuario(
    Long id,
    String nome,
    String email,
    String login,
    String senha,
    Pessoa pessoa
  ) {
    this.id = id;
    this.nome = nome;
    this.email = email;
    this.login = login;
    this.senha = senha;
    this.pessoa = pessoa;
  }

  public Usuario() {}
}
