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
		Date d1 = new Date(12, 12);
		Date d2 = new Date(3, 2);
		Date d3 = new Date(4, 2);
		Date d4 = new Date(7, 2);
		BookingDate bd = new BookingDate(d3, d4);
		System.out.println(bd.datesCollideWithBooking(d1, d2));
	}
}
