package controlador;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class SoundManager {

    public static void play(String rutaRecurso) {
        try {
            URL url = SoundManager.class.getResource(rutaRecurso);
            if (url == null) {
                System.err.println("SoundManager: no se encontró: " + rutaRecurso);
                return;
            }
            AudioInputStream audio = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start(); 
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}