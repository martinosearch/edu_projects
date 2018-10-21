package configurationAdmin;

import function.Constance;
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
import javax.swing.JSpinner;

import reference.Reference;
import tableComponent.MartTabModel;
import tableComponent.MartTable;
import classe.Niveau;
import matiere.Matiere;
import configurationAppli.AbstractConfigPanel;
import configurationAppli.Setting;
import connection.DAO;
import connection.DAOFactory;

public class ConfReferences extends AbstractConfigPanel {
	private MartList<MartCheckBox> checks;
	private DAO<Setting> setdao;
	private DAO<Reference> refdao;
	private MartList<Niveau> niveaux;
	private MartTable tabNiveau;
	private MartList<Reference> refs;

	private MartTable tabRef;
	private JSpinner jspStaNoteCompo;

	public ConfReferences(String tit) {
		super(tit);
		refdao = DAOFactory.getDAO(DAO.REFERENCE);
		setdao = DAOFactory.getDAO(DAO.SETTING);
		this.setLayout(new BorderLayout());
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
		refdao.load();

		refs = refdao.getListObt();
		Object[][] data3 = new Object[refs.size()][2];

		for (int j = 0; j < refs.size(); j++) {
			Reference ref = refs.get(j);
			data3[j][0] = ref.getRef();
			data3[j][1] = ref.getValueRef();
		}

		String[] title3 = { "Référence", "Valeur" };
		MartTabModel model3 = new MartTabModel(data3, title3);
		model3.setColEditable(1);
		tabRef = new MartTable(model3);

		this.add(new JScrollPane(tabRef), BorderLayout.CENTER);

		// POUR LES STATIQUES

	}

	@Override
	public void save() {
		int heightR = tabRef.getRowCount();
		for (int i = 0; i < heightR; i++) {
			try {
				// la matiere en cours
				String refstr = (String) tabRef.getValueAt(i, 0);
				String value = (String) tabRef.getValueAt(i, 1);

				Reference ref = new Reference(refstr, value);
				refdao.update_create(ref);
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
