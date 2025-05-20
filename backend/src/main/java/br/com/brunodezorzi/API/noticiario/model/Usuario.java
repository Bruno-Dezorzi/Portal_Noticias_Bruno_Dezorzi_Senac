package br.com.brunodezorzi.api.noticiario.model;

import jakarta.persistence.Entity;

@Entity
public class Usuario extends Pessoa {

  private String login;
  private String senha;

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

  public Usuario(String nome, String email, String login, String senha) {
    setNome(nome);
    setEmail(email);
    this.login = login;
    this.senha = senha;
  }

  public Usuario() {}
}
