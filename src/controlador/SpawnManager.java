package controlador;

import modelo.Barril;
import java.util.List;
import java.util.Random;

public class SpawnManager {

    private int timer = 0;
    private int intervalo = 120; // frames entre barriles (a 30fps = 4 segundos)
    private Random rand = new Random();

    public void update(List<Barril> barriles) {
        timer++;
        if (timer >= intervalo) {
            timer = 0;
            // Spawn en la parte superior izquierda, dirección aleatoria
            int dir = rand.nextBoolean() ? 1 : -1;
            //barriles.add(new Barril(100, 110, dir));
        }
    }
}