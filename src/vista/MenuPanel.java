package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuPanel extends JPanel {

    private Image fondo;

    public MenuPanel(GameWindow window) {

        setLayout(null);

        // Cargar imagen desde resources
        fondo = new ImageIcon(getClass().getResource("/resource/menu.png")).getImage();

        JButton jugar = crearBoton("JUGAR");
        JButton salir = crearBoton("SALIR");

        jugar.setBounds(320, 570, 180, 50);
        salir.setBounds(320, 630, 180, 50);

        jugar.addActionListener(e -> window.iniciarJuego());
        salir.addActionListener(e -> System.exit(0));

        add(jugar);
        add(salir);
    }

    private JButton crearBoton(String texto) {

        JButton boton = new JButton(texto);

        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setContentAreaFilled(false);

        boton.setForeground(new Color(255,210,0)); // amarillo arcade
        boton.setFont(new Font("Arial", Font.BOLD, 26));

        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        boton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                boton.setForeground(new Color(200,160,0)); // amarillo oscuro
            }

            @Override
            public void mouseExited(MouseEvent e) {
                boton.setForeground(new Color(255,210,0));
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