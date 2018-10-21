package test;

/*****> Class : JTableFixedColumn
 *
 **> Auteur : Johann Heymes
 *
 **> Creation : 04 juin 2002
 *
 *
 **> Licence :
 *		Ce programme est un logiciel gratuit ,  il peut être modifié et / ou
 *	distribué en  accord avec  les termes de la licence  'GNU General Public
 *	Licence'  tel qu'elle  est publiée  par la  'Free Software Foundation  .
 *	(version 2 ou précédante).
 *		Ce programme est distribué  dans l'espoir  qu'il pourra être utile ,
 *	mais  SANS AUCUNE GARANTIE . Voir la  'GNU General Public License'  pour 
 *	plus de détail.
 *		Vous devriez avoir reçu une copie de la 'GNU General Public License'
 *	avec la distribution  de ce programme,  si non,  vous pouvez écrire à la
 *	'Free Software Foudation,  Inc.,  59 Temple Place -  Suite 330,  Boston,
 *	MA 02111-1307, USA'.
 *	ou a http://www.gnu.org/licenses/gpl.html
 *
 **> Description :
 *		Cette classe redefini certaine caracterisique de l'aspect visuel d'une JTable
 *	ce source a ete inspire de l'exemple trouve a l'adresse http://www2.gol.com/users/tame/
 *
 ***/
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.Dimension;

public class JTableFixedColumn extends JScrollPane {
	protected JTableNotifySelection tableFix;
	protected JTableNotifySelection table;
	protected DefaultTableModel modelJTable;
	protected int nbColonneFixe;
	protected Object[][] data;
	protected Object[] columnNames;

	public JTableFixedColumn(Object[][] data, Object[] columnNames,
			int nbColonneFixe) {
		super();

		this.data = data;
		this.columnNames = columnNames;
		this.nbColonneFixe = nbColonneFixe;

		this.table = new JTableNotifySelection(new FixedTableModelSuite(this),
				this);
		this.tableFix = new JTableNotifySelection(new FixedTableModel(this),
				this);
		this.table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.tableFix.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		this.tableFix.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		this.setViewportView(this.table);

		JViewport view = new JViewport();
		view.setView(this.tableFix);
		view.setPreferredSize(this.tableFix.getPreferredSize());

		this.setRowHeader(view);
		this.setCorner(super.UPPER_LEFT_CORNER, this.tableFix.getTableHeader());
	}

	protected void fireSelectionChanged(JTableNotifySelection selectedTable) {
		int index1 = this.table.getSelectedRow();
		int index2 = this.tableFix.getSelectedRow();
		int selectedIndex = selectedTable.getSelectedRow();

		if (index1 != index2) {
			if (selectedTable != this.table) {
				this.table
						.setRowSelectionInterval(selectedIndex, selectedIndex);
			} else {
				this.tableFix.setRowSelectionInterval(selectedIndex,
						selectedIndex);
			}
		}
	}

	protected class JTableNotifySelection extends JTable {
		private JTableFixedColumn tableFixedColonne;

		public JTableNotifySelection(AbstractTableModel model,
				JTableFixedColumn tableFixedColonne) {
			super(model);
			this.tableFixedColonne = tableFixedColonne;
		}

		public void valueChanged(ListSelectionEvent e) {
			super.valueChanged(e);
			if (this.tableFixedColonne == null) // c'etait ca leur probleme
												// surment une incompatibilite
												// des jdk il travaille avec le
												// 1.1
			{
				return;
			}
			this.tableFixedColonne.fireSelectionChanged(this);
		}
	}

	protected class FixedTableModel extends AbstractTableModel {
		private JTableFixedColumn tableFixedColonne;

		public FixedTableModel(JTableFixedColumn tableFixedColonne) {
			super();
			this.tableFixedColonne = tableFixedColonne;
		}

		public int getColumnCount() {
			return (this.tableFixedColonne.nbColonneFixe);
		}

		public int getRowCount() {
			return (this.tableFixedColonne.data.length);
		}

		public String getColumnName(int col) {
			return ((String) this.tableFixedColonne.columnNames[col]);
		}

		public Object getValueAt(int row, int col) {
			return (this.tableFixedColonne.data[row][col]);
		}
	}

	protected class FixedTableModelSuite extends AbstractTableModel {
		private JTableFixedColumn tableFixedColonne;

		public FixedTableModelSuite(JTableFixedColumn tableFixedColonne) {
			super();
			this.tableFixedColonne = tableFixedColonne;
		}

		public int getColumnCount() {
			return (this.tableFixedColonne.columnNames.length - this.tableFixedColonne.nbColonneFixe);
		}

		public int getRowCount() {
			return (this.tableFixedColonne.data.length);
		}

		public String getColumnName(int col) {
			return ((String) this.tableFixedColonne.columnNames[col
					+ this.tableFixedColonne.nbColonneFixe]);
		}

		public Object getValueAt(int row, int col) {
			return (this.tableFixedColonne.data[row][col
					+ this.tableFixedColonne.nbColonneFixe]);
		}
	}

	public static void main(String argv[]) {
		JFrame frame = new JFrame("teste");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Object[][] data = new Object[][] {
				{ "1", "11", "A", "", "", "", "", "" },
				{ "2", "22", "", "B", "", "", "", "" },
				{ "3", "33", "", "", "C", "", "", "" },
				{ "4", "44", "", "", "", "D", "", "" },
				{ "5", "55", "", "", "", "", "E", "" },
				{ "6", "66", "", "", "", "", "", "F" } };

		Object[] column = new Object[] { "fixed 1", "fixed 2", "a", "b", "c",
				"d", "e", "f" };

		JTableFixedColumn table = new JTableFixedColumn(data, column, 2);

		frame.getContentPane().add(table);
		frame.setBounds(200, 200, 300, 300);
		frame.setVisible(true);

	}
}