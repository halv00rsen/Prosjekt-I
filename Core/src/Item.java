package src;

public class Item {
	public static int DEFAULT_ID = 0;
	private int id;
	private String name;
	private Status status;

	public static enum Status {
		IN_ORDER,
		BROKEN,
		LOST_AND_FOUND;
	}

	/**
	 * Oppretter Item-objekt med bare navn.
	 * <p>
	 * {@link id} settes til {@link DEFAULT_ID}, må endres til unik id når den lagres i databasen.
	 * {@link status} settes til IN_ORDER.
	 * @param name Navn
	 */
	public Item(String name) {
		this(DEFAULT_ID, name, Item.Status.IN_ORDER);
	}

	/**
	 * Oppretter Item-objekt
	 * @param id Unik id i databasen
	 * @param name Navn
	 * @param status Tilstand, IN_ORDER, BROKEN eller LOST_AND_FOUND 
	 */
	public Item(int id, String name, Status status) {
		this.id = id;
		this.name = name;
		this.status = status;
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}