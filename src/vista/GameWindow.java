package vista;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import controlador.Controlador;
import controlador.Teclado;

public class GameWindow extends JFrame {
	
	private static int ANCHO = 800;
	private static int LARGO = 800;	
	private Canvas canvas;
	
	private BufferStrategy bs;
	private Graphics g;
	private Controlador controlador;
	private Teclado teclado;
	
	public GameWindow () {
		setTitle("OlaKüeAçe");
		setSize(ANCHO, LARGO);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		canvas = new Canvas();
		
		canvas.setPreferredSize(new Dimension(ANCHO, LARGO));
		canvas.setMaximumSize(new Dimension (ANCHO, LARGO));
		canvas.setMinimumSize(new Dimension (ANCHO, LARGO));
		canvas.setFocusable(true);
		
		add(canvas);
		canvas.addKeyListener(teclado);
	}
	
	public void setControlador (Controlador controlador) {
		this.controlador = controlador;
	}
	
	public void dibujar() {
		bs = canvas.getBufferStrategy();
		
		if (bs == null) {
			canvas.createBufferStrategy(3);
			return;
		}
		
		g = bs.getDrawGraphics();
		
		//-----
		g.setColor(Color.BLACK);
		controlador.getGameState().draw(g);
		g.drawString(Integer.toString(Controlador.FPS_PROMEDIO), 10, 10);
		
		//-----
		
		g.dispose();
		bs.show(); 		
	}	
}
