package configurationEcolage;

import graphicsModel.MartList;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.JScrollPane;

import tableComponent.MartTabModel;
import tableComponent.MartTable;
import classe.Niveau;
import configurationAppli.AbstractConfigPanel;
import configurationAppli.Setting;
import connection.DAO;
import connection.DAOFactory;

@SuppressWarnings("serial")
public class ConfEcolage extends AbstractConfigPanel {
	@SuppressWarnings("rawtypes")
	private DAO setdao, nivdao;
	private MartList<Niveau> niveaux;
	private MartTable tableInfo;
	private String annee;

	public ConfEcolage(String tit) {
		super(tit);
		setdao = DAOFactory.getDAO(DAO.SETTING);
		nivdao = DAOFactory.getDAO(DAO.NIVEAU);
		this.setLayout(new BorderLayout());
	}

	public MartList<Setting> getSettings() {
		return sets;
	}

	@Override
	public Object find(String id) {
		return sets.find(id).getAttribut();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void refresh() {
		setdao.load("", "", 0, annee);

		nivdao.load("", "", 0, annee);
		niveaux = nivdao.getListObt();

		String[] titleInfo = { "Titre", "Valeur" };

		int pos = 0;
		Object[][] dataInfo = new Object[niveaux.size()][2];

		for (Niveau niv : niveaux) {
			String value = "";
			try {
				value = (String) ((Setting) setdao.findObj("ecolage_"
						+ niv.getIntitule())).getAttribut();
			} catch (Exception e) {

			}

			dataInfo[pos][0] = niv.getIntitule();
			dataInfo[pos][1] = value;
			sets.add(new Setting("ecolage_" + niv.getIntitule(), value));

			pos++;
		}

		MartTabModel mod = new MartTabModel(dataInfo, titleInfo);
		mod.setColEditable(1);
		tableInfo = new MartTable(mod);

		this.removeAll();
		this.add(new JScrollPane(tableInfo), BorderLayout.CENTER);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void save() {
		for (int i = 0; i < tableInfo.getRowCount(); i++) {
			try {
				String set = "ecolage_" + tableInfo.getValueAt(i, 0);
				String value = (String) tableInfo.getValueAt(i, 1);

				Setting setting = new Setting(set, value);

				setdao.update_create(setting);

				Thread.sleep(500);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void setAnnee(String an) {
		annee = an;
	}

	public String getCentre() {
		return null;
	}
}
