package src;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * CabinMap henter kartet fra hardisken, og legger det i et JPanel
 */
public class CabinMap extends JPanel{
	private String picURL = "gui/pic/";
	private BufferedImage map;
	
	/**
	 * Oppretter kartet
	 * @param picName navnet på kartbildet
	 */
	public CabinMap(String picName){
		try{
			map = ImageIO.read(new File(picURL + picName));
		}catch (IOException e){
			e.printStackTrace();
			map = null;
		}
		repaint();
	}
	
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(map, 0, 0, getWidth(), getHeight(), null);
	}
}
