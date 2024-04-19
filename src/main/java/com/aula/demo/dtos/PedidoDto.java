package com.aula.demo.dtos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.aula.demo.enums.StatusPedido;
import com.aula.demo.models.Pedido;
import com.aula.demo.models.Produto;

public record PedidoDto(long id, LocalDate datapedido, String endereco, StatusPedido status,List<Produto> produtos) {
    public PedidoDto(Pedido pedido) {
        this(pedido.getId(), pedido.getDataPedido(), pedido.getEndereco(), pedido.getStatusPedido(),new ArrayList<>());
    }

}
