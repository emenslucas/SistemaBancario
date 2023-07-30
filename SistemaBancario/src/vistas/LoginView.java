package vistas;

import java.util.Scanner;

import controllers.CuentaController;
import controllers.SessionController;
import entidades.Cuenta;
import entidades.Usuario;

public class LoginView {

	private static final Scanner scanner = new Scanner(System.in);
	
	public static void iniciar() {
		try {
			System.out.println("Inicio Sesión");
			System.out.println("-------------");
			System.out.println("Ingrese su número de cuenta");
			int nroCuenta = scanner.nextInt();
			scanner.nextLine();
			System.out.println("Ingrese el PIN");
			String pin = scanner.nextLine();

			CuentaController cuentaController = CuentaController.getInstance();
			Cuenta cuenta = cuentaController.getById(nroCuenta);
			Usuario usuario = cuentaController.getUserByCuenta(nroCuenta);

			if (cuenta != null && usuario != null && usuario.getCuenta(cuenta.getNroCuenta()) && usuario.getPin().equals(pin)) {
				SessionController session = SessionController.getSession();
				session.login(usuario, cuenta);
				MenuView.iniciar();
			} else {
				System.out.println("Datos incorrectos. Intente nuevamente");
				System.out.println("\n\n");
				iniciar();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
