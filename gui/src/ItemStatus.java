package src;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ItemStatus extends JPanel{

	private ChooseCabin cabins;
	private JTextArea itemInformation;
	
	public ItemStatus(){
		cabins = new ChooseCabin();
		cabins.addActionListener(new CabinListener());
		setLayout(new GridLayout(2,1));
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		panel.add(new JLabel("Velg koie: "), c);
		c.gridx = 2;
		panel.add(cabins.getComboBox(), c);
		itemInformation = new JTextArea();
		itemInformation.setEditable(false);
		itemInformation.setBorder(BorderFactory.createEtchedBorder());
		add(panel);
		add(itemInformation);
		setKoieInformation(cabins.getSelectedItem());
	}
	
	private void setKoieInformation(int cabin){
		itemInformation.setText(cabin + "\nSomething cool.");
	}
	
	private class CabinListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			setKoieInformation(cabins.getSelectedItem());
		}
	}
}
