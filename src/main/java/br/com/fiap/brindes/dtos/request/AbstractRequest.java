package br.com.fiap.brindes.dtos.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AbstractRequest(
  
  @Positive(message = "O Id deve ser um numero positivo")
  @NotNull(message = "O id não pode ser nulo")
  Long id
  ) {
  
}
