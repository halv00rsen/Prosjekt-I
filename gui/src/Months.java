package src;

public enum Months {
	Januar(1), Februar(2), Mars(3), April(4), Mai(5), Juni(6), July(7), August(8),
	September(9), Oktober(10), November(11), Desember(12);
	
	private int value;

	Months(int value){
		this.value = value;
	}
	
	public static int getValue(Months month){
		return month.getValue();
	}
	
	public int getValue(){
		return value;
	}
	
	public static Months getMonth(int value){
		for (Months month: Months.values()){
			if (month.value == value)
				return month;
		}
		return null;
	}
}
