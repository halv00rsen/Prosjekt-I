package src;

/** Inneholder informasjon, utstyr og reservasjoner knyttet til en koie */
public class Koie {
	private final int id;
	private final String name;
	private final String coordinates;
	private final int year;
	private final Inventory inventory;
	private final Calendar cabinRented;

	private int numBeds;
	private int numSeats;
	private double vedmengde;
	private String terreng;
	private String sykkel;
	private String topptur;
	private String jaktOgFiske;
	private String spesialiteter;

	/**
	 * Oppretter et Koie-objekt.
	 * @param id Unik ID i databasen
	 * @param name Navn
	 * @param coordinates Koordinater
	 * @param year Byggeår
	 */
	public Koie(int id, String name, String coordinates, int year) {
		this.id = id;
		this.name = name;
		this.coordinates = coordinates;
		this.year = year;
			
		inventory = new Inventory();
		cabinRented = new Calendar();
	}

	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getCoordinate() {
		return coordinates;
	}
	
	public int getYear() {
		return year;
	}
		
	public Inventory getInventory() {
		return inventory;
	}
	
	public Calendar getCalendar() {
		return cabinRented;
	}
	
	public int getNumBeds() {
		return numBeds;
	}
	
	public void setNumBeds(int beds) {
		this.numBeds = beds;
	}

	public int getNumSeats() {
		return numSeats;
	}
		
	public void setNumSeats(int seats) {
		this.numSeats = seats;
	}
	
	public double getVedmengde() {
		return vedmengde;
	}
		
	public void setVedmengde(double ved) {
		this.vedmengde = ved;
	}

	public String getTerreng() {
		return terreng;
	}
	
	public void setTerreng(String terreng) {
		this.terreng = terreng;
	}

	public String getSykkel() {
		return sykkel;
	}
		
	public void setSykkel(String sykkel) {
		this.sykkel = sykkel;
	}
	
	public String getTopptur() {
		return topptur;
	}
	
	public void setTopptur(String topptur) {
		this.topptur = topptur;
	}

	public String getJaktOgFiske() {
		return jaktOgFiske;
	}
		
	public void setJaktOgFiske(String jaktofisk) {
		this.jaktOgFiske = jaktofisk;
	}

	public String getSpesialiteter() {
		return spesialiteter;
	}
		
	public void setSpesialiteter(String spesial) {
		this.spesialiteter = spesial;
	}

	public String toString() {
		return name +"\t"+
				String.valueOf(numBeds) +"\t"+
				String.valueOf(numBeds) +"\t"+
				coordinates +"\t"+
				String.valueOf(year);

				/*
				terreng +"\t"+
				String.valueOf(sykkel) +"\t"+
				String.valueOf(topptur) +"\t"+
				String.valueOf(jakt) +"\t"+
				String.valueOf(fiske) +"\t"+
				spesialiteter;
				*/
	}
}
	