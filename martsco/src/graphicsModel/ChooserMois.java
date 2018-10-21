package graphicsModel;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.event.ChangeListener;

public class ChooserMois extends JPanel {

	private Font font = new Font("Courrier new", Font.BOLD, 16);
	private Thread action;
	public JSpinner spinner;

	public Thread getAction() {
		return action;
	}

	public void setAction(Thread action) {
		this.action = action;
	}

	public ChooserMois() {
		SpinnerModel model = new SpinnerDateModel();
		spinner = new JSpinner(model);
		JComponent editor = new JSpinner.DateEditor(spinner, "MMMM yyyy");

		spinner.setEditor(editor);
		spinner.setFont(font);

		setLayout(new GridLayout(2, 1));

		add(new JLabel("Choisissez le mois"));
		add(spinner);
	}

	public void addChangeListener(ChangeListener listener) {
		spinner.addChangeListener(listener);
	}

	public String getMois() {
		Date date = (Date) spinner.getValue();
		SimpleDateFormat format = new SimpleDateFormat("MMMM yyyy");

		return format.format(date).toUpperCase();
	}
}
