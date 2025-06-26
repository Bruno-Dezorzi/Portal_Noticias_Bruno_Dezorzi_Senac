package br.com.brunodezorzi.api.noticiario.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PublicidadeDTO {

    private String titulo;
    private String imagemUrl;
    private String linkDestino;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private boolean ativo;
    private boolean prioridade;
    private Integer posicaoId;
    private Integer categoriaId;
}
