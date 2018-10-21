package configurationClasse;

import graphicsModel.ComboEditor;
import graphicsModel.ListeSelectorData;
import graphicsModel.MartCheckBox;
import graphicsModel.MartList;
import graphicsModel.MartListSelector;
import interfacePerso.Observer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;

import tableComponent.MartTabModel;
import tableComponent.MartTable;
import abstractObject.AbstractControler;
import abstractObject.AbstractModel;
import agent.Agent;
import matiere.Matiere;
import matiere.MatiereProg;
import matiere.MatiereProgControler;
import matiere.MatiereProgModel;
import configurationAppli.AbstractConfigPanel;
import configurationAppli.Setting;
import connection.DAO;
import connection.DAOFactory;

public class ConfMatiereProg extends AbstractConfigPanel implements Observer {

	private MartList<MartCheckBox> checks;
	private DAO etsdao, pMatDao, ensDao;
	private MartListSelector selector;
	MartTable tabMatDef;
	MartTable tabMat;
	AbstractModel model;
	AbstractControler controler;
	private MartList<Matiere> matieres;
	private DAO matiereDao;
	private MartList<MatiereProg> matProgs;
	private MartList<Agent> enseignants;
	private String annee;
	private String classe;
	private int trimestre = 1;

	public ConfMatiereProg(String tit) {
		super(tit);
		this.setSize(600, 300);
		this.setLayout(new BorderLayout());

		pMatDao = DAOFactory.getDAO(DAO.MATIERE_PROG);
		matiereDao = DAOFactory.getDAO(DAO.MATIERE);
		ensDao = DAOFactory.getDAO(DAO.AGENT);

		model = new MatiereProgModel();
		model.addObserver(this);
		controler = new MatiereProgControler(model);

		this.setBackground(Color.GREEN);
	}

	public MartList<Setting> getSettings() {
		return sets;
	}

	public Object find(String set) {
		getSettings();
		Object result = sets.find(set).getAttribut();
		return result;
	}

	@Override
	public void refresh() {
		// table de définition des Ets
		ensDao.load();
		pMatDao.load("", classe, trimestre, annee);

		matProgs = pMatDao.getListObt();
		enseignants = ensDao.getListObt();

		matiereDao.load();
		matieres = matiereDao.getListObt();

		MartList<Matiere> temp = new MartList();
		int i = 0;
		for (Matiere mat : matieres) {
			if (matProgs.contains(mat.getId()) == false) {
				temp.add(mat);
				i++;
			}
		}

		// Définition de la table
		Object[][] dataDef = new Object[i][1];
		String[] titleDef = { "Matières par défaut" };
		MartTabModel modtabDef = new MartTabModel(dataDef, titleDef);
		tabMatDef = new MartTable(modtabDef);
		tabMatDef.setColumnSize(0, 0.5);

		i = 0;
		for (Matiere mat : temp) {
			dataDef[i][0] = mat.getIntitule();
			i++;
		}

		Object[][] data = new Object[matProgs.size()][4];
		String[] title = { "N°", "Matière", "Coef.", "Chargé" };

		MartTabModel modtab = new MartTabModel(data, title);

		// pour définir les colonne éditable
		modtab.setColEditable(3);

		tabMat = new MartTable(modtab);
		tabMat.setColumnSize(0, 0.5);
		tabMat.setColumnSize(2, 0.5);

		String[] tabEns = new String[enseignants.size()];
		i = 0;
		for (Agent ens : enseignants) {
			tabEns[i] = ens.getNom();
			i++;
		}
		tabMat.getColumnModel().getColumn(3)
				.setCellEditor(new ComboEditor(new JComboBox(tabEns)));

		i = 0;
		for (MatiereProg mat : matProgs) {
			// System.out.println("llllllllllllllllllll" + ets + "Pour l'exam"
			// + examen + etsPerso.size());

			data[i][0] = i + 1;
			data[i][1] = mat.getIntitule();
			data[i][2] = mat.getCoef();
			data[i][3] = mat.getCharge();
			i++;
		}

		this.removeAll();
		selector = new MartListSelector(this, tabMatDef, tabMat);
		selector.setData(new ListeSelectorData<MatiereProg>(matProgs));

		this.add(selector, BorderLayout.CENTER);

	}

	@Override
	public void save() {
		model.setAnnee(annee);
		model.setClasse(classe);

		controler.setAnnee(annee);
		controler.setClasse(classe);

		controler.valider();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Component source = (Component) e.getSource();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	public MartTable getMatDef() {
		return tabMatDef;
	}

	public MartTable getMat() {
		return tabMat;
	}

	public void setAnnee(String an) {
		annee = an;
	}

	public void setClasse(String cl) {
		classe = cl;
	}

}
