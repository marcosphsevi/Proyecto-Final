package controlador;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.*;

public class MusicManager {

    private static Clip clip;

    public static void play(String rutaRecurso) {
        stop(); // para cualquier música anterior
        try {
            URL url = MusicManager.class.getResource(rutaRecurso);
            if (url == null) {
                System.err.println("No se encontró el archivo: " + rutaRecurso);
                return;
            }
            AudioInputStream audio = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(audio);
            clip.loop(Clip.LOOP_CONTINUOUSLY); // loop infinito
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
    }
}
