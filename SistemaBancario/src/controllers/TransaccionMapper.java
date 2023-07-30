package controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entidades.TipoTransaccion;
import entidades.Transaccion;

public class TransaccionMapper implements BaseMapper<Transaccion> {

	@Override
	public Transaccion toEntity(ResultSet resultSet) {
		Transaccion transaccion = new Transaccion();
		try {
			transaccion.setNroTransaccion(resultSet.getInt("nro_transaccion"));
			transaccion.setTipoTransaccion(toTipoTransaccion(resultSet.getInt("tipo_transaccion")));
			transaccion.setFecha(resultSet.getDate("fecha"));
			transaccion.setMonto(resultSet.getDouble("monto"));
			transaccion.setCuentaOrigen(CuentaController.getInstance().getById(resultSet.getInt("nro_cuenta_origen")));
			Integer cuentaDestino = resultSet.getInt("nro_cuenta_destino");
			if (cuentaDestino != 0) {
				transaccion.setCuentaDestino(CuentaController.getInstance().getById(cuentaDestino));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return transaccion;
	}

	public TipoTransaccion toTipoTransaccion(int nro) {
		switch (nro) {
		case 1:
			return TipoTransaccion.DEPOSITO;
		case 2:
			return TipoTransaccion.RETIRO;
		case 3:
			return TipoTransaccion.TRANSFERENCIA;
		default:
			return null;
		}
	}

	public Integer toNroTipoTransaccion(TipoTransaccion tipo) {
		switch (tipo) {
		case DEPOSITO:
			return 1;
		case RETIRO:
			return 2;
		case TRANSFERENCIA:
			return 3;
		default:
			return null;
		}
	}

	@Override
	public List<Transaccion> toList(ResultSet resultSet) {
		List<Transaccion> transacciones = new ArrayList<>();
		try {
			while (resultSet.next()) {
				Transaccion transaccion = toEntity(resultSet);
				transacciones.add(transaccion);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return transacciones;
	}

	@Override
	public PreparedStatement validarParametros(PreparedStatement prepareStatement, Transaccion parametro) {
		try {
			if (parametro.getTipoTransaccion() != null) {
				prepareStatement.setInt(1, toNroTipoTransaccion(parametro.getTipoTransaccion()));
			}
			if (parametro.getFecha() != null) {
				prepareStatement.setDate(2, parametro.getFecha());
			}

			if (parametro.getMonto() != null) {
				prepareStatement.setDouble(3, parametro.getMonto());
			}
			if (parametro.getCuentaOrigen() != null) {
				prepareStatement.setInt(4, parametro.getCuentaOrigen().getNroCuenta());
			}
			if (parametro.getCuentaDestino() != null) {
				prepareStatement.setInt(5, parametro.getCuentaDestino().getNroCuenta());
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prepareStatement;
	}

}
