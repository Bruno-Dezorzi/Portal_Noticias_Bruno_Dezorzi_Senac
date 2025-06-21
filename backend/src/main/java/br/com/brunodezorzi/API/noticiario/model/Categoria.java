package br.com.brunodezorzi.api.noticiario.model;

import br.com.brunodezorzi.api.noticiario.dto.CategoriaDTO;
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
@Table(name="categoria")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Categoria {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name="nome")
    private String nome;

    @Column(name="descricao")
    private String descricao;

    @Column(name="destaque", nullable=false)
    private Boolean  destaque;

    @JoinColumn(name = "categoria_pai_id", referencedColumnName = "id", nullable=true, updatable=false, insertable=false)
    @ManyToOne(optional=true)
    private Categoria categoria;

    @Column(name="categoria_pai_id")
    private Integer categoriaPaiId;

    

    public Categoria(String nome, String descricao, Categoria categoria, boolean destaque) {
        this.nome = nome;
        this.descricao = descricao;
        this.categoria = categoria;
        this.destaque = destaque;
    }

    public Categoria(CategoriaDTO categoria){
        this.nome = categoria.getNome();
        this.descricao = categoria.getDescricao();
        this.categoriaPaiId = categoria.getCategoriaPaiId();
        this.destaque = categoria.getDestaque();
    }




}
