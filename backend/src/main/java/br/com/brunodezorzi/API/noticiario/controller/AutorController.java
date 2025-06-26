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

import br.com.brunodezorzi.api.noticiario.dto.AutorDTO;
import br.com.brunodezorzi.api.noticiario.model.Autor;
import br.com.brunodezorzi.api.noticiario.model.Pessoa;
import br.com.brunodezorzi.api.noticiario.repository.AutorRepository;
import br.com.brunodezorzi.api.noticiario.repository.PessoaRepository;

@RestController
@RequestMapping("/autor")
public class AutorController {

    @Autowired
    private AutorRepository autorRepository;
    
    @Autowired
    private PessoaRepository pessoaRepository;

    @GetMapping("/listar")
    public ResponseEntity<List<Autor>> listar() {
        List<Autor> autores = autorRepository.findAll();
        return ResponseEntity.ok(autores);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<Autor> listar(@PathVariable(value = "id") Integer id) {
        Optional<Autor> autor = autorRepository.findById(id);
        if (autor.isPresent()) {
            return ResponseEntity.ok(autor.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/novo")
    public ResponseEntity<Autor> novo(@RequestBody AutorDTO autorDTO) {
        try {
            Pessoa pessoa;
            
            // Verifica se foi informado um ID de pessoa existente
            if (autorDTO.getPessoaId() != null) {
                Optional<Pessoa> pessoaExistente = pessoaRepository.findById(autorDTO.getPessoaId());
                if (pessoaExistente.isPresent()) {
                    pessoa = pessoaExistente.get();
                    // Atualiza os dados da pessoa se foram informados
                    if (autorDTO.getNome() != null) {
                        pessoa.setNome(autorDTO.getNome());
                    }
                    if (autorDTO.getEmail() != null) {
                        pessoa.setEmail(autorDTO.getEmail());
                    }
                    pessoa = pessoaRepository.save(pessoa);
                } else {
                    return ResponseEntity.badRequest().build(); // Pessoa não encontrada
                }
            } else {
                // Cria nova pessoa
                if (autorDTO.getNome() == null || autorDTO.getEmail() == null) {
                    return ResponseEntity.badRequest().build(); // Nome e email são obrigatórios para nova pessoa
                }
                pessoa = new Pessoa(autorDTO.getNome(), autorDTO.getEmail());
                pessoa = pessoaRepository.save(pessoa);
            }
            
            // Cria o autor
            Autor autor = new Autor();
            autor.setBiografia(autorDTO.getBiografia());
            autor.setPessoaId(pessoa.getId());
            
            Autor autorSalvo = autorRepository.save(autor);
            return ResponseEntity.status(HttpStatus.CREATED).body(autorSalvo);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Autor> atualizar(
            @PathVariable(value = "id") Integer id,
            @RequestBody AutorDTO autorDTO) {
        
        Optional<Autor> autorOptional = autorRepository.findById(id);
        if (!autorOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        try {
            Autor autorExistente = autorOptional.get();
            
            // Atualiza dados da pessoa se fornecidos
            if (autorExistente.getPessoaId() != null) {
                Optional<Pessoa> pessoaOptional = pessoaRepository.findById(autorExistente.getPessoaId());
                if (pessoaOptional.isPresent()) {
                    Pessoa pessoa = pessoaOptional.get();
                    boolean pessoaAtualizada = false;
                    
                    if (autorDTO.getNome() != null) {
                        pessoa.setNome(autorDTO.getNome());
                        pessoaAtualizada = true;
                    }
                    if (autorDTO.getEmail() != null) {
                        pessoa.setEmail(autorDTO.getEmail());
                        pessoaAtualizada = true;
                    }
                    
                    if (pessoaAtualizada) {
                        pessoaRepository.save(pessoa);
                    }
                }
            } else if (autorDTO.getNome() != null && autorDTO.getEmail() != null) {
                // Cria nova pessoa se não existia antes
                Pessoa novaPessoa = new Pessoa(autorDTO.getNome(), autorDTO.getEmail());
                novaPessoa = pessoaRepository.save(novaPessoa);
                autorExistente.setPessoaId(novaPessoa.getId());
            }
            
            // Atualiza dados do autor
            if (autorDTO.getBiografia() != null) {
                autorExistente.setBiografia(autorDTO.getBiografia());
            }
            
            Autor autorAtualizado = autorRepository.save(autorExistente);
            return ResponseEntity.ok(autorAtualizado);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> remover(@PathVariable("id") Integer id) {
        Optional<Autor> autor = autorRepository.findById(id);
        if (autor.isPresent()) {
            autorRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}