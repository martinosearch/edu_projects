package configurationClasse;

import function.Constance;
import graphicsModel.MartList;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import agent.Agent;
import agent.Responsable;
import annee.Decoupage;
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

public class ConfAutresInfosClasse extends AbstractConfigPanel {
	private MartList<Agent> profs;
	private MartList<Responsable> resps;
	private String[] tabProfs;
	DAO ensdao, respdao, setdao;

	private JPanel container;
	private JPanel panTitulaire;
	private JLabel lbTitulaire;
	private JLabel lbTitulaireFunction;
	private JComboBox cbTitulaire;
	private Agent titulaire;
	private JPanel panDecoupage;
	private JCheckBox ckTrimestre;
	private JCheckBox ckSemestre;
	private String classe;
	private String annee;
	private JComboBox cbTitulaireFunction;
	private int trimestre;
	private Decoupage dec;

	public ConfAutresInfosClasse(String tit) {
		super(tit);
		this.setLayout(new BorderLayout());
		ensdao = DAOFactory.getDAO(DAO.AGENT);
		respdao = DAOFactory.getDAO(DAO.RESPONSABLE);
		setdao = DAOFactory.getDAO(DAO.SETTING);

		container = new JPanel();
		container.setLayout(new FlowLayout());
		initComponent();

		this.add(container, BorderLayout.CENTER);
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

		cbTitulaire = new JComboBox(tabProfs);

		cbTitulaireFunction = new JComboBox();
		cbTitulaireFunction.addItem("Le Titulaire");
		cbTitulaireFunction.addItem("Le Professeur Principal");

		// le conteur pour le titulaire
		panTitulaire = new JPanel();
		panTitulaire.setBorder(BorderFactory
				.createTitledBorder("Le Titulaire de classe"));
		panTitulaire.setLayout(new GridLayout(2, 2, 10, 10));

		lbTitulaire = new JLabel("Nom du Titulaire:");
		lbTitulaireFunction = new JLabel("Fonction:");

		panTitulaire.add(lbTitulaire);
		panTitulaire.add(cbTitulaire);
		panTitulaire.add(lbTitulaireFunction);
		panTitulaire.add(cbTitulaireFunction);

		panTitulaire.setPreferredSize(new Dimension(260, 80));

		// pour le découpage
		// le conteur pour le titulaire
		panDecoupage = new JPanel();
		panDecoupage.setBorder(BorderFactory
				.createTitledBorder("Le Découpage de l'année"));
		panDecoupage.setLayout(new GridLayout());

		lbTitulaire = new JLabel("Nom du Titulaire:");
		ckTrimestre = new JCheckBox("Trimestre");
		ckSemestre = new JCheckBox("Semestre");

		ButtonGroup bg = new ButtonGroup();
		bg.add(ckSemestre);
		bg.add(ckTrimestre);

		ckTrimestre.setSelected(true);

		panDecoupage.add(ckTrimestre);
		panDecoupage.add(ckSemestre);

		container.add(panTitulaire);
		container.add(panDecoupage);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Component source = (Component) e.getSource();

	}

	public Agent getTitulaire() {
		// initialisation
		int index = cbTitulaire.getSelectedIndex() - 1;
		titulaire = profs.get(index);
		return titulaire;
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
		// on insert le titulaire de classe
		String titre = "titulaire_" + Constance.getCor(classe) + "_"
				+ Constance.getCor(annee);

		try {
			String matriresp = getTitulaire().getCodeInfo();
			String sexe = getTitulaire().getSexe();
			Responsable resp = new Responsable(titre, matriresp, sexe);
			resp.setFonction(getTitulaireFonction());

			respdao.update_create(resp);

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Le nom du Titulaire n'est "
					+ "pas choisi", "ERREUR!", JOptionPane.ERROR_MESSAGE);
		}

		// on met ajour le découpage
		setdao.load(null, null, 0, annee);
		int typedec = getDecoupage().getTypeDec();
		Setting decoupage = new Setting("decoupage" + classe,
				String.valueOf(typedec));

		System.out.println("decoupage: " + typedec);

		setdao.update_create(decoupage);
	}

	public Decoupage getDecoupage() {
		Decoupage dec = new Decoupage();
		dec.setClasse(classe);

		if (ckTrimestre.isSelected()) {
			dec.setTypeDec(Decoupage.TRIMESTRE);
		}

		if (ckSemestre.isSelected()) {
			dec.setTypeDec(Decoupage.SEMESTRE);
		}

		return dec;
	}

	private String getTitulaireFonction() {
		return (String) cbTitulaireFunction.getSelectedItem();
	}

	@Override
	public void refresh() {
		// initialisation pour Titulaire
		Responsable titTemp = (Responsable) respdao.findObj("titulaire_"
				+ Constance.getCor(classe) + "_" + Constance.getCor(annee));
		try {
			titulaire = profs.find(titTemp.getCodeInfo());

			cbTitulaire.setSelectedItem(titulaire.decrisToi());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// initialisation pour Découpage
		setdao.load("", null, 0, annee);
		Setting decoupage = (Setting) setdao.findObj("decoupage" + classe);

		dec = new Decoupage();
		try {
			dec = new Decoupage(classe, Integer.parseInt((String) decoupage
					.getAttribut()));
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (dec.getTypeDec() == Decoupage.TRIMESTRE) {
			ckTrimestre.setSelected(true);
		}

		if (dec.getTypeDec() == Decoupage.SEMESTRE) {
			ckSemestre.setSelected(true);
		}
	}

	public void setClasse(String cl) {
		classe = cl;
	}

	public void setAnnee(String an) {
		annee = an;
	}

}
