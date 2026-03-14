package modelo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Plataforma extends GameObject {

    private int inclinacion; // positivo = baja hacia la derecha, negativo = baja hacia la izquierda

    public Plataforma(int x, int y, int width, int height, int inclinacion) {
        super(x, y, width, height);
        this.inclinacion = inclinacion;
    }

    public int getInclinacion() { return inclinacion; }

    // Dado un X, calcula el Y de la superficie en ese punto
    public int getYEnX(int x) {
        int xRelativo = x - (int) posicion.getX();
        float proporcion = (float) xRelativo / width;
        return (int) posicion.getY() + (int) (proporcion * inclinacion);
    }

    public Rectangle getBounds() {
        return new Rectangle((int) posicion.getX(), (int) posicion.getY(), width, height + Math.abs(inclinacion));
    }

    @Override
    public void update() {}

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.ORANGE);
        // Dibuja línea inclinada gruesa
        int x1 = (int) posicion.getX();
        int y1 = (int) posicion.getY();
        int x2 = x1 + width;
        int y2 = y1 + inclinacion;
        for (int i = 0; i < height; i++) {
            g.drawLine(x1, y1 + i, x2, y2 + i);
        }
    }
}