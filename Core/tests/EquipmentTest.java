package tests;

import src.Equipment;

public class EquipmentTest {
	
	public static void main(String[] args) {
		//int x = 2;
		Equipment e = new Equipment("Gitar");
		System.out.println(e.getName());
		System.out.println(e.isBroken());
		e.setBroken();
		System.out.println(e.isBroken());
		
	}

}
