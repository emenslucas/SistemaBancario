package entidades;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
	private Integer id;
	private String nombre;
	private String apellido;
	private Date fechaNacimiento;
	private String pin;
	private List<Cuenta> cuentas;

	public Usuario() {

	}

	public Usuario(Integer id, String nombre, String apellido, Date fechaNacimiento, String pin) throws Exception {
		setId(id);
		setNombre(nombre);
		setApellido(apellido);
		setFechaNacimiento(fechaNacimiento);
		setPin(pin);
		this.cuentas = new ArrayList<>();
	}

	public Usuario(Integer id, String nombre, String apellido, Date fechaNacimiento, String pin, List<Cuenta> cuentas) throws Exception {
		setId(id);
		setNombre(nombre);
		setApellido(apellido);
		setFechaNacimiento(fechaNacimiento);
		setPin(pin);
		setCuentas(cuentas);
}

	public void addCuenta(Cuenta cuenta) {
		this.cuentas.add(cuenta);
	}

	public List<Cuenta> getCuentas() {
		return this.cuentas;
	}

	public void setCuentas(List<Cuenta> lista) {
		this.cuentas = lista;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public void setPin(String pin) throws Exception {
		if (pin.length() != 4) {
			throw new Exception("El pin debe ser de 4 dígitos");
		}
		this.pin = pin;
	}

	public String getPin() {
		return this.pin;
	}

	public boolean getCuenta(int nroCuenta) {
		boolean enc = false;
		int i = 0;
		while (i < this.cuentas.size() && !enc) {
			if (this.cuentas.get(i).getNroCuenta() == nroCuenta) {
				enc = true;
			}
			i++;
		}
		return enc;
	}

}
