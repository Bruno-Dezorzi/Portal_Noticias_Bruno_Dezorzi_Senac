package br.com.brunodezorzi.api.noticiario.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PessoaDTO {

  private Integer id;
  private String nome;
  private String email;
}
