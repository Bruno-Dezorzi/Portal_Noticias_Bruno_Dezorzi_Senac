package br.com.brunodezorzi.api.noticiario.mapperimpl;

import br.com.brunodezorzi.api.noticiario.dto.CategoriaDTO;
import br.com.brunodezorzi.api.noticiario.mapper.CategoriaMapper;
import br.com.brunodezorzi.api.noticiario.model.Categoria;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapperImpl implements CategoriaMapper {

  @Override
  public CategoriaDTO toDTO(Categoria categoria) {
    if (categoria == null) return null;

    CategoriaDTO dto = new CategoriaDTO();
    dto.setId(categoria.getId());
    dto.setNome(categoria.getNome());

    // Se quiser incluir o nome da categoria pai também, pode ser útil para o front
    dto.setCategoriaPaiId(
      categoria.getCategoriaPai() != null
        ? categoria.getCategoriaPai().getId()
        : null
    );

    return dto;
  }

  @Override
  public Categoria toEntity(CategoriaDTO dto, Categoria categoriaPai) {
    if (dto == null) return null;

    Categoria categoria = new Categoria();
    categoria.setNome(dto.getNome());
    categoria.setCategoriaPai(categoriaPai);

    return categoria;
  }
}
/*package br.com.brunodezorzi.api.noticiario.mapperimpl;

import br.com.brunodezorzi.api.noticiario.dto.CategoriaDTO;
import br.com.brunodezorzi.api.noticiario.mapper.CategoriaMapper;
import br.com.brunodezorzi.api.noticiario.model.Categoria;
import org.springframework.stereotype.Component;



@Component
public class CategoriaMapperImpl implements CategoriaMapper {

  @Override
  public CategoriaDTO toDTO(Categoria categoria) {
    if (categoria == null) return null;

    CategoriaDTO dto = new CategoriaDTO();
    dto.setId(categoria.getId());
    dto.setNome(categoria.getNome());
    dto.setDestaque(categoria.getDestaque());
    dto.setDescricao(categoria.getDescricao());
    dto.setCategoriaPaiId(
      categoria.getCategoria() != null ? categoria.getCategoria().getId() : null
    );

    return dto;
  }

  @Override
  public Categoria toEntity(CategoriaDTO dto, Categoria categoriaPai) {
    if (dto == null) return null;

    Categoria categoria = new Categoria();
    categoria.setNome(dto.getNome());
    categoria.setDescricao(dto.getDescricao());
    categoria.setDestaque(dto.getDestaque());
    categoria.setCategoria(categoriaPai);

    return categoria;
  }
}*/
