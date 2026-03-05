package modelo;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Nivel {

    private List<Plataforma> plataformas;
    private List<Escalera> escaleras;

    public Nivel() {
        plataformas = new ArrayList<>();
        escaleras   = new ArrayList<>();
        construir();
    }

    private void construir() {
        // Piso 1 (abajo) - inclinado hacia la izquierda
        plataformas.add(new Plataforma(50,  680, 700, 12, -30));
        // Piso 2
        plataformas.add(new Plataforma(50,  540, 700, 12,  30));
        // Piso 3
        plataformas.add(new Plataforma(50,  400, 700, 12, -30));
        // Piso 4
        plataformas.add(new Plataforma(50,  260, 700, 12,  30));
        // Piso 5 (arriba, donde está DK)
        plataformas.add(new Plataforma(50,  130, 700, 12, -10));

        // Escaleras
        escaleras.add(new Escalera(630, 540, 30, 140)); // sube piso1 -> piso2
        escaleras.add(new Escalera(150, 400, 30, 140)); // sube piso2 -> piso3
        escaleras.add(new Escalera(600, 260, 30, 140)); // sube piso3 -> piso4
        escaleras.add(new Escalera(200, 130, 30, 130)); // sube piso4 -> piso5
    }

    public List<Plataforma> getPlataformas() { return plataformas; }
    public List<Escalera>   getEscaleras()   { return escaleras;   }

    public void draw(Graphics g) {
        for (Plataforma p : plataformas) p.draw(g);
        for (Escalera e   : escaleras)   e.draw(g);
    }
}