package br.com.brunodezorzi.API.noticiario.controller;

import br.com.brunodezorzi.API.noticiario.model.Autor;
import br.com.brunodezorzi.API.noticiario.repository.AutorRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autor")
public class AutorController {

  @Autowired
  private AutorRepository autorRepository;

  //listar autores
  @GetMapping("/autor")
  @ResponseStatus(HttpStatus.OK)
  public List<Autor> getAllAutores() {
    return this.autorRepository.findAll();
  }
  // pegar pelo id
  /*@GetMapping("/autor/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Autor> getAutorById(@PathVariable(value= "id") long Id)  throws ResourceNotFoundException {
    Autor autor = autorRepository.findById(Id)
	      .orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada para o ID :: " + cadastroId));
	    return ResponseEntity.ok().body(cadastro);
  }*/
}
