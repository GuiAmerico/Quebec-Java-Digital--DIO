package com.dio.main;

import com.dio.dao.ClienteDAO;
import com.dio.dao.ComprasDAO;
import com.dio.dao.ProdutoDAO;
import com.dio.model.Cliente;
import com.dio.model.Compras;
import com.dio.model.Produto;

public class Main {

	public static void main(String[] args) {
		Cliente cliente = new Cliente("Guilherme");
		ClienteDAO.save(cliente);

		Produto produto = new Produto("Celular", 1880.3);
		ProdutoDAO.save(produto);

		cliente.comprar(produto);

		for (Cliente c : ClienteDAO.getClientes()) {
			System.out.printf("ID => %d || Nome => %s \n", c.getId(), c.getNome());
		}

		for (Produto p : ProdutoDAO.getProdutos()) {
			System.out.printf("ID => %d || Nome => %s || Preço => %f \n", p.getId(), p.getNome(), p.getPreco());
		}

		for (Compras compra : ComprasDAO.getCompras()) {
			System.out.printf("ID => %d || produto_id => %d || cliente_id => %d \n", compra.getId(),
					compra.getProduto().getId(), compra.getCliente().getId());
		}
	}

}
