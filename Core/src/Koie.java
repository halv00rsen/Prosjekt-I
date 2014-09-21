package src;

import java.util.ArrayList;
import java.util.List;

public class Koie {
	public final String name;
	public final int numBeds;
	
	private final List<Equipment> equipment;
	private final Calendar cabinRented;
	
	public Koie(String name, int numBeds){
		this.name = name;
		this.numBeds = numBeds;
		equipment = new ArrayList<Equipment>();
		cabinRented = new Calendar();
	}
	
	public boolean cabinIsRented(int day, int month){
		return cabinRented.dayIsTaken(day, month);
	}
	
	public void reserveCabin(int dayFrom, int monthFrom, int numOfDays){
		
	}
}
