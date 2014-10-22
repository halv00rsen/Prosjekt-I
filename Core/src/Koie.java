package src;

import java.util.List;


public class Koie {
	private final String name;
	private final int id, numBeds, numSeats, year;
	private final boolean sykkel, toppTur, jakt, fiske;
	private final String coordinates, terreng, spesialiteter;
	private final List<Item> equipment;
	private final Calendar cabinRented;

	public Koie(int id, String name,
				int numBeds, int numSeats, String coordinates,
				int year, String terreng,
				boolean sykkel, boolean toppTur, boolean jakt, boolean fiske,
				String spesialiteter, List<Item> equipment) {

		this.id = id;
		this.name = name;
		this.numBeds = numBeds;
		this.numSeats = numSeats;
		this.coordinates = coordinates;
		this.year = year;
		this.terreng = terreng;
		this.sykkel= sykkel;
		this.toppTur = toppTur;
		this.jakt = jakt;
		this.fiske = fiske;
		this.spesialiteter = spesialiteter;
		this.equipment = equipment;
			
		cabinRented = new Calendar();
	}
	
	public String toString() {
		return name +"\t"+
				String.valueOf(numBeds) +"\t"+
				String.valueOf(numBeds) +"\t"+
				coordinates +"\t"+
				String.valueOf(year) +"\t"+
				terreng +"\t"+
				String.valueOf(sykkel) +"\t"+
				String.valueOf(toppTur) +"\t"+
				String.valueOf(jakt) +"\t"+
				String.valueOf(fiske) +"\t"+
				spesialiteter;
	}
			

	public int getId() {
		return id;
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
	
	public String getCoordinates() {
		return coordinates;
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
	
	public List<Item> getEquipment() {
		return equipment;
	}

	public void reserveCabin(int dayFrom, int monthFrom, int numOfDays){
		if (cabinRented.validDate(dayFrom,monthFrom)) {
//			cabinRented.reservePeriod( dayFrom, monthFrom, numOfDays)
			}
//		else
	}
}
	
	