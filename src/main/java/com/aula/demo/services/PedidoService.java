package com.aula.demo.services;

import com.aula.demo.models.Pedido;
import com.aula.demo.models.Produto;
import com.aula.demo.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aula.demo.dtos.PedidoDto;
import com.aula.demo.enums.StatusPedido;
import com.aula.demo.repositories.PedidoRepository;

import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Page<PedidoDto> findAll(Pageable pagination) {
        return pedidoRepository.findAll(pagination).map(PedidoDto::new);
    }

    public PedidoDto findById(long id) {
        Pedido pedido = pedidoRepository.getReferenceById(id);
        return new PedidoDto(pedido);
    }

    @Transactional
    public PedidoDto save(PedidoDto pedidoDto) {
        List<Pedido> pedidoSalvo = new ArrayList<>();
        for (Produto produto : pedidoDto.produtos()) {
            Pedido pedidoTemp = Pedido.fromDto(pedidoDto);
            pedidoTemp.setStatusPedido(StatusPedido.CRIADO);
            pedidoTemp.setProduto(produto);
            Pedido pedidoSalvo1 =pedidoRepository.save(pedidoTemp);
            pedidoSalvo.add(pedidoSalvo1);
        }
        return new PedidoDto(pedidoSalvo.getFirst());
    }

    @Transactional
    public PedidoDto update(long id, PedidoDto pedidoDto) {
        Pedido pedido = com.aula.demo.models.Pedido.fromDto(pedidoDto);
        pedido.setId(id);
        Pedido pedidosaved = pedidoRepository.save(pedido);
        return new PedidoDto(pedidosaved);
    }

    public void delete(long id) {
        pedidoRepository.deleteById(id);
    }

    @Transactional
    public String atualizarStatus(long id, PedidoDto pedidoDto) {
        Pedido pedido = Pedido.fromDto(pedidoDto);
        pedido.setId(id);
        pedido.setStatusPedido(pedidoDto.status());
        pedidoRepository.save(pedido);
        return "Pedido Atualizado com Sucesso";
    }
}
