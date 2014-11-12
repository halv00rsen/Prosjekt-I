package src;

public class UserDatesBooked {

	public final int cabinId;
	public final Date from, to;
	public final int resID;
	
	public UserDatesBooked(int cabinId, Date from, Date to, int resID){
		this.cabinId = cabinId;
		this.from = from;
		this.to = to;
		this.resID = resID;
	}
}
