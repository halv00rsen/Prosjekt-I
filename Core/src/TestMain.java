package src;

public class TestMain {
	
	public static void main(String[] args){
//		String[] hei = new String[] {"Januar", "Februar", "Mars", "April","Mai", "Juni", "July", "August", "September", "Oktober", "November", "Desember"};
//		for (int a = 0; a < 12; a++){
//			System.out.println(hei[a]);
//			for (int b = -2; b < 35; b++){
//				System.out.println("Day: " + b + ", isValid: " + Calendar.validDate(b, a + 1));
//			}
//		}
		System.out.println(Calendar.getNumOfDaysBetween(new Date(12, 12), new Date(5, 1)));
	}
}
