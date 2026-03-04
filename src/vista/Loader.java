package vista;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Loader {
	public static BufferedImage imageLoader (String ruta) {
		try {
			return ImageIO.read(Loader.class.getResource(ruta));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
