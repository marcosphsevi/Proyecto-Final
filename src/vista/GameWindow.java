package vista;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
<<<<<<< HEAD
//import java.awt.event.MouseMotionAdapter;
//import java.awt.event.MouseEvent;
=======
>>>>>>> 6e24ffc7c304286f4f1ecded1bad270c67c77b51
import java.awt.image.BufferStrategy;
import java.awt.BorderLayout;

import javax.swing.JPanel;
<<<<<<< HEAD
=======
import javax.swing.SwingUtilities;
>>>>>>> 6e24ffc7c304286f4f1ecded1bad270c67c77b51
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
<<<<<<< HEAD
        
        /*canvas.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                System.out.println("X: " + e.getX() + "  Y: " + e.getY());
            }
        });*/
		
=======

>>>>>>> 6e24ffc7c304286f4f1ecded1bad270c67c77b51
        mostrarMenu();
        setVisible(true);
    }

<<<<<<< HEAD
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public Teclado getTeclado() {
        return teclado;
    }
=======
    public void setControlador(Controlador controlador) { this.controlador = controlador; }
    public Teclado getTeclado() { return teclado; }
>>>>>>> 6e24ffc7c304286f4f1ecded1bad270c67c77b51

    public void mostrarMenu() {
        menuPanel = new MenuPanel(this);
        setContentPane(menuPanel);
        revalidate();
        repaint();
    }

<<<<<<< HEAD
=======
    public void mostrarGameOver() {
        SwingUtilities.invokeLater(() -> {
            setContentPane(new GameOverPanel(this));
            revalidate();
            repaint();
        });
    }

    public void mostrarVictoria() {
        SwingUtilities.invokeLater(() -> {
            setContentPane(new VictoriaPanel(this));
            revalidate();
            repaint();
        });
    }

>>>>>>> 6e24ffc7c304286f4f1ecded1bad270c67c77b51
    public void iniciarJuego() {
        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new BorderLayout());
        gamePanel.add(canvas, BorderLayout.CENTER);
<<<<<<< HEAD

        setContentPane(gamePanel);
        revalidate();
        repaint();

=======
        setContentPane(gamePanel);
        revalidate();
        repaint();
>>>>>>> 6e24ffc7c304286f4f1ecded1bad270c67c77b51
        canvas.requestFocusInWindow();
        controlador.startGame();
    }

<<<<<<< HEAD
    public void dibujar() {
        bs = canvas.getBufferStrategy();

=======
    public void reiniciarJuego() {
        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new BorderLayout());
        gamePanel.add(canvas, BorderLayout.CENTER);
        setContentPane(gamePanel);
        revalidate();
        repaint();
        canvas.requestFocusInWindow();
        controlador.reiniciarJuego();
    }

    public void dibujar() {
        bs = canvas.getBufferStrategy();
>>>>>>> 6e24ffc7c304286f4f1ecded1bad270c67c77b51
        if (bs == null) {
            canvas.createBufferStrategy(3);
            return;
        }
<<<<<<< HEAD

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, ANCHO, LARGO);

        controlador.getGameState().draw(g);
        g.setColor(Color.WHITE);
        g.drawString("FPS: " + Controlador.FPS_PROMEDIO, 10, 20);

        g.dispose();
        bs.show();
    }
}
=======
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, ANCHO, LARGO);
        controlador.getGameState().draw(g);
        g.setColor(Color.WHITE);
        g.drawString("FPS: " + Controlador.FPS_PROMEDIO, 10, 20);
        g.dispose();
        bs.show();
    }
}
>>>>>>> 6e24ffc7c304286f4f1ecded1bad270c67c77b51
