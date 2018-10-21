package tableComponent;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class FixedTableModel extends AbstractTableModel implements KeyListener {
	private MartFixedTable tableFixedColonne;

	public FixedTableModel(MartFixedTable tableFixedColonne) {
		super();
		this.tableFixedColonne = tableFixedColonne;
	}

	public int getColumnCount() {
		return (this.tableFixedColonne.getModel().getNbColonneFixe());
	}

	public int getRowCount() {
		return (this.tableFixedColonne.getModel().data.length);
	}

	public String getColumnName(int col) {
		return ((String) this.tableFixedColonne.getModel().title[col]);
	}

	public Object getValueAt(int row, int col) {
		return (this.tableFixedColonne.getModel().data[row][col]);
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