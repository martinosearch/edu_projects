package annee;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.Date;

import javax.swing.JPanel;

import org.joda.time.DateTime;

import com.pallas.swing.date.DateComboBox;

public class ChooserDate extends JPanel {
	private DateComboBox cbDate;
	private Font police = new Font("Arial", Font.BOLD, 16);

	public ChooserDate() {
		super();
		setLayout(new BorderLayout());
		cbDate = new DateComboBox(new Date(), "dd' 'MMM' 'yyyy");
		cbDate.setFont(police);
		add(cbDate, BorderLayout.CENTER);
	}

	public DateTime getDate() {
		return new DateTime(cbDate.getDate());
	}

	public void setDate(DateTime date) {
		try {
			cbDate.setDate(new Date(date.getMillis()));
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	public void setEmpty() {
		cbDate.setSelectedItem(null);
	}
}
