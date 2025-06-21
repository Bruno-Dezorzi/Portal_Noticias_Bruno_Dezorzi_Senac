package br.com.brunodezorzi.api.noticiario.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaDTO {

    private String nome;

    private String descricao;

    private Boolean  destaque;

    private Integer categoriaPaiId;
    







}
