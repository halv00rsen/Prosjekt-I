package src;

import java.util.List;

public interface UserReportListener {
	public void okPressed(String comment, List<String> brokenInventory);
	public void cancelPressed();
	
}
