package annee;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.joda.time.DateTime;

import progress.Avancer;
import abstractObject.AbstractChooser;
import classe.Classe;

import com.pallas.swing.date.DateComboBox;

public class ChooserPeriode extends AbstractChooser {

	protected static ChooserPeriode instance;
	private JPanel panDate;
	private DateComboBox cbDateDebut;
	private DateComboBox cbDateFin;
	private Font police = new Font("Arial", Font.BOLD, 16);
	private JLabel lbDebut;
	private JLabel lbFin;

	public ChooserPeriode() {
		super();
		setSize(600, 200);
		setTitle("CHOIX DE LA PERIODE");
		// setLocationRelativeTo(null);

		initComponent();

		getContentPane().add(container, BorderLayout.CENTER);
		setVisible(true);
	}

	private void initComponent() {
		container = new JPanel();
		container.setLayout(new BorderLayout());

		panDate = new JPanel();
		panDate.setLayout(new FlowLayout());

		cbDateDebut = new DateComboBox(new Date(), "dd' 'MMMM' 'yyyy");
		cbDateFin = new DateComboBox(new Date(), "dd' 'MMMM' 'yyyy");

		cbDateDebut.setFont(police);
		cbDateFin.setFont(police);

		lbDebut = new JLabel("Date début");
		lbFin = new JLabel("Date fin");

		lbDebut.setFont(police);
		lbFin.setFont(police);

		lbDebut.setForeground(Color.BLUE);
		lbFin.setForeground(Color.BLUE);

		panDate.add(lbDebut);
		panDate.add(cbDateDebut);
		panDate.add(lbFin);
		panDate.add(cbDateFin);

		panButton.add(bValider);
		panButton.add(bAnnuler);

		container.add(panDate, BorderLayout.CENTER);
		container.add(panButton, BorderLayout.SOUTH);
	}

	public static ChooserPeriode getInstance() {
		if (instance == null) {
			instance = new ChooserPeriode();
		}
		return instance;
	}

	public static void main(String[] args) {
		getInstance();
	}

	public DateTime getDateDebut() {
		return new DateTime(cbDateDebut.getDate());
	}

	public DateTime getDateFin() {
		return new DateTime(cbDateFin.getDate());
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public Avancer getAvancer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Component source = (Component) e.getSource();

		if (source == bAnnuler) {
			close();
		}
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

	@Override
	public Classe getClasse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAnnee() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getFirstOption() {
		// TODO Auto-generated method stub
		return false;
	}

}
