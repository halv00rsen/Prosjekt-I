package src;

/**
 * 
 * Et enum med alle instanser til en måned, navn og hvilket nummer i rekken måneden er
 */
public enum Months {
	Januar(1), Februar(2), Mars(3), April(4), Mai(5), Juni(6), July(7), August(8),
	September(9), Oktober(10), November(11), Desember(12);
	
	private int value;

	/**
	 * Oppretter måneden
	 * @param value - hvilken måned i verdi det er
	 */
	Months(int value){
		this.value = value;
	}
	
	/**
	 * 
	 * @param month - en måned
	 * @return hvilken verdi month har
	 */
	public static int getValue(Months month){
		return month.getValue();
	}
	
	/**
	 * 
	 * @return value - hvilken verdi en måned har
	 */
	public int getValue(){
		return value;
	}
	
	/**
	 * 
	 * @param value - en månedsverdi
	 * @return month - returnerer måneden med verdi "value"
	 */
	public static Months getMonth(int value){
		for (Months month: Months.values()){
			if (month.value == value)
				return month;
		}
		return null;
	}
}
