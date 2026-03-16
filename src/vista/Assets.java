package vista;

import java.awt.image.BufferedImage;

public class Assets {

    public static BufferedImage player;
<<<<<<< HEAD
    public static BufferedImage[] barrilRodando; // sprites 1-4
    public static BufferedImage[] barrilEscalera; // sprites 5-6
=======
    public static BufferedImage[] barrilRodando;
    public static BufferedImage[] barrilEscalera;

    // Princesa (2 frames animados)
    public static BufferedImage[] princesa;

    // Malo (2 frames: reposo y lanzando)
    public static BufferedImage[] malo;
>>>>>>> 6e24ffc7c304286f4f1ecded1bad270c67c77b51

    public static void init() {
        player = Loader.imageLoader("/resource/marioBros.png");

        barrilRodando = new BufferedImage[4];
<<<<<<< HEAD
        for (int i = 0; i < 4; i++) {
            barrilRodando[i] = Loader.imageLoader("/resource/barril/" + (i + 1) + ".PNG");
        }

        barrilEscalera = new BufferedImage[2];
        for (int i = 0; i < 2; i++) {
            barrilEscalera[i] = Loader.imageLoader("/resource/barril/" + (i + 5) + ".PNG");
        }
=======
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
>>>>>>> 6e24ffc7c304286f4f1ecded1bad270c67c77b51
    }
}