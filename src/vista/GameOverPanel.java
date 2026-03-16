package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameOverPanel extends JPanel {

    private Image fondo;

    public GameOverPanel(GameWindow window) {

        setLayout(null);

        fondo = new ImageIcon(getClass().getResource("/resource/GameOver.png")).getImage();

        JButton reintentar = crearBoton("RETRY");
        JButton salir      = crearBoton("EXIT");

        reintentar.setBounds(310, 570, 200, 50);
        salir.setBounds(310, 630, 200, 50);

        reintentar.addActionListener(e -> window.reiniciarJuego());
        salir.addActionListener(e -> System.exit(0));

        add(reintentar);
        add(salir);
    }

    private JButton crearBoton(String texto) {

        JButton boton = new JButton(texto);

        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setContentAreaFilled(false);
        boton.setForeground(new Color(255, 210, 0));
        boton.setFont(new Font("Arial", Font.BOLD, 26));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                boton.setForeground(new Color(200, 160, 0));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                boton.setForeground(new Color(255, 210, 0));
            }
        });

        return boton;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fondo, 0, 0, getWidth(), getHeight(), null);
    }
}