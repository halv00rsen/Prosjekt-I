package src;

/**
 * 
 * Et enum med alle instanser til en m�ned, navn og hvilket nummer i rekken m�neden er
 */
public enum Months {
	Januar(1), Februar(2), Mars(3), April(4), Mai(5), Juni(6), July(7), August(8),
	September(9), Oktober(10), November(11), Desember(12);
	
	private int value;

	/**
	 * Oppretter m�neden
	 * @param value - hvilken m�ned i verdi det er
	 */
	Months(int value){
		this.value = value;
	}
	
	/**
	 * 
	 * @param month - en m�ned
	 * @return hvilken verdi month har
	 */
	public static int getValue(Months month){
		return month.getValue();
	}
	
	/**
	 * 
	 * @return value - hvilken verdi en m�ned har
	 */
	public int getValue(){
		return value;
	}
	
	/**
	 * 
	 * @param value - en m�nedsverdi
	 * @return month - returnerer m�neden med verdi "value"
	 */
	public static Months getMonth(int value){
		for (Months month: Months.values()){
			if (month.value == value)
				return month;
		}
		return null;
	}
}
