package configurationPlanning;

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
public class ConfContrainte extends AbstractConfigPanel {
	@SuppressWarnings("rawtypes")
	private DAO setdao, nivdao;
	private MartList<Niveau> niveaux;
	private MartTable tableInfo;
	private String annee;
	private MartButton bDefinirSalaire;

	public ConfContrainte(String tit) {
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

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Component source = (Component) e.getSource();

	}

	public void setAnnee(String an) {
		annee = an;
	}

	public String getCentre() {
		return null;
	}
}
