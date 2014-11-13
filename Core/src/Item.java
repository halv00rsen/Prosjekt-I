package src;

/** Inneholder unik database-ID, navn og status for en ting som tilhører en {@link Koie} */
public class Item implements Comparable<Item>{
	public static int DEFAULT_ID = 0;
	private int id;
	private String name;
	private Status status;

	/**
	 * Item-objektets tilstander
	 * <p>
	 * {@link #IN_ORDER} eller {@link #BROKEN} eller {@link #LOST_AND_FOUND}
	 */
	public static enum Status {
		/** I orden */
		IN_ORDER,
		
		/** Ødelagt */
		BROKEN,
		
		/** Gjenglemt */
		LOST_AND_FOUND;
	}
	
	/**
	 * Gjør om en streng til en tilsvarende {@link Status}
	 * @param statusString Status som streng
	 * @return returnStatus Status i enum {@link Status} 
	 */
	public static Item.Status getItemStatus(String statusString) {
		Item.Status returnStatus;
		if (statusString.equals("IN_ORDER")) {
			returnStatus = Item.Status.IN_ORDER;
		} else if (statusString.equals("BROKEN")) {
			returnStatus = Item.Status.BROKEN;
		} else if (statusString.equals("LOST_AND_FOUND")) {
			returnStatus = Item.Status.LOST_AND_FOUND;
		} else {
			returnStatus = null;
		}
		return returnStatus;
	}

	/**
	 * Oppretter Item-objekt med bare navn. Brukes når admin legger inn nytt utstyr.
	 * <p>
	 * {@link #id} settes til {@link DEFAULT_ID}, må endres til unik id når den lagres i databasen.
	 * {@link status} settes til {@link Status#IN_ORDER}.
	 * @param name Navn
	 */
	public Item(String name) {
		this(DEFAULT_ID, name, Item.Status.IN_ORDER);
	}

	/**
	 * Oppretter Item-objekt med navn og status. Brukes for gjenglemte ting.
	 * <p>
	 * {@link #id} settes til {@link #DEFAULT_ID}, må endres til unik id når den lagres i databasen.
	 * @param name Navn
	 * @param status Status i enum {@link Status} 
	 */
	public Item(String name, Status status) {
		this(DEFAULT_ID, name, status);
	}

	/**
	 * Oppretter Item-objekt. Brukes når data kommer fra databasen.
	 * @param id Unik id i databasen
	 * @param name Navn
	 * @param status Status i enum {@link Status} 
	 */
	public Item(int id, String name, Status status) {
		this.id = id;
		this.name = name;
		this.status = status;
	}
	
	/**
	 * Returnerer Item-objektets ID
	 * @return Item-objektets ID
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Setter Item-objektets ID
	 * @param id Item-objektets ID
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Returnerer Item-objektets navn
	 * @return Item-objektets navn
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Setter Item-objektets navn
	 * @param name Item-objektets navn
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returnerer Item-objektets status
	 * @return Item-objektets status i enum {@link Status}
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * Setter Item-objektets status
	 * @param status Item-objektets status i enum {@link Status}
	 */
	public void setStatus(Status status) {
		this.status = status;
	}
	
	/**
	 * Returnerer Item-objektets navn
	 */
	public String toString(){
		return name;
	}

	/**
	 * Sammenligner navnene til to Item-objekter
	 */
	public int compareTo(Item item) {
		if (item.name.equals(name)){
			return 0;
		}
		return 1;
	}
}