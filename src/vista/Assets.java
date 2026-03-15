package vista;

import java.awt.image.BufferedImage;

public class Assets {

    public static BufferedImage player;
    public static BufferedImage[] barrilRodando; // sprites 1-4
    public static BufferedImage[] barrilEscalera; // sprites 5-6

    public static void init() {
        player = Loader.imageLoader("/resource/marioBros.png");

        barrilRodando = new BufferedImage[4];
        for (int i = 0; i < 4; i++) {
            barrilRodando[i] = Loader.imageLoader("/resource/barril/" + (i + 1) + ".PNG");
        }

        barrilEscalera = new BufferedImage[2];
        for (int i = 0; i < 2; i++) {
            barrilEscalera[i] = Loader.imageLoader("/resource/barril/" + (i + 5) + ".PNG");
        }
    }
}