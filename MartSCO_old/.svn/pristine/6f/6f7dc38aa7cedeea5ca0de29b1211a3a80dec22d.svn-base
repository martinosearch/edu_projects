package configurationEcole;

import function.Constance;
import graphicsModel.MartCheckBox;
import graphicsModel.MartList;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import configurationAppli.AbstractConfigPanel;
import configurationAppli.Setting;
import connection.DAO;
import connection.DAOFactory;
import rapportBulletin.DocFormat;

public class ConfStaMoyAn extends AbstractConfigPanel {
	private Dimension dimPanes = new Dimension(480, 80);
	private MartCheckBox staMixtesAn;
	private MartCheckBox staMascFemAn;
	private JSpinner jspStaMoyAn;
	private DAO setdao;

	public ConfStaMoyAn(String tit) {
		super(tit);
		this.setLayout(new FlowLayout());
		setdao = DAOFactory.getDAO(DAO.SETTING);

		// Type de statistiques
		JPanel panTypeStaMoyAn = new JPanel();
		panTypeStaMoyAn.setPreferredSize(dimPanes);
		panTypeStaMoyAn.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.BLUE),
				"Type de Statistiques", 0, 0));
		staMixtesAn = new MartCheckBox("Statistiques mixtes", "staMixtesAn");
		staMascFemAn = new MartCheckBox("Statistiques Masculin/ Feminin",
				"staMFAn");
		ButtonGroup bgAn = new ButtonGroup();
		bgAn.add(staMixtesAn);
		bgAn.add(staMascFemAn);

		panTypeStaMoyAn.setLayout(new GridLayout(1, 2));

		panTypeStaMoyAn.add(staMixtesAn);
		panTypeStaMoyAn.add(staMascFemAn);

		// Format de la fiche
		JPanel panFormatStaMoyAn = new JPanel();
		panFormatStaMoyAn.setPreferredSize(dimPanes);
		panFormatStaMoyAn.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.BLUE),
				"Hauteur des lignes", 0, 0));

		jspStaMoyAn = new JSpinner();
		jspStaMoyAn.setModel(new SpinnerNumberModel(1, 0.3, 2, 0.1));

		JLabel lbAn = new JLabel("Statistiques Moy. Annuelles");
		panFormatStaMoyAn.add(lbAn);
		panFormatStaMoyAn.add(jspStaMoyAn);

		this.add(panTypeStaMoyAn);
		this.add(panFormatStaMoyAn);
	}

	public MartList<Setting> getSettings() {
		checks.add(staMixtesAn);
		checks.add(staMascFemAn);
		sets = new MartList<Setting>();

		for (MartCheckBox ck : checks) {
			if (ck.isSelected()) {
				Setting set = new Setting("typeSta", ck.getId());
				sets.add(set);
			}
		}

		Setting set = new Setting("hCellule", (double) jspStaMoyAn.getValue());
		sets.add(set);

		return sets;
	}

	@Override
	public Object find(String id) {
		Object obj = null;
		getSettings();

		if (id.equals("typeSta")) {
			try {
				obj = sets.find("typeSta").getAttribut();
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				if (obj.equals("staMixtesAn"))
					obj = DocFormat.STA_MIXTE;

				else if (obj.equals("staMFAn"))
					obj = DocFormat.STA_MASC_FEM;

				else
					obj = 0;
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		if (id.equals("hCellule")) {
			obj = sets.find("hCellule").getAttribut();
		}

		return obj;
	}

	@Override
	public void refresh() {
		setdao.load("", "", trimestre, annee);
		getSettings();

		Setting setRowAn = (Setting) setdao.findObj("hRowMoyAn");

		double valueAn = 1.0;
		try {
			valueAn = Double.valueOf((String) setRowAn.getAttribut())
					.doubleValue();
		} catch (Exception e) {
			e.printStackTrace();
			valueAn = 1.0;
		}

		jspStaMoyAn.setValue(valueAn);

		// pour les cases à cocher
		for (MartCheckBox ck : checks) {
			try {
				Setting set = new Setting();
				String intitule = Constance.getCor(ck.getId());
				set = (Setting) setdao.findObj(intitule);
				String etat = (String) set.getAttribut();
				if (etat.equals("true"))
					ck.setSelected(true);
				else
					ck.setSelected(false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void save() {
		setdao.load("", "", trimestre, annee);
		String intitule = "hRowMoyAn";
		String attribut3 = String.valueOf((double) jspStaMoyAn.getValue());

		Setting setHRow3 = new Setting(intitule, attribut3);
		setdao.update_create(setHRow3);

		// pour les cases à cocher
		for (MartCheckBox ck : checks) {
			try {
				Setting set = new Setting();
				intitule = Constance.getCor(ck.getId());

				if (ck.isSelected())
					set = new Setting(intitule, "true");
				else
					set = new Setting(intitule, "false");

				setdao.update_create(set);

			} catch (Exception e2) {
				e2.printStackTrace();
			}
			// *********************FIN********************
		}

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void setAnnee(String an) {
		annee = an;
	}
}
