package modelo;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;

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
        g.fillRect(x, y, width, height);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}