package tableComponent;

import function.MartConverter;
import interfacePerso.MartRangeable;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

public class MartTable extends JTable implements MartRangeable {

	private String intitule = "";
	private MartFixedTable tableFixedColonne;

	public MartTable(MartTabModel model) {
		super(model);
		this.addMouseListener(model);
		this.addKeyListener(new DirectEditor());

		try {
			this.addKeyListener((KeyListener) model);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public MartTable(AbstractTableModel model, MartFixedTable tableFixedColonne) {
		super(model);
		this.tableFixedColonne = tableFixedColonne;
		this.addKeyListener(new DirectEditor());

		try {
			this.addKeyListener((KeyListener) model);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public MartTable() {
		super();
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return intitule;
	}

	@Override
	public void setId(String id) {
		intitule = id;
	}

	@Override
	public String getIntitule() {
		// TODO Auto-generated method stub
		return intitule;
	}

	@Override
	public void setIntitule(String inti) {
		intitule = inti;
	}

	@Override
	public int getRang() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setRang(int rg) {
		// TODO Auto-generated method stub

	}

	@Override
	public double getValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setValue(double val) {
		// TODO Auto-generated method stub

	}

	public void setColumnSize(int num, double d) {
		int size = MartConverter.getPtValue(d);
		TableColumn col = this.getColumnModel().getColumn(num);
		col.setPreferredWidth(size);
	}

	public void setRowSize(int num, double sizeCm) {
		int size = MartConverter.getPtValue(sizeCm);
		this.setRowHeight(num, size);
	}

	// ajouter
	public void valueChanged(ListSelectionEvent e) {
		super.valueChanged(e);

		if (this.tableFixedColonne == null) {
			return;
		}
		// c'etait ca leur probleme
		// surment une incompatibilite
		// des jdk il travaille avec le
		// 1.1
		tableFixedColonne.fireSelectionChanged(this);
	}

	class DirectEditor implements KeyListener {
		public DirectEditor() {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (!e.isActionKey() && e.getKeyCode() != 9 && e.getKeyCode() != 10) {
				MartTable.this.setValueAt("", MartTable.this.getSelectedRow(),
						MartTable.this.getSelectedColumn());
			}

			// System.out.println("Je repond........" + e.getKeyCode() + " "
			// + e.isActionKey());
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
}