package src;

public class Item{
	private int id;
	private String name;
	private boolean broken;

	/**
	 * Oppretter Item-objekt
	 * @param id	
	 * @param name
	 * @param broken
	 */
	public Item(int id, String name, boolean broken){
		this.id = id;
		this.name = name;
		this.broken = broken;
	}
	
	/**
	 * Oppretter Item-objekt med bare navn, setter id til 0 og broken til false.
	 * @param name	Navn
	 */
	public Item(String name){
		this(0, name, false);
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}

	public boolean isBroken(){
		return broken;
	}
	
	public void setBroken(boolean broken){
		this.broken = broken;
	}
	
	// Setter broken automatisk til true hvis argumentet ikke er oppgitt
	public void setBroken(){
		this.setBroken(true);
	}
}