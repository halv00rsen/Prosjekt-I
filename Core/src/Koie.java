package src;

import java.util.ArrayList;
import java.util.List;


public class Koie {
	public final String name;
	public final int numBeds;
	public final int numSeats;
	public final boolean toppTur;
	public final boolean jakt;
	public final boolean fiske;
	public final boolean sykkel;
	public final boolean gitar;
	public final boolean vaffel;
	public final enum terreng {S,T,S/T,H};
	public final String spesialiteter;
	
	private final List<Equipment> equipment;
	private final Calendar cabinRented;
	

	public Koie(String name, int numBeds, numSeats, boolean sykkel, toppTur, jakt, fiske, gitar, vaffel enum terreng, String spesialiteter){
		this.name = name;
		this.numBeds = numBeds;
		this.numSeats = numSeats;
		this.toppTur = toppTur;
		this.jakt = jakt;
		this fiske = fiske;
		this terreng = terreng;
		this.spesialiteter=spesialiteter;
		this.gitar=gitar;
		this.vaffel=vaffel;
		equipment = new ArrayList<Equipment>();
		cabinRented = new Calendar();
		this.sykkel= sykkel;
	}
	
	
	
	public void reserveCabin(int dayFrom, int monthFrom, int numOfDays){
		if cabinRented.validDate(dayFrom,monthFrom) {
			cabinRented.reservePeriod( dayFrom, monthFrom, numOfDays)
			}
		else
		
	}
	
	