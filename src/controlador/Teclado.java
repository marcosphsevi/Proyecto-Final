package controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Teclado implements KeyListener {

    public boolean izquierda, derecha, salto, arriba, abajo;

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:  
            	izquierda = true; 
            	break;
            case KeyEvent.VK_RIGHT: 
            	derecha = true;   
            	break;
            case KeyEvent.VK_SPACE: 
            	salto = true;     
            	break;
            case KeyEvent.VK_UP:   
            	arriba = true;  	  
            	break;
            case KeyEvent.VK_DOWN: 
            	abajo = true;  	  
            	break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:  
            	izquierda = false; 
            	break;
            case KeyEvent.VK_RIGHT: 
            	derecha = false;   
            	break;
            case KeyEvent.VK_SPACE: 
            	salto = false;     
            	break;
            case KeyEvent.VK_UP:   
            	arriba = false; 	   
            	break;
            case KeyEvent.VK_DOWN: 
            	abajo = false; 	   
            	break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}
