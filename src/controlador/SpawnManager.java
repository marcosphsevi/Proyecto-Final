package controlador;

import modelo.Barril;
import java.util.List;
import java.util.Random;

public class SpawnManager {

<<<<<<< HEAD
	private int timer = 0;
	private int intervalo;
	private Random rand = new Random();

	public SpawnManager() {
		intervalo = siguienteIntervalo(); // primer intervalo al arrancar
	}

	public void update(List<Barril> barriles) {
		timer++;
		if (timer >= intervalo) {
			timer = 0;
			intervalo = siguienteIntervalo(); // nuevo intervalo aleatorio para el siguiente
			barriles.add(new Barril(100, 110));
		}
	}

	private int siguienteIntervalo() {
		return 90 + rand.nextInt(61); // entre 3 y 5 segundos
	}
=======
    private int   timer     = 0;
    private int   intervalo;
    private Random rand     = new Random();

    // Tiempo total de juego en frames (a 30 fps)
    private int framesTotales = 0;

    // Callback para avisar que acaba de spawnear un barril
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
        // Empieza entre 3-5 s (90-150 frames) y baja hasta mínimo ~1.5 s (45 frames)
        // Cada 30 segundos (900 frames) reduce ~15 frames el intervalo base
        int reduccion = Math.min((framesTotales / 900) * 15, 60);
        int base      = Math.max(90 - reduccion, 45);
        int variacion = Math.max(60 - reduccion / 2, 15);
        return base + rand.nextInt(variacion);
    }
>>>>>>> 6e24ffc7c304286f4f1ecded1bad270c67c77b51
}