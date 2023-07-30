package controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import entidades.Usuario;

public class UsuarioController implements BaseController<Usuario> {
	private static UsuarioController instance;

	private UsuarioController() {

	}

	public static UsuarioController getInstance() {
		if (instance == null) {
			instance = new UsuarioController();
		}
		return instance;
	}

	@Override
	public List<Usuario> getAll() {
		String consulta = "SELECT * FROM tpfinal.usuario";
		UsuarioMapper mapper = new UsuarioMapper();
		ConnectionManager<Usuario> connectionManager = ConnectionManager.getInstance();
		List<Usuario> usuarios = connectionManager.select(consulta, mapper);

		for (Usuario usuario : usuarios) {
			usuario.setCuentas(CuentaController.getInstance().getByUser(usuario.getId()));
		}
		return usuarios;
	}

	@Override
	public Usuario getById(int id) {
		String consulta = "SELECT * FROM tpfinal.usuario where usuario.id = " + id;
		Usuario aux = new Usuario();
		aux.setId(id);
		Usuario usuario = getByParam(aux, consulta);
		usuario.setCuentas(CuentaController.getInstance().getByUser(id));
		return usuario;
	}

	@Override
	public int insert(Usuario usuario) {
		String query = "INSERT INTO tpfinal.usuario (nombre, apellido, fecha_nacimiento, pin) VALUES ( ?, ?, ?, ?)";
		UsuarioMapper mapper = new UsuarioMapper();
		ConnectionManager<Usuario> connectionManager = ConnectionManager.getInstance();
		return connectionManager.insertOrUpdate(query, mapper, usuario);
	}

	@Override
	public Usuario getByParam(Usuario usuario, String consulta) {
		UsuarioMapper mapper = new UsuarioMapper();
		ConnectionManager<Usuario> connectionManager = ConnectionManager.getInstance();
		ResultSet resultSet = connectionManager.selectParametrizado(consulta, mapper, usuario);
		try {
			while (resultSet.next()) {
				usuario = mapper.toEntity(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuario;
	}

	@Override
	public int update(Usuario usuario) {
		String query = "UPDATE tpfinal.usuario SET nombre = ?, apellido = ?, fecha_nacimiento = ?, pin = ? where "
				+ "id = " + usuario.getId();
		UsuarioMapper mapper = new UsuarioMapper();
		ConnectionManager<Usuario> connectionManager = ConnectionManager.getInstance();
		return connectionManager.insertOrUpdate(query, mapper, usuario);
	}

}
