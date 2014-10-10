package src;

import java.util.ArrayList;
import java.util.List;


public class Koie {
	public final String name;
	public final int numBeds, numSeats;
	public final boolean toppTur, jakt, fiske, sykkel, gitar, vaffel;
	public final String terreng, spesialiteter;
	
	private final List<Equipment> equipment;
	private final Calendar cabinRented;
	

	public Koie(String name, int numBeds, int numSeats, boolean sykkel, boolean toppTur, 
			boolean jakt, boolean fiske, boolean gitar, boolean vaffel, String terreng, String spesialiteter){
		this.name = name;
		this.numBeds = numBeds;
		this.numSeats = numSeats;
		this.sykkel= sykkel;
		this.toppTur = toppTur;
		this.jakt = jakt;
		this.fiske = fiske;
		
		equipment = new ArrayList<Equipment>();
		if (gitar){
			Equipment e = new Equipment("Gitar");
			equipment.add(e);
		}
		if (vaffel){
			Equipment e = new Equipment("Vaffeljern");
			equipment.add(e);
		}
		this.gitar=gitar;
		this.vaffel=vaffel;
		
		
		this.terreng = terreng;
		this.spesialiteter=spesialiteter;
		cabinRented = new Calendar();
	}
		
	public void reserveCabin(int dayFrom, int monthFrom, int numOfDays){
		if (cabinRented.validDate(dayFrom,monthFrom)) {
//			cabinRented.reservePeriod( dayFrom, monthFrom, numOfDays)
			}
//		else
	}
}
	
	