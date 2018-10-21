package tableComponent;

import graphicsModel.MartList;
import interfacePerso.Observable;
import interfacePerso.Observer;

import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class MartTabModel extends AbstractTableModel implements MouseListener,
		Observable, KeyListener {

	public Object[][] data;
	public String[] title;
	private int selectedRow;
	private int selectedColumn;
	private static MartTable tableAct = new MartTable();
	private ArrayList<Integer> cellsEditable = new ArrayList<>();
	private int nbColonneFixe;
	private MartList<Observer> listObserver = new MartList<Observer>();

	// les static int de copie
	// types
	public static int CUT_ROW = 0, COPY_ROW = 1, CUT_ALL = 2, COPY_ALL = 3,
			DELETE_ROW = 4;
	// modes
	public static int ADD_ROW = 0, REMOVE_ROW = 1, ADD_ALL = 2, REMOVE_ALL = 3,
			ADD_ROW_G = 4, ADD_ROW_D = 5, REMOVE_ROW_D = 6, REMOVE_ROW_G = 7;

	private static MartTabModel instance;

	public MartTabModel(Object[][] data, String[] title2) {
		this.data = data;
		this.title = title2;
	}

	// constructeur pour préciser les colonnes fixes
	public MartTabModel(Object[][] data, String[] title2, int nbColonneFixe) {
		this.data = data;
		this.title = title2;

		this.nbColonneFixe = nbColonneFixe;
	}

	public MartTabModel() {
		// TODO Auto-generated constructor stub
	}

	public int getNbColonneFixe() {
		return nbColonneFixe;
	}

	// recup�re le nombre de colonne
	public int getColumnCount() {
		return this.title.length - nbColonneFixe;
	}

	// recup�re le nombre de lignes
	public int getRowCount() {
		return this.data.length;
	}

	// recup�re la valeur dans un cellule
	public Object getValueAt(int row, int col) {
		return this.data[row][col + nbColonneFixe];
	}

	// rtourne le titre des colonnes
	public String getColumnName(int col) {
		return this.title[col + nbColonneFixe];
	}

	// recupere la classe de la valeur dans la cellule
	public Class getColumnClass(int col) {
		if (this.data[0][col + nbColonneFixe] != null)
			return this.data[0][col + nbColonneFixe].getClass();
		else
			return title[col + nbColonneFixe].getClass();
	}

	// averti qu'une cellule est éditable
	public boolean isCellEditable(int row, int col) {
		for (int i : cellsEditable) {
			if (col + nbColonneFixe == i) {
				return true;
			}
		}
		return false;
	}

	public void setColEditable(int col) {
		cellsEditable.add(col);
	}

	// modifier une donnée
	public void setValueAt(Object value, int row, int col) {
		this.data[row][col + nbColonneFixe] = value;
	}

	// ajoute une ligne au tableau
	public void addRow(Object[] data) {
		int indice = 0, nbRow = this.data.length, nbCol = this.title.length;
		Object temp[][] = this.data;
		this.data = new Object[nbRow + 1][nbCol];

		for (Object[] value : temp)
			this.data[indice++] = value;
		this.data[indice] = data;
		temp = null;

		// Cette méthode permet d'avertir le tableau que les données
		// ont été modifiées, ce qui permet une mise à jour complète du tableau
		this.fireTableDataChanged();
	}

	// Méthode permettant de copier une ligne du tableau
	public Object[] copyRow(int position) {
		int indice = 0, indice2 = 0, nbRow = this.getRowCount() - 1, nbCol = this
				.getColumnCount();
		Object temp[][] = new Object[nbRow][nbCol];

		for (Object[] value : this.data) {
			temp[indice2] = value;
			indice2++;
		}

		this.data = temp;
		temp = null;

		return this.data;
	}

	// Méthode permettant de retirer une ligne du tableau
	public void removeRow(int position) {
		int indice = 0, indice2 = 0, nbRow = this.getRowCount() - 1, nbCol = this
				.getColumnCount();
		Object temp[][] = new Object[nbRow][nbCol];

		for (Object[] value : this.data) {
			if (indice != (position) && indice2 < nbRow) {
				temp[indice2] = value;
				indice2++;
			}
			indice++;

		}

		this.data = temp;
		temp = null;
		// Cette méthode permet d'avertir le tableau que les données
		// ont été modifiées, ce qui permet une mise à jour complète du tableau
		this.fireTableDataChanged();
	}

	// Méthode permettant de retirer toutes les lignes du tableau
	public void removeAll() {
		Object[][] temp = new Object[0][0];

		this.data = temp;
		temp = null;
		// Cette méthode permet d'avertir le tableau que les donn�es
		// ont été modifiées, ce qui permet une mise � jour compl�te du tableau
		this.fireTableDataChanged();
	}

	// methode qui permet de chercher une ligne
	public int chercher(Object[] ligne) {
		int i = 0;
		int max = data.length;
		int index = -1;

		for (Object[] lg : data) {
			while (i < max && lg != null) {
				if (lg == ligne) {
					index = i;
				} else {
					index = -1;
				}
			}
		}
		return index;
	}

	public void mouseClicked(MouseEvent e) {

	}

	public void setSelectedColumn(int selectedColumn) {
		this.selectedColumn = selectedColumn;

	}

	public void setSelectedRow(int selectedRow) {
		this.selectedRow = selectedRow;

	}

	public int getSelectedRow() {
		return this.selectedRow;
	}

	public int getSelectedColumn() {
		return this.selectedColumn + nbColonneFixe;
	}

	public void mouseEntered(MouseEvent arg0) {

	}

	public void mouseExited(MouseEvent arg0) {

	}

	public void mousePressed(MouseEvent e) {
		System.out.println("le model est averti...");
		this.setSelectedRow(((JTable) e.getSource()).getSelectedRow());
		this.setSelectedColumn(((JTable) e.getSource()).getSelectedColumn());
		this.tableAct = ((MartTable) e.getSource());

		System.out.println("La table instantier pour le retrait est:"
				+ tableAct);
		System.out.println("La ligne choisi >> >> >> >> >> >> "
				+ this.getSelectedRow());
		System.out.println("La Colonne choisi >> >> >> >> >> >> "
				+ this.getSelectedColumn());
	}

	public void mouseReleased(MouseEvent arg0) {

	}

	public static MartTable getTabActuel() {
		return tableAct;
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
		for (Observer obs : listObserver) {
			obs.update();
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		notifyObserver();
		System.out.println("i HAVE DONE IT");
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		notifyObserver();
		System.out.println("i HAVE DONE IT");
	}
}
