package src;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * 
 * Et panel som henter vedstatus til koiene og der man kan forandre vedstatusen
 */
public class WoodStatus extends JPanel implements ChangeTabListener{
	
	private final JPanel updateWood;
	private final JTextArea woodInformation;
	private final ChooseCabin cabins;
	private final JFormattedTextField numWood;
	private final JButton addButton;
	private Koie cabinInUse;
	public static final int minSacks = 5;
	private final Map<Integer, Double> woodStatus;
	private final Map<Integer, String> cabinNames;
	
	/**
	 * Oppretter objektet
	 */
	public WoodStatus(){
		setLayout(new GridBagLayout());
		updateWood = new JPanel();
		woodInformation = new JTextArea(20, 20);
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
		woodStatus = new HashMap<Integer, Double>();
		cabinNames = new HashMap<Integer, String>();
		c.gridx = 0;
		c.gridy = 0;
		add(updateWood, c);
		c.gridx = 1;
//		c.gridheight = 5;
//		c.gridwidth = 5;
		add(pane, c);
	}
	
	private void setWoodInfo(){
		String info = "";
		for (Integer id : woodStatus.keySet()){
			info += cabinNames.get(id) + ": " + woodStatus.get(id) + " vedsekker." + (woodStatus.get(id) < 5? "Trenger påfyll": "") + "\n";
		}
		woodInformation.setText(info);
	}
	
	/**
	 * 
	 * En lytter til knappene i panelet som opdaterer databasen og feltene i objektet
	 */
	private class ButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			String woods = numWood.getText();
			String numbs = "0123456789-";
			if (cabinInUse == null)
				return;
			if (woods.length() == 0){
				JOptionPane.showMessageDialog(null, "Feil vedinput.");
				return;
			}
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
			if (!Database.toDatabase(cabinInUse)){
				JOptionPane.showMessageDialog(null, "Feil med kommunikasjonen med databasen");
				return;
			}
			JOptionPane.showMessageDialog(null, cabinInUse.getName() + " fikk lagt til " + numSacks + " sekker.");
			numWood.setText("");
			woodStatus.put(cabinInUse.getId(), cabinInUse.getVedmengde());
			setWoodInfo();
		}
	}
	
	/**
	 * 
	 * En lytter som blir kalt når ei ny koie blir valgt
	 */
	private class UpdateListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			cabinInUse = Database.getKoie(cabins.getSelectedItem());
		}
	}

	public void initPanel() {
		cabinNames.clear();
		cabinNames.putAll(GUI.getIdMap());
		Map<Integer, Double> woodAmount = Database.getVedstatusForAlleKoier();
		if (woodAmount == null)
			return;
		woodStatus.clear();
		for (Integer cabinId : cabinNames.keySet()){
			woodStatus.put(cabinId, woodAmount.get(cabinId));
		}
		cabinInUse = Database.getKoie(cabins.getSelectedItem());
		setWoodInfo();
	}
}
