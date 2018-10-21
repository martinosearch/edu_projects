package tableComponent;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class ButtonEditor extends DefaultCellEditor {
	protected JButton button;
	private boolean isPushed;
	private ButtonListener bListener = new ButtonListener();

	public ButtonEditor(JCheckBox jcheckbox) {
		super(jcheckbox);
		
		button = new JButton();
		button.setOpaque(true);
		button.addActionListener(bListener);
		
	}
	//classe revoyant le bouton �dit�
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column){
		//On precise au listener ligne, colonne et tableau
		bListener.setRow(row);
		bListener.setColumn(column);
		bListener.setTable(table);
		
		//on reaffecte le libel� au bouton
		button.setText((value==null)? "": value.toString());
		
		//ce que la methode renvoie
		return button;
	}
	
	class ButtonListener implements ActionListener{
		private int row, column;
		private JTable table;
		private int nbre=0;
		private JButton button;
		
	
		
		public void setRow(int row){this.row=row;}
		public void setColumn(int column){this.column=column;}
		public void setTable(JTable table){this.table=table;}
		
		public JButton getButton(){return this.button;}
		public int getRow(){return this.row;}
		public int getColumn(){return this.column;}
		
		
		public void actionPerformed(ActionEvent e) {
			System.out.println("Coucou!! \n"+ "Vous avez appuy� sur le bouton "+
					((JButton)e.getSource()).getText()+"\n Ce buton a pour "
							+ "coordonn�es ("+ getRow()+","+ getColumn()+ ")");
			
			//change une valeur
			((AbstractTableModel)table.getModel())
					.setValueAt("New Value "+(++nbre), this.row, (this.column-1));
			
			//pr�vient le tableau qu'une valeur a chang�e
			((AbstractTableModel)table.getModel())
					.fireTableCellUpdated(this.row, this.column-1);
			
			this.button= ((JButton)e.getSource());
			
		}
		
	}

}
