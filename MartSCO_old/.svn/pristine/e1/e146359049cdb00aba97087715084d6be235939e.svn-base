package configurationExamen;

import etablissement.Etablissement;
import function.Constance;
import graphicsModel.MartList;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import agent.Agent;
import agent.Responsable;
import configurationAppli.AbstractConfigPanel;
import configurationAppli.Setting;
import connection.DAO;
import connection.DAOFactory;

/**
 * Cette classe permet de définir les responsable pour chaque niveau
 * d'enseignement. Elle n'est pas du model MVC, donc est autonome.
 * 
 * @author martino
 *
 */

public class ConfAutresInfosExam extends AbstractConfigPanel {
	Responsable president, dirCol, dirLeg, dirLet;
	private JPanel pan2;

	private MartList<Agent> profs;
	private MartList<Responsable> resps;
	private String[] tabProfs;
	DAO ensdao, respdao, etsdao;

	private JLabel lbNomPresident, lbFonctionPresident;

	private JComboBox cbNomPresident, cbFonctionPresident;

	private JScrollPane sc;

	private JPanel container;
	private MartList<Etablissement> etablissements;

	public ConfAutresInfosExam(String tit) {
		super(tit);
		this.setLayout(new BorderLayout());
		ensdao = DAOFactory.getDAO(DAO.AGENT);
		respdao = DAOFactory.getDAO(DAO.RESPONSABLE);
		etsdao = DAOFactory.getDAO(DAO.ETABLISSEMENT);

		container = new JPanel();
		container.setLayout(new FlowLayout());
		initComponent();

		this.add(container, BorderLayout.CENTER);
	}

	private void initComponent() {
		respdao.load();
		ensdao.load();
		etsdao.load();

		profs = ensdao.getListObt();
		resps = respdao.getListObt();
		etablissements = etsdao.getListObt();

		tabProfs = new String[profs.size() + 1];
		tabProfs[0] = "";

		int i = 1;
		for (Agent prof : profs) {
			tabProfs[i] = prof.decrisToi();
			i++;
		}

		String[] titres = { "Le Directeur", "La Directrice", "Le Proviseur",
				"Le Censeur", "Le Président du Jury" };

		// pour les dirigeant Primaire
		lbNomPresident = new JLabel("Nom");
		cbNomPresident = new JComboBox(tabProfs);

		lbFonctionPresident = new JLabel("Fonction");
		cbFonctionPresident = new JComboBox(titres);

		pan2 = new JPanel();
		pan2.setLayout(new GridLayout(2, 2));
		pan2.setBorder(BorderFactory.createTitledBorder("Le Président du Jury"));
		pan2.add(lbNomPresident);
		pan2.add(cbNomPresident);
		pan2.add(lbFonctionPresident);
		pan2.add(cbFonctionPresident);

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
		// on insert le Président de JURY
		Agent rspTemp = new Agent();
		for (Agent ens : profs) {
			if (ens.decrisToi().equals(cbNomPresident.getSelectedItem())) {
				rspTemp = ens;
			}
		}

		String titre = "president_" + Constance.getCor(annee);
		String matricule = rspTemp.getCodeInfo();
		String sexe = rspTemp.getSexe();
		String fonction = (String) cbFonctionPresident.getSelectedItem();

		president = new Responsable(titre, matricule, sexe);
		president.setFonction(fonction);

		try {
			respdao.update_create(president);
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null,
					"Veuillez compléter tous les champs");
		}
	}

	@Override
	public void refresh() {
		// initialisation
		try {
			president = resps.find("president_" + Constance.getCor(annee));

			Agent ensPrim = profs.find(president.getCodeInfo());
			cbNomPresident.setSelectedItem(ensPrim.decrisToi());
		} catch (Exception e) {

		}
	}

}
