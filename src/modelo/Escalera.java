package modelo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Escalera extends GameObject {

    public Escalera(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public boolean contiene(int px, int py) {
        return getBounds().contains(px, py);
    }

    public Rectangle getBounds() {
        return new Rectangle((int) posicion.getX(), (int) posicion.getY(), width, height);
    }

    @Override
    public void update() {}

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect((int) posicion.getX(), (int) posicion.getY(), width, height);
        // Peldaños
        g.setColor(Color.DARK_GRAY);
        for (int i = 0; i < height; i += 12) {
            g.drawLine((int) posicion.getX(), (int) posicion.getY() + i,
                       (int) posicion.getX() + width, (int) posicion.getY() + i);
        }
    }
}