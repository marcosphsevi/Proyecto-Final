package controlador;

import modelo.Barril;
import java.util.List;
import java.util.Random;

public class SpawnManager {

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
		return 60 + rand.nextInt(61); // entre 3 y 5 segundos
	}
}