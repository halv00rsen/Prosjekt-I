package src;

import src.Inventory;

public class Koie {
	private final int id;
	private final String name;
	private final float vedkapasitet;
	private float vedmengde;
	private final int numBeds;
	private final Inventory inventory;
	private final Calendar cabinRented;
	/*
	private final String numSeats, year; //trenger ikke � v�re int fordi vi bruker aldri de numeriske verdiene til noe
	private final boolean sykkel, topptur, jakt, fiske;
	private final String coordinates, terreng, spesialiteter;
	*/

	public Koie(int id, String name, float vedkapasitet, float vedmengde, int numBeds, Inventory inventory) {
				/*
				String numBeds, String numSeats, String coordinates,
				String year, String terreng,
				boolean sykkel, boolean topptur, boolean jakt, boolean fiske,
				String spesialiteter, Inventory inventory, float vedMax, float ved) {
				*/

		this.id = id;
		this.name = name;
		this.vedkapasitet = vedkapasitet;
		this.vedmengde = vedmengde;
		this.numBeds = numBeds;
		this.inventory = inventory;
		/*
		this.numSeats = numSeats;
		this.coordinates = coordinates;
		this.year = year;
		this.terreng = terreng;
		this.sykkel= sykkel;
		this.topptur = topptur;
		this.jakt = jakt;
		this.fiske = fiske;
		this.spesialiteter = spesialiteter;
		*/
			
		cabinRented = new Calendar();
	}

	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public float getVedkapasitet() {
		return vedkapasitet;
	}
	
	public float getVedmengde() {
		return vedmengde;
	}
	
	public void setVedmengde(float vedmengde) {
		this.vedmengde = vedmengde;
	}

	public int getNumBeds() {
		return numBeds;
	}
	
	public Inventory getInventory() {
		return inventory;
	}
	
	public Calendar getCalendar() {
		return cabinRented;
	}
/*
	public String getNumSeats() {
		return numSeats;
	}
	
	public String getCoordinates() {
		return coordinates;
	}

	public boolean hasTopptur() {
		return topptur;
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
*/
//	public void reserveCabin(int dayFrom, int monthFrom, int numOfDays, String name){
//		if (cabinRented.validDate(dayFrom,monthFrom)) {
//			cabinRented.reservePeriod( dayFrom, monthFrom, numOfDays)
//			}
//		else
//	}
/*
	public String toString() {
		return name +"\t"+
				String.valueOf(numBeds) +"\t"+
				String.valueOf(numBeds) +"\t"+
				coordinates +"\t"+
				String.valueOf(year) +"\t"+
				terreng +"\t"+
				String.valueOf(sykkel) +"\t"+
				String.valueOf(topptur) +"\t"+
				String.valueOf(jakt) +"\t"+
				String.valueOf(fiske) +"\t"+
				spesialiteter;
	}
	*/
}
	
	