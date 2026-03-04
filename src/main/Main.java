package main;

import controlador.Controlador;
import vista.GameWindow;

public class Main {
	public static void main(String[] args) {
		GameWindow ventana = new GameWindow();
		Controlador controlador = new Controlador(ventana);
		ventana.setControlador(controlador);
		controlador.start();
	}
}
