package configurationAdmin;

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
import function.Constance;
import graphicsModel.MartList;

/**
 * Cette classe permet de définir les responsable pour chaque niveau
 * d'enseignement. Elle n'est pas du model MVC, donc est autonome.
 *
 * @author martino
 *
 */

public class ConfResponsable extends AbstractConfigPanel {
	private String Prim = "Primaire", Coll = "Collège", LEG = "Lycée d'Enseignement Général",
			LET = "Lycée d'Enseignement Technique";

	Responsable dirPrim, dirCol, dirLeg, dirLet;

	private static String annee = "";

	private JPanel pan11;
	private JPanel pan2;
	private JPanel pan3;
	private JPanel pan4;
	private JPanel pan5;
	private JPanel container2;
	private JPanel container1;
	private static int nbre = 0;
	private MartList<Agent> profs;
	private MartList<Responsable> resps;
	private String[] tabProfs;
	DAO ensdao, respdao;

	String fonction = "Le Directeur";

	private JLabel lbNomPrimaire, lbNomCol, lbNomLeg, lbFonctionLeg, lbFonctionLet, lbNomLet, lbFonctionCol,
			lbFonctionPrim;

	private JComboBox cbNomPrim, cbNomCol, cbNomLeg, cbFonctionLeg, cbFonctionLet, cbFonctionPrim, cbNomLet,
			cbFonctionCol;

	private JScrollPane sc;

	public ConfResponsable(String tit) {
		super(tit);
		this.setLayout(new FlowLayout());
		ensdao = DAOFactory.getDAO(DAO.AGENT);
		respdao = DAOFactory.getDAO(DAO.RESPONSABLE);

		initComponent();

		sc = new JScrollPane(container2, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		this.add(container1);
		this.add(sc);
	}

	private void initComponent() {
		respdao.load();
		ensdao.load();
		profs = ensdao.getListObt();
		resps = respdao.getListObt();

		tabProfs = new String[profs.size() + 1];
		tabProfs[0] = "";

		int i = 1;
		for (Agent prof : profs) {
			tabProfs[i] = prof.decrisToi();
			i++;
		}

		pan11 = new JPanel();
		pan2 = new JPanel();
		pan3 = new JPanel();
		pan4 = new JPanel();
		pan5 = new JPanel();

		container1 = new JPanel();
		// container2 = new MartDynamicPanel();
		container2 = new JPanel();
		container1.setPreferredSize(dimPanes2);
		container2.setPreferredSize(dimPanes3);

		container1.setLayout(new BorderLayout());

		String[] titres = { "Le Directeur", "La Directrice", "Le Proviseur", "Le Censeur" };

		// pour les dirigeant Primaire
		lbNomPrimaire = new JLabel("Nom");
		cbNomPrim = new JComboBox(tabProfs);
		lbFonctionPrim = new JLabel("Fonction");
		cbFonctionPrim = new JComboBox(titres);
		pan2.setLayout(new GridLayout(2, 2));
		pan2.setBorder(BorderFactory.createTitledBorder("Pour le Primaire"));
		pan2.add(lbNomPrimaire);
		pan2.add(cbNomPrim);
		pan2.add(lbFonctionPrim);
		pan2.add(cbFonctionPrim);

		// pour les dirigeant Collège
		lbNomCol = new JLabel("Nom");
		cbNomCol = new JComboBox(tabProfs);
		lbFonctionCol = new JLabel("Fonction");
		cbFonctionCol = new JComboBox(titres);

		pan3.setLayout(new GridLayout(2, 2));
		pan3.setBorder(BorderFactory.createTitledBorder("Pour le Collège"));
		pan3.add(lbNomCol);
		pan3.add(cbNomCol);
		pan3.add(lbFonctionCol);
		pan3.add(cbFonctionCol);

		// pour les dirigeant du leg
		lbNomLeg = new JLabel("Nom");
		cbNomLeg = new JComboBox(tabProfs);
		lbFonctionLeg = new JLabel("Fonction");
		cbFonctionLeg = new JComboBox(titres);

		pan4.setLayout(new GridLayout(2, 2));
		pan4.setBorder(BorderFactory.createTitledBorder("Pour le LEG"));
		pan4.add(lbNomLeg);
		pan4.add(cbNomLeg);
		pan4.add(lbFonctionLeg);
		pan4.add(cbFonctionLeg);

		// pour les dirigeant LET
		lbNomLet = new JLabel("Nom");
		cbNomLet = new JComboBox(tabProfs);
		lbFonctionLet = new JLabel("Fonction");
		cbFonctionLet = new JComboBox(titres);

		pan5.setLayout(new GridLayout(2, 2));
		pan5.setBorder(BorderFactory.createTitledBorder("Pour le LET"));
		pan5.add(lbNomLet);
		pan5.add(cbNomLet);
		pan5.add(lbFonctionLet);
		pan5.add(cbFonctionLet);

		container2.add(pan2);
		container2.add(pan3);
		container2.add(pan4);
		container2.add(pan5);

		// Ajout d'écouteur
		cbNomPrim.addActionListener(this);
		cbNomCol.addActionListener(this);
		cbNomLeg.addActionListener(this);
		cbNomLet.addActionListener(this);

		cbFonctionPrim.addActionListener(this);
		cbFonctionCol.addActionListener(this);
		cbFonctionLeg.addActionListener(this);
		cbFonctionLet.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Component source = (Component) e.getSource();

	}

	@Override
	public void setAnnee(String an) {
		annee = an;
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
		try {
			Agent rsp = new Agent();
			for (Agent ens : profs) {
				if (ens.decrisToi().equals(cbNomPrim.getSelectedItem())) {
					rsp = ens;
				}
			}

			String titre = "directeur_PRIM_" + Constance.getCor(annee);
			dirPrim = new Responsable(titre, rsp.getCodeInfo(), rsp.getSexe());
			fonction = (String) cbFonctionPrim.getSelectedItem();
			dirPrim.setFonction(fonction);

			respdao.update_create(dirPrim);

			rsp = new Agent();
			for (Agent ens : profs) {
				if (ens.decrisToi().equals(cbNomCol.getSelectedItem())) {
					rsp = ens;
				}
			}

			titre = "directeur_COL_" + Constance.getCor(annee);
			dirCol = new Responsable(titre, rsp.getCodeInfo(), rsp.getSexe());
			fonction = (String) cbFonctionCol.getSelectedItem();
			dirCol.setFonction(fonction);

			respdao.update_create(dirCol);

			rsp = new Agent();
			for (Agent ens : profs) {
				if (ens.decrisToi().equals(cbNomLeg.getSelectedItem())) {
					rsp = ens;
				}
			}

			titre = "directeur_LEG_" + Constance.getCor(annee);
			dirLeg = new Responsable(titre, rsp.getCodeInfo(), rsp.getSexe());
			fonction = (String) cbFonctionLeg.getSelectedItem();
			dirLeg.setFonction(fonction);

			respdao.update_create(dirLeg);

			rsp = new Agent();
			for (Agent ens : profs) {
				if (ens.decrisToi().equals(cbNomLet.getSelectedItem())) {
					rsp = ens;
				}
			}

			titre = "directeur_LET_" + Constance.getCor(annee);
			dirLet = new Responsable(titre, rsp.getCodeInfo(), rsp.getSexe());
			fonction = (String) cbFonctionLet.getSelectedItem();
			dirLet.setFonction(fonction);

			respdao.update_create(dirLet);

		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "Veuillez compléter tous les champs");
		}
	}

	@Override
	public void refresh() {
		// initialisation
		try {
			dirPrim = resps.find("directeur_PRIM_" + Constance.getCor(annee));
			Agent ensPrim = profs.find(dirPrim.getCodeInfo());
			cbNomPrim.setSelectedItem(ensPrim.decrisToi());
			cbFonctionPrim.setSelectedItem(dirPrim.getFonction());
		} catch (Exception e) {

		}

		// initialisation
		try {
			dirCol = resps.find("directeur_COL_" + Constance.getCor(annee));
			Agent ensCol = profs.find(dirCol.getCodeInfo());
			cbNomCol.setSelectedItem(ensCol.decrisToi());
			cbFonctionCol.setSelectedItem(dirCol.getFonction());
		} catch (Exception e) {

		}

		// initialisation
		try {
			dirLeg = resps.find("directeur_LEG_" + Constance.getCor(annee));
			Agent ensLeg = profs.find(dirLeg.getCodeInfo());
			cbNomLeg.setSelectedItem(ensLeg.decrisToi());
			cbFonctionLeg.setSelectedItem(dirLeg.getFonction());
		} catch (Exception e) {

		}

		// initialisation
		try {
			dirLet = resps.find("directeur_LET_" + Constance.getCor(annee));
			Agent ensLet = profs.find(dirLet.getCodeInfo());
			cbNomLet.setSelectedItem(ensLet.decrisToi());
			cbFonctionLet.setSelectedItem(dirLet.getFonction());
		} catch (Exception e) {

		}
	}
}
