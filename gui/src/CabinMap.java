package src;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * CabinMap henter kartet fra hardisken, og legger det i en JPanel
 */
public class CabinMap extends JPanel{
	
	private String picURL = "gui\\pic\\";
//	private String picURL = "C:\\Users\\jorge_000\\prosjekt 1\\Prosjekt-I\\gui\\pic\\cabinMap.png";
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
	
	/**
	 * @param g tegner kartet i JPanelet
	 */
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(map, 0, 0, getWidth(), getHeight(), null);
	}
}
