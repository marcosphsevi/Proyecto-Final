package vista;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class MenuPanel extends JPanel {

    private Image fondo;

    public MenuPanel(GameWindow window) {

        setLayout(null);

        fondo = new ImageIcon("res/menu_fondo.png").getImage();

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
        g.drawImage(fondo, 0, 0, getWidth(), getHeight(), null);
    }
}
