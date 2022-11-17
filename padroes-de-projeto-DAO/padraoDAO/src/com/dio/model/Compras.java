package com.dio.model;

public class Compras {
	public Compras() {
		// TODO Auto-generated constructor stub
	}

	public Compras(Produto produto) {
		this.produto = produto;
	}

	private Integer id;
	Produto produto;
	Cliente cliente;

	public Integer getId() {
		return id;
		
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	
}

