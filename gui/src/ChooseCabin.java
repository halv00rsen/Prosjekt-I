package src;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;

/**
 * 
 * Henter navnet på alle koiene fra GUI og legger dem i en JComboBox som en annen klasse kan hente og bruke
 */
public class ChooseCabin {

	private JComboBox<String> cabins;
	private Map<Integer, String> cabinsIdName;
	
	/**
	 * Oppretter ChooseCabin
	 * Objektet henter navnet på alle koiene i databasen og lagrer disse i en JComboBox
	 */
	public ChooseCabin(){
		cabins = new JComboBox<String>();
		cabinsIdName = GUI.getIdMap();
		for (String name : getNameCabins())
			cabins.addItem(name);
	}
	
	/**
	 * 
	 * @return cabins for at andre skal få legge til komboboksen til sitt panel
	 */
	public JComboBox<String> getComboBox(){
		return cabins;
	}
	
	/**
	 * 
	 * @return cabinNames en liste med alle navnene til koiene
	 */
	private List<String> getNameCabins(){
		if (cabinsIdName == null)
			return null;
		List<String> cabinNames = new ArrayList<String>();
		for (Integer id : cabinsIdName.keySet()){
			cabinNames.add(cabinsIdName.get(id));
		}
		return cabinNames;
	}
	
	/**
	 * 
	 * @param id idverdien til ei koie
	 * @return cabinsIdName stringen til koie med verdi id
	 */
	public String getCabinWith(int id){
		return cabinsIdName.get(id);
	}
	
	/**
	 * 
	 * @return cabinId returnerer idverdien til koia som er valgt
	 */
	public int getSelectedItem(){
		String item = (String) cabins.getSelectedItem();
		int cabinId = -1;
		for (Integer id : cabinsIdName.keySet()){
			if (cabinsIdName.get(id).equals(item)){
				cabinId = id;
				break;
			}
		}
		return cabinId;
	}
	
	/**
	 * 
	 * @return cabinName - returnerer navnet til den valgte koia
	 */
	public String getSelectedCabinName(){
		return (String) cabins.getSelectedItem();
	}
	
	/**
	 * 
	 * @param l setter en actionlistener til comboboksen
	 */
	public void addActionListener(ActionListener l){
		cabins.addActionListener(l);
	}
}
