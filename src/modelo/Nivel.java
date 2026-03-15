package modelo;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Nivel {

    private TileMap tileMap;
    private List<Plataforma> plataformas;
    private List<Escalera>   escaleras;

    public Nivel() {
        tileMap     = new TileMap();
        plataformas = new ArrayList<>();
        escaleras   = new ArrayList<>();
        construirManualmente();
    }

    private void construirManualmente() {

        // PLATAFORMAS

        // Tramo recto: de (0,756) a (390,756)
        plataformas.add(new Plataforma(0, 756, 390, 14, 0));

        // Tramo inclinado: de (390,756) a (782,732) → sube 24px
        plataformas.add(new Plataforma(390, 756, 392, 14, -24));

        // Tramo inclinado: de (0,615) a (727,659) → baja 44px
        plataformas.add(new Plataforma(0, 615, 727, 14, 44));

        // Tramo inclinado: de (56,543) a (782,501) → sube 42px
        plataformas.add(new Plataforma(56, 543, 726, 14, -42));

        // Tramo inclinado: de (0,384) a (727,427) → baja 43px
        plataformas.add(new Plataforma(0, 384, 727, 14, 43));

        // Tramo inclinado: de (55,311) a (782,270) → sube 41px
        plataformas.add(new Plataforma(55, 311, 727, 14, -41));

        // Tramo inclinado: de (503,182) a (727,196) → baja 14px
        plataformas.add(new Plataforma(503, 182, 224, 14, 14));

        // Tramo recto: de (0,182) a (503,182)
        plataformas.add(new Plataforma(0, 182, 503, 14, 0));

        // Tramo recto: de (308,84) a (475,84)
        plataformas.add(new Plataforma(308, 84, 167, 14, 0));

        // ESCALERAS
        
        //escalera primer piso
        escaleras.add(new Escalera(643, 653, 28, 86));
        
        //escalera rota primer piso
        escaleras.add(new Escalera(279, 727, 28, 28));
        
        //escalera 1 segundo piso
        escaleras.add(new Escalera(336, 523, 28, 114));
        
        //escalera 2 segundo piso
        escaleras.add(new Escalera(112, 539, 28, 84));
        
        //escalera 1 tercer piso
        escaleras.add(new Escalera(392, 407, 28, 116));
        
        //escalera 2 tercer piso
        escaleras.add(new Escalera(643, 421, 28, 86));
        
        //escalera rota tercer piso
        escaleras.add(new Escalera(223, 504, 28, 28));
        
        //escalera 1 cuato piso
        escaleras.add(new Escalera(252, 299, 28, 100));
        
        //escalera 2 cuarto piso
        escaleras.add(new Escalera(112, 306, 28, 86));

        //escalera rota cuarto piso
        escaleras.add(new Escalera(588, 392, 28, 28));
        
        //escalera rota quinto piso
        escaleras.add(new Escalera(308, 251, 28, 47));
        
        //escalera quingo piso
        escaleras.add(new Escalera(643, 190, 28, 86));
        
        //ultima escalera
        escaleras.add(new Escalera(448, 83, 28, 99));
    }

    public TileMap getTileMap()              { return tileMap;     }
    public List<Plataforma> getPlataformas() { return plataformas; }
    public List<Escalera>   getEscaleras()   { return escaleras;   }

    public void draw(Graphics g) {
        tileMap.draw(g);
        // Descomentar para depurar hitboxes:
        //for (Plataforma p : plataformas) p.draw(g);
        //for (Escalera e   : escaleras)   e.draw(g);
    }
}
