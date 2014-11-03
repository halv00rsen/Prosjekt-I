package src;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;

public class ChooseCabin {

	private JComboBox<String> cabins;
	
	public ChooseCabin(){
		cabins = new JComboBox<String>();
		for (String name : getNameCabins())
			cabins.addItem(name);
	}
	
	public JComboBox<String> getComboBox(){
		return cabins;
	}
	
//	Skal kommunisere med databasen
	private List<String> getNameCabins(){
		List<String> koie = new ArrayList<String>();
		koie.add("Koie 1");
		koie.add("Koie 2");
		koie.add("Koie 3");
		koie.add("Koie 4");
		return koie;
	}
	
	public String getSelectedItem(){
		return (String) cabins.getSelectedItem();
	}
	
	public void addActionListener(ActionListener l){
		cabins.addActionListener(l);
	}
}
