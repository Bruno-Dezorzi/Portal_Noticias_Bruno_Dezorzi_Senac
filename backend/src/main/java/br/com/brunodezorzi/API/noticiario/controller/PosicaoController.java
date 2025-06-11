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

import br.com.brunodezorzi.api.noticiario.model.Posicao;
import br.com.brunodezorzi.api.noticiario.repository.PosicaoRepository;

@RestController
@RequestMapping("/posicao")
public class PosicaoController {

    @Autowired
  private PosicaoRepository PosicaoRepository;


  @GetMapping("/listar")
  public ResponseEntity<List<Posicao>> listar() {
    List<Posicao> posicoes = PosicaoRepository.findAll();
    return ResponseEntity.ok(posicoes);
  }

  // pegar pelo id
  @GetMapping("/listar/{id}")
  public ResponseEntity<Posicao> listar(@PathVariable(value = "id") Integer Id) {
    Optional<Posicao> Posicao = PosicaoRepository.findById(Id);
    if (Posicao.isPresent()) {
      return new ResponseEntity<>(Posicao.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/novo")
  public ResponseEntity<Posicao> novo(@RequestBody Posicao Posicao) {
    return new ResponseEntity<>(PosicaoRepository.save(Posicao), HttpStatus.OK);
  }

  @PutMapping("/atualizar/{id}")
  public ResponseEntity<Posicao> atualizar(
    @PathVariable(value = "id") Integer id,
    @RequestBody Posicao novPosicao
  ) {
    Optional<Posicao> Posicao = PosicaoRepository.findById(id);
    if (Posicao.isPresent()) {
      return new ResponseEntity<>(
        PosicaoRepository.save(novPosicao),
        HttpStatus.OK
      );
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/remover/{id}")
  public ResponseEntity<Void> remover(@PathVariable("id") Integer id) {
    Optional<Posicao> Posicao = PosicaoRepository.findById(id);
    if (Posicao.isPresent()) {
      PosicaoRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
