package configurationAdmin;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.JScrollPane;

import configurationAppli.AbstractConfigPanel;
import configurationAppli.Setting;
import connection.DAO;
import connection.DAOFactory;
import function.Constance;
import graphicsModel.MartList;
import tableComponent.MartTabModel;
import tableComponent.MartTable;

@SuppressWarnings("serial")
public class ConfAutresInfos extends AbstractConfigPanel {
    private DAO<Setting> setdao;
    private MartTable tableInfo;
    private int trimeste;
    private String annee;

    @SuppressWarnings("unchecked")
    public ConfAutresInfos(String tit) {
	super(tit);
	setdao = DAOFactory.getDAO(DAO.SETTING);
	this.setLayout(new BorderLayout());
    }

    @Override
    public Object find(String id) {
	return sets.find(id).getAttribut();
    }

    @Override
    public void refresh() {
	sets = new MartList<>();
	setdao.load("", "", trimeste, annee);

	String[] titleInfo = { "Titre de l'info", "valeur" };

	String[] infos = { "ministere", "ministere_abr", "inspection", "inspection_abr", "devise",
		"moyenne_passage_6ème", "moyenne_passage_5ème", "moyenne_passage_4ème", "moyenne_passage_2nde_CD",
		"moyenne_passage_2nde_A4", "moyenne_passage_CI", "moyenne_passage_CP1", "moyenne_passage_CP2",
		"moyenne_passage_CE1", "moyenne_passage_CE2", "moyenne_passage_CM1" };

	int pos = 0;
	Object[][] dataInfo = new Object[infos.length][2];

	for (String str : infos) {
	    String value3 = "";
	    String strData = Constance.getCor(str);

	    try {
		value3 = (String) setdao.findObj(strData).getAttribut();
	    } catch (Exception e) {

	    }

	    dataInfo[pos][0] = str;
	    dataInfo[pos][1] = value3;
	    sets.add(new Setting(strData, value3));

	    pos++;
	}

	MartTabModel mod = new MartTabModel(dataInfo, titleInfo);
	mod.setColEditable(1);
	tableInfo = new MartTable(mod);

	this.removeAll();
	this.add(new JScrollPane(tableInfo), BorderLayout.CENTER);
    }

    @Override
    public void save() {
	setdao.load("", "", trimeste, annee);
	for (int i = 0; i < tableInfo.getRowCount(); i++) {
	    try {
		String set = Constance.getCor((String) tableInfo.getValueAt(i, 0));
		String value = Constance.getCorAposthr((String) tableInfo.getValueAt(i, 1));

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

    @Override
    public void setAnnee(String an) {
	annee = an;
    }

    public String getCentre() {
	return null;
    }

    @Override
    public MartList<Setting> getSettings() {
	return null;
    }
}
