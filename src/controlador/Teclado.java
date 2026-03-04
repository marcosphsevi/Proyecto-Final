package controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Teclado implements KeyListener {
	public Teclado () {
		
	}



	@Override
	public void keyPressed(KeyEvent e) {	//Que tecla fue presionada
		// TODO Auto-generated method stub
		System.out.println(e.getExtendedKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {	//Cuando se suelta la tecla
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}
	
}
