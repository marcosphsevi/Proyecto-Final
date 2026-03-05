package modelo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Plataforma extends GameObject {

    public Plataforma(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void update() {
        // Las plataformas no se mueven
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect((int) posicion.getX(), (int) posicion.getY(), width, height);
    }

    public Rectangle getBounds() {
        return new Rectangle((int) posicion.getX(), (int) posicion.getY(), width, height);
    }
}