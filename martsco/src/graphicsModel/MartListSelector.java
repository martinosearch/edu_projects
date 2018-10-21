package graphicsModel;

import interfacePerso.Observable;
import interfacePerso.Observer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import tableComponent.MartTabModel;
import tableComponent.MartTable;
import componentFactory.MartButton;
import abstractObject.AbstractPojo;

public class MartListSelector extends JPanel implements ActionListener,
		Observable {

	private JPanel panBut = new JPanel();
	private JPanel container1 = new JPanel();
	private JPanel container2 = new JPanel();
	private JPanel container3 = new JPanel();
	private JSplitPane split1;
	private JSplitPane split2;
	JPanel splitCont = new JPanel();
	public MartButton bLeftAll = new MartButton("<<");
	public MartButton bLeft = new MartButton("<");
	public MartButton bRight = new MartButton(">");
	public MartButton bRightAll = new MartButton(">>");

	public MartTable newTable;
	public MartTable defaultTable;

	JLabel lbTitle = new JLabel("Choisissez les éléments concernés");

	Font police1 = new Font("Times new Romam", Font.TYPE1_FONT, 14);
	private Dimension dimParent;
	private Component parent;
	private int type;
	private MartTabModel nModel;
	private MartTabModel dModel;
	private Object[] newData;
	private ListeSelectorData data;
	private MartList<Observer> listObserver;
	private String matricule;

	// constructeur
	public MartListSelector(Component par, MartTable tableDef, MartTable table) {
		parent = par;
		this.setLayout(new BorderLayout());
		defaultTable = tableDef;
		newTable = table;
		initComponent();
		dimParent = parent.getPreferredSize();

		this.add(splitCont, BorderLayout.CENTER);
		container1.revalidate();
		container2.revalidate();
		container3.revalidate();

		nModel = (MartTabModel) newTable.getModel();
		dModel = (MartTabModel) defaultTable.getModel();
	}

	/**
	 * Initialisation des composants
	 * 
	 */
	public void initComponent() {
		listObserver = new MartList<Observer>();

		// le conteneur des boutons
		panBut = new JPanel();
		panBut.setLayout(new GridLayout());

		// Libellé
		lbTitle.setForeground(Color.BLUE);

		container2.setLayout(new GridLayout(8, 1, 10, 10));
		container2.add(new JLabel());
		container2.add(new JLabel());
		container2.add(bLeftAll);
		container2.add(bLeftAll);
		container2.add(bLeft);
		container2.add(bRight);
		container2.add(bRightAll);

		container1.setLayout(new BorderLayout());
		container1.removeAll();
		container1.add(new JScrollPane(defaultTable), BorderLayout.CENTER);

		container3.setBackground(Color.GREEN);
		container3.setLayout(new BorderLayout());
		container3.add(lbTitle, BorderLayout.NORTH);
		container3.add(new JScrollPane(newTable), BorderLayout.CENTER);

		split1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, container3,
				container2);
		split2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, split1, container1);

		split1.setDividerLocation((parent.getWidth() * 55 / 100));
		split2.setDividerLocation(parent.getWidth() * 65 / 100);

		splitCont.setLayout(new BorderLayout());
		splitCont.add(lbTitle, BorderLayout.NORTH);
		splitCont.add(split2, BorderLayout.CENTER);
		splitCont.add(panBut, BorderLayout.SOUTH);

		// ajout de listener
		bLeft.addActionListener(this);
		bLeftAll.addActionListener(this);
		bRight.addActionListener(this);
		bRightAll.addActionListener(this);

	}

	public void add() {
		int x = dModel.getSelectedRow();
		int y = dModel.getSelectedColumn();

		// les valeurs à ajouter
		matricule = (String) defaultTable.getValueAt(x, 0);
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
			matricule = (String) defaultTable.getValueAt(i - 1, 0);
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
		matricule = (String) newTable.getValueAt(x, 1);

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
			matricule = (String) newTable.getValueAt(i - 1, 1);

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
			add();
		}

		if (source == bLeftAll) {
			addAll();
		}

		if (source == bRight) {
			remove();
		}

		if (source == bRightAll) {
			removeAll();
		}

		notifyObserver();
	}

	public void setData(ListeSelectorData listeSD) {
		data = listeSD;
	}

	public void setTitle1(String str) {
		lbTitle.setText(str);

	}

	public MartTable getTableDef() {
		return defaultTable;
	}

	public MartTable getTable() {
		// TODO Auto-generated method stub
		return newTable;
	}

	@Override
	public void addObserver(Observer obs) {
		listObserver.add(obs);
	}

	@Override
	public void removeObserver() {
		listObserver = new MartList<Observer>();
	}

	@Override
	public void notifyObserver() {
		System.out.println("Mise à jour de :" + listObserver.size()
				+ " Observers");
		for (Observer obs : listObserver) {

			obs.update();
		}
	}

	public String getSelection() {
		return matricule;
	}

	/*
	 * public static void main(String[] args) { MartListSelector sel = new
	 * MartListSelector(); MyFrame fr = new MyFrame(); fr.setSize(400, 400);
	 * fr.getContentPane().add(sel); fr.setVisible(true); }
	 */
}
