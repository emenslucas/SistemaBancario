package entidades;

import java.sql.Date;

public class Transaccion {
	private Integer nroTransaccion;
	private TipoTransaccion tipoTransaccion;
	private Date fecha;
	private Double monto;
	private Cuenta cuentaOrigen;
	private Cuenta cuentaDestino;

	public Transaccion(TipoTransaccion tipo, Date fecha, Double monto, Cuenta cuentaOrigen) {
		this.tipoTransaccion = tipo;
		this.monto = monto;
		this.fecha = fecha;
		this.cuentaOrigen = cuentaOrigen;
		this.cuentaDestino = null;
	}

	public Transaccion() {
		this.cuentaOrigen = null;
		this.fecha = null;
		this.cuentaDestino = null;
	}

	public Transaccion(TipoTransaccion tipo, Date fecha, Double monto, Cuenta cuentaOrigen, Cuenta cuentaDestino) {
		this.tipoTransaccion = tipo;
		this.monto = monto;
		this.cuentaOrigen = cuentaOrigen;
		this.fecha = fecha;
		this.cuentaDestino = cuentaDestino;
	}

	public Integer getNroTransaccion() {
		return nroTransaccion;
	}

	public void setNroTransaccion(Integer nroTransaccion) {
		this.nroTransaccion = nroTransaccion;
	}

	public TipoTransaccion getTipoTransaccion() {
		return tipoTransaccion;
	}

	public void setTipoTransaccion(TipoTransaccion tipoTransaccion) {
		this.tipoTransaccion = tipoTransaccion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public Cuenta getCuentaOrigen() {
		return cuentaOrigen;
	}

	public void setCuentaOrigen(Cuenta nroCuentaOrigen) {
		this.cuentaOrigen = nroCuentaOrigen;
	}

	public Cuenta getCuentaDestino() {
		return cuentaDestino;
	}

	public void setCuentaDestino(Cuenta nroCuentaDestino) {
		this.cuentaDestino = nroCuentaDestino;
	}

}
