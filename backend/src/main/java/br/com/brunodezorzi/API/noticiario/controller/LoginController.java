package br.com.brunodezorzi.api.noticiario.controller;

import br.com.brunodezorzi.api.noticiario.dto.LoginDTO;
import br.com.brunodezorzi.api.noticiario.model.Usuario;
import br.com.brunodezorzi.api.noticiario.repository.UsuarioRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

  @Autowired
  private UsuarioRepository usuarioRepository;

  @PostMapping("path")
  public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
    Optional<Usuario> usuarioOpt = usuarioRepository.findByLoginAndSenha(
      loginDTO.getLogin(),
      loginDTO.getSenha()
    );

    if (usuarioOpt.isPresent()) {
      return ResponseEntity.ok("Login permitido");
    } else {
      return ResponseEntity.status(400).body("Login ou senha invalidos");
    }
  }
}
