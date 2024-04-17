package com.aula.demo.services;

import com.aula.demo.dtos.ProdutoDto;
import com.aula.demo.models.Produto;
import com.aula.demo.repositories.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Page<ProdutoDto> findAll(Pageable pagination) {
        return produtoRepository.findAll(pagination).map(ProdutoDto::new);
    }

    @Transactional
    public ProdutoDto save(ProdutoDto produtoDto){
        Produto produto = Produto.fromDto(produtoDto);
        Produto produtoSalvo = produtoRepository.save(produto);
        return new ProdutoDto(produtoSalvo);
    }

    @Transactional
    public void delete(long id) {
        produtoRepository.deleteById(id);
    }

    public ProdutoDto findById(long id) {
        Produto produto = produtoRepository.getReferenceById(id);
        return new ProdutoDto(produto);
    }

    @Transactional
    public ProdutoDto update(long id, ProdutoDto produtoDto) {
        Produto produto = Produto.fromDto(produtoDto);
        produto.setId(id);
        Produto produtoSalvo = produtoRepository.save(produto);
        return new ProdutoDto(produtoSalvo);
    }
}
