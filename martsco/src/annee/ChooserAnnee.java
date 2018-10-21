package annee;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import classe.Classe;
import progress.Avancer;
import abstractObject.AbstractChooser;
import connection.DAO;
import connection.DAOFactory;

public class ChooserAnnee extends AbstractChooser {
	private static ChooserAnnee instance;

	private boolean periodeChoosing = false;
	private JCheckBox ckPeriode;
	private Dimension dimCb = new Dimension(150, 20);

	public ChooserAnnee() {
		this.setSize(300, 150);
		// this.setLocationRelativeTo(null);

		initComponent();

		this.getContentPane().add(container, BorderLayout.CENTER);
	}

	private void initComponent() {
		load();
		// liste des années
		String[] listeEleve = new String[annees.size()];

		int i = 0;
		for (Annee an : annees) {
			listeEleve[i] = an.getIntitule();
			i++;
		}

		cbAnnee = new JComboBox(listeEleve);
		cbAnnee.setPreferredSize(dimCb);

		container = new JPanel();

		container.setLayout(new BorderLayout());
		panAnnee.add(new JLabel("Année- scolaire: "));
		panAnnee.add(cbAnnee);
		panButton.add(bValider);
		panButton.add(bAnnuler);

		ckPeriode = new JCheckBox("Définir une période");

		container.add(panAnnee, BorderLayout.NORTH);
		container.add(ckPeriode, BorderLayout.CENTER);
		container.add(panButton, BorderLayout.SOUTH);
	}

	public static void main(String[] args) {
		getInstance().setVisible(true);
		getInstance().setPeriodeChoosing(true);
	}

	public static ChooserAnnee getInstance() {
		if (instance == null)
			instance = new ChooserAnnee();

		instance.periodeChoosing = false;

		return instance;
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
			setPeriodeChoosing(false);
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
	public void windowActivated(WindowEvent e) {
		thisControl();
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		setPeriodeChoosing(false);
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

	}

	@Override
	public void load() {
		andao = DAOFactory.getDAO(DAO.ANNEE);
		andao.load();

		annees = andao.getListObt();
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

	public String getAnnee() {
		return (String) cbAnnee.getSelectedItem();
	}

	public void setValiderTitle(String str) {
		bValider.setText(str);
		bValider.revalidate();
		this.revalidate();
	}

	@Override
	public Classe getClasse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	public boolean isPeriodeChoosing() {
		return periodeChoosing;
	}

	public void setPeriodeChoosing(boolean b) {
		this.periodeChoosing = b;
		thisControl();
		revalidate();
	}

	private void thisControl() {
		ckPeriode.setVisible(isPeriodeChoosing());
	}

	public boolean isPeriodeActivated() {
		return ckPeriode.isSelected();
	}

	@Override
	public boolean getFirstOption() {
		// TODO Auto-generated method stub
		return false;
	}

}
