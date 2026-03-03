package main;

import controlador.Controlador;
import vista.GameWindow;

public class Main {
	public static void main(String[] args) {
		Controlador controlador = new Controlador(new GameWindow());
		controlador.start();
	}
}
