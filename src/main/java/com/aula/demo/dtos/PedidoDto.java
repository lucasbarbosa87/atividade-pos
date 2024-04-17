package com.aula.demo.dtos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.aula.demo.enums.StatusPedido;
import com.aula.demo.models.Pedido;
import com.aula.demo.models.Produto;

public record PedidoDto(long id, LocalDate datapedido, String endereco, StatusPedido status,
                        List<ProdutoDto> produtos) {
    public PedidoDto(Pedido pedido) {
        this(pedido.getId(), pedido.getDataPedido(), pedido.getEndereco(), pedido.getStatusPedido(), new ArrayList<>());
        addProdutos(pedido.getProdutos());
    }

    private void addProdutos(List<Produto> produtos) {
        for (Produto produto : produtos) {
            produtos().add(new ProdutoDto(produto));
        }
    }

}
