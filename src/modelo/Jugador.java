package modelo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;
import controlador.Teclado;

public class Jugador extends GameObject {

    private static final float VELOCIDAD    = 3f;
    private static final float FUERZA_SALTO = -6.2f;
    private static final float GRAVEDAD     = 0.4f;
    private static final float VEL_ESCALERA = 2.5f;

    private static final int W = 32;
    private static final int H = 64;

    private float   velY       = 0;
    private boolean enElSuelo  = false;
    private boolean enEscalera = false;
    private boolean vivo       = true;

    private Teclado          teclado;
    private List<Plataforma> plataformas;
    private List<Escalera>   escaleras;

    public Jugador(Vector2D posicion, BufferedImage texture, Teclado teclado,
                   List<Plataforma> plataformas, List<Escalera> escaleras) {
        super(posicion, texture);
        this.teclado     = teclado;
        this.plataformas = plataformas;
        this.escaleras   = escaleras;
    }

    @Override
    public void update() {
        if (!vivo) return;

        // ── Escalera ──────────────────────────────────────────────────
        Escalera escaleraActual = escaleraEnContacto();
        enEscalera = escaleraActual != null && (teclado.arriba || teclado.abajo || enEscalera);

        if (enEscalera && escaleraActual != null) {
            velY = 0;
            if (teclado.arriba) posicion.setY(posicion.getY() - VEL_ESCALERA);
            if (teclado.abajo)  posicion.setY(posicion.getY() + VEL_ESCALERA);
            posicion.setX(escaleraActual.getPosicion().getX()
                          + escaleraActual.width / 2.0 - W / 2.0);
            if (teclado.izquierda || teclado.derecha) enEscalera = false;

            // Si el jugador llega al fondo de la escalera, salir
            float fondoEscalera = (float)(escaleraActual.getPosicion().getY() + escaleraActual.height);
            if (posicion.getY() + H >= fondoEscalera) {
                posicion.setY(fondoEscalera - H);
                enEscalera = false;
            }

        } else {
            enEscalera = false;

            // ── Movimiento horizontal ──────────────────────────────────
            if (enElSuelo) {
                if (teclado.izquierda)    posicion.setX(posicion.getX() - VELOCIDAD);
                else if (teclado.derecha) posicion.setX(posicion.getX() + VELOCIDAD);
                if (teclado.salto) {
                    velY = FUERZA_SALTO;
                    enElSuelo = false;
                }
            }

            // ── Física vertical ────────────────────────────────────────
            velY += GRAVEDAD;
            posicion.setY(posicion.getY() + velY);
            enElSuelo = false;

            // ── Colisión con plataformas ───────────────────────────────
            int centroX = (int) posicion.getX() + W / 2;
            float piesY = (float) posicion.getY() + H;

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
                    velY      = 0;
                    enElSuelo = true;
                }
            }
        }

        // ── Límites de pantalla ────────────────────────────────────────
        if (posicion.getX() < 0)   posicion.setX(0);
        if (posicion.getX() > 736) posicion.setX(736);
    }

    private Escalera escaleraEnContacto() {
        Rectangle bounds = getBounds();
        for (Escalera e : escaleras) {
            if (e.getBounds().intersects(bounds)) return e;
        }
        return null;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) posicion.getX(), (int) posicion.getY(), W, H);
    }

    public void morir() {
        vivo = false;
        System.out.println("¡Has muerto!");
    }

    @Override
    public void draw(Graphics g) {
        if (!vivo) return;
        if (texture != null)
            g.drawImage(texture, (int) posicion.getX(), (int) posicion.getY(), W, H, null);
        else {
            g.setColor(Color.WHITE);
            g.fillRect((int) posicion.getX(), (int) posicion.getY(), W, H);
        }
    }
}
