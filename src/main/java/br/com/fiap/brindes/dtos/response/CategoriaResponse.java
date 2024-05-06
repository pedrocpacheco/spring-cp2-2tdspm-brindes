package br.com.fiap.brindes.dtos.response;

import lombok.Builder;

@Builder
public record CategoriaResponse(
        Long id,
        String nome
) {
  
}
