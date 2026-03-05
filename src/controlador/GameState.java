package controlador;

import java.awt.Graphics;
import modelo.Jugador;
import modelo.Vector2D;
import vista.Assets;
import vista.GameWindow;

public class GameState {

    private Jugador player;
    private Teclado teclado;

    public GameState(Teclado teclado) {
        this.teclado = teclado;
        player = new Jugador(new Vector2D(100, 100), Assets.player, teclado);
    }

    public void update() {
        player.update();
    }

    public void draw(Graphics g) {
        player.draw(g);
    }
}