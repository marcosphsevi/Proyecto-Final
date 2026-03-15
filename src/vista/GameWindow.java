package vista;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
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
        
        /*canvas.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                System.out.println("X: " + e.getX() + "  Y: " + e.getY());
            }
        });*/
		
        mostrarMenu();
        setVisible(true);
    }

    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public Teclado getTeclado() {
        return teclado;
    }

    public void mostrarMenu() {
        menuPanel = new MenuPanel(this);
        setContentPane(menuPanel);
        revalidate();
        repaint();
    }

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

    public void dibujar() {
        bs = canvas.getBufferStrategy();

        if (bs == null) {
            canvas.createBufferStrategy(3);
            return;
        }

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
