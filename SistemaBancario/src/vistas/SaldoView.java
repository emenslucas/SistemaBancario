package vistas;

import controllers.SessionController;
import entidades.Cuenta;
import entidades.TipoMoneda;

public class SaldoView {	
	public static void iniciar() {
		try {
			System.out.println("Vista de Saldo");
			System.out.println("--------------");
			SessionController session = SessionController.getSession();
			Cuenta cuenta = session.getCuenta();
			double saldo = cuenta.getSaldo();
			String simbolo = cuenta.getTipoMoneda() == TipoMoneda.ARS ? "ARS$" : "USD$";
			System.out.println("Saldo actual: " + simbolo + saldo);
			System.out.println("---------------");
			MenuView.iniciar();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			MenuView.iniciar();
		}
	}
}
