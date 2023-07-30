package vistas;

import java.util.Scanner;

import controllers.SessionController;
import controllers.UsuarioController;
import entidades.Usuario;

public class CambioPinView {

	public static void iniciar(Scanner scanner) {
		try {
			SessionController session = SessionController.getSession();
			Usuario usuario = session.getUsuario();
			String pinActual = usuario.getPin();
			int chances = 0;
			boolean coincide = false;
			System.out.println("Cambio Pin");
			System.out.println("----------");
			scanner.nextLine();
			String msjConfirmacion = "Pin cambiado exitosamente";
			String msjError = "No se pudo hacer el cambio";
			do {
				System.out.println(chances == 0 ? "Ingrese su pin actual"
						: "Pin Incorrecto. Ingrese nuevamente (te quedan " + (3 - chances) + " intento/s)");
				String pinActualIngresado = scanner.nextLine();
				coincide = pinActualIngresado.equals(pinActual);
				chances++;
			} while (!coincide && chances < 3);
			if (coincide) {
				System.out.println("Ingresá tu nuevo pin de 4 digitos");
				String nuevoPin = scanner.nextLine();
				chances = 1;
				while (nuevoPin.length() != 4 && chances < 3) {
					System.out.println("El pin debe ser de 4 dígitos. Ingrese nuevamente (te quedan " + (3 - chances)
							+ " intento/s)");
					nuevoPin = scanner.nextLine();
					chances++;
				}
				if (chances == 3) {
					System.out.println(msjError);
					MenuView.iniciar();
					return;
				}

				System.out.println("Ingrese nuevamente su nuevo pin");
				chances = 1;
				String confirmacionPin = scanner.nextLine();
				while (!confirmacionPin.equals(nuevoPin) && chances < 3) {
					System.out.println("Los pin deben coincidir (te quedan " + (3 - chances) + " intento/s)");
					confirmacionPin = scanner.nextLine();
					chances++;
				}
				if (chances < 3) {
					usuario.setPin(nuevoPin);
					UsuarioController.getInstance().update(usuario);
					System.out.println(msjConfirmacion);
				} else {
					System.out.println(msjError);
				}
			} else {
				System.out.println(msjError);
			}

			MenuView.iniciar();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			MenuView.iniciar();
		}
	}
}
