package vista;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JFrame;

import controlador.Controlador;
import controlador.Teclado;

public class GameWindow extends JFrame {

    private static final int ANCHO = 800;
    private static final int LARGO = 800;

    private Canvas canvas;
    private MenuPanel menuPanel;
    private BufferStrategy bs;
    private Controlador controlador;
    private Teclado teclado;

    public GameWindow() {
        setTitle("MiniMario");
        setSize(ANCHO, LARGO);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        teclado = new Teclado();

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(ANCHO, LARGO));
        canvas.setMaximumSize(new Dimension(ANCHO, LARGO));
        canvas.setMinimumSize(new Dimension(ANCHO, LARGO));
        canvas.setFocusable(true);
        canvas.addKeyListener(teclado);

        mostrarMenu();
        setVisible(true);
    }


    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public Teclado getTeclado() {
        return teclado;
    }

    /** Muestra el panel del menú principal */
    public void mostrarMenu() {
        menuPanel = new MenuPanel(this);
        setContentPane(menuPanel);
        revalidate();
        repaint();
    }

 // Método iniciarJuego corregido:
    public void iniciarJuego() {
        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new BorderLayout());
        gamePanel.add(canvas, BorderLayout.CENTER);
        
        setContentPane(gamePanel);
        revalidate();
        repaint();
        
        canvas.requestFocusInWindow();
        controlador.startGame();
    }

    /** Llamado por el Controlador cada frame para dibujar */
    public void dibujar() {
        bs = canvas.getBufferStrategy();

        if (bs == null) {
            canvas.createBufferStrategy(3);
            return;
        }

        g = bs.getDrawGraphics();

        // Limpiar pantalla
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, ANCHO, LARGO);

        // Dibujar estado del juego
        controlador.getGameState().draw(g);
        g.setColor(Color.WHITE);
        g.drawString("FPS: " + Controlador.FPS_PROMEDIO, 10, 20);

        g.dispose();
        bs.show();
    }

    private Graphics g; // campo para reutilizar referencia
}