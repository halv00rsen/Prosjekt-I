package src;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import pic.Pic;

/**
 * 
 * Er et jpanel og implementerer ChangeTabListener
 * Lager instanser av kartet og et felt med informasjon om ei koie.
 * Kan trykke på kartet for å få opp info om koia.
 */
public class CabinMapField extends JPanel implements ChangeTabListener{
	
	private final CabinMap map1, map2;
	private final JTextArea cabinInfo;
	private final HashMap<Integer, int[]> cabinPos;
	
	/**
	 * Oppretter objektet
	 */
	public CabinMapField(){
		map1 = new CabinMap("cabinMap1.png");
		map2 = new CabinMap("cabinMap2.png");
		setLayout(new GridLayout(1,3));
		cabinInfo = new JTextArea();
		cabinInfo.setEditable(false);
		
		Listener l = new Listener();
		map1.addMouseListener(l);
		map2.addMouseListener(l);
		cabinPos = Pic.getKoiePositions();
		cabinInfo.setWrapStyleWord(true);
		cabinInfo.setLineWrap(true);
		cabinInfo.setBorder(BorderFactory.createEtchedBorder());
		JScrollPane pane = new JScrollPane(cabinInfo);
		pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		add(map1);
		add(map2);
		add(pane);
		
	}

	/**
	 * Gjør ingenting i dette tilfellet, siden det ikke er noen spesielle databasekall som må
	 * gjøres når man åpner fanen
	 */
	public void initPanel() {
		// TODO Auto-generated method stub
		
	}
	
	private void setTextToField(int cabinId){
		Koie cabin = Database.getKoie(cabinId);
		String info = "Koie: " + cabin.getName() + "\nVedstatus: " + cabin.getVedmengde() + "\n\nReservasjoner: ";
		for (BookingDate booking : cabin.getCalendar().getDatesBooked()){
			info += "\n" + booking.dateFrom + " til " + booking.dateTo;
		}
		if (cabin.getCalendar().getDatesBooked().size() == 0){
			info += "\nIngen reservasjoner";
		}
		info += "\n\nØdelagte ting:";
		for (Item item : cabin.getInventory().getBrokenItems()){
			info += "\n" + item.getName();
		}
		if (cabin.getInventory().getBrokenItems().size() == 0){
			info += "\nIngen ødelagte ting";
		}
		cabinInfo.setText(info);
	}
	
	private boolean validCoord(int[] coords, int x, int y){
		int bx = coords[0];
		int by = coords[1];
		return (Math.abs(bx - x) < 12) && (Math.abs(by - y) < 12);
	}
	
	/**
	 * 
	 * Lytter til musen om det bli gjort et klikk på kartet
	 */
	private class Listener implements MouseListener{

		public void mouseClicked(MouseEvent arg0) {
			
		}

		public void mouseEntered(MouseEvent arg0) {

		}

		public void mouseExited(MouseEvent arg0) {
			
		}

		/**
		 * Henter info om den koia som blir trykt på
		 * @param arg0 - en MouseEvent som blir kalt når musen blir presset ned
		 */
		public void mousePressed(MouseEvent arg0) {
			int x = arg0.getX();
			int y = arg0.getY();
			if (arg0.getSource() == map2){
				x += map1.getWidth();
			}
			for (Integer id : cabinPos.keySet()){
				if (validCoord(cabinPos.get(id), x, y)){
					setTextToField(id);
					return;
				}
			}
		}

		public void mouseReleased(MouseEvent arg0) {
			
		}
		
	}
}
