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

public class ConfStaMoyTrim extends AbstractConfigPanel {
	private Dimension dimPanes = new Dimension(480, 80);
	private MartCheckBox staMixtes2;
	private MartCheckBox staMascFem2;
	private JSpinner jspStaMoyTrim;
	private DAO setdao;

	public ConfStaMoyTrim(String tit) {
		super(tit);
		this.setLayout(new FlowLayout());
		setdao = DAOFactory.getDAO(DAO.SETTING);

		// Type de statistiques
		JPanel panTypeStaMoyTrim = new JPanel();
		panTypeStaMoyTrim.setPreferredSize(dimPanes);
		panTypeStaMoyTrim.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.BLUE),
				"Type de Statistiques", 0, 0));
		staMixtes2 = new MartCheckBox("Statistiques mixtes", "staMixtes2");
		staMascFem2 = new MartCheckBox("Statistiques Masculin/ Feminin",
				"staMF2");
		ButtonGroup bg3 = new ButtonGroup();
		bg3.add(staMixtes2);
		bg3.add(staMascFem2);

		panTypeStaMoyTrim.setLayout(new GridLayout(1, 2));

		panTypeStaMoyTrim.add(staMixtes2);
		panTypeStaMoyTrim.add(staMascFem2);

		// Format de la fiche
		JPanel panFormatStaMoyTrim = new JPanel();
		panFormatStaMoyTrim.setPreferredSize(dimPanes);
		panFormatStaMoyTrim.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.BLUE),
				"Hauteur des lignes", 0, 0));

		jspStaMoyTrim = new JSpinner();
		jspStaMoyTrim.setModel(new SpinnerNumberModel(1, 0.3, 2, 0.1));

		JLabel lb2 = new JLabel("Statistique Moy. Trimestrielles");
		panFormatStaMoyTrim.add(lb2);
		panFormatStaMoyTrim.add(jspStaMoyTrim);

		this.add(panTypeStaMoyTrim);
		this.add(panFormatStaMoyTrim);
	}

	public MartList<Setting> getSettings() {
		checks.add(staMixtes2);
		checks.add(staMascFem2);
		sets = new MartList<Setting>();

		for (MartCheckBox ck : checks) {
			if (ck.isSelected()) {
				Setting set = new Setting("typeSta", ck.getId());
				sets.add(set);
			}
		}

		Setting set = new Setting("hCellule", (double) jspStaMoyTrim.getValue());
		sets.add(set);

		return sets;
	}

	@Override
	public Object find(String id) {
		getSettings();
		String param = "";
		Object attrib = 0;

		if (id.equals("typeSta")) {
			try {
				param = (String) sets.find("typeSta").getAttribut();
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (param.equals("staMixtes2"))
				attrib = DocFormat.STA_MIXTE;
			else if (param.equals("staMF2"))
				attrib = DocFormat.STA_MASC_FEM;
			else
				attrib = 0;
		}

		if (id.equals("hCellule")) {
			attrib = sets.find("hCellule").getAttribut();
		}

		return attrib;
	}

	@Override
	public void refresh() {
		setdao.load("", "", trimestre, annee);
		getSettings();

		Setting setRow2 = (Setting) setdao.findObj("hRowMoyTrim");

		double value2 = 1.0;
		try {
			value2 = Double.valueOf((String) setRow2.getAttribut())
					.doubleValue();
		} catch (Exception e) {
			e.printStackTrace();
			value2 = 1.0;
		}

		jspStaMoyTrim.setValue(value2);
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
		String intitule = "hRowMoyTrim";
		String attribut2 = String.valueOf((double) jspStaMoyTrim.getValue());
		Setting setHRow2 = new Setting(intitule, attribut2);
		setdao.update_create(setHRow2);

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

	public int getHRow() {
		// TODO Auto-generated method stub
		return 0;
	}
}
