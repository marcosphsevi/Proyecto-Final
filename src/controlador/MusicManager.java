package controlador;

import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.*;

public class MusicManager {

    private static Clip clip;

    // Música en loop (fondo)
    public static void play(String rutaRecurso) {
        stop();
        try {
            URL url = MusicManager.class.getResource(rutaRecurso);
            if (url == null) { System.err.println("No se encontró: " + rutaRecurso); return; }
            AudioInputStream audio = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(audio);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    // Sonido puntual (sin loop, no interrumpe la música de fondo)
    public static void playOnce(String rutaRecurso) {
        try {
            URL url = MusicManager.class.getResource(rutaRecurso);
            if (url == null) { System.err.println("No se encontró: " + rutaRecurso); return; }
            AudioInputStream audio = AudioSystem.getAudioInputStream(url);
            Clip sfx = AudioSystem.getClip();
            sfx.open(audio);
            sfx.start();
            // Se cierra solo cuando termina
            sfx.addLineListener(e -> {
                if (e.getType() == LineEvent.Type.STOP) {
                    sfx.close();
                }
            });
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