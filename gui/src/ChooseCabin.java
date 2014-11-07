package src;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JComboBox;

public class ChooseCabin {

	private JComboBox<String> cabins;
	private HashMap<Integer, String> cabinsIdName;
	
	public ChooseCabin(){
		cabins = new JComboBox<String>();
		cabinsIdName = Database.getIdNameMap();
		for (String name : getNameCabins())
			cabins.addItem(name);
	}
	
	public JComboBox<String> getComboBox(){
		return cabins;
	}
	
//	Skal kommunisere med databasen
	private List<String> getNameCabins(){
		List<String> cabinNames = new ArrayList<String>();
		for (Integer id : cabinsIdName.keySet()){
			cabinNames.add(cabinsIdName.get(id));
		}
		return cabinNames;
	}
	
	public String getCabinWith(int id){
		return cabinsIdName.get(id);
	}
	
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
	
	public void addActionListener(ActionListener l){
		cabins.addActionListener(l);
	}
}
