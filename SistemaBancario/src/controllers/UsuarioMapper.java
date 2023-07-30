package controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidades.Usuario;

public class UsuarioMapper implements BaseMapper<Usuario> {

	@Override
	public Usuario toEntity(ResultSet resultSet) {
		Usuario usuario = new Usuario();
		try {
			usuario.setId(resultSet.getInt("id"));
			usuario.setNombre(resultSet.getString("nombre"));
			usuario.setApellido(resultSet.getString("apellido"));
			usuario.setFechaNacimiento(resultSet.getDate("fecha_nacimiento"));
			usuario.setPin(resultSet.getString("pin"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuario;
	}

	@Override
	public List<Usuario> toList(ResultSet resultSet) {
		List<Usuario> usuarios = new ArrayList<>();

		try {
			while (resultSet.next()) {
				Usuario usuario = toEntity(resultSet);
				usuarios.add(usuario);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return usuarios;
	}

	@Override
	public PreparedStatement validarParametros(PreparedStatement prepareStatement, Usuario parametro) {
		try {
			if (parametro.getNombre() != null) {
				prepareStatement.setString(1, parametro.getNombre());
			}
			if (parametro.getApellido() != null) {
				prepareStatement.setString(2, parametro.getApellido());
			}
			if (parametro.getFechaNacimiento() != null) {
				prepareStatement.setDate(3, parametro.getFechaNacimiento());
			}
			if (parametro.getPin() != null) {
				prepareStatement.setString(4, parametro.getPin());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prepareStatement;
	}

}
