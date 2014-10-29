package src;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ValidDates extends JPanel{

	private final Date today, maxDate;
	private final JComboBox<Integer> date, numDays;
	private final JComboBox<Months> month;
	
	public ValidDates(){
		Calendar calendar = new Calendar();
		today = calendar.getTodaysDate();
		maxDate = calendar.getMaxDate();
		date = new JComboBox<Integer>();
		month = new JComboBox<Months>();
		numDays = new JComboBox<Integer>();
		for (int a = 1; a < 11; a++){
			numDays.addItem(a);
		}
		month.addActionListener(new MonthChanged());
		int month = today.month;
		for (int a = month; a <= month + 6; a++){
			if (a % 12 == 0)
				this.month.addItem(Months.getMonth(12));
			else
				this.month.addItem(Months.getMonth(a % 12));
		}
		updateDays();
		this.setLayout(new GridLayout(1, 6));
		this.add(new JLabel("Dag: "));
		this.add(date);
		this.add(new JLabel("Måned: "));
		this.add(this.month);
		this.add(new JLabel("Antall dager: "));
		this.add(numDays);
		
	}
	
	public int[] getReservation(){//returns [dag, måned, antall dager]
		int[] res = new int[3];
		res[0] = (int) date.getSelectedItem();
		res[1] = ((Months) month.getSelectedItem()).getValue();
		res[2] = (int) numDays.getSelectedItem();
		return res;
	}
	
	private void updateDays(){
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
	}
	
	private class MonthChanged implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			updateDays();
		}
	}
}
