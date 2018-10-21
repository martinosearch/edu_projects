package rapportBulletin;

import java.io.File;

import javax.swing.JOptionPane;

import agent.Responsable;
import classe.Classe;
import configurationEcole.ConfigEcole;
import connection.DAO;
import function.Constance;
import interfacePerso.Afficher;

public abstract class DocFormat implements Afficher {
    public static int DIR = 0, TITUL = 1, DIR_TITUL = 2, CHEF = 3, PRESI = 4;
    public static int STA_NOTE_COMPO = 0;
    public static int STA_MOY_TRIM = 1;
    public static int STA_MIXTE = 0;
    public static int STA_MASC_FEM = 1;

    public static int BULLSINGLE = 1;
    public static int BULLDOUBLE = 2;

    protected static int nbreColBull;
    public static int pcent, pcentrest, serie, colspan1, colspan2;
    protected String annee;
    protected String classe;
    protected String anneeCor;
    protected String classeCor;
    protected int trimestre;

    protected static File Plogo;
    protected static ConfigEcole conf;
    protected String typeEns;
    protected DAO respdao;
    protected DAO setdao;
    protected boolean titulaireManque = false;
    protected int effectif;
    protected int model;
    protected int typeRapport;
    protected static String html;
    protected static Responsable DIRECTEUR;
    protected static Responsable TITULAIRE;
    protected static Responsable PRESIDENT;

    public DocFormat() {

    }

    public String writeEntete() {
	String str = "<table  class='tabSB' width='100%'>" + "<tr>" + "<td id='ministere' width='50%'>"
		+ Constance.MINISTERE + "</td>" + "<td width='15%'>          </td>"

		+ "<td width='35%'>" + "<div id='ministere'>REPUBLIQUE TOGOLAISE</div>"
		+ "<div id='adresse_bull1'>Travail- Liberté- Patrie</div>" + "</td>" + "</tr>" + "</table>";// fin table
													    // des
	// en-têtes
	return str;
    }

    public String writeRefEts() {
	String str = "<table width='100%'>"// table ref ets
		+ "<tr>" + "<td class='tdSB' width='20%'><div id='image'><img src='" + Plogo.toURI() + "'/></div></td>"
		+ "<td class='tdSB' width='55%'>" + "<div id='titre' align='center'>"
		+ (Constance.INITIALE).replaceAll("[\\s]", "&#160;") + "&#160;"
		+ (Constance.NOM).replaceAll("[\\s]", "&#160;") + "</div>" + "<div id='adresse2' align='center'>"
		+ Constance.QUARTIER + "</div>" + "<div id='adresse2' align='center'>" + Constance.BP + " / Tel: "
		+ Constance.TEL + "</div></td><td width='25%'><div>Classe: " + classe + "</div><div>Effectif: "
		+ effectif + "</div></td></tr></table>";
	return str;
    }

    public abstract String getTabSignature(int dIR_TITUL2, String string);

    public abstract String writeEntete(String str);

    public abstract String getTabNote();

    public String writeCode(String code, String midle, String right) {
	String str = "<div class='tabB'>" + "<table class='tabSB' width='100%'>" + "<tr>"
		+ "<td id='codebar' width='25%'>" + code + "AUTH</td>" + "<td id='code' valign='top' width='8%'></td>"
		+ "<td id='devise' valign='top' width='42%'>" + midle + "</td>"
		+ "<td id='NB' valign='top' width='25%'>" + right + "</td>" + "</tr></table>"
		+ "</div><div id='pub' colspan=3>Imprimé avec le logiciel MartSCO de "
		+ "Martino Corporation (91 75 56 32/ 97 19 20 84)</div>";
	return str;
    }

    public String getSignatureSta() {
	return "<table class='tabSB' width='100%'>" + "<tr>" + "<td class='tdSB' width='60%'><td class='tdSB' >"
		+ Constance.VILLE + " le.........................</td>" + "</tr><tr><td class='tdSB' width='60%'></td>"
		+ "<td class='tdSB' width='40%'>" + "<div><b>Le Chef d'Etablissement</b></div>"
		+ "<div></div><div></div>" + "<div></div></td></tr></table>";
    }

    public void setClasse(Classe cls) {
	typeEns = cls.getTypeEns();
	classe = cls.getIntitule();
	classeCor = cls.getCor();

	effectif = cls.getEffectif();
	System.out.println("la classe reçue est" + cls.getIntitule() + " le TypeEns:" + typeEns);
    }

    public int getPcent() {
	return pcent;
    }

    /*
     * public static void main(String[] args){ }
     */
    public int getnbreCol() {

	return nbreColBull;
    }

    public void TitulaireManque() {
	if (titulaireManque == false) {
	    JOptionPane.showMessageDialog(null, "Veuillez définir les infos du titulaire de classe");
	    titulaireManque = true;
	}
    }

    public void DirManque() {
	JOptionPane.showMessageDialog(null, "Veuillez définir les infos les signataires de ce document");
    }

    public void setModel(int mod) {
	model = mod;
    }

    public void setTypeRapport(int type) {
	typeRapport = type;
    }
}
