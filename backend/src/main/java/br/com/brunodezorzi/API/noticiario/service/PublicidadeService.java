package br.com.brunodezorzi.api.noticiario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.brunodezorzi.api.noticiario.model.Publicidade;
import br.com.brunodezorzi.api.noticiario.repository.PublicidadeRepository;

@Service
public class PublicidadeService {

    @Autowired
    private PublicidadeRepository publicidadeRepository;

    public List<Publicidade> listarPorPosicao(String nomePosicao) {
        return publicidadeRepository.findByPosicao_NomeIgnoreCase(nomePosicao);
    }

    public List<Publicidade> listarTodas() {
        return publicidadeRepository.findAll();
    }
}
