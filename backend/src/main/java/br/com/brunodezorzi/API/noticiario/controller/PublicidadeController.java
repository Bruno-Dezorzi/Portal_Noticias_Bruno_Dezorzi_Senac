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

import br.com.brunodezorzi.api.noticiario.model.Publicidade;
import br.com.brunodezorzi.api.noticiario.repository.PublicidadeRepository;

@RestController
@RequestMapping("/publicidade")
public class PublicidadeController {
    @Autowired
  private PublicidadeRepository publicidadeRepository;


  @GetMapping("/listar")
  public ResponseEntity<List<Publicidade>> listar() {
    List<Publicidade> publicidades = publicidadeRepository.findAll();
    return ResponseEntity.ok(publicidades);
  }

  // pegar pelo id
  @GetMapping("/listar/{id}")
  public ResponseEntity<Publicidade> listar(@PathVariable(value = "id") Integer Id) {
    Optional<Publicidade> publicidade = publicidadeRepository.findById(Id);
    if (publicidade.isPresent()) {
      return new ResponseEntity<>(publicidade.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/novo")
  public ResponseEntity<Publicidade> novo(@RequestBody Publicidade publicidade) {
    return new ResponseEntity<>(publicidadeRepository.save(publicidade), HttpStatus.OK);
  }

  @PutMapping("/atualizar/{id}")
  public ResponseEntity<Publicidade> atualizar(
    @PathVariable(value = "id") Integer id,
    @RequestBody Publicidade novpublicidade
  ) {
    Optional<Publicidade> publicidade = publicidadeRepository.findById(id);
    if (publicidade.isPresent()) {
      return new ResponseEntity<>(
        publicidadeRepository.save(novpublicidade),
        HttpStatus.OK
      );
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/remover/{id}")
  public ResponseEntity<Void> remover(@PathVariable("id") Integer id) {
    Optional<Publicidade> publicidade = publicidadeRepository.findById(id);
    if (publicidade.isPresent()) {
      publicidadeRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
