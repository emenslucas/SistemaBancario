package controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import entidades.Cuenta;
import entidades.Usuario;

public class CuentaController implements BaseController<Cuenta>{
	private static CuentaController instance;
	private CuentaController() {

	}
	
	public static CuentaController getInstance() {
		if (instance == null) {
			instance = new CuentaController();
		}
		return instance;
	}

	@Override
	public List<Cuenta> getAll() {
		String consulta = "SELECT * FROM tpfinal.cuenta";
		List<Cuenta> cuentas;
		CuentaMapper mapper = new CuentaMapper();
		ConnectionManager<Cuenta> connectionManager = ConnectionManager.getInstance();
		cuentas= connectionManager.select(consulta, mapper);
		return cuentas;
	}

	@Override
	public Cuenta getById(int id) {
		String consulta = "SELECT * FROM tpfinal.cuenta WHERE nro_cuenta = " + id;
		List<Cuenta> cuentas;
		CuentaMapper mapper = new CuentaMapper();
		ConnectionManager<Cuenta> connectionManager = ConnectionManager.getInstance();
		cuentas = connectionManager.select(consulta, mapper);
		return !cuentas.isEmpty() ? cuentas.get(0) : null;
	}
	
	public Usuario getUserByCuenta(int nroCuenta) {
		List<Usuario> usuarios = UsuarioController.getInstance().getAll();
		boolean enc = false;
		int i = 0;
		Usuario user = null;
		while (!enc && i < usuarios.size()) {
			if(usuarios.get(i).getCuenta(nroCuenta)) {
				user = usuarios.get(i);
				enc = true;
			}
			i++;
		}
		return user;
	}

	@Override
	public int insert(Cuenta cuenta) {
		String query = "INSERT INTO tpfinal.cuenta (saldo, tipo_cuenta, tipo_moneda, usuario) VALUES ( ?, ?, ?, ?)";
		CuentaMapper mapper = new CuentaMapper();
		ConnectionManager<Cuenta> connectionManager = ConnectionManager.getInstance();
		return connectionManager.insertOrUpdate(query,mapper,cuenta);
	}

	@Override
	public Cuenta getByParam(Cuenta cuenta, String consulta) {
		CuentaMapper mapper = new CuentaMapper();
		ConnectionManager<Cuenta> connectionManager = ConnectionManager.getInstance();
		ResultSet resultSet = connectionManager.selectParametrizado(consulta, mapper, cuenta);
		try {
			while (resultSet.next()) {
				cuenta = mapper.toEntity(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cuenta;
	}

	@Override
	public int update(Cuenta cuenta) {
		String query = "UPDATE tpfinal.cuenta SET saldo = ?, tipo_cuenta = ?, tipo_moneda = ?, usuario = ? where "
				+ "nro_cuenta = " + cuenta.getNroCuenta();
		CuentaMapper mapper = new CuentaMapper();
		ConnectionManager<Cuenta> connectionManager = ConnectionManager.getInstance();
		return connectionManager.insertOrUpdate(query,mapper,cuenta);
	}
	
	public List<Cuenta> getByUser(int userId){
		String consulta = "SELECT * FROM tpfinal.cuenta WHERE usuario = " + userId;
		List<Cuenta> cuentas;
		CuentaMapper mapper = new CuentaMapper();
		ConnectionManager<Cuenta> connectionManager = ConnectionManager.getInstance();
		cuentas= connectionManager.select(consulta, mapper);
		return cuentas;
	}
	
}
