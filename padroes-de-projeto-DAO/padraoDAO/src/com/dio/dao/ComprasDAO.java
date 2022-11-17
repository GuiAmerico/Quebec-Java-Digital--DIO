package com.dio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dio.factory.ConnectionFactory;
import com.dio.model.Cliente;
import com.dio.model.Compras;
import com.dio.model.Produto;

public abstract class ComprasDAO {
	public static void save(Compras compra) {
		String sql = "INSERT INTO compra (produto_id,cliente_id)" + "  VALUES (?,?)";
		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			conn = ConnectionFactory.getConnection();
			pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstm.setInt(1, compra.getProduto().getId());
			pstm.setInt(2, compra.getCliente().getId());

			pstm.executeUpdate();

			ResultSet rs = pstm.getGeneratedKeys();
			if (rs.next()) {
				final int lastId = rs.getInt(1);
				compra.setId(lastId);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			try {
				if (pstm != null) {
					pstm.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public static void deleteById(int id) {

		String sql = "DELETE FROM compra WHERE id = ?";
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = ConnectionFactory.getConnection();
			pstm = conn.prepareStatement(sql);

			pstm.setInt(1, id);
			pstm.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	public static void update(Compras compras) {
		Connection conn = null;
		PreparedStatement pstm = null;

		String sql = "UPDATE compra SET produto_id = ?, cliente_id WHERE id = ?";
		try {
			conn = ConnectionFactory.getConnection();
			pstm = conn.prepareStatement(sql);

			pstm.setObject(1, compras.getProduto());
			pstm.setInt(2, compras.getCliente().getId());

			pstm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public static List<Compras> getCompras() {
		List<Compras> compras = new ArrayList<>();
		PreparedStatement pstm = null;
		Connection conn = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM compra";

		try {

			conn = ConnectionFactory.getConnection();
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();

			while (rs.next()) {
				Compras compra = new Compras();
				compra.setId(rs.getInt("id"));
				Produto produto = new Produto();
				produto.setId(rs.getInt("produto_id"));
				Cliente cliente = new Cliente();
				cliente.setId(rs.getInt("cliente_id"));
				compra.setCliente(cliente);
				compra.setProduto(produto);

				compras.add(compra);

			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			try {
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		return compras;
	}

	public static Compras getCompraById(int id) throws SQLException {
		Compras compra = new Compras();
		PreparedStatement pstm = null;
		Connection conn = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM compras WHERE id = ?";

		try {

			conn = ConnectionFactory.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			rs = pstm.executeQuery();

			while (rs.next()) {
				compra.setId(rs.getInt("id"));
				Produto produto = new Produto();
				produto.setId(rs.getInt("produto_id"));
				compra.setProduto(produto);
				produto.setPreco(rs.getDouble("preco"));
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			try {
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		return compra;
	}

}
