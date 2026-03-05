package controlador;

import vista.Assets;
import vista.GameWindow;

public class Controlador implements Runnable {

    private GameWindow gameWindow;
    private Thread hilo;
    private volatile boolean corriendo = false; // volatile para visibilidad entre hilos
    private volatile boolean juegoActivo = false;

    private static final int FPS = 30;
    private double TARGETTIME = 1_000_000_000.0 / FPS;
    private double delta = 0;
    public static int FPS_PROMEDIO = FPS;

    private GameState gameState;

    public Controlador(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
    }

    public GameState getGameState() {
        return gameState;
    }

    private void init() {
        Assets.init();
        gameState = new GameState(gameWindow.getTeclado());
    }
    
    /** Arranca el hilo (se llama desde Main) */
    public void start() {
        corriendo = true;
        hilo = new Thread(this);
        hilo.start();
    }

    /** Activa el game loop (se llama cuando el usuario presiona JUGAR) */
    public void startGame() {
        init();
        juegoActivo = true;
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        long ahora;
        int fps = 0;
        long tiempo = 0;

        while (corriendo) {
            ahora = System.nanoTime();
            long elapsed = ahora - lastTime;
            lastTime = ahora;

            if (juegoActivo) {
                delta += elapsed / TARGETTIME;
                tiempo += elapsed;

                if (delta >= 1) {
                    update();
                    gameWindow.dibujar();
                    delta--;
                    fps++;
                }

                if (tiempo >= 1_000_000_000) {
                    FPS_PROMEDIO = fps;
                    fps = 0;
                    tiempo = 0;
                }
            } else {
                // Mientras estamos en el menú, dormir para no quemar CPU
                try {
                    Thread.sleep(16);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    private void update() {
        gameState.update();
    }

    public void stop() {
        corriendo = false;
        try {
            hilo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}