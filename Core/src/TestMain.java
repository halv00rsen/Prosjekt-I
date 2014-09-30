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
		Calendar c = new Calendar();
		Date check = new Date(1,1);
		for (int b = 1; b < 13; b++){
			for (int a = 1; a < 365; a++){
				if (Calendar.validDate(a, b)){
					Date d = new Date(a, b);
					System.out.println(check + "    d: " + d);
					System.out.println("After: " + check.isAfter(d));
					System.out.println("Before: " + check.isBefore(d) + "\n");
				}
			}
		}
	}
}
