package br.com.fiap.brindes.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record LojaRequest(
  
  @Positive(message = "O Id deve ser um numero positivo")
  @NotNull(message = "O id não pode ser nulo")
  Long id,

  @Size(min=1, max=255)
  @NotBlank(message = "O nome não pode ser vazio")
  @NotNull(message = "O nome não pode ser nulo")
  String nome
) {

}
