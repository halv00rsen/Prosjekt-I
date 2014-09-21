package src;

public abstract class Equipment {
	
	private boolean broken;
	
	public boolean isBroken(){
		return broken;
	}
	
	public abstract String getName();
}
