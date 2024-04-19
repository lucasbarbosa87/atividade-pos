package com.aula.demo.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.aula.demo.dtos.PedidoDto;
import com.aula.demo.dtos.ProdutoDto;
import com.aula.demo.enums.StatusPedido;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate dataPedido;
    private String endereco;
    @Enumerated(EnumType.STRING)
    private StatusPedido statusPedido;
    @ManyToOne
    @JoinColumn(name="produto_id", nullable=false)
    private Produto produto;

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Pedido() {
    }

    public Pedido(LocalDate datapedido, String endereco) {
        this.dataPedido = datapedido;
        this.endereco = endereco;
    }

    public StatusPedido getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(StatusPedido statusPedido) {
        this.statusPedido = statusPedido;
    }

    public static Pedido fromDto(PedidoDto pedidoDto) {
        return new Pedido(pedidoDto.datapedido(), pedidoDto.endereco());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
