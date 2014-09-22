package src;

public class Equipment {
	private static String name;
	private boolean broken;

	public Equipment(String name){
	        this.name = name;	
	}

	public boolean isBroken(){
		return broken;
	}
	
	public void setBroken(boolean broken){
		this.broken = broken;
	}

	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
}
