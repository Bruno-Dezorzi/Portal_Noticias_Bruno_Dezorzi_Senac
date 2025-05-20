package br.com.brunodezorzi.api.noticiario.model;

import jakarta.persistence.Entity;

@Entity
public class Autor extends Pessoa {

  private String biografia;

  public String getBiografia() {
    return biografia;
  }

  public void setBiografia(String biografia) {
    this.biografia = biografia;
  }

  public Autor() {}

  public Autor(String biografia) {
    this.biografia = biografia;
  }
}
