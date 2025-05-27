package br.com.brunodezorzi.api.noticiario.controller;

import br.com.brunodezorzi.api.noticiario.model.Autor;
import br.com.brunodezorzi.api.noticiario.repository.AutorRepository;
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

@RestController
@RequestMapping("/autor")
public class AutorController {

  @Autowired
  private AutorRepository autorRepository;

  //listar autores
  @GetMapping("/listar")
  public ResponseEntity<List<Autor>> listar() {
    List<Autor> autores = autorRepository.findAll();
    return ResponseEntity.ok(autores);
  }

  // pegar pelo id
  @GetMapping("/listar/{id}")
  public ResponseEntity<Autor> listar(@PathVariable(value = "id") Long Id) {
    Optional<Autor> autor = autorRepository.findById(Id);
    if (autor.isPresent()) {
      return new ResponseEntity<>(autor.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/novo")
  public ResponseEntity<Autor> novo(@RequestBody Autor autor) {
    return new ResponseEntity<>(autorRepository.save(autor), HttpStatus.OK);
  }

  @PutMapping("/atualizar/{id}")
  public ResponseEntity<Autor> atualizar(
    @PathVariable(value = "id") Long id,
    @RequestBody Autor novAutor
  ) {
    Optional<Autor> autor = autorRepository.findById(id);
    if (autor.isPresent()) {
      return new ResponseEntity<>(
        autorRepository.save(novAutor),
        HttpStatus.OK
      );
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/remover/{id}")
  public ResponseEntity<Void> remover(@PathVariable("id") Long id) {
    Optional<Autor> autor = autorRepository.findById(id);
    if (autor.isPresent()) {
      autorRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
