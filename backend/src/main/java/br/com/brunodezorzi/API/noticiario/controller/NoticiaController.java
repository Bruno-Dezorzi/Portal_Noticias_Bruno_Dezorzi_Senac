package br.com.brunodezorzi.api.noticiario.controller;

import br.com.brunodezorzi.api.noticiario.model.Noticia;
import br.com.brunodezorzi.api.noticiario.repository.NoticiaRepository;
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
@RequestMapping(value = "/noticia")
public class NoticiaController {

  @Autowired
  private NoticiaRepository noticiaRepository;

  @RequestMapping(value = "/listar", method = RequestMethod.GET)
  public ResponseEntity<List<Noticia>> listar() {
    List<Noticia> noticias = noticiaRepository.findAll();
    return ResponseEntity.ok(noticias);
  }

  @GetMapping(value = "/listar/{id}")
  public ResponseEntity<Noticia> listar(
    @PathVariable(value = "id") Integer id
  ) {
    Optional<Noticia> noticia = noticiaRepository.findById(id);
    if (noticia.isPresent()) {
      return new ResponseEntity<>(noticia.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/novo")
  public ResponseEntity<Noticia> novo(@RequestBody Noticia noticia) {
    return new ResponseEntity<>(noticiaRepository.save(noticia), HttpStatus.OK);
  }

  @PutMapping("/atualizar/{id}")
  public ResponseEntity<Noticia> atualizar(
    @PathVariable(value = "id") Integer id,
    @RequestBody Noticia novnoticia
  ) {
    Optional<Noticia> noticia = noticiaRepository.findById(id);
    if (noticia.isPresent()) {
      return new ResponseEntity<>(
        noticiaRepository.save(novnoticia),
        HttpStatus.OK
      );
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/remover/{id}")
  public ResponseEntity<Void> remover(@PathVariable("id") Integer id) {
    Optional<Noticia> noticia = noticiaRepository.findById(id);
    if (noticia.isPresent()) {
      noticiaRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
