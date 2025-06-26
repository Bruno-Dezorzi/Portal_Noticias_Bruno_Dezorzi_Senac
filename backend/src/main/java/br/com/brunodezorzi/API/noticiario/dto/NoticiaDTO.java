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
public class NoticiaDTO {

    private String titulo;
    
    private String corpo;

    private LocalDate dataPublicacao;

    private String imagem_url;

    private Integer categoriaId;

    private Integer autorId;

    private boolean destaque;
}
