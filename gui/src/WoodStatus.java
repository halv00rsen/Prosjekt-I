package src;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class WoodStatus extends JPanel{
	
	private final JPanel updateWood;
	private final JTextArea woodInformation;
	private final ChooseCabin cabins;
	private final JFormattedTextField numWood;
	private final JButton addButton;
	private Koie cabinInUse;
	public static final int minSacks = 5;
	
	public WoodStatus(){
		setLayout(new GridLayout(1,2));
		updateWood = new JPanel();
		woodInformation = new JTextArea();
		woodInformation.setEditable(false);
		woodInformation.setLineWrap(true);
		woodInformation.setWrapStyleWord(true);
		JScrollPane pane = new JScrollPane(woodInformation);
		pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		cabins = new ChooseCabin();
		updateWood.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		updateWood.add(new JLabel("Koie: "), c);
		c.gridx = 1;
		updateWood.add(cabins.getComboBox(), c);
		c.gridx = 0;
		c.gridy = 1;
		updateWood.add(new JLabel("Legg til vedsekker (antall): "), c);
		NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.getDefault());
		DecimalFormat decimalFormat = (DecimalFormat) numberFormat;
		decimalFormat.setGroupingUsed(false);
		numWood = new JFormattedTextField(decimalFormat);
		numWood.setColumns(5);
		c.gridx = 0;
		c.gridy = 2;
		updateWood.add(numWood, c);
		addButton = new JButton("Legg til");
		addButton.addActionListener(new ButtonListener());
		c.gridx = 1;
		updateWood.add(addButton, c);
		cabins.addActionListener(new UpdateListener());
		Map<Integer, String> cabinsId = Database.getIdNameMap();
		String cabinSacks = "";
		Map<Integer, Double> woodAmount = Database.getVedstatusForAlleKoier();
		for (Integer cabinId : cabinsId.keySet()){
			cabinSacks += cabinsId.get(cabinId) + ": " + woodAmount.get(cabinId) + " vedsekker.";
			if (woodAmount.get(cabinId) <= minSacks){
				cabinSacks += " Trenger snart påfyll.";
			}
			cabinSacks += "\n";
		}
		cabinInUse = Database.getKoie(cabins.getSelectedItem());
		woodInformation.setText(cabinSacks);
		add(updateWood);
		add(pane);
	}
	
	private class ButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			String woods = numWood.getText();
			String numbs = "0123456789";
			if (woods.length() == 0)
				return;
			for (int a = 0; a < woods.length(); a++){
				if (numbs.indexOf(woods.charAt(a)) == -1){
					JOptionPane.showMessageDialog(null, woods + " er ikke et valid tall.");
					return;
				}
			}
			double numSacks = Double.parseDouble(woods);
			if (numSacks < 1 || numSacks > 99){
				JOptionPane.showMessageDialog(null, numSacks + " er ikke en valid input.");
				return;
			}
			cabinInUse.setVedmengde(cabinInUse.getVedmengde() + numSacks);
			Database.toDatabase(cabinInUse);
			JOptionPane.showMessageDialog(null, cabinInUse.getName() + " fikk lagt til " + numSacks + " sekker.");
			numWood.setText("");
		}
	}
	
	private class UpdateListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			cabinInUse = Database.getKoie(cabins.getSelectedItem());
		}
	}
}
