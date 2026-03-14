package modelo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import vista.Loader;

public class TileMap {

    public static final int TILE_SIZE = 28;
    public static final int COLS = 28;
    public static final int ROWS = 28;

    public enum TipoTile { VACIO, PLATAFORMA, ESCALERA, ESCALERA_Y_PLATAFORMA }

    // Colores clave detectados en los tiles
    private static final int MASK_ROSA = 0x00EC3194; // (236, 49, 148) sin alpha
    private static final int MASK_CIAN = 0x0014F3FF; // ( 20,243,255) sin alpha

    // Matriz del mapa (valores del Excel)
    public static final String[][] MAP = {
        {"1A","1A","1A","1A","1A","1A","1A","1A","1C","1A","1C","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A"},
        {"1A","1A","1A","1A","1A","1A","1A","1A","1C","1A","1C","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A"},
        {"1A","1A","1A","1A","1A","1A","1A","1A","1C","1A","1C","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A"},
        {"1A","1A","1A","1A","1A","1A","1A","1A","1C","1A","1C","1","1","1","1","1","1","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A"},
        {"1A","1A","1A","1A","1A","1A","1A","1A","1C","1A","1C","1A","1A","1A","1A","1A","1C","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A"},
        {"1A","1A","1A","1A","1A","1A","1A","1A","1C","1A","1C","1A","1A","1A","1A","1A","1C","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A"},
        {"5","5","5","5","5","5","5","5","5B","5","5B","5","5","5","5","5","5B","5","6","6","7","7","8","8","1A","1A","1A","1A"},
        {"A5","5A","5A","5A","5A","5A","5A","5A","5A","5A","5A","5C","5A","5A","5A","5A","5A","5A","6A","6A","7A","7A","8A","8C","1","1","1A","1A"},
        {"1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1C","1A","1A","1A","1A"},
        {"1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1C","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","8","8B","7","7","6","6"},
        {"1A","1A","1A","1A","1A","1A","8","8","7","7","6","6B","5","5","4","4","3","3","2","2","1","1","8A","8A","7A","7A","6A","6A"},
        {"1A","1A","2","2","1","1","8A","8A","7A","7C","6A","6A","5A","5A","4A","4A","3A","3A","2A","2A","1A","1C","1A","1A","1A","1A","1A","1A"},
        {"1A","1A","2A","2A","1C","1A","1A","1A","1A","1C","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A"},
        {"7","7","8","8","1C","1A","1A","1A","1A","1C","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A"},
        {"7A","7A","8A","8A","1","1","2","2","3","3B","4","4","5","5","6","6","7","7","8","8","1A","1C","1A","1A","1A","1A","1A","1A"},
        {"1A","1A","1A","1A","1A","1A","2A","2A","3C","3A","4A","4A","5A","5A","6C","6A","7A","7A","8A","8A","1","1","2","2","3","3","1A","1A"},
        {"1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1C","1A","1A","1A","1A","1A","1A","1A","2A","2C","3A","3A","1A","1A"},
        {"1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1C","1A","1A","1A","1A","1A","1A","1A","1A","1C","1A","1A","8","8"},
        {"1A","1A","1A","1A","1A","1A","1A","1A","1C","1A","8","8","7","7","6B","6","5","5","4","4","3","3","2","2B","1","1","8A","8A"},
        {"1A","1A","4","4","3","3","2","2","1","1","8A","8A","7C","7A","6A","6A","5A","5A","4A","4A","3A","3A","2A","2A","1A","1A","1A","1A"},
        {"1A","1A","4A","4A","3C","3A","2A","2A","1A","1A","1A","1A","1C","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A"},
        {"1A","1A","1A","1A","1C","1A","1A","1A","1A","1A","1A","1A","1C","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A"},
        {"1","1","2","2","3B","3","4","4","5","5","6","6","7B","7","8","8","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A"},
        {"1A","1A","2A","2A","3A","3A","4A","4A","5A","5A","6C","6A","7A","7A","8A","8A","1","1","2","2","3","3","4","4","5","5","1A","1A"},
        {"1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","2A","2A","3A","3A","4A","4C","5A","5A","1A","1A"},
        {"1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1C","1A","1A","1A","1A"},
        {"1A","1A","1A","1A","1A","1A","1A","1A","1A","1A","1C","1A","1A","1A","8","8","7","7","6","6","5","5","4","4B","3","3","2","2"},
        {"1","1","1","1","1","1","1","1","1","1","1","1","1","1","8A","8A","7A","7A","6A","6A","5A","5A","4A","4A","3A","3A","2A","2A"}
    };

    private Map<String, BufferedImage> tiles;
    private Map<String, TipoTile>      tiposPorNombre;

    public TileMap() {
        tiles          = new HashMap<>();
        tiposPorNombre = new HashMap<>();
        cargarTiles();
    }

    private void cargarTiles() {
        String[] nombres = {
            "1","1A","1B","1C",
            "2","2A","2B","2C",
            "3","3A","3B","3C",
            "4","4A","4B","4C",
            "5","5A","5B","5C",
            "6","6A","6B","6C",
            "7","7A","7B","7C",
            "8","8A","8B","8C"
        };

        for (String nombre : nombres) {
            BufferedImage img = Loader.imageLoader("/resource/tiles/" + nombre + ".PNG");
            if (img != null) {
                tiles.put(nombre, img);
                tiposPorNombre.put(nombre, clasificarPorColor(img));
            }
        }
    }

    /**
     * Clasifica un tile analizando sus píxeles:
     *  - Rosa  (236,49,148) → plataforma sólida
     *  - Cian  (20,243,255) → escalera
     *  - Ambos             → escalera y plataforma
     *  - Ninguno           → vacío (fondo)
     */
    private TipoTile clasificarPorColor(BufferedImage img) {
        boolean tienePink = false;
        boolean tieneCian = false;

        int w = img.getWidth();
        int h = img.getHeight();

        outer:
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int rgb = img.getRGB(x, y) & 0x00FFFFFF;
                if (rgb == MASK_ROSA) tienePink = true;
                if (rgb == MASK_CIAN) tieneCian = true;
                if (tienePink && tieneCian) break outer;
            }
        }

        if (tienePink && tieneCian) return TipoTile.ESCALERA_Y_PLATAFORMA;
        if (tienePink)              return TipoTile.PLATAFORMA;
        if (tieneCian)              return TipoTile.ESCALERA;
        return TipoTile.VACIO;
    }

    /** Devuelve el tipo del tile en la posición (col, fila) de la matriz */
    public TipoTile getTipo(int col, int fila) {
        if (col < 0 || col >= COLS || fila < 0 || fila >= ROWS) return TipoTile.VACIO;
        String key = MAP[fila][col];
        if (key.equals("A5")) key = "5A";
        TipoTile tipo = tiposPorNombre.get(key);
        return tipo != null ? tipo : TipoTile.VACIO;
    }

    public void draw(Graphics g) {
        for (int fila = 0; fila < ROWS; fila++) {
            for (int col = 0; col < COLS; col++) {
                String key = MAP[fila][col];
                if (key.equals("A5")) key = "5A";

                BufferedImage img = tiles.get(key);
                if (img != null) {
                    g.drawImage(img,
                        col * TILE_SIZE,
                        fila * TILE_SIZE,
                        TILE_SIZE,
                        TILE_SIZE,
                        null);
                }
            }
        }
    }

    public Map<String, BufferedImage> getTiles() { return tiles; }
}
