package modelo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;

import vista.Assets;

public class Barril extends GameObject {

    private float velX, velY;

    private boolean enElSuelo;
    private boolean bajandoEscalera;
    private boolean decisionTomada;

    private float probabilidadEscalera;

    private static final float GRAVEDAD  = 0.4f;
    private static final float VELOCIDAD = 3f;
    private static final float VEL_BAJADA = 2.5f;

    private static final int W = 24;
    private static final int H = 24;

    // Plataforma sobre la que está parado al iniciar el descenso
    // (la ignoramos durante el descenso para no "aterrizar" en ella)
    private Plataforma plataformaOrigen = null;

    private int rotacion = 0;
    private BufferedImage sprite;

    public Barril(int x, int y) {
        super(x, y, W, H);
        velX = VELOCIDAD;
        velY = 0;
        enElSuelo       = false;
        bajandoEscalera = false;
        decisionTomada  = false;
        sprite = Assets.barril;

        float tipo = (float) Math.random();
        if      (tipo < 0.33f) probabilidadEscalera = 0.00f;
        else if (tipo < 0.66f) probabilidadEscalera = 0.40f;
        else                   probabilidadEscalera = 0.85f;
    }

    public boolean isActivo() {
        return posicion.getY() < 900;
    }

    @Override
    public void update() {}

    public void update(List<Plataforma> plataformas, List<Escalera> escaleras) {

        int centroX = (int) posicion.getX() + W / 2;

        // ═══════════════════════════════════════════
        // MODO BAJANDO ESCALERA
        // ═══════════════════════════════════════════
        if (bajandoEscalera) {

            posicion.setY(posicion.getY() + VEL_BAJADA);

            int piesY = (int) posicion.getY() + H;

            for (Plataforma p : plataformas) {

                // Ignorar la plataforma desde la que empezamos a bajar
                if (p == plataformaOrigen) continue;

                float platX  = (float) p.getPosicion().getX();
                float platX2 = platX + p.width;

                boolean dentroX = (posicion.getX() + W) > platX &&
                                   posicion.getX()       < platX2;
                if (!dentroX) continue;

                float superficie = p.getYEnX(centroX);

                // Margen generoso para no atravesar la plataforma
                if (piesY >= superficie - 2) {
                    posicion.setY(superficie - H);
                    bajandoEscalera  = false;
                    decisionTomada   = false;
                    plataformaOrigen = null;
                    velX = VELOCIDAD;
                    velY = 0;
                    enElSuelo = true;
                    break;
                }
            }
            return;
        }

        // ═══════════════════════════════════════════
        // DETECCIÓN DE ESCALERA BAJO EL BARRIL
        // ═══════════════════════════════════════════
        if (enElSuelo && !decisionTomada) {

            for (Escalera esc : escaleras) {
                Rectangle re = esc.getBounds();

                boolean alineadoX = centroX >= re.x && centroX <= re.x + re.width;

                int piesBarril  = (int) posicion.getY() + H;
                int topEscalera = re.y;

                // La escalera debe empezar justo donde están los pies del barril
                boolean encima = piesBarril >= topEscalera - 8 &&
                                 piesBarril <= topEscalera + 14;

                if (alineadoX && encima) {
                    decisionTomada = true;

                    if (Math.random() < probabilidadEscalera) {

                        // Guardar plataforma actual para ignorarla al bajar
                        plataformaOrigen = plataformaEnContacto(plataformas, centroX);

                        bajandoEscalera = true;
                        velX = 0;
                        velY = 0;
                        // Centrar en la escalera
                        posicion.setX(re.x + re.width / 2.0 - W / 2.0);
                    }
                    break;
                }
            }
        }

        // ═══════════════════════════════════════════
        // FÍSICA NORMAL
        // ═══════════════════════════════════════════
        velY += GRAVEDAD;
        posicion.setX(posicion.getX() + velX);
        posicion.setY(posicion.getY() + velY);

        enElSuelo = false;

        int piesY = (int) posicion.getY() + H;

        for (Plataforma p : plataformas) {
            float platX  = (float) p.getPosicion().getX();
            float platX2 = platX + p.width;

            boolean dentroX = (posicion.getX() + W) > platX &&
                               posicion.getX()       < platX2;
            if (!dentroX) continue;

            float superficie = p.getYEnX(centroX);
            float margen     = Math.abs(velY) + GRAVEDAD + 2;

            if (velY >= 0 && piesY >= superficie && piesY <= superficie + margen) {
                posicion.setY(superficie - H);
                velY      = 0;
                enElSuelo = true;
                decisionTomada = false; // nueva plataforma → nueva oportunidad

                if (p.getInclinacion() != 0) {
                    float pendiente = p.getInclinacion() / (float) p.width;
                    velX = VELOCIDAD * (pendiente >= 0 ? 1 : -1)
                           * (1 + Math.abs(pendiente) * 0.5f);
                }
            }
        }

        // ═══════════════════════════════════════════
        // BORDES
        // ═══════════════════════════════════════════
        if (posicion.getX() <= 0)       velX =  VELOCIDAD;
        if (posicion.getX() + W >= 784) velX = -VELOCIDAD;

        rotacion += velX * 4;
    }

    /** Devuelve la plataforma sobre la que está parado el barril ahora mismo */
    private Plataforma plataformaEnContacto(List<Plataforma> plataformas, int centroX) {
        int piesY = (int) posicion.getY() + H;
        for (Plataforma p : plataformas) {
            float platX  = (float) p.getPosicion().getX();
            float platX2 = platX + p.width;
            boolean dentroX = (posicion.getX() + W) > platX && posicion.getX() < platX2;
            if (!dentroX) continue;
            float superficie = p.getYEnX(centroX);
            if (Math.abs(piesY - superficie) <= 6) return p;
        }
        return null;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) posicion.getX(), (int) posicion.getY(), W, H);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        int x = (int) posicion.getX();
        int y = (int) posicion.getY();
        g2.rotate( Math.toRadians(rotacion), x + W / 2.0, y + H / 2.0);
        g2.drawImage(sprite, x, y, W, H, null);
        g2.rotate(-Math.toRadians(rotacion), x + W / 2.0, y + H / 2.0);
    }
}