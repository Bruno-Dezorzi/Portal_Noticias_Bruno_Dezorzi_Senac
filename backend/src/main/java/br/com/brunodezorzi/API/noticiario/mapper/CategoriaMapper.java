package br.com.brunodezorzi.api.noticiario.mapper;

import br.com.brunodezorzi.api.noticiario.dto.CategoriaDTO;
import br.com.brunodezorzi.api.noticiario.model.Categoria;

public interface CategoriaMapper {
  CategoriaDTO toDTO(Categoria categoria);

  Categoria toEntity(CategoriaDTO dto, Categoria categoriaPai);
}
