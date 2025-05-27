package br.com.brunodezorzi.api.noticiario.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class GenericController<T, ID> {

  @Autowired
  private CrudRepository<T, ID> repository;

  @GetMapping("/listar")
  public ResponseEntity<List<T>> listar() {
    return ResponseEntity.ok((List<T>) repository.findAll());
  }

  @GetMapping(value = "/listar/{id}")
  public ResponseEntity<T> getByID(@PathVariable(value = "id") ID id) {
    Optional<T> object = repository.findById(id);
    if (object.isPresent()) {
      return new ResponseEntity<>(object.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/novo")
  public ResponseEntity<T> novo(@RequestBody T objeto) {
    return new ResponseEntity<>(repository.save(objeto), HttpStatus.OK);
  }

  @PutMapping("/atualizar/{id}")
  public ResponseEntity<T> atualizar(
    @PathVariable(value = "id") ID id,
    @RequestBody T novObjeto
  ) {
    Optional<T> object = repository.findById(id);
    if (object.isPresent()) {
      return new ResponseEntity<>(repository.save(novObjeto), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/remover/{id}")
  public ResponseEntity<Void> remover(@PathVariable("id") ID id) {
    Optional<T> object = repository.findById(id);
    if (object.isPresent()) {
      repository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
