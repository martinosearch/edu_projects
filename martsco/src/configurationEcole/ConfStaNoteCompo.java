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

public class ConfStaNoteCompo extends AbstractConfigPanel {
	private Dimension dimPanes = new Dimension(480, 80);

	private JPanel panStaNoteCompo;
	private MartCheckBox staMixtes;
	private MartCheckBox staMascFem;
	private JSpinner jspStaNoteCompo;

	private DAO setdao;

	public ConfStaNoteCompo(String tit) {
		super(tit);
		this.setLayout(new FlowLayout());
		setdao = DAOFactory.getDAO(DAO.SETTING);

		panStaNoteCompo = new JPanel();
		panStaNoteCompo.setLayout(new FlowLayout());

		// Type de statistiques
		JPanel panTypeStaCompo = new JPanel();
		panTypeStaCompo.setPreferredSize(dimPanes);
		panTypeStaCompo.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.BLUE),
				"Type de Statistiques", 0, 0));
		staMixtes = new MartCheckBox("Statistiques mixtes", "staMixtes");
		staMascFem = new MartCheckBox("Statistiques Masculin/ Feminin", "staMF");
		ButtonGroup bgStaCompo = new ButtonGroup();
		bgStaCompo.add(staMixtes);
		bgStaCompo.add(staMascFem);

		panTypeStaCompo.setLayout(new GridLayout(1, 2));

		panTypeStaCompo.add(staMixtes);
		panTypeStaCompo.add(staMascFem);

		// Format de la fiche
		JPanel panFormatStaCompo = new JPanel();
		panFormatStaCompo.setPreferredSize(dimPanes);
		panFormatStaCompo.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.BLUE),
				"Hauteur des lignes", 0, 0));

		jspStaNoteCompo = new JSpinner();
		jspStaNoteCompo.setModel(new SpinnerNumberModel(1, 0.3, 2, 0.1));

		JLabel lb1 = new JLabel("Statistique Note de Compo.");
		panFormatStaCompo.add(lb1);
		panFormatStaCompo.add(jspStaNoteCompo);

		this.add(panTypeStaCompo);
		this.add(panFormatStaCompo);
	}

	public MartList<Setting> getSettings() {
		checks.add(staMixtes);
		checks.add(staMascFem);
		sets = new MartList<Setting>();

		for (MartCheckBox ck : checks) {
			if (ck.isSelected()) {
				Setting set = new Setting("typeSta", ck.getId());
				sets.add(set);
			}
		}

		Setting set = new Setting("hCellule",
				(double) jspStaNoteCompo.getValue());
		sets.add(set);

		return sets;
	}

	@Override
	public Object find(String id) {
		getSettings();

		Object obj = null;

		if (id.equals("typeSta")) {
			obj = sets.find("typeSta").getAttribut();
			if (obj.equals("staMixtes"))
				obj = DocFormat.STA_MIXTE;
			else if (obj.equals("staMF"))
				obj = DocFormat.STA_MASC_FEM;
			else
				obj = 0;
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

		Setting setRow = (Setting) setdao.findObj("hRowNoteCompo");

		double value = 1.0;
		try {
			value = Double.valueOf((String) setRow.getAttribut()).doubleValue();
		} catch (Exception e) {
			e.printStackTrace();
			value = 1.0;
		}
		jspStaNoteCompo.setValue(value);

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
		String intitule = "hRowNoteCompo";
		String attribut = String.valueOf((double) jspStaNoteCompo.getValue());
		Setting setHRow = new Setting(intitule, attribut);

		setdao.update_create(setHRow);

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
