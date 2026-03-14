package modelo;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import modelo.TileMap.TipoTile;

public class Nivel {

    private TileMap tileMap;
    private List<Plataforma> plataformas;
    private List<Escalera>   escaleras;

    public Nivel() {
        tileMap     = new TileMap();
        plataformas = new ArrayList<>();
        escaleras   = new ArrayList<>();
        construirDesdeImagenes();
    }

    private void construirDesdeImagenes() {
        int ts   = TileMap.TILE_SIZE;
        int cols = TileMap.COLS;
        int rows = TileMap.ROWS;

        // --- Plataformas: segmentos horizontales de tiles con rosa ---
        for (int fila = 0; fila < rows; fila++) {
            int inicio = -1;
            for (int col = 0; col <= cols; col++) {
                boolean esPlat = col < cols && esPlataforma(tileMap.getTipo(col, fila));
                if (esPlat && inicio == -1) {
                    inicio = col;
                } else if (!esPlat && inicio != -1) {
                    int x     = inicio * ts;
                    int y     = fila   * ts;
                    int ancho = (col - inicio) * ts;

                    // Fila 27 especial: cols 0-13 plano, cols 14-27 inclinado
                    if (fila == 27) {
                        // Parte plana: col 0-13 -> x=0, w=392, y=756, inclinacion=0
                        if (inicio < 14) {
                            int finPlano = Math.min(col, 14);
                            plataformas.add(new Plataforma(inicio * ts, y, (finPlano - inicio) * ts, ts, 0));
                        }
                        // Parte inclinada: col 14-27 -> sube 28px en 392px (inclinacion=-28)
                        if (col > 14) {
                            int iniInc = Math.max(inicio, 14);
                            plataformas.add(new Plataforma(iniInc * ts, y, (col - iniInc) * ts, ts, -56));
                        }
                    } else {
                        plataformas.add(new Plataforma(x, y, ancho, ts, 0));
                    }
                    inicio = -1;
                }
            }
        }

        // --- Escaleras: segmentos verticales de tiles con cian ---
        for (int col = 0; col < cols; col++) {
            int inicio = -1;
            for (int fila = 0; fila <= rows; fila++) {
                boolean esEsc = fila < rows && esEscalera(tileMap.getTipo(col, fila));
                if (esEsc && inicio == -1) {
                    inicio = fila;
                } else if (!esEsc && inicio != -1) {
                    int x    = col    * ts;
                    int y    = inicio * ts;
                    int alto = (fila - inicio) * ts;
                    escaleras.add(new Escalera(x, y, ts, alto));
                    inicio = -1;
                }
            }
        }
    }

    private boolean esPlataforma(TipoTile t) {
        return t == TipoTile.PLATAFORMA || t == TipoTile.ESCALERA_Y_PLATAFORMA;
    }

    private boolean esEscalera(TipoTile t) {
        return t == TipoTile.ESCALERA || t == TipoTile.ESCALERA_Y_PLATAFORMA;
    }

    public TileMap getTileMap()              { return tileMap;     }
    public List<Plataforma> getPlataformas() { return plataformas; }
    public List<Escalera>   getEscaleras()   { return escaleras;   }

    public void draw(Graphics g) {
        tileMap.draw(g);
        // Descomentar para depurar hitboxes:
        // for (Plataforma p : plataformas) p.draw(g);
        // for (Escalera e   : escaleras)   e.draw(g);
    }
}
