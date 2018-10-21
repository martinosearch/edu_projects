package abstractObject;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import annee.Annee;
import classe.Classe;
import componentFactory.MartButton;
import connection.DAO;
import examen.Examen;
import graphicsModel.MartDialog;
import graphicsModel.MartFrame;
import graphicsModel.MartList;
import interfacePerso.Observer;

public abstract class AbstractChooser extends MartDialog implements Observer {
    protected JPanel panAnnee, panTrimestre, panButton, panClasse, panTypeNote, panReference, panExamen, panEts;
    protected JCheckBox ckTrimestre1;
    protected JCheckBox ckTrimestre2;
    protected JCheckBox ckTrimestre3;

    protected JComboBox cbAnnee = new JComboBox<String>();
    protected JComboBox cbClasse = new JComboBox<String>();
    protected JComboBox cbMatiere = new JComboBox<String>();
    protected JComboBox cbExamen = new JComboBox<String>(), cbEts = new JComboBox<String>();

    protected MartList<Annee> annees;
    protected MartList<Classe> classes;
    protected MartList<Examen> examens;

    protected int trimestre;
    protected DAO clsdao, andao, matdao, elvdao, elvclsdao, matpdao, notedao;
    protected String annee, classe;
    protected ButtonGroup bgTrimestre;
    protected ActionListener validerListener;

    protected MartButton bValider = new MartButton().getValider();
    protected MartButton bAnnuler = new MartButton().getAnnuler();

    protected JLabel lbAnnee = new JLabel("Année- Scolaire");
    protected JLabel lbClasse = new JLabel("Classe");
    protected JLabel lbMatiere = new JLabel("Matière");
    protected JLabel lbEts = new JLabel("Ets");

    protected boolean isListChoosing = false;
    protected boolean isTrimestreChoosing = true;
    protected JCheckBox ckInterro1;
    protected JCheckBox ckInterro2;
    protected JCheckBox ckDevoir;
    protected JCheckBox ckCompo;
    private boolean isEtsChoosing;

    public AbstractChooser() {
	setLocation(MartFrame.SMALL_FRAME_CHOOSER_LOCATION);
	addWindowListener(this);

	panAnnee = new JPanel();
	panTrimestre = new JPanel();
	panButton = new JPanel();
	panClasse = new JPanel();
	panReference = new JPanel();

	ckTrimestre1 = new JCheckBox("1er trimestre");
	ckTrimestre2 = new JCheckBox("2è trimestre");
	ckTrimestre3 = new JCheckBox("3è trimestre");
	ckTrimestre1.setSelected(true);

	ckInterro1 = new JCheckBox("Intérro1");
	ckInterro2 = new JCheckBox("Intérro2");
	ckDevoir = new JCheckBox("Devoir");
	ckCompo = new JCheckBox("Compo");

	ckInterro1.setSelected(true);
	ckInterro2.setSelected(true);
	ckDevoir.setSelected(true);
	ckCompo.setSelected(true);

	// ajout des checkBox
	panTypeNote = new JPanel();
	panTypeNote.setBorder(BorderFactory.createTitledBorder("Séries de notes"));
	panTypeNote.setLayout(new GridLayout(4, 1));

	panTypeNote.add(ckInterro1);
	panTypeNote.add(ckInterro2);
	panTypeNote.add(ckDevoir);
	panTypeNote.add(ckCompo);

	bgTrimestre = new ButtonGroup();

	bgTrimestre.add(ckTrimestre1);
	bgTrimestre.add(ckTrimestre2);
	bgTrimestre.add(ckTrimestre3);

	bValider.addActionListener(this);
	bAnnuler.addActionListener(this);
    }

    public void control() {
	// initialisation
	if (isTrimestreChoosing == false) {
	    panTrimestre.setBorder(BorderFactory.createEmptyBorder());
	    ckTrimestre1.setVisible(false);
	    ckTrimestre2.setVisible(false);
	    ckTrimestre3.setVisible(false);

	} else {
	    ckTrimestre1.setVisible(true);
	    ckTrimestre2.setVisible(true);
	    ckTrimestre3.setVisible(true);

	    panTrimestre.setBorder(
		    BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "TRIMESTRE", 0, 0));
	    ckTrimestre1.setText("1er trimestre");
	    ckTrimestre2.setText("2è trimestre");
	    ckTrimestre3.setText("3è trimestre");
	    /*
	     * if (dec.getTypeDec() == Decoupage.SEMESTRE) {
	     * panTrimestre.setBorder(BorderFactory.createTitledBorder(
	     * BorderFactory.createLineBorder(Color.GRAY), "SEMESTRE", 0, 0));
	     * ckTrimestre1.setText("1er semestre"); ckTrimestre2.setText("2è semestre");
	     * 
	     * ckTrimestre3.setVisible(false); }
	     */

	    // Cas des listes (pas de nécessité du trimestre)
	    // Demande de l'option pour les photos
	}

	if (isListChoosing == true) {
	    panTrimestre.setBorder(BorderFactory.createEmptyBorder());
	    ckTrimestre1.setText("Photos");
	    ckTrimestre1.setVisible(true);

	    ckTrimestre2.setVisible(false);
	    ckTrimestre3.setVisible(false);

	    bgTrimestre.remove(ckTrimestre1);
	    bgTrimestre.remove(ckTrimestre2);
	    bgTrimestre.remove(ckTrimestre3);

	    ckTrimestre1.setSelected(false);
	}
    }

    public int getTrimestre() {
	if (ckTrimestre1.isSelected()) {
	    trimestre = 1;
	}

	else if (ckTrimestre2.isSelected()) {
	    trimestre = 2;
	}

	else if (ckTrimestre3.isSelected()) {
	    trimestre = 3;
	}

	return trimestre;
    }

    public void setListChoosing(boolean b) {
	isListChoosing = b;
	control();
    }

    public void setTrimestreChoosing(boolean b) {
	isTrimestreChoosing = b;
	control();
    }

    public void setAction(ActionListener action) {
	bValider.removeActionListener(validerListener);
	validerListener = action;
	bValider.addActionListener(validerListener);
    }

    public boolean isEtsChoosing() {
	return isEtsChoosing;
    }

    public void setEtsChoosing(boolean b) {
	this.isEtsChoosing = b;
	try {
	    cbEts.setVisible(b);
	    lbEts.setVisible(b);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public abstract Classe getClasse();

    public abstract String getAnnee();

    public abstract boolean getFirstOption();

}
