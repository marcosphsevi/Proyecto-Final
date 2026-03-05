package modelo;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;
import controlador.Teclado;

public class Jugador extends GameObject {

    private static final float VELOCIDAD      = 3f;
    private static final float FUERZA_SALTO   = -12f;
    private static final float GRAVEDAD       = 0.5f;
    private static final float VEL_ESCALERA   = 2.5f;

    private float velY = 0;
    private boolean enElSuelo   = false;
    private boolean enEscalera  = false;
    private boolean vivo        = true;

    private Teclado teclado;
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

        // --- Escalera ---
        Escalera escaleraActual = escaleraEnContacto();
        enEscalera = escaleraActual != null && (teclado.arriba || teclado.abajo || enEscalera);

        if (enEscalera && escaleraActual != null) {
            velY = 0;
            if (teclado.arriba)  posicion.setY(posicion.getY() - VEL_ESCALERA);
            if (teclado.abajo)   posicion.setY(posicion.getY() + VEL_ESCALERA);
            // Centrar en la escalera
            posicion.setX(escaleraActual.getPosicion().getX() + escaleraActual.width / 2.0 - 16);

            // Sale de la escalera si se mueve horizontal
            if (teclado.izquierda || teclado.derecha) enEscalera = false;

        } else {
            enEscalera = false;

            // --- Movimiento horizontal (solo en suelo) ---
            if (enElSuelo) {
                if (teclado.izquierda)     posicion.setX(posicion.getX() - VELOCIDAD);
                else if (teclado.derecha)  posicion.setX(posicion.getX() + VELOCIDAD);

                if (teclado.salto) {
                    velY = FUERZA_SALTO;
                    enElSuelo = false;
                }
            }

            // --- Física vertical ---
            velY += GRAVEDAD;
            posicion.setY(posicion.getY() + velY);
            enElSuelo = false;

            // --- Colisión con plataformas ---
            for (Plataforma p : plataformas) {
                int px = (int) posicion.getX() + 16; // centro del jugador
                int superficieY = p.getYEnX(px);

                boolean dentroX = posicion.getX() + 32 > p.getPosicion().getX()
                               && posicion.getX() < p.getPosicion().getX() + p.width;

                if (dentroX && posicion.getY() + 64 >= superficieY
                            && posicion.getY() + 64 <= superficieY + 20
                            && velY >= 0) {
                    posicion.setY(superficieY - 64);
                    velY = 0;
                    enElSuelo = true;
                }
            }
        }

        // Límites de pantalla
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
        return new Rectangle((int) posicion.getX(), (int) posicion.getY(), 32, 64);
    }

    public void morir() {
        vivo = false;
        // Aquí luego puedes lanzar un evento de game over
        System.out.println("¡Has muerto!");
    }

    @Override
    public void draw(Graphics g) {
        if (!vivo) return;
        g.drawImage(texture, (int) posicion.getX(), (int) posicion.getY(), 32, 64, null);
    }
}