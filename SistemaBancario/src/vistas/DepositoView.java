package vistas;

import java.util.Scanner;

import controllers.CuentaController;
import controllers.SessionController;
import controllers.TransaccionController;
import entidades.Cuenta;
import entidades.TipoTransaccion;
import entidades.Transaccion;

public class DepositoView {
	public DepositoView() {
		
	}
	
	public static void iniciar(Scanner scanner) {
	try {
		System.out.println("Depósito");
		System.out.println("--------");
		SessionController session = SessionController.getSession();
		System.out.println("¿Que cantidad desea depositar?");
		double cantidad = scanner.nextDouble();
		while (cantidad < 1) {
		    System.out.println("La cantidad no puede ser menor a 1. Ingrese nuevamente");
		    cantidad = scanner.nextDouble();
		}
		Cuenta cuenta = session.getCuenta();
		cuenta.depositar(cantidad);

		CuentaController cuentaController = CuentaController.getInstance();
		cuentaController.update(cuenta);
		
		TransaccionController transaccionController = TransaccionController.getInstance();
		transaccionController.insert(new Transaccion(TipoTransaccion.DEPOSITO, TransaccionController.fechaNow(), cantidad, cuenta));
		
		System.out.println("Monto depositado exitosamente");
		MenuView.iniciar();
	} catch(Exception e) {
		System.out.println(e.getMessage());
		MenuView.iniciar();
	}
	}
}
