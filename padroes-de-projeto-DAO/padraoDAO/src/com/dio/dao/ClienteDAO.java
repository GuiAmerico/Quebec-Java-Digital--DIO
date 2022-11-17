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

public class ClienteDAO {
	public static void save(Cliente cliente) {
		String sql = "INSERT INTO cliente (nome)" + "  VALUES (?)";
		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			conn = ConnectionFactory.getConnection();
			pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstm.setString(1, cliente.getNome());

			pstm.executeUpdate();

			ResultSet rs = pstm.getGeneratedKeys();
			if (rs.next()) {
				final int lastId = rs.getInt(1);
				cliente.setId(lastId);
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
		
		String sql = "DELETE FROM cliente WHERE id = ?";
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

	public static void update(Cliente cliente) {
		Connection conn = null;
		PreparedStatement pstm = null;

		String sql = "UPDATE cliente SET nome = ? WHERE id = ?";
		try {
			conn = ConnectionFactory.getConnection();
			pstm = conn.prepareStatement(sql);

			pstm.setString(1, cliente.getNome());
			pstm.setInt(2, cliente.getId());

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

	public static List<Cliente> getClientes() {
		List<Cliente> clientes = new ArrayList<>();
		PreparedStatement pstm = null;
		Connection conn = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM cliente";

		try {

			conn = ConnectionFactory.getConnection();
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();

			while (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setId(rs.getInt("id"));
				cliente.setNome(rs.getString("nome"));

				clientes.add(cliente);

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

		return clientes;
	}

	public static Cliente getClienteById(int id) throws SQLException {
		Cliente cliente = new Cliente();
		PreparedStatement pstm = null;
		Connection conn = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM cliente WHERE id = ?";

		try {

			conn = ConnectionFactory.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			rs = pstm.executeQuery();

			while (rs.next()) {
				cliente.setId(rs.getInt("id"));
				cliente.setNome(rs.getString("nome"));
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

		return cliente;
	}
}
