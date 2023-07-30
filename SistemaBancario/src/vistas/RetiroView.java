package vistas;

import java.util.Scanner;

import controllers.CuentaController;
import controllers.SessionController;
import controllers.TransaccionController;
import entidades.Cuenta;
import entidades.TipoMoneda;
import entidades.TipoTransaccion;
import entidades.Transaccion;

public class RetiroView {

	public static void iniciar(Scanner scanner) {
		try {
			SessionController session = SessionController.getSession();
			System.out.println("Retiro");
			System.out.println("------");
			System.out.println("¿Que cantidad desea retirar?");
			 Cuenta cuenta = session.getCuenta();
			TipoMoneda tipoMoneda = cuenta.getTipoMoneda();
			double minCantidad = (tipoMoneda == TipoMoneda.ARS) ? 100 : 20;
			double maxCantidad = (tipoMoneda == TipoMoneda.ARS) ? 500000 : 50000;

			System.out.println("Cantidad mínima: $" + minCantidad);
			System.out.println("Cantidad máxima: $" + maxCantidad);

			  double cantidad;
	            do {
	                cantidad = scanner.nextDouble();
	                if (cantidad < minCantidad || cantidad > maxCantidad) {
	                    System.out.println("Cantidad fuera de límite. Ingrese nuevamente");
	                }
	            } while (cantidad < minCantidad || cantidad > maxCantidad);

			cuenta.extraer(cantidad);

			CuentaController cuentaController = CuentaController.getInstance();
			cuentaController.update(cuenta);

			TransaccionController transaccionController = TransaccionController.getInstance();
			transaccionController.insert(new Transaccion(TipoTransaccion.RETIRO, TransaccionController.fechaNow(), cantidad, cuenta));

			System.out.println("Monto retirado exitosamente!");
			MenuView.iniciar();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			MenuView.iniciar();
		}

	}
}
