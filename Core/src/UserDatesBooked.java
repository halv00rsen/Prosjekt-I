package src;

public class UserDatesBooked {

	public final int cabinId;
	public final Date from, to;
	
	public UserDatesBooked(int cabinId, Date from, Date to){
		this.cabinId = cabinId;
		this.from = from;
		this.to = to;
	}
}
