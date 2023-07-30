package entidades;

public class CajaAhorro extends Cuenta{

	public CajaAhorro(int nroCuenta, double saldo, TipoMoneda tipoMoneda) {
		super(nroCuenta, saldo, tipoMoneda);
	}
	
	public CajaAhorro() {
		super();
	}

	@Override
	public void extraer(double monto) throws Exception {
		double nuevoSaldo = this.getSaldo() - monto;
		if(nuevoSaldo < 0) {
			throw new Exception("No tenés fondos suficientes");
		}
		this.setSaldo(nuevoSaldo);
		
	}
	

}
