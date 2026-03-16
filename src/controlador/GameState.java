package controlador;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import modelo.Barril;
import modelo.Jugador;
import modelo.Nivel;
import modelo.Vector2D;
import vista.Assets;

public class GameState {

    private Jugador      player;
    private Nivel        nivel;
    private List<Barril> barriles;
    private SpawnManager spawnManager;

    public GameState(Teclado teclado) {
        nivel        = new Nivel();
        barriles     = new ArrayList<>();
        spawnManager = new SpawnManager();
        player       = new Jugador(new Vector2D(80, 692), Assets.player, teclado,
                                   nivel.getPlataformas(), nivel.getEscaleras());
    }

    public boolean isGameOver() {
        return player.isDead();
    }

    public void update() {
        player.update();
        spawnManager.update(barriles);

        Iterator<Barril> it = barriles.iterator();
        while (it.hasNext()) {
            Barril b = it.next();
            b.update(nivel.getPlataformas(), nivel.getEscaleras());
            if (!b.isActivo()) it.remove();

            if (b.getBounds().intersects(player.getBounds())) {
                player.morir();
            }
        }
    }

    public void draw(Graphics g) {
        nivel.draw(g);
        for (Barril b : barriles) b.draw(g);
        player.draw(g);
    }
}