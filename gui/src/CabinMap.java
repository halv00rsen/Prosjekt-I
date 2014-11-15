package src;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CabinMap extends JPanel{
	
	private String picURL = "gui\\pic\\cabinMap.png";
//	private String picURL = "C:\\Users\\jorge_000\\prosjekt 1\\Prosjekt-I\\gui\\pic\\cabinMap.png";
	private BufferedImage map;
	
	public CabinMap(){
		try{
			map = ImageIO.read(new File(picURL));
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
