package br.com.fiap.brindes.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import br.com.fiap.brindes.dtos.request.CategoriaRequest;
import br.com.fiap.brindes.dtos.response.CategoriaResponse;
import br.com.fiap.brindes.entity.Categoria;
import br.com.fiap.brindes.repository.CategoriaRepository;

@Service
public class CategoriaService implements ServiceDTO<Categoria, CategoriaRequest, CategoriaResponse>{
  
  @Autowired
  private CategoriaRepository repository;

  @Override
  public Collection<Categoria> findAll() {
    return repository.findAll();
  }

  @Override
  public Collection<Categoria> findAll(Example<Categoria> example) {
    return repository.findAll(example);
  }

  @Override
  public Categoria findById(Long id) {
    return repository.findById(id)
      .orElseThrow(() -> new RuntimeException("Categoria com ID: " + id + " não encontrada"));
  }

  @Override
  public Categoria save(Categoria categoria) {
    return repository.save(categoria);
  }

  @Override
  public Categoria toEntity(CategoriaRequest dto) {
    Categoria categoria = new Categoria();
    categoria.setNome(dto.nome());
    return categoria;
  }

  @Override
  public CategoriaResponse toResponse(Categoria categoria) {
    return new CategoriaResponse(categoria.getId(), categoria.getNome());
  }
}