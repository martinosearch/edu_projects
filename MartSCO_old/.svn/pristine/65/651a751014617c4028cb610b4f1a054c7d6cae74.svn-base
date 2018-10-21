package configurationEcole;

import graphicsModel.ComboEditor;
import graphicsModel.MartCheckBox;
import graphicsModel.MartList;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import tableComponent.MartTabModel;
import tableComponent.MartTable;
import classe.Niveau;
import matiere.Matiere;
import configurationAppli.AbstractConfigPanel;
import configurationAppli.Setting;
import connection.DAO;
import connection.DAOFactory;

public class ConfNiveau extends AbstractConfigPanel {
	private Dimension dimPanes = new Dimension(480, 80);
	private DAO<Niveau> nivdao;
	private MartList<Niveau> niveaux;
	private MartTable tabNiveau;

	public ConfNiveau(String tit) {
		super(tit);
		nivdao = DAOFactory.getDAO(DAO.NIVEAU);
		this.setLayout(new FlowLayout());
	}

	public MartList<Setting> getSettings() {
		sets = new MartList<Setting>();

		for (MartCheckBox ck : checks) {
			boolean choise = ck.isSelected();
			Setting set = new Setting(ck.getIntitule(), choise);
			sets.add(set);
		}
		return sets;
	}

	@Override
	public Object find(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void refresh() {
		nivdao.load();
		niveaux = nivdao.getListObt();
		Object[][] data2 = new Object[niveaux.size()][2];

		for (int j = 0; j < niveaux.size(); j++) {
			Niveau niveau = niveaux.get(j);
			data2[j][0] = niveau.getIntitule();
			data2[j][1] = niveau.getTypeEns();
		}

		String[] title2 = { "Niveau", "Type d'Enseignement" };
		MartTabModel model2 = new MartTabModel(data2, title2);
		model2.setColEditable(1);
		tabNiveau = new MartTable(model2);
		String[] cbdata2 = { "PRIM", "COL", "LEG", "LET", "LEE" };

		JComboBox cb = new JComboBox(cbdata2);
		tabNiveau.getColumn("Type d'Enseignement").setCellEditor(
				new ComboEditor(cb));

		this.add("Niveaux", new JScrollPane(tabNiveau));
	}

	@Override
	public void save() {
		int heightN = tabNiveau.getRowCount();
		for (int i = 0; i < heightN; i++) {
			try {
				// la matiere en cours
				String niveaustr = (String) tabNiveau.getValueAt(i, 0);
				String typeEns = (String) tabNiveau.getValueAt(i, 1);

				Niveau niv = new Niveau(niveaustr, typeEns);
				nivdao.update_create(niv);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
}
