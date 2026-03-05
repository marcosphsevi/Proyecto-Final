package modelo;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class GameObject {
    protected BufferedImage texture;
    protected Vector2D posicion;
    protected int width, height; // añadido para Plataforma

    public GameObject(Vector2D posicion, BufferedImage texture) {
        this.posicion = posicion;
        this.texture = texture;
    }

    // Constructor para objetos sin textura (como Plataforma)
    public GameObject(int x, int y, int width, int height) {
        this.posicion = new Vector2D(x, y);
        this.width = width;
        this.height = height;
    }

    public abstract void update();
    public abstract void draw(Graphics g);

    public Vector2D getPosicion() { return posicion; }
    public void setPosicion(Vector2D posicion) { this.posicion = posicion; }
}