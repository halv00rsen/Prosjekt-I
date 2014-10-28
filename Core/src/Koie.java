package src;

import src.Inventory;

public class Koie {
	private final String name;
	private final String id, numBeds, numSeats, year; //trenger ikke � v�re int fordi vi bruker aldri de numeriske verdiene til noe
	private final boolean sykkel, toppTur, jakt, fiske;
	private final String coordinates, terreng, spesialiteter;
	private final Inventory inventory;
	private final Calendar cabinRented;

	public Koie(String id, String name,
				String numBeds, String numSeats, String coordinates,
				String year, String terreng,
				boolean sykkel, boolean toppTur, boolean jakt, boolean fiske,
				String spesialiteter, Inventory inventory) {

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
		this.inventory = inventory;
			
		cabinRented = new Calendar();
	}

	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public String getNumBeds() {
		return numBeds;
	}

	public String getNumSeats() {
		return numSeats;
	}
	
	public String getCoordinates() {
		return coordinates;
	}

	public boolean hasToppTur() {
		return toppTur;
	}

	public boolean hasJakt() {
		return jakt;
	}

	public boolean hasFiske() {
		return fiske;
	}

	public boolean hasSykkel() {
		return sykkel;
	}

	public String getTerreng() {
		return terreng;
	}

	public String getSpesialiteter() {
		return spesialiteter;
	}
	
	public Inventory getInventory() {
		return inventory;
	}

//	public void reserveCabin(int dayFrom, int monthFrom, int numOfDays, String name){
//		if (cabinRented.validDate(dayFrom,monthFrom)) {
//			cabinRented.reservePeriod( dayFrom, monthFrom, numOfDays)
//			}
//		else
//	}

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
}
	
	