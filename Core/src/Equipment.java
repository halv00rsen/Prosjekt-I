package src;

public class Equipment{
	private String name;
	private boolean broken;

	public Equipment(String name, boolean broken){
		this.name = name;
		this.broken = broken;
	}
	
	public Equipment(String name){
		this(name, false);
	}

	public boolean isBroken(){
		return broken;
	}
	
	public void setBroken(boolean broken){
		this.broken = broken;
	}
	
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
