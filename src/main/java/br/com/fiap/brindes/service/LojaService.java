package br.com.fiap.brindes.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import br.com.fiap.brindes.dtos.request.LojaRequest;
import br.com.fiap.brindes.dtos.response.LojaResponse;
import br.com.fiap.brindes.dtos.response.ProdutoResponse;
import br.com.fiap.brindes.entity.Loja;
import br.com.fiap.brindes.repository.LojaRepository;

@Service
public class LojaService implements ServiceDTO<Loja, LojaRequest, LojaResponse> {
  @Autowired
  private LojaRepository repository;

  @Autowired
  private ProdutoService produtoService;

  @Override
  public Collection<Loja> findAll() {
    return repository.findAll();
  }

  @Override
  public Collection<Loja> findAll(Example<Loja> example) {
    return repository.findAll(example);
  }

  @Override
  public Loja findById(Long id) {
    return repository.findById(id)
        .orElseThrow(() -> new RuntimeException("Loja com ID: " + id + " não encontrada"));
  }

  @Override
  public Loja save(Loja loja) {
    return repository.save(loja);
  }

  @Override
  public Loja toEntity(LojaRequest dto) {
    Loja loja = new Loja();
    loja.setNome(dto.nome());
    return loja;
  }

  @Override
  public LojaResponse toResponse(Loja loja) {
    Collection<ProdutoResponse> produtosComercializados = loja.getProdutosComercializados()
        .stream()
        .map(produtoService::toResponse)
        .collect(Collectors.toList());

    return new LojaResponse(
        loja.getId(),
        loja.getNome(),
        produtosComercializados
    );
  }
}

