package src;

public abstract class Equipment {
        private static String name;
	private boolean broken;

	public Equipment(String name){
	        this.name = name;	
	}

	public boolean isBroken(){
		return broken;
	}

	public String getName(){
		return name;
	}
}
