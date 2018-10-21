package configurationExamen;

import etablissement.Etablissement;
import function.MartFormatter;
import graphicsModel.MartList;
import graphicsModel.MartListSelector;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import tableComponent.MartTabModel;
import tableComponent.MartTable;
import configurationAppli.AbstractConfigPanel;
import configurationAppli.Setting;
import connection.DAO;
import connection.DAOFactory;

public class ConfEtablissement extends AbstractConfigPanel {
	private MartList<String> etsPerso = new MartList<String>();
	private MartList<Etablissement> listeEts = new MartList<Etablissement>();
	private DAO etsdao, setdao;

	private MartListSelector selector;
	MartTable tabEtsDef;
	MartTable tabEts;
	private JLabel lbCentre;
	private JComboBox cbCentre;
	private JPanel panCentre;

	public ConfEtablissement(String tit) {
		super(tit);
		this.setSize(dimContainer);
		this.setLayout(new BorderLayout());

		etsdao = DAOFactory.getDAO(DAO.ETABLISSEMENT);
		setdao = DAOFactory.getDAO(DAO.SETTING);

		this.setBackground(Color.GREEN);

		initComponent();
	}

	public void initComponent() {
		lbCentre = new JLabel("Centre d'Ecrit");
		cbCentre = new JComboBox();
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
		etsdao.load();
		listeEts = etsdao.getListObt();

		// la table des établissements convoqués
		setdao.load(null, null, 0, annee);
		try {
			String intituleCentre = "ets" + annee;
			String etsStr = (String) ((Setting) setdao.findObj(intituleCentre))
					.getAttribut();
			etsPerso = getListe(etsStr);
		} catch (Exception e) {
			e.printStackTrace();
		}

		MartList<String> temp = new MartList();
		int i = 0;

		for (Etablissement ets : listeEts) {
			boolean exist = false;
			for (String str : etsPerso) {
				if (ets.define().equals(str)) {
					exist = true;
				}
			}

			if (exist == false) {
				temp.add(ets.define());
				i++;
			}
		}

		// Définition de la table
		Object[][] dataDef = new Object[i][1];
		String[] titleDef = { "Nom de l'Etablissements" };
		MartTabModel modtabDef = new MartTabModel(dataDef, titleDef);
		tabEtsDef = new MartTable(modtabDef);
		tabEtsDef.setColumnSize(0, 1);

		i = 0;
		for (String str : temp) {
			dataDef[i][0] = str;
			i++;

		}

		Object[][] data = new Object[etsPerso.size()][2];
		String[] title = { "N°", "Nom de l'Etablissements" };
		MartTabModel modtab = new MartTabModel(data, title);
		tabEts = new MartTable(modtab);
		tabEts.setColumnSize(0, 1);
		tabEts.setColumnSize(1, 10);

		i = 0;
		for (String ets : etsPerso) {
			data[i][0] = i + 1;
			data[i][1] = ets;
			i++;
		}

		this.removeAll();
		selector = new MartListSelector(this, tabEtsDef, tabEts);

		for (Etablissement str : listeEts) {
			cbCentre.addItem(str.define());
		}

		panCentre = new JPanel();
		panCentre.setLayout(new GridLayout(2, 2));
		panCentre.setBorder(BorderFactory
				.createTitledBorder("Le Centre d'Ecrit"));

		panCentre.add(lbCentre);
		panCentre.add(cbCentre);
		panCentre.setPreferredSize(dimPanes);

		// pour le centre d'écrit
		setdao.load(null, null, 0, annee);
		try {
			String intituleCentre = "centre" + annee;
			String centre = (String) ((Setting) setdao.findObj(intituleCentre))
					.getAttribut();
			cbCentre.setSelectedItem(centre);
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.add(selector, BorderLayout.CENTER);
		this.add(panCentre, BorderLayout.SOUTH);
	}

	// cette méthde tranforme la chaine de liste en liste.
	private MartList<String> getListe(String etsStr) {
		MartList<String> temp = new MartList<String>();
		MartFormatter mf = new MartFormatter();
		temp = mf.decomposer(etsStr, ';');

		try {
			if (temp.get(0).equals("")) {
				temp = new MartList();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return temp;
	}

	@Override
	public void save() {
		String liststr = "";
		try {
			for (int i = 0; i < tabEts.getRowCount(); i++) {
				String value = (String) tabEts.getValueAt(i, 1);

				// System.out.println("*******************>> la valeur"
				// + value);

				if (!value.equals("")) {
					liststr += value + ";";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		Setting ets = new Setting("ets" + annee, liststr);
		Setting centre = new Setting("centre" + annee, getCentre());

		setdao.load(null, null, 0, annee);
		setdao.update_create(ets);
		setdao.update_create(centre);
	}

	public String getCentre() {
		return (String) cbCentre.getSelectedItem();
	}

	public String getChef() {
		return (String) cbCentre.getSelectedItem();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Component source = (Component) e.getSource();

	}

	public MartTable getMatDef() {
		return tabEtsDef;
	}

	public MartTable getMat() {
		return tabEts;
	}

	public MartList<String> getEtsPerso() {
		return etsPerso;
	}

}
