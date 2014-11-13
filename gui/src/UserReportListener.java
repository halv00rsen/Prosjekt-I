package src;

import java.util.List;

public interface UserReportListener {
	public void okPressed(String comment, List<Item> brokenInventory, int usedWood, List<Item> lostItems);
	public void cancelPressed();
}
