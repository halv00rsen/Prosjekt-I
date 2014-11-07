package src;

public interface ReservationsFrameListener {
	public boolean isValidReservation(int day, int month, int numDays);
	public String getCabinInformation(String cabin);
}
