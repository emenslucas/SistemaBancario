package controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidades.CajaAhorro;
import entidades.Cuenta;
import entidades.CuentaCorriente;
import entidades.TipoMoneda;

public class CuentaMapper implements BaseMapper<Cuenta> {

	@Override
	public Cuenta toEntity(ResultSet resultSet) {
		Cuenta cuenta = null;
		try {
			cuenta = toTipoCuenta(resultSet.getInt("tipo_cuenta"));
			cuenta.setNroCuenta(resultSet.getInt("nro_cuenta"));
			cuenta.setSaldo(resultSet.getDouble("saldo"));
			cuenta.setTipoMoneda(toTipoMoneda(resultSet.getInt("tipo_moneda")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cuenta;
	}

	public Cuenta toTipoCuenta(int nro) {
		switch (nro) {
		case 1:
			return new CajaAhorro();
		case 2:
			return new CuentaCorriente();
		default:
			return null;
		}
	}

	public TipoMoneda toTipoMoneda(int nro) {
		switch (nro) {
		case 1:
			return TipoMoneda.ARS;
		case 2:
			return TipoMoneda.US;
		default:
			return null;
		}
	}

	public Integer toNroTipoCuenta(Cuenta cuenta) {
		if (cuenta instanceof CajaAhorro) {
			return 1;
		} else if (cuenta instanceof CuentaCorriente) {
			return 2;
		} else {
			return null;
		}
	}

	public Integer toNroTipoMoneda(TipoMoneda tipo) {
		switch (tipo) {
		case ARS:
			return 1;
		case US:
			return 2;
		default:
			return null;
		}
	}

	@Override
	public List<Cuenta> toList(ResultSet resultSet) {
		List<Cuenta> cuentas = new ArrayList<>();

		try {
			while (resultSet.next()) {
				Cuenta cuenta = toEntity(resultSet);
				cuentas.add(cuenta);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return cuentas;
	}

	@Override
	public PreparedStatement validarParametros(PreparedStatement prepareStatement, Cuenta parametro) {
		try {
			if (parametro.getSaldo() != null && parametro.getTipoMoneda() != null) {
                prepareStatement.setDouble(1, parametro.getSaldo());
                prepareStatement.setInt(2, toNroTipoCuenta(parametro));
                prepareStatement.setInt(3, toNroTipoMoneda(parametro.getTipoMoneda()));
                prepareStatement.setInt(4, CuentaController.getInstance().getUserByCuenta(parametro.getNroCuenta()).getId());
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prepareStatement;
	}

}
