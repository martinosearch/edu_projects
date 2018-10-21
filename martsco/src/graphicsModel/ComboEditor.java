package graphicsModel;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;

import tableComponent.MartTable;

public class ComboEditor extends DefaultCellEditor{
	JComboBox cb;
	comboListener cbListener=new comboListener();
	String newtext=new String();
	

	public ComboEditor(JComboBox combo) {
		super(combo);
		
		cb=(JComboBox) editorComponent;
		cb.addActionListener(cbListener);
	}
	
	//classe revoyant le bouton �dit�
	public Component getTableCellEditorComponent(MartTable table, Object value,
			boolean isSelected, int row, int column){
			cbListener.setTable(table);
			cbListener.setRow(row);
			cbListener.setColumn(column);
			
			System.out.println("Vous avez appuyez"+table+
			cb.getSelectedItem()+""+row+""+column+
			""+table.getValueAt(0, 1)+" "+value);
			
			table.setValueAt(cb,row, column);
			
		return cb;
	}
		
	//La classe g�rant les modifications
	class comboListener implements ActionListener{
		private MartTable table;
		private int row;
		private int column;
		
		public void setTable(MartTable tab){
			this.table=tab;
		}
		
		public void setRow(int row){
			this.row=row;
		}
		
		public void setColumn(int col){
			this.column=col;
		}
		
		public void actionPerformed(ActionEvent arg0) {
		}
	}
}
