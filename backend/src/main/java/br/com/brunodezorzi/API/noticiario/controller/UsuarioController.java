package br.com.brunodezorzi.api.noticiario.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.brunodezorzi.api.noticiario.dto.UsuarioDTO;
import br.com.brunodezorzi.api.noticiario.model.Pessoa;
import br.com.brunodezorzi.api.noticiario.model.Usuario;
import br.com.brunodezorzi.api.noticiario.repository.PessoaRepository;
import br.com.brunodezorzi.api.noticiario.repository.UsuarioRepository;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Autowired
  private PessoaRepository pessoaRepository;

  @GetMapping("/listar")
  public ResponseEntity<List<Usuario>> listar() {
    List<Usuario> usuarios = usuarioRepository.findAll();
    return ResponseEntity.ok(usuarios);
  }

  @GetMapping("/listar/{id}")
  public ResponseEntity<Usuario> listar(@PathVariable(value = "id") Integer id) {
    Optional<Usuario> usuario = usuarioRepository.findById(id);
    if (usuario.isPresent()) {
      return ResponseEntity.ok(usuario.get());
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping("/novo")
public ResponseEntity<Usuario> novo(@RequestBody UsuarioDTO usuarioDTO) {
    // Criar a Pessoa primeiro
    Pessoa pessoa = new Pessoa();
    pessoa.setNome(usuarioDTO.getNome());
    pessoa.setEmail(usuarioDTO.getEmail());
    
    Pessoa pessoaSalva = pessoaRepository.save(pessoa);
    
    // Criar o Usuario
    Usuario usuario = new Usuario();
    usuario.setLogin(usuarioDTO.getLogin());
    usuario.setSenha(usuarioDTO.getSenha());
    usuario.setPessoa(pessoaSalva);
    usuario.setPessoaId(pessoaSalva.getId());
    
    Usuario usuarioSalvo = usuarioRepository.save(usuario);
    return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo);
}

  @PutMapping("/atualizar/{id}")
public ResponseEntity<Usuario> atualizar(
    @PathVariable(value = "id") Integer id,
    @RequestBody UsuarioDTO usuarioDTO
) {
    Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
    if (usuarioOpt.isPresent()) {
        Usuario usuario = usuarioOpt.get();
        
        // Atualizar dados do Usuario
        usuario.setLogin(usuarioDTO.getLogin());
        usuario.setSenha(usuarioDTO.getSenha());
        
        // Atualizar dados da Pessoa (se ela existir)
        if (usuario.getPessoa() != null) {
            Pessoa pessoa = usuario.getPessoa();
            pessoa.setNome(usuarioDTO.getNome());
            pessoa.setEmail(usuarioDTO.getEmail());
            pessoaRepository.save(pessoa); // Salvar pessoa atualizada
        } else if (usuarioDTO.getNome() != null && usuarioDTO.getEmail() != null) {
            // Se não tem pessoa mas veio dados, cria uma nova
            Pessoa novaPessoa = new Pessoa();
            novaPessoa.setNome(usuarioDTO.getNome());
            novaPessoa.setEmail(usuarioDTO.getEmail());
            Pessoa pessoaSalva = pessoaRepository.save(novaPessoa);
            usuario.setPessoa(pessoaSalva);
            usuario.setPessoaId(pessoaSalva.getId());
        }
        
        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return ResponseEntity.ok(usuarioSalvo);
    } else {
        return ResponseEntity.notFound().build();
    }
}

  @DeleteMapping("/remover/{id}")
  public ResponseEntity<Void> remover(@PathVariable("id") Integer id) {
    Optional<Usuario> usuario = usuarioRepository.findById(id);
    if (usuario.isPresent()) {
      usuarioRepository.deleteById(id);
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}