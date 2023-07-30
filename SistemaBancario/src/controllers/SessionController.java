package controllers;

import entidades.Cuenta;
import entidades.Usuario;

public class SessionController {
	private Usuario usuario;
	private Cuenta cuenta;
	private static SessionController instance;

	private SessionController() {

	}

	public static SessionController getSession() {
		if (instance == null) {
			instance = new SessionController();
		}
		return instance;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public Cuenta getCuenta() {
		return this.cuenta;
	}

	public void login(Usuario user, Cuenta cuenta) {
		this.usuario = user;
		this.cuenta = cuenta;
	}

	public void logout() {
		this.usuario = null;
		this.cuenta = null;
	}

	public boolean sesionActiva() {
		return this.usuario != null && this.cuenta != null;
	}
}
