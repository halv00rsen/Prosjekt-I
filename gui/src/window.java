//package map;
package src;

import java.awt.EventQueue;
import java.awt.Panel;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

//import com.jgoodies.forms.factories.DefaultComponentFactory;

import java.awt.BorderLayout;

import javax.swing.JEditorPane;
import javax.swing.JButton;

import java.awt.FlowLayout;

//import net.miginfocom.swing.MigLayout;

import javax.swing.JLayeredPane;
import javax.swing.JDesktopPane;
import java.awt.Button;

public class window {

	private JFrame frame;

	/**
	 * Launch the application.
	 * @param args Standard args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window window = new window();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 990, 880);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Button button = new Button("New button");
		frame.getContentPane().add(button, BorderLayout.NORTH);
		frame = new JFrame();
		frame.setBounds(100, 100, 990, 880);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.getContentPane().add(new JLabel(new ImageIcon("D:\\eclipse\\work\\map\\image\\koiemap.png")));
		button.setBounds(20, 20, 20, 20);
		
	}
}
//frame = new JFrame();
//frame.setBounds(100, 100, 990, 880);
//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//frame.getContentPane().add(new JLabel(new ImageIcon("D:\\eclipse\\work\\map\\image\\koiemap.png")));
//frame.add(new JButton("Test Button"));