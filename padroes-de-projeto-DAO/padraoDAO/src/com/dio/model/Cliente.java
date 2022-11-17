package com.dio.model;

import java.util.List;

import com.dio.dao.ComprasDAO;

public class Cliente {

	public Cliente() {
	}
	public Cliente(String nome) {
		this.nome = nome;
	}

	private Integer id;
	private String nome;
	private List<Compras> compras;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<?> getCompras() {
		return compras;
	}

	public void setCompras(List<Compras> compras) {
		this.compras = compras;
	}
	
	public void comprar(Produto produto) {
		Compras compra = new Compras();
		compra.setCliente(this);
		compra.setProduto(produto);
		
		ComprasDAO.save(compra);
	}

}
