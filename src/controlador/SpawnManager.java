package controlador;

import modelo.Barril;
import java.util.List;
import java.util.Random;

public class SpawnManager {

    private int timer = 0;
    private int intervalo; // frames entre barriles (a 30fps = 4 segundos)
    private Random rand = new Random();

    public void update(List<Barril> barriles) {
        intervalo = 90 + rand.nextInt(91);
    	timer++;
        if (timer >= intervalo) {
            timer = 0;
            // Spawn en la parte superior izquierda, dirección aleatoria
            barriles.add(new Barril(100, 110));
        }
    }
}