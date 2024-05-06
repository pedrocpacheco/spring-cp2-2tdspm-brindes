package br.com.fiap.brindes.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.brindes.dtos.request.CategoriaRequest;
import br.com.fiap.brindes.dtos.response.CategoriaResponse;
import br.com.fiap.brindes.entity.Categoria;
import br.com.fiap.brindes.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource implements ResourceDTO<CategoriaRequest, CategoriaResponse> {

  @Autowired
  private CategoriaService service;

  @Override
  @PostMapping
  public ResponseEntity<CategoriaResponse> save(@RequestBody CategoriaRequest request) {
    Categoria categoria = service.toEntity(request);
    categoria = service.save(categoria);
    CategoriaResponse response = service.toResponse(categoria);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @Override
  @GetMapping("/{id}")
  public ResponseEntity<CategoriaResponse> findById(@PathVariable Long id) {
    Categoria categoria = service.findById(id);
    CategoriaResponse response = service.toResponse(categoria);
    return ResponseEntity.ok(response);
  }

  @GetMapping
  public ResponseEntity<List<CategoriaResponse>> listarCategorias() {
    List<CategoriaResponse> responses = service.findAll().stream()
        .map(service::toResponse)
        .collect(Collectors.toList());
    return ResponseEntity.ok(responses);
  }
}
