package src;

import java.util.ArrayList;
import java.util.List;


public class Koie {
	private final String name;
	private final int numBeds, numSeats;
	private final boolean toppTur, jakt, fiske, sykkel;
	private final String terreng, spesialiteter;
	private final List<Equipment> equipment;
	private final Calendar cabinRented;
	

	public Koie(String name, int numBeds, int numSeats, boolean sykkel, boolean toppTur, 
			boolean jakt, boolean fiske, String terreng, String spesialiteter, List<Equipment> equipment){
		this.name = name;
		this.numBeds = numBeds;
		this.numSeats = numSeats;
		this.sykkel= sykkel;
		this.toppTur = toppTur;
		this.jakt = jakt;
		this.fiske = fiske;
		this.terreng = terreng;
		this.spesialiteter = spesialiteter;
		this.equipment = equipment;
			
		cabinRented = new Calendar();
	}
			
	public String getName() {
		return name;
	}

	public int getNumBeds() {
		return numBeds;
	}

	public int getNumSeats() {
		return numSeats;
	}

	public boolean isToppTur() {
		return toppTur;
	}

	public boolean isJakt() {
		return jakt;
	}

	public boolean isFiske() {
		return fiske;
	}

	public boolean isSykkel() {
		return sykkel;
	}

	public String getTerreng() {
		return terreng;
	}

	public String getSpesialiteter() {
		return spesialiteter;
	}
	
	public List<Equipment> getEquipment() {
		return equipment;
	}

	public void reserveCabin(int dayFrom, int monthFrom, int numOfDays){
		if (cabinRented.validDate(dayFrom,monthFrom)) {
//			cabinRented.reservePeriod( dayFrom, monthFrom, numOfDays)
			}
//		else
	}
}
	
	