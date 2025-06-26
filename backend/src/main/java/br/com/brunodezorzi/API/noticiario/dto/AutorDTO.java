package br.com.brunodezorzi.api.noticiario.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AutorDTO {

    private String biografia;
    private Integer pessoaId; // Mantém para casos onde já existe uma pessoa
    
    // Dados da pessoa para criação/atualização
    private String nome;
    private String email;
}