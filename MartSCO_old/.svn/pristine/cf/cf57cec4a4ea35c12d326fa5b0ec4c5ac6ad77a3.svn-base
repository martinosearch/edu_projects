package test;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;

public class SpinnerDateEditorSample {
	public static void main(String args[]) {
		JFrame frame = new JFrame("JSpinner Sample");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		SpinnerModel model = new SpinnerDateModel();
		JSpinner spinner = new JSpinner(model);
		JComponent editor = new JSpinner.DateEditor(spinner, "MMMM");
		spinner.setEditor(editor);

		JPanel panel1 = new JPanel(new BorderLayout());
		panel1.add(spinner, BorderLayout.CENTER);
		frame.add(panel1, BorderLayout.SOUTH);

		frame.setSize(200, 90);
		frame.setVisible(true);
	}
}