package tableComponent;

import java.awt.Component;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

//contr�le des affichage
public class ButtonRenderer extends JButton implements TableCellRenderer {

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		if (value.getClass().getSimpleName().equals("String")) {
			setText(value.toString());
		}

		setIcon(((AbstractButton) value).getIcon());

		return this;
	}
}
