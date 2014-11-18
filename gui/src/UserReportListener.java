package src;

import java.util.List;

/**
 * 
 * En lytter til brukerrapporten
 */
public interface UserReportListener {
	/**
	 * Sier i fra om rapporten ble skrevet.
	 * @param comment - kommentar
	 * @param brokenInventory - en liste med ødelagt utstyr
	 * @param usedWood - antall vedsekker brukt
	 * @param lostItems - utstyr som er mistet
	 */
	public void okPressed(String comment, List<Item> brokenInventory, int usedWood, List<Item> lostItems);
	/**
	 * Sier i fra at rapporten ikke ble skrevet, og dermed avsluttet
	 */
	public void cancelPressed();
}
