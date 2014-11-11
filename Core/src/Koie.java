package src;

import src.Inventory;

public class Koie {
	private final int id;
	private final String name;
	private final String coordinate;
	private final int year;
	
	private double vedmengde;
	private int numBeds;
	private int numSeats;
	private String terreng;
	private String sykkel;
	private String topptur;
	private String jaktOgFiske;
	private String spesialiteter;
	
	private final Inventory inventory;
	private final Calendar cabinRented;


	public Koie(int id, String name, String coordinate, int year) {

		this.id = id;
		this.name = name;
		this.coordinate = coordinate;
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
		return coordinate;
	}
	
	public int getYear() {
		return year;
	}
	
	public double getVedmengde() {
		return vedmengde;
	}
	
	public int getNumBeds() {
		return numBeds;
	}
	
	public int getNumSeats() {
		return numSeats;
	}
	
	public String getTerreng() {
		return terreng;
	}
	
	public String getSykkel() {
		return sykkel;
	}
	
	public String getTopptur() {
		return topptur;
	}
	
	public String getJaktOgFiske() {
		return jaktOgFiske;
	}
	
	public String getSpesialiteter() {
		return spesialiteter;
	}
	
	public Calendar getCalendar() {
		return cabinRented;
	}
	
	public Inventory getInventory() {
		return inventory;
	}
	
	
	
	public void setVedmengde(double ved) {
		this.vedmengde = ved;
	}
	
	public void setNumBeds(int beds) {
		this.numBeds = beds;
	}
	
	public void setNumSeats(int seats) {
		this.numSeats = seats;
	}
	
	public void setTerreng(String terreng) {
		this.terreng = terreng;
	}
	
	public void setSykkel(String sykkel) {
		this.sykkel = sykkel;
	}
	
	public void setTopptur(String topptur) {
		this.topptur = topptur;
	}
	
	public void setJaktOgFiske(String jaktofisk) {
		this.jaktOgFiske = jaktofisk;
	}
	
	public void setSpesialiteter(String spesial) {
		this.spesialiteter = spesial;
	}
	
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
	
	