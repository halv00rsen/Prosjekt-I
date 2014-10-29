package src;

public class Item {
	private int id;
	private String name;
	private boolean broken;

	/**
	 * Oppretter Item-objekt med bare navn.
	 * <p>
	 * {@link id} settes til 0, må endres til unik id når den lagres i databasen.
	 * {@link broken} settes til false.
	 * @param name Navn
	 */
	public Item(String name) {
		this(0, name, false);
	}

	/**
	 * Oppretter Item-objekt
	 * @param id Unik id i databasen
	 * @param name Navn
	 * @param broken Status, true/false
	 */
	public Item(int id, String name, boolean broken) {
		this.id = id;
		this.name = name;
		this.broken = broken;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public boolean isBroken() {
		return broken;
	}

	/**
	 * Setter {@link broken} til true når verdien ikke oppgis
	 */
	public void setBroken() {
		this.setBroken(true);
	}

	public void setBroken(boolean broken) {
		this.broken = broken;
	}
}