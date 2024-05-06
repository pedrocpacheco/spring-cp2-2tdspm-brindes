package br.com.fiap.brindes.dtos.response;

import lombok.Builder;

@Builder
public record ProdutoResponse(
  Long id,
  String nome,
  Double preco,
  CategoriaResponse categoria
) {
}