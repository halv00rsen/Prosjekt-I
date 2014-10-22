package src;

public class Item{
	private String name;
	private boolean broken;

	public Item(String name, boolean broken){
		this.name = name;
		this.broken = broken;
	}
	
	// Setter broken automatisk til false hvis argumentet ikke er oppgitt
	public Item(String name){
		this(name, false);
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

	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
}
