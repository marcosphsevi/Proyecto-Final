package vista;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

public class MenuPanel extends JPanel {

    public MenuPanel(GameWindow window) {
        setLayout(null);

        JButton jugar = new JButton("JUGAR");
        JButton salir = new JButton("SALIR");

        jugar.setBounds(330, 300, 140, 40);
        salir.setBounds(330, 360, 140, 40);

        jugar.addActionListener(e -> window.iniciarJuego());
        salir.addActionListener(e -> System.exit(0));

        add(jugar);
        add(salir);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Fondo simple hasta que tengas la imagen lista
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 48));
        g.drawString("MiniMario", 280, 200);
    }
}