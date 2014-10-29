package src;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ValidDates extends JPanel{

	private final Date today, maxDate;
	private final JComboBox<Integer> date, month;
	
	public ValidDates(){
		Calendar calendar = new Calendar();
		today = calendar.getTodaysDate();
		maxDate = calendar.getMaxDate();
		date = new JComboBox<Integer>();
		month = new JComboBox<Integer>();
		month.addActionListener(new MonthChanged());
		int month = today.month;
		for (int a = month; a < month + 6; a++){
			if (a % 12 == 0)
				this.month.addItem(12);
			else
				this.month.addItem(a % 12);
		}
		updateDays();
		this.setLayout(new GridLayout(1, 4));
		this.add(new JLabel("Dag: "));
		this.add(date);
		this.add(new JLabel("Måned: "));
		this.add(this.month);
	}
	
	private void updateDays(){
		date.removeAllItems();
		int valueMonth = (int) month.getSelectedItem();
		int start = 1;
		int end = Calendar.getDayOfMonth(valueMonth);
		if (valueMonth == today.month){
			start = today.day;
		}else if (valueMonth == maxDate.month)
			end = maxDate.day;
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
