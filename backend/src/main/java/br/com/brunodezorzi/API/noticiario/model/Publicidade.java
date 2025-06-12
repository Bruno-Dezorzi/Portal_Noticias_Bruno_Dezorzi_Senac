package br.com.brunodezorzi.api.noticiario.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "publicidade")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Publicidade {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // : identificador único do anúncio.

    @Column(name = "titulo")
    private String titulo; // : título do anúncio.

    @Column(name = "imagemUrl")
    private String imagemUrl; // : caminho da imagem (ou banner) a ser exibido.

    @Column(name = "linkDestino")
    private String linkDestino; // : link para o qual o usuário será redirecionado ao clicar no anúncio.

    @Column(name = "dataInicio")
    private LocalDate dataInicio; // : data de início da exibição.

    @Column(name = "dataFim")
    private LocalDate dataFim; // : data final de exibição.

    @Column(name = "ativo")
    private Boolean ativo; // : indica se o anúncio está ativo.

    @Column(name = "prioridade")
    private Boolean prioridade;

    @JoinColumn(name = "posicao_id", referencedColumnName = "id")
    @ManyToOne
    private Posicao posicao; // : posição da página onde o anúncio será exibido (relação com Posicao).

    @JoinColumn(name = "categoria_id", referencedColumnName = "id")
    @ManyToOne
    private Categoria categoria;//

    public Publicidade(String titulo, String imagemUrl, String linkDestino, LocalDate dataInicio, LocalDate dataFim,
            Boolean ativo, Posicao posicao, Categoria categoria) {
        this.titulo = titulo;
        this.imagemUrl = imagemUrl;
        this.linkDestino = linkDestino;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.ativo = ativo;
        this.posicao = posicao;
        this.categoria = categoria;
    }

}
