package graphicsModel;

import java.awt.Component;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

//contr�le des affichage
public class OptionItemRenderer extends OptionItem implements TableCellRenderer {

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {

		setPreferredSize(((OptionItem) value).getPreferredSize());
		setText(((OptionItem) value).getText());
		setChemin(((OptionItem) value).getChemin());
		setOpaque(false);

		return this;
	}
}
