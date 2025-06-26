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

import br.com.brunodezorzi.api.noticiario.dto.PublicidadeDTO;
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

    @GetMapping("/listar/{id}")
    public ResponseEntity<Publicidade> listar(@PathVariable(value = "id") Integer id) {
        Optional<Publicidade> publicidade = publicidadeRepository.findById(id);
        if (publicidade.isPresent()) {
            return ResponseEntity.ok(publicidade.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/novo")
    public ResponseEntity<Publicidade> novo(@RequestBody PublicidadeDTO publicidadeDTO) {
        // Convertendo DTO para Entity
        Publicidade publicidade = new Publicidade();
        // Aqui você faria a conversão dos campos do DTO para a Entity
        // publicidade.setTitulo(publicidadeDTO.getTitulo());
        // publicidade.setUrl(publicidadeDTO.getUrl());
        // publicidade.setPosicao(publicidadeDTO.getPosicao());
        // etc...
        
        Publicidade publicidadeSalva = publicidadeRepository.save(publicidade);
        return ResponseEntity.status(HttpStatus.CREATED).body(publicidadeSalva);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Publicidade> atualizar(
        @PathVariable(value = "id") Integer id,
        @RequestBody PublicidadeDTO publicidadeDTO
    ) {
        Optional<Publicidade> publicidade = publicidadeRepository.findById(id);
        if (publicidade.isPresent()) {
            // Convertendo DTO para Entity
            Publicidade publicidadeAtualizada = new Publicidade();
            publicidadeAtualizada.setId(id); // Mantém o ID para atualização
            // Aqui você faria a conversão dos campos do DTO para a Entity
            // publicidadeAtualizada.setTitulo(publicidadeDTO.getTitulo());
            // publicidadeAtualizada.setUrl(publicidadeDTO.getUrl());
            // publicidadeAtualizada.setPosicao(publicidadeDTO.getPosicao());
            // etc...
            
            return ResponseEntity.ok(publicidadeRepository.save(publicidadeAtualizada));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> remover(@PathVariable("id") Integer id) {
        Optional<Publicidade> publicidade = publicidadeRepository.findById(id);
        if (publicidade.isPresent()) {
            publicidadeRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint específico para buscar por posição
    @GetMapping("/posicao/{nome}")
    public ResponseEntity<List<Publicidade>> buscarPorPosicao(@PathVariable String nome) {
        List<Publicidade> lista = publicidadeRepository.findByPosicao_NomeIgnoreCase(nome);
        return ResponseEntity.ok(lista);
    }
}