package br.com.fiap.brindes.dtos.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ProdutoRequest(

  @Positive(message = "O Id deve ser um numero positivo")
  @NotNull(message = "O id não pode ser nulo")
  Long id,
  
  @Size(min = 3, max = 255, message = "O nome deve ter de 3 a 255 caracteres")
  @NotNull(message = "O nome não pode ser nulo")
  String nome,

  @Positive(message = "Preço deve ser positivo")
  @NotNull(message = "Preço não pode ser nulo")
  Double preco,

  @NotNull(message = "Categoria não pode ser nula")
  AbstractRequest categoria
) {

}
