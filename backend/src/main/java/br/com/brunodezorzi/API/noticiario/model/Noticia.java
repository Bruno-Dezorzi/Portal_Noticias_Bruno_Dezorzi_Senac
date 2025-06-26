package br.com.brunodezorzi.api.noticiario.model;

import java.time.LocalDate;

import br.com.brunodezorzi.api.noticiario.dto.NoticiaDTO;
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

@Table(name="noticia")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Noticia {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name="titulo")
    private String titulo;
    
    @Column(name="corpo", columnDefinition = "TEXT")
    private String corpo;

    @Column(name="data_publicacao")
    private LocalDate dataPublicacao;

    @Column(name = "imagem_url")
    private String imagem_url;

    @JoinColumn(name="categoria_pai_id", referencedColumnName="id",nullable=true, updatable=false, insertable=false)
    @ManyToOne
    private Categoria categoria;

    @Column(name="categoria_pai_id")
    private Integer categoriaId;

    @JoinColumn(name="autor_id", referencedColumnName="id",nullable=true, updatable=false, insertable=false)
    @ManyToOne
    private Autor autor;

    @Column(name="autor_id")
    private Integer autorId;


    @Column(name="destaque")
    private boolean destaque;

    public Noticia(NoticiaDTO noticia){
        this.titulo = noticia.getTitulo();
        this.corpo = noticia.getCorpo();
        this.dataPublicacao = noticia.getDataPublicacao();
        this.imagem_url = noticia.getImagem_url();
        this.categoriaId = noticia.getCategoriaId();
        this.autorId = noticia.getAutorId();
        this.destaque = noticia.isDestaque();
    }


    

}
