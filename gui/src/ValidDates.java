package src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;

/**
 * 
 * Et objekt som håndterer riktig format p� dato.
 */
public class ValidDates {

	private Date today, maxDate;
	private final JComboBox<Integer> date, numDays;
	private final JComboBox<Months> month;
	private final List<ValidDatesListener> listeners;
	private final MonthChanged listener;
	
	/**
	 * Oppretter objektet
	 * @param day - en JComboBox som alle dager i en måned skal settes til.
	 * @param month - en JComboBox som alle lovlige måneder kan reserveres i.
	 * @param numDays - en JComboBox som viser antall dager man leier for
	 */
	public ValidDates(JComboBox<Integer> day, JComboBox<Months> month, JComboBox<Integer> numDays){
		today = Calendar.getTodaysDate();
		maxDate = Calendar.getMaxDate();
		this.date = day;
		this.month = month;
		this.numDays = numDays;
		listeners = new ArrayList<ValidDatesListener>();
		for (int a = 0; a < 11; a++){
			numDays.addItem(a);
		}
		date.addItem(0);//Must be here, else nullpointerexception
		listener = new MonthChanged();
		this.month.addActionListener(listener);
		date.addActionListener(listener);
		this.numDays.addActionListener(listener);
		int monthToDay = today.month;
		for (int a = monthToDay; a <= monthToDay + 6; a++){
			if (a % 12 == 0)
				this.month.addItem(Months.getMonth(12));
			else
				this.month.addItem(Months.getMonth(a % 12));
		}
		updateDays();
	}
	
	/**
	 * Henter verdiene som er valgt
	 */
	public void setCabin(){
		int[] res = getReservation();
		callValidDate(res[0], res[1], res[2]);
	}
	
	private void callValidDate(int day, int month, int numDays){
		for (ValidDatesListener l: listeners)
			l.updateField(day, month, numDays);
	}
	
	/**
	 * 
	 * @param l - lytteren blir lagt til
	 */
	public void addListener(ValidDatesListener l){
		listeners.add(l);
	}
	
	/**
	 * 
	 * @param l - lytteren blir fjernet
	 */
	public void removeListener(ValidDatesListener l){
		listeners.remove(l);
	}
	
	/**
	 * 
	 * @return res - returnerer den valgte dato og antall dager som skal leies
	 */
	public int[] getReservation(){//returns [dag, måned, antall dager]
		int[] res = new int[3];
		res[0] = (int) date.getSelectedItem();
		res[1] = ((Months) month.getSelectedItem()).getValue();
		res[2] = (int) numDays.getSelectedItem();
		return res;
	}
	
	private void checkUpdateDay(){
		today = Calendar.getTodaysDate();
		maxDate = Calendar.getMaxDate();
	}
	
	private void updateDays(){
		date.removeActionListener(listener);
		int saveDay = (int) date.getSelectedItem();
		date.removeAllItems();
		int valueMonth = ((Months) month.getSelectedItem()).getValue();
		int start = 1;
		int end = Calendar.getDayOfMonth(valueMonth);
		if (valueMonth == today.month){
			start = today.day;
		}else if (valueMonth == maxDate.month){
			end = maxDate.day;
		}
		for (int a = start; a < end + 1; a++){
			date.addItem(a);
		}
		date.setSelectedItem(saveDay);
		date.addActionListener(listener);
	}
	
	/**
	 * 
	 * Lytter til månedboksen, siden antall dager varierer fra måned til måned
	 */
	private class MonthChanged implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if (arg0.getSource() == month)
				updateDays();
			checkUpdateDay();
			int[] res = getReservation();
			callValidDate(res[0], res[1], res[2]);
		}
	}
}
