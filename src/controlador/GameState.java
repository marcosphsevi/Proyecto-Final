package controlador;

import java.awt.Graphics;

import modelo.Jugador;
import modelo.Vector2D;
import vista.Assets;

public class GameState {
	
	private Jugador player; 
	
	public GameState () { 
		player = new Jugador(new Vector2D (100, 500), Assets.player);
	}
	
	public void update () {
		
	}
	
	public void draw (Graphics g) {
		player.draw(g);
	}
	
}
