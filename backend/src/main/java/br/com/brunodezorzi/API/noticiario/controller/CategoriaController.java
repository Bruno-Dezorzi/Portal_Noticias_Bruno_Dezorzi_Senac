package br.com.brunodezorzi.api.noticiario.controller;

import br.com.brunodezorzi.api.noticiario.model.Categoria;
import br.com.brunodezorzi.api.noticiario.repository.CategoriaRepository;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/categoria")
public class CategoriaController {

  @Autowired
  private CategoriaRepository categoriaRepository;

  @RequestMapping(value = "/listar", method = RequestMethod.GET)
  public ResponseEntity<List<Categoria>> listar() {
    List<Categoria> categorias = categoriaRepository.findAll();
    return ResponseEntity.ok(categorias);
  }

  @GetMapping(value = "/listar/{id}")
  public ResponseEntity<Categoria> listar(
    @PathVariable(value = "id") Integer id
  ) {
    Optional<Categoria> categoria = categoriaRepository.findById(id);
    if (categoria.isPresent()) {
      return new ResponseEntity<>(categoria.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/novo")
  public ResponseEntity<Categoria> novo(@RequestBody Categoria categoria) {
    return new ResponseEntity<>(
      categoriaRepository.save(categoria),
      HttpStatus.OK
    );
  }

  @PutMapping("/atualizar/{id}")
  public ResponseEntity<Categoria> atualizar(
    @PathVariable(value = "id") Integer id,
    @RequestBody Categoria novCategoria
  ) {
    Optional<Categoria> categoria = categoriaRepository.findById(id);
    if (categoria.isPresent()) {
      return new ResponseEntity<>(
        categoriaRepository.save(novCategoria),
        HttpStatus.OK
      );
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/remover/{id}")
  public ResponseEntity<Void> remover(@PathVariable("id") Integer id) {
    Optional<Categoria> categoria = categoriaRepository.findById(id);
    if (categoria.isPresent()) {
      categoriaRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
