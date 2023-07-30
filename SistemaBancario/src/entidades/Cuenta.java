package entidades;

public abstract class Cuenta implements Deposito, Extraccion{
	private Integer nroCuenta; 
	private Double saldo;
	private TipoMoneda tipoMoneda;
	
	public Cuenta(Integer nroCuenta, Double saldo, TipoMoneda tipoMoneda) {
		this.nroCuenta = nroCuenta;
		this.saldo = saldo;
		this.tipoMoneda = tipoMoneda;
	}
	
	public Cuenta() {
		
	}

	@Override
	public void depositar(double monto) throws Exception {
		if(monto < 1) {
			throw new Exception("El monto a depositar es inválido");
		}
		this.setSaldo(this.saldo + monto);
	}
	
	public TipoMoneda getTipoMoneda() {
		return this.tipoMoneda;
	}
	
	public Double getSaldo() {
		return this.saldo;
	}
	
	public Integer getNroCuenta() {
		return this.nroCuenta;
	}
	
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public void setNroCuenta(Integer nroCuenta) {
		this.nroCuenta = nroCuenta;
	}

	public void setTipoMoneda(TipoMoneda tipoMoneda) {
		this.tipoMoneda = tipoMoneda;
	}
	
	
}
