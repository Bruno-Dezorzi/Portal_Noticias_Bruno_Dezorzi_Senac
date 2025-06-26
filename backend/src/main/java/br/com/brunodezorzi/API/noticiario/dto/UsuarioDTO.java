package br.com.brunodezorzi.api.noticiario.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    private String login;
    private String senha;
    //private Integer pessoaId;
    
    // Adicionar campos da Pessoa
    private String nome;
    private String email;
}
