package vista;

import java.awt.image.BufferedImage;

public class Assets {

    public static BufferedImage player;
    public static BufferedImage[] barrilRodando;
    public static BufferedImage[] barrilEscalera;

    // Princesa (2 frames animados)
    public static BufferedImage[] princesa;

    // Malo (2 frames: reposo y lanzando)
    public static BufferedImage[] malo;

    // Hampter (6 sprites: 1=idle, 2-3=caminar, 4=salto, 5-6=escalera)
    public static BufferedImage[] hampter;

    public static void init() {
        player = Loader.imageLoader("/resource/marioBros.png");

        hampter = new BufferedImage[6];
        for (int i = 0; i < 6; i++)
            hampter[i] = Loader.imageLoader("/resource/hampter/" + (i + 1) + ".PNG");

        barrilRodando = new BufferedImage[4];
        for (int i = 0; i < 4; i++)
            barrilRodando[i] = Loader.imageLoader("/resource/barril/" + (i + 1) + ".PNG");

        barrilEscalera = new BufferedImage[2];
        for (int i = 0; i < 2; i++)
            barrilEscalera[i] = Loader.imageLoader("/resource/barril/" + (i + 5) + ".PNG");

        princesa = new BufferedImage[2];
        princesa[0] = Loader.imageLoader("/resource/princesa1.png");
        princesa[1] = Loader.imageLoader("/resource/princesa2.png");

        malo = new BufferedImage[2];
        malo[0] = Loader.imageLoader("/resource/Malo1.png");
        malo[1] = Loader.imageLoader("/resource/Malo2.png");
    }
}