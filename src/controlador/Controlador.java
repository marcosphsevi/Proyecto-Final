package controlador;

import vista.GameWindow;

public class Controlador implements Runnable{

	private GameWindow gameWindow;
	private Thread hilo;
	private boolean estado = false;
	
	private static final int FPS = 60;
	private double TARGETTIME = 1000000000/FPS;
	private double delta = 0;
	public static int FPS_PROMEDIO = FPS;
	
	
	public Controlador (GameWindow gameWindow) {
		this.gameWindow = gameWindow;
	}
	
	@Override
	public void run() {
		
		long ahora = 0;
		long lastTime = System.nanoTime();
		int fps = 0;
		long tiempo = 0;
		
		while(estado) {
			ahora = System.nanoTime();
			delta += (ahora - lastTime)/TARGETTIME;
			tiempo += (ahora - lastTime);
			lastTime = ahora;
			
			if (delta >= 1) {
				update();
				gameWindow.dibujar();
				delta--;
				fps++;
				System.out.println(fps);
			}
			if (tiempo >= 1000000000) {
				FPS_PROMEDIO = fps;
				fps = 0;
				tiempo = 0;
			}
			
		}
		stop();
	}
	
	private void update() {
		
	}
	
	public void start() {
		hilo = new Thread(this);
		hilo.start();
		estado = true;
	}
	
	private void stop() {
		try {
			hilo.join();
			estado = false;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
