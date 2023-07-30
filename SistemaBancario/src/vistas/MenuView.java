package vistas;

import java.util.Scanner;

import controllers.SessionController;

public class MenuView {
	 private static final Scanner scanner = new Scanner(System.in);

	public static void iniciar() {
		try {
		System.out.println();
		System.out.println();
		System.out.println("¡Bienvenido a Banco Empreissman! ¿En qué podemos ayudarlo?");
		System.out.println("---------------------------------------------------------");
		System.out.println("1 - Consultar Saldo");
		System.out.println("2 - Retirar");
		System.out.println("3 - Depositar");
		System.out.println("4 - Transferir");
		System.out.println("5 - Cambiar PIN");
		System.out.println("6 - Salir");
		System.out.println("\n\n");
		  
		int opcion = scanner.nextInt();
		
		switch (opcion) {
		case 1:
			SaldoView.iniciar();
			break;
		
		case 2:
			RetiroView.iniciar(scanner);
			break;
			
		case 3:
			DepositoView.iniciar(scanner);
			break;
			
		
		case 4:
			TransferenciaView.iniciar(scanner);
			break;
			
		case 5:
			CambioPinView.iniciar(scanner);
			break;
			
		case 6:
			SessionController session = SessionController.getSession();
			session.logout();
			System.out.println("¡Gracias por confiar en nosotros! Hasta pronto.");
			break;

		default:
			System.out.println("Opción inválida. Intente nuevamente");
			iniciar();
			break;
		}
	} catch(Exception e) {
		System.out.println(e.getMessage());
		iniciar();
	}

	}
}
