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

import br.com.brunodezorzi.api.noticiario.dto.PosicaoDTO;
import br.com.brunodezorzi.api.noticiario.model.Posicao;
import br.com.brunodezorzi.api.noticiario.repository.PosicaoRepository;

@RestController
@RequestMapping("/posicao")
public class PosicaoController {

  @Autowired
  private PosicaoRepository posicaoRepository;

  @GetMapping("/listar")
  public ResponseEntity<List<Posicao>> listar() {
    List<Posicao> posicoes = posicaoRepository.findAll();
    return ResponseEntity.ok(posicoes);
  }

  @GetMapping("/listar/{id}")
  public ResponseEntity<Posicao> listar(@PathVariable(value = "id") Integer id) {
    Optional<Posicao> posicao = posicaoRepository.findById(id);
    if (posicao.isPresent()) {
      return ResponseEntity.ok(posicao.get());
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping("/novo")
  public ResponseEntity<Posicao> novo(@RequestBody PosicaoDTO posicaoDTO) {
    // Convertendo DTO para Entity
    Posicao posicao = new Posicao();
    // Aqui você faria a conversão dos campos do DTO para a Entity
    // posicao.setNome(posicaoDTO.getNome());
    // posicao.setDescricao(posicaoDTO.getDescricao());
    // etc...
    
    Posicao posicaoSalva = posicaoRepository.save(posicao);
    return ResponseEntity.status(HttpStatus.CREATED).body(posicaoSalva);
  }

  @PutMapping("/atualizar/{id}")
  public ResponseEntity<Posicao> atualizar(
    @PathVariable(value = "id") Integer id,
    @RequestBody PosicaoDTO posicaoDTO
  ) {
    Optional<Posicao> posicao = posicaoRepository.findById(id);
    if (posicao.isPresent()) {
      // Convertendo DTO para Entity
      Posicao posicaoAtualizada = new Posicao();
      posicaoAtualizada.setId(id); // Mantém o ID para atualização
      // Aqui você faria a conversão dos campos do DTO para a Entity
      // posicaoAtualizada.setNome(posicaoDTO.getNome());
      // posicaoAtualizada.setDescricao(posicaoDTO.getDescricao());
      // etc...
      
      return ResponseEntity.ok(posicaoRepository.save(posicaoAtualizada));
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/remover/{id}")
  public ResponseEntity<Void> remover(@PathVariable("id") Integer id) {
    Optional<Posicao> posicao = posicaoRepository.findById(id);
    if (posicao.isPresent()) {
      posicaoRepository.deleteById(id);
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}