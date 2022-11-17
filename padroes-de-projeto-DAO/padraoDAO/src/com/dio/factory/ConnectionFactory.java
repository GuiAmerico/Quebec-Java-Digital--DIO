package com.dio.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class ConnectionFactory {

	private static final String URL = "jdbc:mysql://localhost/dio?verifyServerCertificate=false&useSSL=true";
	private static final String USER = "root";
	private static final String PASSWORD = "4d3c2b1a";

	public static Connection getConnection() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver found");

		} catch (ClassNotFoundException e) {
			System.out.println("Driver not found. " + e.getMessage());
		}

		try {
			Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connected to databse");
			return connection;

		} catch (SQLException e) {
			System.out.println("Not connected to databse." + e.getMessage());
			return null;
		}
	}

}
