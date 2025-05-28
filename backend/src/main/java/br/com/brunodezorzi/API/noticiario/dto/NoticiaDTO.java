package br.com.brunodezorzi.api.noticiario.dto;

import java.time.LocalDate;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NoticiaDTO {

  private Integer id;
  private String titulo;
  private String conteudo;
  private LocalDate dataPublicacao;
  private CategoriaDTO categoria;
  private Integer autorId;
}
