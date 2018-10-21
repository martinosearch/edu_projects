package configurationExamen;

import etablissement.Etablissement;
import graphicsModel.MartCheckBox;
import graphicsModel.MartList;
import graphicsModel.MartListSelector;
import interfacePerso.Observer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import matiere.Matiere;
import matiere.MatiereProg;
import matiere.MatiereProgExamControler;
import matiere.MatiereProgExamModel;
import tableComponent.MartTabModel;
import tableComponent.MartTable;
import abstractObject.AbstractControler;
import abstractObject.AbstractModel;
import configurationAppli.AbstractConfigPanel;
import configurationAppli.Setting;
import connection.DAO;
import connection.DAOFactory;

public class ConfMatiereProgExam extends AbstractConfigPanel implements
		Observer {

	private MartList<MartCheckBox> checks;
	private MartCheckBox bullbloc;
	private DAO etsdao;
	private MartList<Etablissement> listeEts;
	private MartListSelector selector;
	MartTable tabEtsDef;
	MartTable tabEts;
	AbstractModel model;
	AbstractControler controler;
	private DAO pMatExamDao;
	private MartList<Matiere> matieres;
	private DAO matiereDao;
	private MartList<MatiereProg> matProgs;

	public ConfMatiereProgExam(String tit) {
		super(tit);
		this.setSize(dimContainer);
		this.setLayout(new BorderLayout());

		pMatExamDao = DAOFactory.getDAO(DAO.MATIERE_PROG_EXAM);
		matiereDao = DAOFactory.getDAO(DAO.MATIERE);

		model = new MatiereProgExamModel();
		model.addObserver(this);
		controler = new MatiereProgExamControler(model);

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
		pMatExamDao.loadExam(annee);
		matProgs = pMatExamDao.getListObt();

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
		String[] titleDef = { "Nom de l'Etablissements" };
		MartTabModel modtabDef = new MartTabModel(dataDef, titleDef);
		tabEtsDef = new MartTable(modtabDef);
		tabEtsDef.setColumnSize(0, 10);

		i = 0;
		for (Matiere mat : temp) {
			dataDef[i][0] = mat.getIntitule();
			i++;
		}

		Object[][] data = new Object[matProgs.size()][2];
		String[] title = { "N°", "Matière" };

		MartTabModel modtab = new MartTabModel(data, title);
		tabEts = new MartTable(modtab);
		tabEts.setColumnSize(0, 1);
		tabEts.setColumnSize(1, 10);

		i = 0;
		for (MatiereProg mat : matProgs) {
			// System.out.println("llllllllllllllllllll" + ets + "Pour l'exam"
			// + examen + etsPerso.size());

			data[i][0] = i + 1;
			data[i][1] = mat.getIntitule();
			i++;
		}

		this.removeAll();
		selector = new MartListSelector(this, tabEtsDef, tabEts);

		this.add(selector, BorderLayout.CENTER);
	}

	@Override
	public void save() {
		model.setExamen(annee);
		controler.setExamen(annee);
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
		return tabEtsDef;
	}

	public MartTable getMat() {
		return tabEts;
	}
}
