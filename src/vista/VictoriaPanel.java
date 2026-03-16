package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import controlador.MusicManager;

public class VictoriaPanel extends JPanel {

    private Image fondo;

    public VictoriaPanel(GameWindow window) {
        setLayout(null);
        fondo = new ImageIcon(getClass().getResource("/resource/Victoria.png")).getImage();

        // Música de victoria en loop
        MusicManager.play("/resource/music/victory.wav");

        JButton salir = crearBoton("EXIT");
        salir.setBounds(310, 570, 200, 50);
        salir.addActionListener(e -> {
            MusicManager.playOnce("/resource/music/hampter.wav");
            System.exit(0);
        });

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
                MusicManager.playOnce("/resource/music/hover.wav");
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