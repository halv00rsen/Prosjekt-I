package src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JPanel;

public class AdminReport extends JPanel implements ChangeTabListener{

	private final ChooseCabin cabins;
	
	public AdminReport(){
		cabins = new ChooseCabin();
		cabins.addActionListener(new CabinListener());
		add(cabins.getComboBox());
	}
	
	private void getReports(){
		List<String> reports = Database.getRapport(cabins.getSelectedItem());
		for (String r : reports){
			System.out.println(r);
			System.out.println();
		}
	}

	public void initPanel() {
		
	}
	
	private class CabinListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			getReports();
		}
		
	}
}
