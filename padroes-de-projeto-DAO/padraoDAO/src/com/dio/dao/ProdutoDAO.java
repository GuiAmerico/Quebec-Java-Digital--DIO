package com.dio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dio.factory.ConnectionFactory;
import com.dio.model.Produto;

public abstract class ProdutoDAO {

	public static void save(Produto produto) {
		String sql = "INSERT INTO produto (nome,preco)" + "  VALUES (?,?)";
		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			conn = ConnectionFactory.getConnection();
			pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstm.setString(1, produto.getNome());
			pstm.setDouble(2, produto.getPreco());

			pstm.executeUpdate();

			ResultSet rs = pstm.getGeneratedKeys();
			if (rs.next()) {
				final int lastId = rs.getInt(1);
				produto.setId(lastId);
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

		String sql = "DELETE FROM produto WHERE id = ?";
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

	public static void update(Produto produto) {
		Connection conn = null;
		PreparedStatement pstm = null;

		String sql = "UPDATE produto SET nome = ?, preco = ? WHERE id = ?";
		try {
			conn = ConnectionFactory.getConnection();
			pstm = conn.prepareStatement(sql);

			pstm.setString(1, produto.getNome());
			pstm.setDouble(3, produto.getPreco());
			pstm.setInt(2, produto.getId());

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

	public static List<Produto> getProdutos() {
		List<Produto> produtos = new ArrayList<>();
		PreparedStatement pstm = null;
		Connection conn = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM produto";

		try {

			conn = ConnectionFactory.getConnection();
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();

			while (rs.next()) {
				Produto produto = new Produto();
				produto.setId(rs.getInt("id"));
				produto.setNome(rs.getString("nome"));
				produto.setPreco(rs.getDouble("preco"));

				produtos.add(produto);

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

		return produtos;
	}

	public static Produto getProdutoById(int id) throws SQLException {
		Produto produto = new Produto();
		PreparedStatement pstm = null;
		Connection conn = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM produto WHERE id = ?";

		try {

			conn = ConnectionFactory.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			rs = pstm.executeQuery();

			while (rs.next()) {
				produto.setId(rs.getInt("id"));
				produto.setNome(rs.getString("nome"));
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

		return produto;
	}
}
