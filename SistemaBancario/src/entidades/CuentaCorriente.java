package entidades;

public class CuentaCorriente extends Cuenta{

	public CuentaCorriente(int nroCuenta, double saldo, TipoMoneda tipoMoneda) {
		super(nroCuenta, saldo, tipoMoneda);
	}
	
	public CuentaCorriente() {
		super();
	}

	@Override
	public void extraer(double monto) {
		this.setSaldo(this.getSaldo() - monto);
		
	}

	
	
}
