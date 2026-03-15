package vista;

import java.awt.image.BufferedImage;

public class Assets {
	
	public static BufferedImage player;
	public static BufferedImage barril;
	
	public static void init() {
		player = Loader.imageLoader("/resource/marioBros.png");
		barril = Loader.imageLoader("/resource/barril.png");
	}

}