package com.aula.demo.controllers;

import com.aula.demo.dtos.ProdutoDto;
import com.aula.demo.services.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(path = "/produtos",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
public class ProdutoController {

    final private ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public ResponseEntity<Page<ProdutoDto>> findAll(@PageableDefault(size = 5)Pageable pagination){
        return ResponseEntity.ok(produtoService.findAll(pagination));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDto> findById(@PathVariable long id){
        return ResponseEntity.ok(produtoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProdutoDto> save(@RequestBody @Valid ProdutoDto produtoDto, UriComponentsBuilder uriComponentsBuilder){
        ProdutoDto produtoSalvo = produtoService.save(produtoDto);
        URI uri = uriComponentsBuilder.path("/produtos/{id}").buildAndExpand(produtoSalvo.id()).toUri();
        return ResponseEntity.created(uri).body(produtoSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDto> update(@PathVariable long id,@RequestBody ProdutoDto produtoDto){
        return ResponseEntity.ok(produtoService.update(id,produtoDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
