package controllers;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import entidades.Transaccion;


public class TransaccionController implements BaseController<Transaccion>{
	
	private static TransaccionController instance;
	
	private TransaccionController() {
		
	}
	
	public static TransaccionController getInstance() {
		if(instance ==null) {
			instance = new TransaccionController();
		}
		return instance;
	}
	@Override
	public List<Transaccion> getAll() {
		String consulta = "SELECT * FROM tpfinal.transaccion";
		List<Transaccion> transacciones;
		TransaccionMapper mapper = new TransaccionMapper();
		ConnectionManager<Transaccion> connectionManager = ConnectionManager.getInstance();
		transacciones= connectionManager.select(consulta, mapper);
		return transacciones;
	}

	@Override
	public Transaccion getById(int id) {
		String consulta = "SELECT * FROM tpfinal.transaccion where transaccion.nro_transaccion = " + id;
		Transaccion aux = new Transaccion();
		aux.setNroTransaccion(id);
		return getByParam(aux, consulta);
	}

	@Override
	public int insert(Transaccion transaccion) {
		String aux1="";
		String aux2 ="";
		if(transaccion.getCuentaDestino() != null) {
			aux1 = ", nro_cuenta_destino";
			aux2 = ", ?";
		}
		String query = "INSERT INTO tpfinal.transaccion (tipo_transaccion, fecha, monto, nro_cuenta_origen" + aux1 + ") VALUES ( ?, ?, ?, ?" + aux2 + ")";
		TransaccionMapper mapper = new TransaccionMapper();
		ConnectionManager<Transaccion> connectionManager = ConnectionManager.getInstance();
		return connectionManager.insertOrUpdate(query,mapper,transaccion);
	}

	@Override
	public Transaccion getByParam(Transaccion transaccion, String consulta) {
		TransaccionMapper mapper = new TransaccionMapper();
		ConnectionManager<Transaccion> connectionManager = ConnectionManager.getInstance();
		ResultSet resultSet = connectionManager.selectParametrizado(consulta, mapper, transaccion);
		try {
			while (resultSet.next()) {
				transaccion = mapper.toEntity(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return transaccion;
	}

	@Override
	public int update(Transaccion transaccion) {
		String aux="";
		if(transaccion.getCuentaDestino() != null) aux = ", nro_cuenta_destino = ?";
		String query = "UPDATE tpfinal.transaccion SET tipo_transaccion = ?, fecha= ?, monto = ?, nro_cuenta_origen= ?" + aux +" where "
				+ "nro_transaccion = " + transaccion.getNroTransaccion();
		TransaccionMapper mapper = new TransaccionMapper();
		ConnectionManager<Transaccion> connectionManager = ConnectionManager.getInstance();
		return connectionManager.insertOrUpdate(query,mapper,transaccion);
	}
	
	public static Date fechaNow() {
		return new Date(System.currentTimeMillis());
	}

}
