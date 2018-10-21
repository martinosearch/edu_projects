package configurationEcole;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import configurationAppli.AbstractConfigPanel;
import configurationAppli.Setting;
import connection.DAO;
import connection.DAOFactory;
import function.Constance;
import graphicsModel.MartCheckBox;
import graphicsModel.MartList;

public class ConfBulletin extends AbstractConfigPanel {

    private MartList<MartCheckBox> checks;
    private MartCheckBox bullbloc;

    private MartCheckBox num;
    private MartCheckBox mat;
    private MartCheckBox ntCls;
    private MartCheckBox moycl;
    private MartCheckBox compo;
    private MartCheckBox moygen;
    private MartCheckBox ptobt;
    private MartCheckBox prof;
    private MartCheckBox sign;
    private MartCheckBox pLogo;
    private MartCheckBox gLogo;
    private MartCheckBox filigranne;
    private MartCheckBox photoelv;
    private MartCheckBox titsign;
    private MartCheckBox dirsign;
    private MartCheckBox titnom;
    private MartCheckBox dirnom;
    private DAO<Setting> setdao;
    private String annee;
    private int trimeste;
    private MartCheckBox decisionAuto;

    public ConfBulletin(String tit) {
	super(tit);
	this.setLayout(new FlowLayout());

	setdao = DAOFactory.getDAO(DAO.SETTING);

	// Listes des cases à cocher
	checks = new MartList<>();

	// Sections
	JPanel panSection = new JPanel();
	panSection.setPreferredSize(dimPanes);
	panSection.setBorder(
		BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Sections", 0, 0));

	bullbloc = new MartCheckBox("Bulletin en bloc", "bullBloc");

	panSection.setLayout(new GridLayout(1, 1));
	panSection.add(bullbloc);

	// Séries de notes
	JPanel panSerie = new JPanel();
	panSerie.setPreferredSize(dimPanes);

	panSerie.setBorder(
		BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Série de notes", 0, 0));
	num = new MartCheckBox("N°", "numOrdre");
	mat = new MartCheckBox("Matière", "matiere");
	ntCls = new MartCheckBox("Note de Classe", "noteClasse");
	moycl = new MartCheckBox("Moy. de Classe", "moyClasse");
	compo = new MartCheckBox("Note de Compo", "noteCompo");
	moygen = new MartCheckBox("Moyenne Générale", "moyGen");
	ptobt = new MartCheckBox("Points Obtenus", "ptObtenus");
	prof = new MartCheckBox("Professeur", "prof");
	sign = new MartCheckBox("Signature", "signature");

	panSerie.setLayout(new GridLayout(4, 3));
	panSerie.add(num);
	panSerie.add(compo);
	panSerie.add(prof);
	panSerie.add(mat);
	panSerie.add(moygen);
	panSerie.add(sign);
	panSerie.add(ntCls);
	panSerie.add(moycl);
	panSerie.add(ptobt);

	// images
	JPanel panImage = new JPanel();
	panImage.setPreferredSize(dimPanes);
	panImage.setBorder(
		BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Série de notes", 0, 0));

	pLogo = new MartCheckBox("Logo en-tête", "logoEntete");
	gLogo = new MartCheckBox("logo fond", "logoFond");
	filigranne = new MartCheckBox("Filigranne", "filigrane");
	photoelv = new MartCheckBox("Photo de l'élèves", "photoEleve");

	panImage.setLayout(new GridLayout(2, 2));
	panImage.add(pLogo);
	panImage.add(gLogo);
	panImage.add(filigranne);
	panImage.add(photoelv);

	// signature
	JPanel panSign = new JPanel();
	panSign.setPreferredSize(dimPanes);
	panSign.setBorder(
		BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Série de notes", 0, 0));
	titsign = new MartCheckBox("Signature du Titulaire", "signTit");
	dirsign = new MartCheckBox("Signature du Chef", "signChef");
	titnom = new MartCheckBox("Nom du Titulaire", "nomTit");
	dirnom = new MartCheckBox("Nom du chef", "nomChef");

	panSign.setLayout(new GridLayout(2, 2));
	panSign.add(titsign);
	panSign.add(dirsign);
	panSign.add(titnom);
	panSign.add(dirnom);

	// Sections
	JPanel panAppr = new JPanel();
	panAppr.setPreferredSize(dimPanes);
	panAppr.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE),
		"Décision en fin d'annee- scolaire", 0, 0));

	decisionAuto = new MartCheckBox("Décision automatique", "decisionAuto");

	panAppr.setLayout(new GridLayout(1, 1));
	panAppr.add(decisionAuto);

	this.add(panSection);
	this.add(panSerie);
	this.add(panImage);
	this.add(panSign);
	this.add(panAppr);
    }

    @Override
    public MartList<Setting> getSettings() {
	checks.add(pLogo);
	checks.add(gLogo);
	checks.add(filigranne);
	checks.add(photoelv);
	checks.add(titsign);
	checks.add(dirsign);
	checks.add(titnom);
	checks.add(dirnom);
	checks.add(bullbloc);
	checks.add(num);
	checks.add(mat);
	checks.add(ntCls);
	checks.add(moycl);
	checks.add(compo);
	checks.add(moygen);
	checks.add(ptobt);
	checks.add(prof);
	checks.add(sign);
	checks.add(decisionAuto);

	sets = new MartList<>();
	for (MartCheckBox ck : checks) {
	    boolean choise = ck.isSelected();
	    Setting set = new Setting(ck.getId(), choise);
	    sets.add(set);
	}
	return sets;
    }

    @Override
    public Object find(String set) {
	getSettings();
	Object result = sets.find(set).getAttribut();
	return result;
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
		set = setdao.findObj(intitule);
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
	setdao.load("", "", trimeste, annee);
	String intitule = "";
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

    @Override
    public void setAnnee(String an) {
	annee = an;
    }
}
