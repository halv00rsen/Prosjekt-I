package src;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ItemStatus extends JPanel{

	private final ChooseCabin cabins;
	private final JTextArea inOrder, broken, lostAndFound;
	
	public ItemStatus(){
		cabins = new ChooseCabin();
		cabins.addActionListener(new CabinListener());
//		panel.add(cabins.getComboBox(), c);
		setLayout(new GridLayout(2,1));
		add(cabins.getComboBox());
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,3));
		inOrder = createTextArea("Utstyr", panel);
		broken = createTextArea("Ødelagt", panel);
		lostAndFound = createTextArea("Mistet utstyr", panel);
		add(panel);
		
		setKoieInformation(cabins.getSelectedItem());
		
	}
	
	private JTextArea createTextArea(String header, JPanel panel){
		JTextArea area = new JTextArea();
		area.setEditable(false);
		area.setBorder(BorderFactory.createTitledBorder(null, header));
		JScrollPane pane = new JScrollPane(area);
		pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panel.add(pane);
		return area;
	}
	
	private void setKoieInformation(int cabinId){
		Inventory cabin = Database.getKoie(cabinId).getInventory();
		String inOrder = "";
		for (Item item : cabin.getInOrderItems())
			inOrder += item.getName() + "\n";
		this.inOrder.setText(inOrder);
		String broken = "";
		for (Item item : cabin.getBrokenItems())
			broken += item.getName() + "\n";
		this.broken.setText(broken);
		String lost = "";
		for (Item item: cabin.getLostAndFoundItems())
			lost += item.getName() + "\n";
		this.lostAndFound.setText(lost);
		this.revalidate();
		this.repaint();
	}
	
	private class CabinListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			setKoieInformation(cabins.getSelectedItem());
		}
	}
}
