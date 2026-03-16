package controlador;

import modelo.Barril;
import java.util.List;
import java.util.Random;

public class SpawnManager {

    private int    timer         = 0;
    private int    intervalo;
    private Random rand          = new Random();
    private int    framesTotales = 0;
    private Runnable onSpawn;

    public SpawnManager(Runnable onSpawn) {
        this.onSpawn = onSpawn;
        intervalo = siguienteIntervalo();
    }

    public void update(List<Barril> barriles) {
        framesTotales++;
        timer++;

        if (timer >= intervalo) {
            timer     = 0;
            intervalo = siguienteIntervalo();
            barriles.add(new Barril(100, 110));
            if (onSpawn != null) onSpawn.run();
        }
    }

    private int siguienteIntervalo() {
        // Empieza entre 2-5 s (60-150 frames) y baja hasta mínimo ~1.5 s (45 frames)
        // Cada 30 segundos (900 frames) reduce ~15 frames el intervalo base
        int reduccion = Math.min((framesTotales / 900) * 15, 45);
        int base      = Math.max(60 - reduccion, 45);
        int variacion = Math.max(90 - reduccion, 15);
        return base + rand.nextInt(variacion);
    }
}