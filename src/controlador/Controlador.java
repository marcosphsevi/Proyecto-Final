package controlador;

import vista.Assets;
import vista.GameWindow;

public class Controlador implements Runnable {

    private GameWindow gameWindow;
<<<<<<< HEAD
    private Thread hilo;
    private volatile boolean corriendo = false; // volatile para visibilidad entre hilos
    private volatile boolean juegoActivo = false;

    private static final int FPS = 30;
    private double TARGETTIME = 1_000_000_000.0 / FPS;
    private double delta = 0;
    public static int FPS_PROMEDIO = FPS;
=======
    private Thread     hilo;
    private volatile boolean corriendo   = false;
    private volatile boolean juegoActivo = false;

    private static final int FPS    = 30;
    private double TARGETTIME       = 1_000_000_000.0 / FPS;
    private double delta            = 0;
    public static int FPS_PROMEDIO  = FPS;
>>>>>>> 6e24ffc7c304286f4f1ecded1bad270c67c77b51

    private GameState gameState;

    public Controlador(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
    }

<<<<<<< HEAD
    public GameState getGameState() {
        return gameState;
    }
=======
    public GameState getGameState() { return gameState; }
>>>>>>> 6e24ffc7c304286f4f1ecded1bad270c67c77b51

    private void init() {
        Assets.init();
        gameState = new GameState(gameWindow.getTeclado());
    }
<<<<<<< HEAD
    
    /** Arranca el hilo (se llama desde Main) */
=======

>>>>>>> 6e24ffc7c304286f4f1ecded1bad270c67c77b51
    public void start() {
        corriendo = true;
        hilo = new Thread(this);
        hilo.start();
    }

<<<<<<< HEAD
    /** Activa el game loop (se llama cuando el usuario presiona JUGAR) */
=======
>>>>>>> 6e24ffc7c304286f4f1ecded1bad270c67c77b51
    public void startGame() {
        init();
        juegoActivo = true;
    }

<<<<<<< HEAD
=======
    public void reiniciarJuego() {
        init();
        juegoActivo = true;
    }

>>>>>>> 6e24ffc7c304286f4f1ecded1bad270c67c77b51
    @Override
    public void run() {
        long lastTime = System.nanoTime();
        long ahora;
<<<<<<< HEAD
        int fps = 0;
=======
        int  fps    = 0;
>>>>>>> 6e24ffc7c304286f4f1ecded1bad270c67c77b51
        long tiempo = 0;

        while (corriendo) {
            ahora = System.nanoTime();
            long elapsed = ahora - lastTime;
            lastTime = ahora;

            if (juegoActivo) {
<<<<<<< HEAD
                delta += elapsed / TARGETTIME;
=======
                delta  += elapsed / TARGETTIME;
>>>>>>> 6e24ffc7c304286f4f1ecded1bad270c67c77b51
                tiempo += elapsed;

                if (delta >= 1) {
                    update();
                    gameWindow.dibujar();
                    delta--;
                    fps++;
                }

                if (tiempo >= 1_000_000_000) {
                    FPS_PROMEDIO = fps;
<<<<<<< HEAD
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
=======
                    fps    = 0;
                    tiempo = 0;
                }

                // Detectar fin de partida
                if (gameState.isGameOver()) {
                    juegoActivo = false;
                    gameWindow.mostrarGameOver();
                } else if (gameState.isVictoria()) {
                    juegoActivo = false;
                    gameWindow.mostrarVictoria();
                }

            } else {
                try { Thread.sleep(16); }
                catch (InterruptedException e) { Thread.currentThread().interrupt(); }
>>>>>>> 6e24ffc7c304286f4f1ecded1bad270c67c77b51
            }
        }
    }

<<<<<<< HEAD
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
=======
    private void update() { gameState.update(); }

    public void stop() {
        corriendo = false;
        try { hilo.join(); }
        catch (InterruptedException e) { e.printStackTrace(); }
>>>>>>> 6e24ffc7c304286f4f1ecded1bad270c67c77b51
    }
}