package src;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class CabinMapField extends JPanel implements ChangeTabListener{
	
	private final CabinMap map;
	private final JTextArea cabinInfo;
	
	public CabinMapField(){
		map = new CabinMap();
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		cabinInfo = new JTextArea();
		cabinInfo.setEditable(false);
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.5;
		c.weighty = 1;
		add(cabinInfo,c);
		map.addMouseListener(new Listener());
		c.weightx = 1;
		c.gridwidth = 2;
		c.gridx = 1;
		add(map,c);
	}

	@Override
	public void initPanel() {
		// TODO Auto-generated method stub
		
	}
	
	private class Listener implements MouseListener{

		public void mouseClicked(MouseEvent arg0) {
			
		}

		public void mouseEntered(MouseEvent arg0) {
			
		}

		public void mouseExited(MouseEvent arg0) {
			
		}

		public void mousePressed(MouseEvent arg0) {
		}

		public void mouseReleased(MouseEvent arg0) {
			
		}
		
	}
}
