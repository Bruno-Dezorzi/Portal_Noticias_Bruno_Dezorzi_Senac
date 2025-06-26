package br.com.brunodezorzi.api.noticiario.model;

import br.com.brunodezorzi.api.noticiario.dto.PosicaoDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "posicao")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Posicao {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome")
    private String nome;

    public Posicao(String nome) {
        this.nome = nome;
    }

    public Posicao(PosicaoDTO posicao) {
        this.nome = posicao.getNome();
    }

}
