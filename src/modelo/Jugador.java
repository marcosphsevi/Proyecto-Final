package modelo;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import controlador.Teclado;

public class Jugador extends GameObject {

    private static final float VELOCIDAD   = 4f;
    private static final float FUERZA_SALTO = -14f;
    private static final float GRAVEDAD    = 0.5f;
    private static final int   SUELO       = 500; // Y donde toca el suelo

    private float velY = 0;
    private boolean enElSuelo = false;

    private Teclado teclado;

    public Jugador(Vector2D posicion, BufferedImage texture, Teclado teclado) {
        super(posicion, texture);
        this.teclado = teclado;
    }

    @Override
    public void update() {
        // --- Movimiento horizontal (solo si está en el suelo) ---
        if (enElSuelo) {
            if (teclado.izquierda)       posicion.setX(posicion.getX() - VELOCIDAD);
            else if (teclado.derecha)    posicion.setX(posicion.getX() + VELOCIDAD);

            // Salto
            if (teclado.salto) {
                velY = FUERZA_SALTO;
                enElSuelo = false;
            }
        }

        // --- Física vertical (siempre activa) ---
        velY += GRAVEDAD;
        posicion.setY(posicion.getY() + velY);

        // --- Colisión con el suelo ---
        if (posicion.getY() >= SUELO) {
            posicion.setY(SUELO);
            velY = 0;
            enElSuelo = true;
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(texture, (int) posicion.getX(), (int) posicion.getY(), 64, 64, null);
    }
}