package vista;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Canvas;

public class GamePanel extends JPanel {

    private Canvas canvas;
    private GameLoop loop;

    public GamePanel() {

        setLayout(null);

        canvas = new Canvas();
        canvas.setBounds(0, 0, 800, 600);
        add(canvas);

        loop = new GameLoop(canvas);
    }

    public void start() {
        loop.start();
    }
}