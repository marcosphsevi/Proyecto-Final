package modelo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;

public class Barril extends GameObject {

    private float velX, velY;
    private static final float GRAVEDAD  = 0.4f;
    private static final float VELOCIDAD = 3f;
    private static final int   W = 24;
    private static final int   H = 24;
    private int rotacion = 0;

    public Barril(int x, int y) {
        super(x, y, W, H);
        this.velX = VELOCIDAD;
        this.velY = 0;
    }

    public boolean isActivo() {
        return !(posicion.getX() <= 25 && posicion.getY() >= 720);
    }

    @Override
    public void update() {}

    public void update(List<Plataforma> plataformas) {
        velY += GRAVEDAD;
        posicion.setX(posicion.getX() + velX);
        posicion.setY(posicion.getY() + velY);

        int   centroX = (int) posicion.getX() + W / 2;
        float piesY   = (float) posicion.getY() + H;

        for (Plataforma p : plataformas) {
            float platX  = (float) p.getPosicion().getX();
            float platX2 = platX + p.width;
            boolean dentroX = (posicion.getX() + W) > platX
                           && posicion.getX() < platX2;
            if (!dentroX) continue;

            float superficieY = p.getYEnX(centroX);
            float margen = Math.abs(velY) + GRAVEDAD + 2;

            if (velY >= 0 && piesY >= superficieY && piesY <= superficieY + margen) {
                posicion.setY(superficieY - H);
                velY = 0;

                if (p.getInclinacion() != 0) {
                    float pendiente = p.getInclinacion() / (float) p.width;
                    velX = VELOCIDAD * (pendiente >= 0 ? 1 : -1) * (1 + Math.abs(pendiente) * 0.5f);
                }
            }
        }

        if (posicion.getX() <= 0) velX = VELOCIDAD;
        if (posicion.getX() + W >= 784) velX = -VELOCIDAD;

        rotacion = (rotacion + 5) % 360;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) posicion.getX(), (int) posicion.getY(), W, H);
    }

    @Override
    public void draw(Graphics g) {
        int x = (int) posicion.getX();
        int y = (int) posicion.getY();
        g.setColor(new Color(120, 60, 20));
        g.fillOval(x, y, W, H);
        g.setColor(Color.GRAY);
        g.drawOval(x, y, W, H);
        double rad = Math.toRadians(rotacion);
        g.drawLine(x + 12, y + 12,
                   x + 12 + (int)(10 * Math.cos(rad)),
                   y + 12 + (int)(10 * Math.sin(rad)));
    }
}
