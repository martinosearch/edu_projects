package tableComponent;

import java.awt.event.FocusListener;
import java.awt.event.MouseListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumn;

import function.MartConverter;

public class MartFixedTable extends JScrollPane {
	public MartTable tableFix;
	public MartTable table;
	private MartTabModel model;
	private JViewport view;

	public MartTabModel getModel() {
		return model;
	}

	public MartFixedTable(MartTabModel model) {
		super();

		this.model = model;
		this.table = new MartTable(model, this);
		this.table.addMouseListener(model);

		this.tableFix = new MartTable(new FixedTableModel(this), this);

		this.table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		this.tableFix.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		this.tableFix.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		this.setViewportView(this.table);

		view = new JViewport();
		view.setView(this.tableFix);
		view.setPreferredSize(this.tableFix.getPreferredSize());

		this.setRowHeader(view);
		this.setCorner(super.UPPER_LEFT_CORNER, this.tableFix.getTableHeader());
	}

	public void fireSelectionChanged(MartTable martTable) {
		int index1 = this.table.getSelectedRow();
		int index2 = this.tableFix.getSelectedRow();

		int selectedIndex = martTable.getSelectedRow();

		if (index1 != index2) {
			if (martTable != this.table) {
				this.table
						.setRowSelectionInterval(selectedIndex, selectedIndex);
			} else {
				this.tableFix.setRowSelectionInterval(selectedIndex,
						selectedIndex);
			}
		}

		// pour l'édition directe
	}

	public void setColumnSize(int num, double d) {
		int size = MartConverter.getPtValue(d);

		if (num < model.getNbColonneFixe()) {
			TableColumn col = tableFix.getColumnModel().getColumn(num);
			col.setPreferredWidth(size);
			view.setPreferredSize(this.tableFix.getPreferredSize());

			System.out.println("col: " + num + "size: " + size);
		} else {
			TableColumn col = table.getColumnModel().getColumn(
					num - model.getNbColonneFixe());
			col.setPreferredWidth(size);
		}
	}

	public void setRowHeight(double d) {
		int size = MartConverter.getPtValue(d);
		tableFix.setRowHeight(size);
		table.setRowHeight(size);
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

		String[] column = { "fixed 1", "fixed 2", "a", "b", "c", "d", "e", "f" };

		MartTabModel model = new MartTabModel(data, column, 2);
		MartFixedTable table = new MartFixedTable(model);
		frame.getContentPane().add(table);

		frame.setBounds(200, 200, 300, 300);
		frame.setVisible(true);
	}

	public String getColumnName(int num) {
		if (num < model.getNbColonneFixe()) {
			return tableFix.getColumnName(num);
		} else {
			return table.getColumnName(num - model.getNbColonneFixe());
		}
	}

	public TableColumn getColumn(String titre) {
		TableColumn tab = null;
		try {
			tab = tableFix.getColumn(titre);
		} catch (Exception e) {
			tab = table.getColumn(titre);
		}
		return tab;
	}

	public int getRowCount() {
		return model.data.length;
	}

	public int getColumnCount() {
		return model.title.length;
	}

	public Object getValueAt(int i, int j) {
		if (j < model.getNbColonneFixe()) {
			return tableFix.getValueAt(i, j);
		} else {
			return table.getValueAt(i, j - model.getNbColonneFixe());
		}
	}

	public void addMouseListener(MouseListener listener) {
		table.addMouseListener(listener);
	}
}