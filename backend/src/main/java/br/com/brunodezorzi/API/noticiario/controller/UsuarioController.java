package br.com.brunodezorzi.api.noticiario.controller;

import br.com.brunodezorzi.api.noticiario.model.Usuario;
import br.com.brunodezorzi.api.noticiario.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

  @Autowired
  private UsuarioRepository usuarioRepository;

  @GetMapping("/listar")
  public ResponseEntity<List<Usuario>> listar() {
    List<Usuario> usuarios = usuarioRepository.findAll();
    return ResponseEntity.ok(usuarios);
  }

  @GetMapping(value = "/listar/{id}")
  public ResponseEntity<Usuario> listar(
    @PathVariable(value = "id") Integer id
  ) {
    Optional<Usuario> usuario = usuarioRepository.findById(id);
    if (usuario.isPresent()) {
      return new ResponseEntity<>(usuario.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/novo")
  public ResponseEntity<Usuario> novo(@RequestBody Usuario usuario) {
    return new ResponseEntity<>(usuarioRepository.save(usuario), HttpStatus.OK);
  }

  @PutMapping("/atualizar/{id}")
  public ResponseEntity<Usuario> atualizar(
    @PathVariable(value = "id") Integer id,
    @RequestBody Usuario novusuario
  ) {
    Optional<Usuario> usuario = usuarioRepository.findById(id);
    if (usuario.isPresent()) {
      return new ResponseEntity<>(
        usuarioRepository.save(novusuario),
        HttpStatus.OK
      );
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/remover/{id}")
  public ResponseEntity<Void> remover(@PathVariable("id") Integer id) {
    Optional<Usuario> usuario = usuarioRepository.findById(id);
    if (usuario.isPresent()) {
      usuarioRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
