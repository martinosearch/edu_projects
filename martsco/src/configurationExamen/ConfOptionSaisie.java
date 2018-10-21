package configurationExamen;

import graphicsModel.MartList;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import agent.Agent;
import agent.Responsable;
import configurationAppli.AbstractConfigPanel;
import configurationAppli.Setting;
import connection.DAO;
import connection.DAOFactory;

/**
 * Cette classe permet de définir les option de saisie des notes d'enseignement.
 * Elle n'est pas du model MVC, donc est autonome.
 * 
 * @author martino
 *
 */

public class ConfOptionSaisie extends AbstractConfigPanel {
	Responsable president, dirCol, dirLeg, dirLet;
	private JPanel pan2;

	private MartList<Agent> profs;
	private MartList<Responsable> resps;
	DAO setdao = DAOFactory.getDAO(DAO.SETTING);

	private JCheckBox ckAfficherNom = new JCheckBox(
			"Afficher les noms des élèves lors de la saisie des notes");

	private JPanel container;

	public ConfOptionSaisie(String tit) {
		super(tit);
		this.setLayout(new BorderLayout());

		container = new JPanel();
		container.setLayout(new FlowLayout());
		initComponent();

		this.add(container, BorderLayout.CENTER);
	}

	private void initComponent() {
		pan2 = new JPanel(new FlowLayout());
		pan2.setBorder(BorderFactory
				.createTitledBorder("Option des affichages"));
		pan2.add(ckAfficherNom);

		pan2.setPreferredSize(dimPanes);
		container.add(pan2);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Component source = (Component) e.getSource();

	}

	@Override
	public MartList<Setting> getSettings() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object find(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save() {
		boolean bool = ckAfficherNom.isSelected();

		Setting ets = new Setting("afficherNomSaisie", String.valueOf(bool));

		setdao.load(null, null, 0, annee);
		setdao.update_create(ets);
	}

	@Override
	public void refresh() {
		boolean bool = false;

		setdao.load(null, null, 0, annee);
		try {
			bool = Boolean.valueOf((String) ((Setting) setdao
					.findObj("afficherNomSaisie")).getAttribut());
		} catch (Exception e) {
			e.printStackTrace();
		}

		ckAfficherNom.setSelected(bool);
	}

	public boolean getAfficherNom() {
		// TODO Auto-generated method stub
		return ckAfficherNom.isSelected();
	}

}
