package com.aula.demo.models;

import com.aula.demo.dtos.ProdutoDto;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_produtos")
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false, length = 80)
	private String nome;
	private int quantidade;
	@Column(nullable = false, length = 80)
	private String descricao;
	@Column(nullable = false)
	private float preco;

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	@ManyToOne
	@JoinColumn(name="pedido_id", nullable=false)
	private Pedido pedido;

	public Produto() {
	}

	public Produto(String nome, int quantidade, String descricao, float preco) {
		this.nome = nome;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.preco = preco;
	}

	public static Produto fromDto(ProdutoDto produtoDto) {
		return new Produto(produtoDto.nome(), produtoDto.quantidade(), produtoDto.descricao(), produtoDto.preco());
	}


	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public float getPreco() {
		return preco;
	}
	public void setPreco(float preco) {
		this.preco = preco;
	}

}
