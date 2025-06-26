package br.com.brunodezorzi.api.noticiario.model;

import br.com.brunodezorzi.api.noticiario.dto.UsuarioDTO;
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

@Table(name = "usuario")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "login")
    private String login;

    @Column(name = "senha")
    private String senha;

    @JoinColumn(name = "pessoa_id", referencedColumnName = "id", nullable = true, updatable = false, insertable = false)
    @ManyToOne
    private Pessoa pessoa;

    @Column(name = "pessoa_id")
    private Integer pessoaId;

    public Usuario(UsuarioDTO usuario){
    this.login = usuario.getLogin();
    this.senha = usuario.getSenha();
    //this.pessoaId = usuario.getPessoaId();
    
    // Se quiser criar a Pessoa diretamente no construtor
    if (usuario.getNome() != null && usuario.getEmail() != null) {
        this.pessoa = new Pessoa(usuario.getNome(), usuario.getEmail());
    }

    
}

}
