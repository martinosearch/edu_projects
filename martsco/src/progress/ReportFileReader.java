package progress;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import editeur.MartEditorPane;
import graphicsModel.MartDialog;

public class ReportFileReader extends MartDialog {

	private MartEditorPane editor;
	private File file;
	private JScrollPane scr;
	private JScrollBar bar;

	public ReportFileReader(File f) {
		this.setSize(600, 200);
		file = f;
		initComponent();
		scr = new JScrollPane(editor);
		bar = scr.getVerticalScrollBar();
		this.getContentPane().add(scr);
	}

	private void initComponent() {
		editor = new MartEditorPane();

		editor.setBackground(Color.black);
		editor.setForeground(Color.yellow);

		// initialisation
		editor.setText("Restauration de la base de données");
		new Thread(new Runnable() {
			public void run() {
				getProgress();
			}
		}).start();

	}

	private void getProgress() {

		String msg = "";
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		do {
			try {
				bar.setValue(bar.getMaximum());
				msg += "\n" + reader.readLine();
				editor.setText(msg);
				editor.revalidate();
				editor.setFont(new Font("courrier new", Font.PLAIN, 16));
				bar.setValue(bar.getMaximum());
				// Thread.sleep(300);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} while (msg != "");

	}

	public static void main(String[] args) {
		new ReportFileReader(new File("c:\\temp\\restorePostgres.martsco"))
				.setVisible(true);
	}

	@Override
	public Avancer getAvancer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void load() {
		// TODO Auto-generated method stub

	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub

	}

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
