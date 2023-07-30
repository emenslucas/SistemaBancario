package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ConnectionManager<T> {

	private static ConnectionManager<?> instance;

	private Connection con;

	private ConnectionManager() {
		crearConexion();
	}

	private void crearConexion() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String puerto = "3307";
			String base = "tpfinal";
			String url = "jdbc:mysql://localhost:" + puerto + "/" + base;
			String usuario = "root";
			String contrasena = "";
			con = DriverManager.getConnection(url, usuario, contrasena);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public Integer insertOrUpdate(String consulta, BaseMapper<T> mapper, T objeto) {
		Integer resultado = 0;
		try {
			PreparedStatement prepareStatement = this.con.prepareStatement(consulta);
			prepareStatement = mapper.validarParametros(prepareStatement, objeto);
			resultado = prepareStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultado;
	}

	public ResultSet selectParametrizado(String consulta, BaseMapper<T> mapper, T objeto) {

		ResultSet resultSet = null;
		try {
			PreparedStatement prepareStatement = this.con.prepareStatement(consulta);
			prepareStatement = mapper.validarParametros(prepareStatement, objeto);
			resultSet = prepareStatement.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultSet;
	}

	public List<T> select(String consulta, BaseMapper<T> mapper) {
		List<T> resultado = null;
		try {
			Statement statement = this.con.createStatement();
			ResultSet result = statement.executeQuery(consulta);

			resultado = mapper.toList(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}

	public static <T> ConnectionManager<T> getInstance() {
		if (instance == null) {
			instance = new ConnectionManager<>();
		}
		return (ConnectionManager<T>) instance;
	}

}
