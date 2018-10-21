package graphicsModel;

import interfacePerso.Observer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import tableComponent.MartTabModel;
import tableComponent.MartTable;
import componentFactory.MartButton;
import eleve.EleveClasse;
import abstractObject.AbstractControler;
import abstractObject.AbstractModel;
import abstractObject.AbstractPojo;

/**
 * Cette classe est contruite sur le model purement MVC Elle est une vue Elle
 * permet de définir la promotion des élèves
 * 
 * @author martino
 *
 */
public class MartTwoListeSelector extends JPanel implements Observer,
		ActionListener {
	// nom des conteneurs

	private JPanel panNou = new JPanel();
	private JPanel panRed = new JPanel();
	private JPanel panListePrinc = new JPanel();
	private JPanel panMoovers = new JPanel();
	private JPanel container = new JPanel();
	private JPanel panListeEtButton;

	private MartTable tabRed;
	private MartTable defaultTable;
	private MartTable tabNou;

	private MartTabModel modtabRed;
	private MartTabModel modtabNou;

	private AbstractModel model;
	private AbstractControler controler;

	Font police1 = new Font("Times new Romam", Font.TYPE1_FONT, 14);
	public int nouCount, redCount;
	private MartButton bLeft;
	private MartButton bRight;
	private MartButton bRetirer;

	private String title2 = "Liste 2";
	private String title1 = "liste 1";
	private ListeSelectorData data;
	private Object[] newData;
	private MartTabModel dModel;
	private MartTabModel nModel;
	private JTable newTable;
	private Component bRightAll;
	private Component bLeftAll;
	private JLabel lbRedoublant;
	private JLabel lbNouveau;

	public MartTwoListeSelector(Component par, MartTable tabRed2,
			MartTable defaultTable2, MartTable tabNou2) {

		defaultTable = defaultTable2;
		tabRed = tabRed2;
		tabNou = tabNou2;

		// le controleur
		initComponent();

		this.setLayout(new BorderLayout());
		this.add(container, BorderLayout.CENTER);
	}

	public void initComponent() {
		// le conteneur des boutons
		bRetirer = new MartButton("-> Retirer <-");
		bLeft = new MartButton("<-");
		bRight = new MartButton("->");

		bRetirer.addActionListener(this);
		bLeft.addActionListener(this);
		bRight.addActionListener(this);

		panMoovers.setLayout(new GridLayout(4, 1));
		panMoovers.add(bLeft);
		panMoovers.add(bRight);
		panMoovers.add(bRetirer);

		panListeEtButton = new JPanel();
		panListeEtButton.setLayout(new BorderLayout());
		panListeEtButton.add(panMoovers, BorderLayout.NORTH);
		panListeEtButton.add(panListePrinc, BorderLayout.CENTER);

		// -------------------------------------------------------------------
		panListePrinc.setLayout(new BorderLayout());
		panListePrinc.removeAll();

		JLabel lbClasse = new JLabel("Liste de la Classe");
		panListePrinc.setBackground(Color.PINK);
		panListePrinc.add(lbClasse, BorderLayout.NORTH);
		panListePrinc.add(new JScrollPane(defaultTable), BorderLayout.CENTER);
		panListeEtButton.revalidate();

		// liste des nouveaux
		panNou.setLayout(new BorderLayout());
		panNou.removeAll();
		lbNouveau = new JLabel(title2);
		panNou.setBackground(Color.GREEN);
		panNou.add(lbNouveau, BorderLayout.NORTH);
		panNou.add(new JScrollPane(tabNou), BorderLayout.CENTER);
		panNou.revalidate();

		// liste des Redoublants
		panRed.setLayout(new BorderLayout());
		panRed.removeAll();
		lbRedoublant = new JLabel(title1);
		panRed.setBackground(Color.GREEN);
		panRed.add(lbRedoublant, BorderLayout.NORTH);
		panRed.add(new JScrollPane(tabRed), BorderLayout.CENTER);
		panRed.revalidate();

		container.setLayout(new GridLayout(1, 3));
		container.add(panRed);
		container.add(panListeEtButton);
		container.add(panNou);
	}

	public void add() {

		int x = dModel.getSelectedRow();
		int y = dModel.getSelectedColumn();

		// les valeurs à ajouter
		String matricule = (String) defaultTable.getValueAt(x, 0);
		int num = nModel.getRowCount() + 1;

		// on prépare les données
		newData = new Object[newTable.getColumnCount()];
		newData[0] = num;
		newData[1] = matricule;

		addSupplement(matricule);

		dModel.removeRow(x);

		nModel.addRow(newData);
		newData = null;
	}

	public void addAll() {
		int leght = dModel.getRowCount();
		int width = dModel.getColumnCount();
		int i = 0;

		while (i++ < leght) {
			// *********************************************************
			// les valeurs à ajouter
			String matricule = (String) defaultTable.getValueAt(i - 1, 0);
			int num = nModel.getRowCount() + 1;

			// on prépare les données
			newData = new Object[newTable.getColumnCount()];
			newData[0] = num;
			newData[1] = matricule;

			addSupplement(matricule);

			nModel.addRow(newData);
			newData = null;
		}

		dModel.removeAll();
	}

	// methode pour retraît
	public void remove() {
		int x = nModel.getSelectedRow();
		int y = nModel.getSelectedColumn();

		// *********************************************************
		// les valeurs à retirer
		String matricule = (String) newTable.getValueAt(x, 1);

		// on prépare les données
		newData = new Object[defaultTable.getColumnCount()];
		newData[0] = matricule;
		// ***************************************************

		removeSupplement(matricule);

		nModel.removeRow(x);

		dModel.addRow(newData);
		newData = null;
	}

	// methode pour retirer tout
	public void removeAll() {
		int leght = nModel.getRowCount();
		int width = nModel.getColumnCount();

		int i = 0;
		while (i++ < leght) {
			String matricule = (String) newTable.getValueAt(i - 1, 1);

			// on prépare les données
			newData = new Object[defaultTable.getColumnCount()];
			newData[0] = matricule;
			// ***************************************************

			removeSupplement(matricule);

			dModel.addRow(newData);
			newData = null;
		}

		nModel.removeAll();
	}

	private void addSupplement(String matri) {
		// les complétion dynamiques
		String matricule = matri;
		int col = newTable.getColumnCount();
		System.out.println("le matricule demandé: " + matricule);

		for (int i = 2; i < col; i++) {
			try {
				AbstractPojo obj = (AbstractPojo) data.getData(matricule);
				newData[i] = obj.getInfo(i);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	private void removeSupplement(String matri) {
		// les complétion dynamiques
		String matricule = matri;
		int col = defaultTable.getColumnCount();
		System.out.println("le matricule demandé: " + matricule);

		for (int i = 1; i < col; i++) {
			try {
				AbstractPojo obj = (AbstractPojo) data.getData(matricule);
				System.out.println("indice====" + i
						+ ((EleveClasse) obj).getCodeInfo());
				newData[i] = obj.getInfo(i + 1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Component source = (Component) e.getSource();

		if (source == bLeft) {
			dModel = (MartTabModel) defaultTable.getModel();
			nModel = (MartTabModel) tabRed.getModel();
			newTable = tabRed;
			add();
		}

		if (source == bLeftAll) {
			dModel = (MartTabModel) defaultTable.getModel();
			nModel = (MartTabModel) tabRed.getModel();
			newTable = tabRed;
			addAll();
		}

		if (source == bRight) {
			dModel = (MartTabModel) defaultTable.getModel();
			nModel = (MartTabModel) tabNou.getModel();
			newTable = tabNou;
			add();
		}

		if (source == bRightAll) {
			dModel = (MartTabModel) defaultTable.getModel();
			nModel = (MartTabModel) tabNou.getModel();
			newTable = tabNou;
			addAll();
		}

		if (source == bRetirer) {
			dModel = (MartTabModel) defaultTable.getModel();
			newTable = MartTabModel.getTabActuel();
			nModel = (MartTabModel) newTable.getModel();

			remove();
		}
	}

	public void setData(ListeSelectorData listeSD) {
		data = listeSD;
	}

	public MartTable getMatDef() {
		return defaultTable;
	}

	public MartTable getTabDroite() {
		return tabNou;
	}

	public MartTable getTabGauche() {
		return tabRed;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	public void setTitle1(String str) {
		title1 = str;
		lbRedoublant.setText(title1);
	}

	public void setTitle2(String str) {
		title2 = str;
		lbNouveau.setText(title2);
	}
}
