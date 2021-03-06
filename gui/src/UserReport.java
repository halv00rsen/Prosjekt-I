package src;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;

import java.awt.GridBagLayout;

import javax.swing.JPanel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JLabel;

/**
 * 
 * Rapporten en bruker skal sende inn etter at brukeren har vært på koia.
 */
public class UserReport {
	
	private JFrame frame;
	private ButtonListener buttonListener;
	private final JButton okButton, cancelButton;
	private DestroyedItems destroyedItems;
	private final JTextField numWood;
	private final LostItems lostItems;
	private final Koie cabin;
	
	private UserReportListener listener;
	
	/**
	 * Oppretter objektet
	 * @param dayFrom - dag fra
	 * @param monthFrom - måned fra
	 * @param dayTo - dag til
	 * @param monthTo - måned til
	 * @param cabin - det aktuelle koieobjektet
	 */
	public UserReport(int dayFrom, int monthFrom, int dayTo, int monthTo,  Koie cabin){
		frame = new JFrame("Rapport");
		frame.setSize(400, 400);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		buttonListener = new ButtonListener();
		this.cabin = cabin;
		JPanel cancelOkButtons = new JPanel();
		frame.getContentPane().add(cancelOkButtons, BorderLayout.SOUTH);
		cancelOkButtons.setLayout(new GridLayout(1, 0, 0, 0));
		
		okButton = new JButton("Send inn");
		cancelOkButtons.add(okButton);
		okButton.addActionListener(buttonListener);
		
		cancelButton = new JButton("Avbryt");
		cancelOkButtons.add(cancelButton);
		cancelButton.addActionListener(buttonListener);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Koieinfo", null, panel_1, null);
		panel_1.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		panel_1.add(new JLabel("Rapport for " + cabin.getName() + ". "), c);
		c.gridx = 0;
		c.gridy = 1;
		panel_1.add(new JLabel("Leid fra " + dayFrom + "." + monthFrom + " til " + dayTo + ". " + monthTo));
		c.gridy = 2;
		panel_1.add(new JLabel("Brukte vedsekker: "), c);
		c.gridx = 1;
		numWood = new JTextField(4);
		panel_1.add(numWood, c);
		
		destroyedItems = new DestroyedItems();
		tabbedPane.addTab("Ødelagt utsyr", null, destroyedItems, null);
		
		lostItems = new LostItems();
		tabbedPane.addTab("Gjenglemt utstyr", null, lostItems, null);
		
		updateEquipmentInCabin();
		frame.setLocation(GUI.getXPosition(), GUI.getYPosition());
		frame.setVisible(true);
	}
	
	/**
	 * 
	 * @param listener - setter en lytter til rapporten
	 */
	public void setListener(UserReportListener listener){
		this.listener = listener;
	}
	
	/**
	 * Oppdaterer lista med alt utstyr som er i koia
	 */
	private void updateEquipmentInCabin(){
		destroyedItems.setInventory(cabin.getInventory().getInOrderItems());
		destroyedItems.setBrokenInventory(cabin.getInventory().getBrokenItems());
	}
	
	/**
	 * 
	 * En klasse som lytter til knappene
	 */
	private class ButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if (arg0.getSource() == cancelButton){
				listener.cancelPressed();
			}
			else if (arg0.getSource() == okButton){
				String nums = "0123456789";
				String text = numWood.getText();
				for (int a = 0; a < text.length(); a++){
					if (nums.indexOf(text.charAt(a)) == -1){
						JOptionPane.showMessageDialog(null, text + " er ikke et gyldig vedtall.");
						return;
					}
				}
				int number = 0;
				if (text.length() != 0)
					number = Integer.parseInt(text);
				listener.okPressed("Ingen kommentar", destroyedItems.getDestroyedElements(), number, lostItems.getLostItems());
			}
			frame.removeAll();
			frame.setVisible(false);
			frame = null;
		}
	}
}
