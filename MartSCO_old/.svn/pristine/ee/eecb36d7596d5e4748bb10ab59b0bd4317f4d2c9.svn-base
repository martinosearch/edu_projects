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
import javax.swing.JPanel;

import configurationAppli.AbstractConfigPanel;
import configurationAppli.Setting;
import connection.DAO;
import connection.DAOFactory;

public class ConfPolice extends AbstractConfigPanel {
	private MartCheckBox pol1;
	private MartCheckBox pol2;
	private MartCheckBox pol3;
	private MartCheckBox pol4;
	private MartCheckBox pol5;
	private MartCheckBox pol6;
	private DAO setdao;
	private int trimeste;
	private String annee;

	public ConfPolice(String tit) {
		super(tit);
		this.setLayout(new FlowLayout());
		setdao = DAOFactory.getDAO(DAO.SETTING);

		// Police des notes
		JPanel panPolNote = new JPanel();
		panPolNote.setPreferredSize(dimPanes);
		panPolNote.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.BLUE), "Police des notes",
				0, 0));
		pol1 = new MartCheckBox("Calibri", "policeNote");
		pol2 = new MartCheckBox("Ms sans Serif", "policeNote");
		pol3 = new MartCheckBox("Footlight MT Light", "policeNote");
		pol4 = new MartCheckBox("Consola", "policeNote");
		pol5 = new MartCheckBox("Tahoma", "policeNote");
		pol6 = new MartCheckBox("Arial", "policeNote");

		pol5.setSelected(true);

		ButtonGroup bg = new ButtonGroup();
		bg.add(pol1);
		bg.add(pol2);
		bg.add(pol3);
		bg.add(pol4);
		bg.add(pol5);
		bg.add(pol6);

		panPolNote.setLayout(new GridLayout(3, 2));

		panPolNote.add(pol1);
		panPolNote.add(pol2);
		panPolNote.add(pol3);
		panPolNote.add(pol4);
		panPolNote.add(pol5);
		panPolNote.add(pol6);

		this.add(panPolNote);
	}

	public MartList<Setting> getSettings() {
		checks.add(pol1);
		checks.add(pol2);
		checks.add(pol3);
		checks.add(pol4);
		checks.add(pol5);
		checks.add(pol6);

		sets = new MartList<Setting>();

		for (MartCheckBox ck : checks) {
			Setting set = null;

			if (ck.isSelected()) {
				set = new Setting(ck.getId(), ck.getIntitule());
				sets.add(set);
			}
		}

		return sets;
	}

	@Override
	public Object find(String id) {
		String police = "Tahoma";
		getSettings();

		if (id.equals("policeNote")) {
			try {
				System.out.println(sets.size());
				police = sets.find("policeNote").getIntitule();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return police;
	}

	@Override
	public void refresh() {
		setdao.load("", "", trimeste, annee);
		getSettings();

		// configurations pour les bulletins
		for (MartCheckBox ck : checks) {
			try {
				Setting set = new Setting();
				String intitule = Constance.getCor(ck.getId());

				set = (Setting) setdao.findObj(intitule);

				String etat = (String) set.getAttribut();

				if (etat.equals(ck.getIntitule()))
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
		String id = "";
		setdao.load("", "", trimeste, annee);
		for (MartCheckBox ck : checks) {
			Setting set = new Setting();
			if (ck.isSelected()) {
				id = Constance.getCor(ck.getId());
				set = new Setting(id, ck.getIntitule());
				setdao.update_create(set);
			}
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
