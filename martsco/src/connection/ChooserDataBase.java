package connection;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import progress.Avancer;
import abstractObject.AbstractChooser;
import classe.Classe;
import function.Constance;
import function.Fichier;
import function.MartFormatter;
import graphicsModel.MartList;

public class ChooserDataBase extends AbstractChooser {
	private static ChooserDataBase instance;

	private boolean periodeChoosing = false;
	private JCheckBox ckPeriode;
	private Dimension dimCb = new Dimension(350, 30);

	private Fichier fParam;

	private String[] listeBase;

	private JComboBox cbBase;

	private MartList<String> listeBases;

	public ChooserDataBase() {
		this.setSize(500, 150);
		this.setLocationRelativeTo(null);
		setTitle("Choix de la Base de Donnée");
		setModal(true);

		initComponent();
		setPeriodeChoosing(false);// pas d'option supplémentaire

		this.getContentPane().add(container, BorderLayout.CENTER);
	}

	private void initComponent() {

		fParam = Constance.getFichierParam();

		String dbActuel = fParam.findParam("db");
		String value = fParam.findParam("liste_db");
		System.out.println("Valeur: " + dbActuel + "/" + value);

		MartFormatter fmt = new MartFormatter();
		listeBases = fmt.decomposer(value, '/');
		cbBase = new JComboBox();

		int i = 0;
		for (String str : listeBases) {
			cbBase.addItem(str);
		}

		try {
			cbBase.setSelectedItem(dbActuel);
		} catch (Exception e) {
			e.printStackTrace();
		}

		cbBase.setPreferredSize(dimCb);

		container = new JPanel();

		container.setLayout(new BorderLayout());
		panAnnee.add(new JLabel("Base de donnée: "));
		panAnnee.add(cbBase);
		panButton.add(bValider);
		panButton.add(bAnnuler);

		ckPeriode = new JCheckBox("Définir une période");

		container.add(panAnnee, BorderLayout.NORTH);
		container.add(ckPeriode, BorderLayout.CENTER);
		container.add(panButton, BorderLayout.SOUTH);
	}

	public MartList<String> getListeBases() {
		return listeBases;
	}

	public static void main(String[] args) {
		getInstance().setVisible(true);
	}

	public static ChooserDataBase getInstance() {
		if (instance == null)
			instance = new ChooserDataBase();
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
			System.exit(0);
		}
	}

	public void setValiderTitle(String str) {
		bValider.setText(str);
		bValider.revalidate();
		this.revalidate();
	}

	public boolean isPeriodeChoosing() {
		return periodeChoosing;
	}

	public void setPeriodeChoosing(boolean b) {
		this.periodeChoosing = b;
		ckPeriode.setVisible(isPeriodeChoosing());
		revalidate();
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
	public void update() {
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

	public String getDataBase() {
		return (String) cbBase.getSelectedItem();
	}

	@Override
	public boolean getFirstOption() {
		// TODO Auto-generated method stub
		return false;
	}

}
