package br.com.fiap.brindes.resources;

import java.util.List;
import java.util.Set;
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

import br.com.fiap.brindes.dtos.request.LojaRequest;
import br.com.fiap.brindes.dtos.request.ProdutoRequest;
import br.com.fiap.brindes.dtos.response.LojaResponse;
import br.com.fiap.brindes.entity.Loja;
import br.com.fiap.brindes.entity.Produto;
import br.com.fiap.brindes.service.LojaService;
import br.com.fiap.brindes.service.ProdutoService;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/lojas")
public class LojaResource implements ResourceDTO<LojaRequest, LojaResponse> {
    @Autowired
    private LojaService service;

    @Autowired
    private ProdutoService produtoService;

    @Override
    @PostMapping
    public ResponseEntity<LojaResponse> save(@RequestBody LojaRequest request) {
        Loja loja = service.toEntity(request);
        loja = service.save(loja);
        LojaResponse response = service.toResponse(loja);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<LojaResponse> findById(@PathVariable Long id) {
        Loja loja = service.findById(id);
        LojaResponse response = service.toResponse(loja);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<LojaResponse>> listarLojas() {
        List<LojaResponse> responses = service.findAll().stream()
                .map(service::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @Transactional
    @PostMapping("/{id}/produtos-comercializados")
    public ResponseEntity<Void> adicionarProduto(@PathVariable Long id, @RequestBody ProdutoRequest request) {
        Loja loja = service.findById(id);

        Produto produto = produtoService.toEntity(request);
        produto = produtoService.save(produto);

        Set<Produto> produtosComercializados = loja.getProdutosComercializados();
        produtosComercializados.add(produto);
        loja.setProdutosComercializados(produtosComercializados);

        service.save(loja);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
