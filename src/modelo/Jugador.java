package modelo;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Jugador extends GameObject{
	
	
	public Jugador(Vector2D posicion, BufferedImage texture) {
		super(posicion, texture);

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(texture, (int) posicion.getX(), (int)posicion.getY(), 64, 64, null);
	}
	
}
