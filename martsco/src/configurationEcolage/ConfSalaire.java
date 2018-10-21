package configurationEcolage;

import graphicsModel.MartList;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import componentFactory.MartButton;
import salaire.DefinirSalaire;
import tableComponent.MartTable;
import classe.Niveau;
import configurationAppli.AbstractConfigPanel;
import configurationAppli.Setting;
import connection.DAO;
import connection.DAOFactory;

@SuppressWarnings("serial")
public class ConfSalaire extends AbstractConfigPanel {
	@SuppressWarnings("rawtypes")
	private DAO setdao, nivdao;
	private MartList<Niveau> niveaux;
	private MartTable tableInfo;
	private String annee;
	private MartButton bDefinirSalaire;

	public ConfSalaire(String tit) {
		super(tit);
		setdao = DAOFactory.getDAO(DAO.SETTING);
		nivdao = DAOFactory.getDAO(DAO.NIVEAU);

		initComponent();
	}

	private void initComponent() {
		this.setLayout(new FlowLayout());

		bDefinirSalaire = new MartButton().getDefinirSalaire();

		bDefinirSalaire.addActionListener(this);
		this.add(bDefinirSalaire);
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
		this.revalidate();
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
	public void actionPerformed(ActionEvent e) {
		Component source = (Component) e.getSource();

		if (source == bDefinirSalaire) {
			new DefinirSalaire().setVisible(true);
		}
	}

	public void setAnnee(String an) {
		annee = an;
	}

	public String getCentre() {
		return null;
	}
}
