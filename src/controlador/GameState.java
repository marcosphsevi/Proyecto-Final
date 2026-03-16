package controlador;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
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

    // ── Princesa ──────────────────────────────────────────────────────
    // Posición: plataforma más alta izquierda → Plataforma(0,182,503,14,0)
    private static final int PRINCESA_X = 315;
    private static final int PRINCESA_Y = 20;   // 84 - 64
    private static final int PRINCESA_W = 60;
    private static final int PRINCESA_H = 64;

    private int  princesaFrame      = 0;
    private int  princesaTicks      = 0;
    private static final int PRINCESA_TICKS = 15; // 0.5 s a 30 fps

    // ── Malo ──────────────────────────────────────────────────────────
    // Posición: segunda plataforma izquierda → Plataforma(0,384,727,14,43)
    private static final int MALO_X = 20;
    private static final int MALO_Y = 40;  // 182 - 64
    private static final int MALO_W = 80;
    private static final int MALO_H = 145;

    private int  maloFrame          = 0;   // 0 = reposo, 1 = lanzando
    private int  maloTicksLanzando  = 0;
    private static final int MALO_TICKS_LANZANDO = 30; // 1 s a 30 fps

    // ── Estado ────────────────────────────────────────────────────────
    private boolean gameOver  = false;
    private boolean victoria  = false;

    public GameState(Teclado teclado) {
        nivel        = new Nivel();
        barriles     = new ArrayList<>();
        player       = new Jugador(new Vector2D(80, 692), Assets.player, teclado,
                                   nivel.getPlataformas(), nivel.getEscaleras());

        // Al spawnear un barril, el malo hace su animación
        spawnManager = new SpawnManager();
    }

    public boolean isGameOver() { return gameOver;  }
    public boolean isVictoria() { return victoria;  }

    public void update() {
        if (gameOver || victoria) return;

        player.update();
        spawnManager.update(barriles);

        // ── Animación princesa ─────────────────────────────────────────
        princesaTicks++;
        if (princesaTicks >= PRINCESA_TICKS) {
            princesaTicks = 0;
            princesaFrame = 1 - princesaFrame; // alterna 0 ↔ 1
        }

        // ── Animación malo ─────────────────────────────────────────────
        if (maloFrame == 1) {
            maloTicksLanzando++;
            if (maloTicksLanzando >= MALO_TICKS_LANZANDO) {
                maloFrame = 0;
            }
        }

        // ── Barriles ───────────────────────────────────────────────────
        Iterator<Barril> it = barriles.iterator();
        while (it.hasNext()) {
            Barril b = it.next();
            b.update(nivel.getPlataformas(), nivel.getEscaleras());
            if (!b.isActivo()) { it.remove(); continue; }

            if (b.getBounds().intersects(player.getBounds())) {
                player.morir();
                gameOver = true;
            }
        }

        // ── Colisión jugador con princesa ──────────────────────────────
        Rectangle rectPrincesa = new Rectangle(PRINCESA_X, PRINCESA_Y, PRINCESA_W, PRINCESA_H);
        if (player.getBounds().intersects(rectPrincesa)) {
            victoria = true;
        }
    }

    public void draw(Graphics g) {
        nivel.draw(g);

        // Dibujar malo
        BufferedImage spriteMalo = Assets.malo != null ? Assets.malo[maloFrame] : null;
        if (spriteMalo != null)
            g.drawImage(spriteMalo, MALO_X, MALO_Y, MALO_W, MALO_H, null);

        // Dibujar princesa
        BufferedImage spritePrincesa = Assets.princesa != null ? Assets.princesa[princesaFrame] : null;
        if (spritePrincesa != null)
            g.drawImage(spritePrincesa, PRINCESA_X, PRINCESA_Y, PRINCESA_W, PRINCESA_H, null);

        for (Barril b : barriles) b.draw(g);
        player.draw(g);
    }
}