package modelo;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Random;

import vista.Assets;

public class Barril extends GameObject {

    private float velX, velY;
    private static final float GRAVEDAD      = 0.4f;
    private static final float VELOCIDAD     = 3f;
    private static final int   W             = 24;
    private static final int   H             = 24;
<<<<<<< HEAD
    private static final float PROB_ESCALERA = 0.4f;
=======
    private static final float PROB_ESCALERA = 0.6f;
>>>>>>> 6e24ffc7c304286f4f1ecded1bad270c67c77b51

    // Animación
    private int frameActual   = 0;
    private int ticksFrame    = 0;
    private static final int TICKS_POR_FRAME = 6; // cada 6 frames cambia sprite

    private boolean bajandoEscalera = false;
    private Escalera ultimaEscalera = null;
    private Random rand = new Random();

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

    public void update(List<Plataforma> plataformas, List<Escalera> escaleras) {

        // ── Modo escalera ─────────────────────────────────────────────
        if (bajandoEscalera) {
            Escalera escaleraActual = escaleraEnContacto(escaleras);
            if (escaleraActual != null) {
                posicion.setX(escaleraActual.getPosicion().getX() + escaleraActual.width / 2.0 - W / 2.0);
                posicion.setY(posicion.getY() + VELOCIDAD);
                velY = 0;

                float fondoEscalera = (float)(escaleraActual.getPosicion().getY() + escaleraActual.height);
                if (posicion.getY() + H >= fondoEscalera) {
                    posicion.setY(fondoEscalera - H);
                    bajandoEscalera = false;
                }

                avanzarFrame(2); // alterna entre 2 sprites (escalera)
                return;
            } else {
                bajandoEscalera = false;
            }
        }

        // ── Modo normal ───────────────────────────────────────────────
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

                Escalera escaleraCercana = escaleraDebajo(escaleras);
                if (escaleraCercana != null && escaleraCercana != ultimaEscalera) {
                    ultimaEscalera = escaleraCercana;
                    if (rand.nextFloat() < PROB_ESCALERA) {
                        bajandoEscalera = true;
                    }
                }

                if (!bajandoEscalera && p.getInclinacion() != 0) {
                    float pendiente = p.getInclinacion() / (float) p.width;
                    velX = VELOCIDAD * (pendiente >= 0 ? 1 : -1) * (1 + Math.abs(pendiente) * 0.5f);
                }
            }
        }

        if (posicion.getX() <= 0) velX = VELOCIDAD;
        if (posicion.getX() + W >= 784) velX = -VELOCIDAD;

        avanzarFrame(4); // alterna entre 4 sprites (rodando)
    }

    /**
     * Avanza el frame de animación ciclando entre totalFrames.
     * Si rueda hacia la izquierda (velX < 0) va al revés.
     */
    private void avanzarFrame(int totalFrames) {
        ticksFrame++;
        if (ticksFrame >= TICKS_POR_FRAME) {
            ticksFrame = 0;
            if (!bajandoEscalera && velX < 0) {
                frameActual = (frameActual - 1 + totalFrames) % totalFrames;
            } else {
                frameActual = (frameActual + 1) % totalFrames;
            }
        }
    }

    private Escalera escaleraDebajo(List<Escalera> escaleras) {
        int centroX = (int) posicion.getX() + W / 2;
        float piesY = (float) posicion.getY() + H;

        for (Escalera e : escaleras) {
            float ex  = (float) e.getPosicion().getX();
            float ex2 = ex + e.width;
            float ey  = (float) e.getPosicion().getY();

            boolean dentroX = centroX >= ex && centroX <= ex2;
            boolean enTope  = Math.abs(piesY - ey) < 10;

            if (dentroX && enTope) return e;
        }
        return null;
    }

    private Escalera escaleraEnContacto(List<Escalera> escaleras) {
        Rectangle bounds = getBounds();
        for (Escalera e : escaleras) {
            if (e.getBounds().intersects(bounds)) return e;
        }
        return null;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) posicion.getX(), (int) posicion.getY(), W, H);
    }

    @Override
    public void draw(Graphics g) {
        int x = (int) posicion.getX();
        int y = (int) posicion.getY();

        BufferedImage sprite;
        if (bajandoEscalera) {
            sprite = Assets.barrilEscalera != null ? Assets.barrilEscalera[frameActual % 2] : null;
        } else {
            sprite = Assets.barrilRodando != null ? Assets.barrilRodando[frameActual % 4] : null;
        }

        if (sprite != null) {
            g.drawImage(sprite, x, y, W, H, null);
        } else {
            // fallback si no cargaron los sprites
            g.drawOval(x, y, W, H);
        }
    }
}
