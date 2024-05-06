package br.com.fiap.brindes.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import br.com.fiap.brindes.dtos.request.ProdutoRequest;
import br.com.fiap.brindes.dtos.response.CategoriaResponse;
import br.com.fiap.brindes.dtos.response.ProdutoResponse;
import br.com.fiap.brindes.entity.Produto;
import br.com.fiap.brindes.repository.CategoriaRepository;
import br.com.fiap.brindes.repository.Produtorepository;

@Service
public class ProdutoService implements ServiceDTO<Produto, ProdutoRequest, ProdutoResponse> {

  @Autowired
  private Produtorepository repository;

  @Autowired
  private CategoriaRepository categoriaRepository;

  @Override
  public Collection<Produto> findAll() {
    return repository.findAll();
  }

  @Override
  public Collection<Produto> findAll(Example<Produto> example) {
    return repository.findAll(example);
  }

  @Override
  public Produto findById(Long id) {
    return repository.findById(id)
        .orElseThrow(() -> new RuntimeException("Produto com ID: " + id + " não encontrado"));
  }

  @Override
  public Produto save(Produto produto) {
    return repository.save(produto);
  }

  @Override
  public Produto toEntity(ProdutoRequest dto) {
    Produto produto = new Produto();
    produto.setNome(dto.nome());
    produto.setPreco(dto.preco());
    produto.setCategoria(categoriaRepository.findById(dto.categoria().id())
        .orElseThrow(() -> new RuntimeException("Categoria não encontrada")));
    return produto;
  }

  @Override
  public ProdutoResponse toResponse(Produto produto) {
    return new ProdutoResponse(
        produto.getId(),
        produto.getNome(),
        produto.getPreco(),
        new CategoriaResponse(produto.getCategoria().getId(), produto.getCategoria().getNome()));
  }
}

