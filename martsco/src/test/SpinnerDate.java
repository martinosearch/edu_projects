package test;

import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;

public class SpinnerDate extends JFrame {

	public SpinnerDate() {

		setSize(200, 200);
		setLocationRelativeTo(null);

		// SpinnerModel model = new SpinnerDateModel();
		// JSpinner spinner = new JSpinner(model);

		Calendar cal = Calendar.getInstance();
		Date now = cal.getTime();
		cal.add(Calendar.YEAR, -50);
		Date startDate = cal.getTime();
		cal.add(Calendar.YEAR, 100);
		Date endDate = cal.getTime();

		SpinnerModel model = new SpinnerDateModel(now, startDate, endDate,
				Calendar.YEAR);
		JSpinner spinner = new JSpinner(model);

		getContentPane().add(spinner);

		setVisible(true);
	}

	public static void main(String[] args) {
		new SpinnerDate();
	}

}
