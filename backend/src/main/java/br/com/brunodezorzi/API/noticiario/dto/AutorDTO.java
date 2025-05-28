package br.com.brunodezorzi.api.noticiario.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AutorDTO {

  private Integer id;
  private String biografia;
  private Integer pessoaId;
}
