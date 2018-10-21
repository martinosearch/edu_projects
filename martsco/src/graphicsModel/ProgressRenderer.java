package graphicsModel;

import java.awt.Component;

import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;


//contr�le des affichage
	public class ProgressRenderer extends JProgressBar implements TableCellRenderer{
			
		public Component getTableCellRendererComponent(JTable table, Object value,
				boolean isSelected, boolean hasFocus, int row, int column){
			try{
			return (JProgressBar) value;
			}catch (Exception e){
				e.printStackTrace();
			}
			return this;
	}
}

