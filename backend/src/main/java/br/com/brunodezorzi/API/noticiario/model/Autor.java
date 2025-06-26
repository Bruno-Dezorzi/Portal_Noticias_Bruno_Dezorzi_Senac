package br.com.brunodezorzi.api.noticiario.model;

import br.com.brunodezorzi.api.noticiario.dto.AutorDTO;
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
@Table(name = "autor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Autor {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "biografia")
    private String biografia;

    @JoinColumn(name = "pessoa_id", referencedColumnName = "id", nullable = true, updatable = false, insertable = false)
    @ManyToOne
    private Pessoa pessoa;

    @Column(name = "pessoa_id")
    private Integer pessoaId;

    public Autor(AutorDTO autor){
        this.biografia = autor.getBiografia();
        //this.pessoaId = autor.getPessoaId();
    }
    
    // Método auxiliar para atualizar dados
    public void atualizarDados(AutorDTO autorDTO) {
        this.biografia = autorDTO.getBiografia();
    }
}