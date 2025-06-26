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

import br.com.brunodezorzi.api.noticiario.dto.NoticiaDTO;
import br.com.brunodezorzi.api.noticiario.model.Categoria;
import br.com.brunodezorzi.api.noticiario.model.Noticia;
import br.com.brunodezorzi.api.noticiario.repository.CategoriaRepository;
import br.com.brunodezorzi.api.noticiario.repository.NoticiaRepository;

@RestController
@RequestMapping(value = "/noticia")
public class NoticiaController {

  @Autowired
  private NoticiaRepository noticiaRepository;

  @Autowired
    CategoriaRepository categoriaRepository;

  @GetMapping("/listar")
  public ResponseEntity<List<Noticia>> listar() {
    List<Noticia> noticias = noticiaRepository.findAll();
    return ResponseEntity.ok(noticias);
  }

  @GetMapping("/listar/{id}")
  public ResponseEntity<Noticia> listar(@PathVariable(value = "id") Integer id) {
    Optional<Noticia> noticia = noticiaRepository.findById(id);
    if (noticia.isPresent()) {
      return ResponseEntity.ok(noticia.get());
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping("/novo")
  public ResponseEntity<Noticia> novo(@RequestBody NoticiaDTO noticiaDTO) {
    Noticia noticia = new Noticia(noticiaDTO);
    Noticia noticiaSalva = noticiaRepository.save(noticia);
    return ResponseEntity.status(HttpStatus.CREATED).body(noticiaSalva);
  }

  @PutMapping("/atualizar/{id}")
  public ResponseEntity<Noticia> atualizar(
    @PathVariable(value = "id") Integer id,
    @RequestBody NoticiaDTO noticiaDTO
  ) {
    Optional<Noticia> noticia = noticiaRepository.findById(id);
    if (noticia.isPresent()) {
      // Convertendo DTO para Entity
      Noticia noticiaAtualizada = new Noticia(noticiaDTO);
      noticiaAtualizada.setId(id); // Mantém o ID para atualização
      
      return ResponseEntity.ok(noticiaRepository.save(noticiaAtualizada));
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/remover/{id}")
  public ResponseEntity<Void> remover(@PathVariable("id") Integer id) {
    Optional<Noticia> noticia = noticiaRepository.findById(id);
    if (noticia.isPresent()) {
      noticiaRepository.deleteById(id);
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("listarNoticiasPorCategoria/{id_categoria}")
    public ResponseEntity<List<Noticia>> listarNoticiasPorCategoria(@PathVariable(value = "id_categoria") Integer id_categoria) {
        Categoria categoria = categoriaRepository.findById(id_categoria).get();
        return ResponseEntity.ok((List<Noticia>) noticiaRepository.findAllNoticiasByCategoria(categoria));
    }

    @GetMapping("findTopByCategoriaIdOrderByDataPublicacaoDesc/{id_categoria}")
    public ResponseEntity<Noticia> findOneByUltimaNoticiaByCategoria(@PathVariable(value = "id_categoria") Integer id_categoria) {
        Categoria categoria = categoriaRepository.findById(id_categoria).get();
        return ResponseEntity.ok((Noticia) noticiaRepository.findTopByCategoriaIdOrderByDataPublicacaoDesc(id_categoria));
    }

    @GetMapping("findTopByOrderByDataPublicacaoDesc")
    public ResponseEntity<Noticia> findOneByUltimaNoticiaByTodas() {
        return ResponseEntity.ok((Noticia) noticiaRepository.findTopByOrderByDataPublicacaoDesc());
    }
}