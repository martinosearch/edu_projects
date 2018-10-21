package configurationEcole;

import graphicsModel.ComboEditor;
import graphicsModel.MartCheckBox;
import graphicsModel.MartList;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import tableComponent.MartFixedTable;
import tableComponent.MartTabModel;
import tableComponent.MartTable;
import matiere.Matiere;
import note.Coefficient;
import classe.Niveau;
import configurationAppli.AbstractConfigPanel;
import configurationAppli.Setting;
import connection.DAO;
import connection.DAOFactory;

public class ConfCoefficient extends AbstractConfigPanel {
	private Dimension dimPanes = new Dimension(480, 80);
	private MartCheckBox pol1;
	private MartCheckBox pol2;
	private MartCheckBox pol3;
	private MartCheckBox pol4;
	private MartCheckBox pol5;
	private MartList<Niveau> niveaux;
	private MartList<Matiere> matieres;
	private DAO<Niveau> nivdao;
	private DAO<Matiere> matdao;
	private DAO<Coefficient> coefdao;
	private int trimestre;
	private String annee;
	private MartFixedTable tabCoef;

	public ConfCoefficient(String tit) {
		super(tit);
		this.setLayout(new BorderLayout());
		nivdao = DAOFactory.getDAO(DAO.NIVEAU);
		matdao = DAOFactory.getDAO(DAO.MATIERE);
		coefdao = DAOFactory.getDAO(DAO.COEFFICIENT);
	}

	public MartList<Setting> getSettings() {
		return sets;
	}

	@Override
	public Object find(String id) {
		return null;
	}

	@Override
	public void refresh() {
		nivdao.load();
		matdao.load();
		niveaux = nivdao.getListObt();
		matieres = matdao.getListObt();

		String[] title = new String[niveaux.size() + 1];
		title[0] = "Matières";

		for (int i = 0; i < niveaux.size(); i++) {
			title[i + 1] = (String) niveaux.get(i).getIntitule();
		}

		Object[][] data = new Object[matieres.size()][niveaux.size() + 1];

		for (int j = 0; j < matieres.size(); j++) {
			String matiere = matieres.get(j).getIntitule();
			data[j][0] = matiere;

			for (int k = 0; k < niveaux.size(); k++) {
				String niveau = niveaux.get(k).getIntitule();
				double valeurCoef = getCoef(matiere, niveau);
				data[j][k + 1] = valeurCoef;
			}
		}

		MartTabModel model1 = new MartTabModel(data, title, 1);

		for (int k = 1; k < niveaux.size() + 1; k++) {
			model1.setColEditable(k);
		}

		tabCoef = new MartFixedTable(model1);
		tabCoef.setColumnSize(0, 6);
		double[] cbdata = { 0.5, 1, 2, 3, 4, 5 };
		int max2 = title.length;

		for (int k = 1; k < max2; k++) {
			JComboBox cb = new JComboBox();
			for (double d : cbdata) {
				cb.addItem(d);
			}

			String titre = tabCoef.getColumnName(k);
			tabCoef.getColumn(titre).setCellEditor(new ComboEditor(cb));
		}

		JPanel tabCoefPan = new JPanel();
		tabCoefPan.setLayout(new BorderLayout());
		tabCoefPan.add(tabCoef, BorderLayout.CENTER);

		this.add(tabCoefPan, BorderLayout.CENTER);
	}

	// Pour recuppérer le coefficient
	public double getCoef(String mat, String niveau) {
		coefdao.load(new String(), niveau, trimestre, annee);
		return ((Coefficient) coefdao.findObj(mat + niveau)).getCoef();
	}

	@Override
	public void save() {
		int heightC = tabCoef.getRowCount();
		int widthC = tabCoef.getColumnCount();

		for (int i = 0; i < heightC; i++) {
			// la matiere en cours
			String matiere = (String) tabCoef.getValueAt(i, 0);

			// on balaye les colonnes
			for (int j = 0; j < widthC - 1; j++) {
				try {
					double co = (double) tabCoef.getValueAt(i, j + 1);
					String niveau = tabCoef.getColumnName(j + 1);
					Coefficient coef = new Coefficient(co, matiere + niveau);

					coefdao.load(matiere, niveau, trimestre, annee);
					coefdao.update_create(coef);

				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
	}

	public void setAnnee(String an) {
		annee = an;
	}
}
