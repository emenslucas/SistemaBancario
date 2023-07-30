package vistas;

import java.util.Scanner;

import controllers.CuentaController;
import controllers.SessionController;
import controllers.TransaccionController;
import entidades.Cuenta;
import entidades.TipoMoneda;
import entidades.TipoTransaccion;
import entidades.Transaccion;

public class TransferenciaView {
	public TransferenciaView() {

	}

	public static void iniciar(Scanner scanner) {
		try {
			SessionController session = SessionController.getSession();
			CuentaController cuentaController = CuentaController.getInstance();
			System.out.println("Transferencia");
			System.out.println("------");
			System.out.println("Ingrese el numero de cuenta al que desea transferir");
			int nroCuenta = scanner.nextInt();

			Cuenta cuentaDestino = cuentaController.getById(nroCuenta);
			Cuenta cuentaOrigen = session.getCuenta();

			while (cuentaDestino == null || cuentaDestino.getNroCuenta() == cuentaOrigen.getNroCuenta()
					|| cuentaDestino.getTipoMoneda() != cuentaOrigen.getTipoMoneda()) {
			
				String mensaje;
				if(cuentaDestino == null) {
					mensaje = "No se pudo encontrar la cuenta. Ingrese nuevamente";
				}
				else if (cuentaDestino.getTipoMoneda() != cuentaOrigen.getTipoMoneda()) {
					String aux = cuentaDestino.getTipoMoneda() == TipoMoneda.ARS ? "ARS" : "USD";
					mensaje = "No podés transferir a una cuenta en " + aux + ". Ingresá nuevamente";
				} else if (cuentaDestino.getNroCuenta() == cuentaOrigen.getNroCuenta()) {
					mensaje = "No te podés transferir a vos mismo. Ingresá nuevamente";
				} else {
					mensaje = "No se pudo encontrar la cuenta. Ingrese nuevamente";
				}
				System.out.println(mensaje);
				nroCuenta = scanner.nextInt();
				cuentaDestino = cuentaController.getById(nroCuenta);
			}

			double saldoActual = cuentaOrigen.getSaldo();
			System.out.println("Ingrese la cantidad a transferir");
			double cantidad = scanner.nextDouble();
			while (cantidad < 1 || cantidad > saldoActual) {
				System.out.println("Monto inválido. Intente nuevamente");
				cantidad = scanner.nextDouble();
			}

			cuentaOrigen.extraer(cantidad);
			cuentaDestino.depositar(cantidad);

			cuentaController.update(cuentaOrigen);
			cuentaController.update(cuentaDestino);

			TransaccionController transaccionController = TransaccionController.getInstance();
			transaccionController.insert(new Transaccion(TipoTransaccion.TRANSFERENCIA,
					TransaccionController.fechaNow(), cantidad, cuentaOrigen, cuentaDestino));

			System.out.println("Transferencia exitosa");

			MenuView.iniciar();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			MenuView.iniciar();
		}
	}
}
