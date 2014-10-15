package src;

public class Equipment{
	private String name;
	private boolean broken;

	public Equipment(String name, boolean broken){
		this.name = name;
		this.broken = broken;
	}
	
	// Setter broken automatisk til false hvis argumentet ikke er oppgitt
	public Equipment(String name){
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
