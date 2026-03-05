package modelo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;

public class Barril extends GameObject {

    private float velX, velY;
    private boolean enElSuelo;
    private static final float GRAVEDAD = 0.4f;
    private static final float VELOCIDAD = 2.5f;
    private int rotacion = 0;

    public Barril(int x, int y, int direccion) { // direccion: 1 derecha, -1 izquierda
        super(x, y, 24, 24);
        this.velX = VELOCIDAD * direccion;
        this.velY = 0;
        this.enElSuelo = false;
    }

    public boolean isActivo() { return posicion.getY() < 900; } // cae fuera de pantalla

    @Override
    public void update() {}

    // Recibe las plataformas para manejar física
    public void update(List<Plataforma> plataformas) {
        velY += GRAVEDAD;
        posicion.setX(posicion.getX() + velX);
        posicion.setY(posicion.getY() + velY);

        enElSuelo = false;

        for (Plataforma p : plataformas) {
            int px = (int) posicion.getX() + 12; // centro del barril
            int superficieY = p.getYEnX(px);

            boolean dentroX = posicion.getX() + 24 > p.getPosicion().getX()
                           && posicion.getX() < p.getPosicion().getX() + p.width;

            if (dentroX && posicion.getY() + 24 >= superficieY
                        && posicion.getY() + 24 <= superficieY + 16
                        && velY >= 0) {

                posicion.setY(superficieY - 24);
                velY = 0;
                enElSuelo = true;

                // Ajustar velX según inclinación de la plataforma
                float pendiente = p.getInclinacion() / (float) p.width;
                velX = VELOCIDAD * (pendiente >= 0 ? 1 : -1) * (1 + Math.abs(pendiente));
            }
        }

        // Si llega al borde de una plataforma, cae
        rotacion = (rotacion + 5) % 360;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) posicion.getX(), (int) posicion.getY(), 24, 24);
    }

    @Override
    public void draw(Graphics g) {
        int x = (int) posicion.getX();
        int y = (int) posicion.getY();
        g.setColor(new Color(120, 60, 20));
        g.fillOval(x, y, 24, 24);
        g.setColor(Color.GRAY);
        g.drawOval(x, y, 24, 24);
        // Línea que simula rotación
        double rad = Math.toRadians(rotacion);
        g.drawLine(x + 12, y + 12,
                   x + 12 + (int)(10 * Math.cos(rad)),
                   y + 12 + (int)(10 * Math.sin(rad)));
    }
}