package statistique;

import interfacePerso.MartRangeable;
import interfacePerso.Observer;

import java.awt.print.PageFormat;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.SwingUtilities;

import matiere.MatiereProg;
import note.InfoClasse;
import note.InfoNote;
import note.Moyenne;
import note.NoteViewer;
import note.NoteViewerBull;

import org.joda.time.DateTime;

import progress.Progress;
import progress.ProgressFrame;
import rapportBulletin.BullFormat;
import rapportBulletin.DocFormat;
import rapportBulletin.Histo;
import rapportBulletin.HistoManager;
import abstractObject.AbstractModel;
import annee.Decoupage;
import classe.Classe;
import classe.Niveau;
import configurationClasse.ConfigClasse;
import configurationEcole.ConfigEcole;
import connection.DAO;
import connection.DAOFactory;
import editeur.MartEditorPane;
import editeur.MartStyle;
import editeur.Preview;
import eleve.EleveClasse;
import function.Constance;
import function.StaData;
import function.Statistician;
import function.Statistician.MoySta;
import graphicsModel.MartList;

public class StaMoyTrimModel extends AbstractModel {
	String htmlBody;
	String html;
	int nbreSection = 0, nbreClasses = 0, progmax = 0, maxtrim, effectif = 0;
	DAO elvdao, elvclsdao, notedao, matpdao, matdao, rapmoydao, profdao,
			clsdao, niveaudao;
	private MartEditorPane editor;

	private NoteViewer nview;
	private InfoClasse infocls;
	private InfoNote infonote;
	private ArrayList<EleveClasse> Eleves;
	private ArrayList<MatiereProg> Matieres;
	private MartList<Niveau> Niveaux;
	private DocFormat bs;
	private JTable tabClasses;
	private Statistician staman;

	DecimalFormat formatter = new DecimalFormat("00.00");
	private HistoManager histoMng;

	StaMoyTrim writer;
	private ConfigEcole conf;
	private Progress progress;
	private ProgressFrame pFrame;
	private Decoupage dec;
	private ConfigClasse confClasse;

	public StaMoyTrimModel() {
		elvdao = DAOFactory.getDAO(DAO.ELEVE);
		elvclsdao = DAOFactory.getDAO(DAO.ELEVE_CLASSE);
		notedao = DAOFactory.getDAO(DAO.NOTE);
		matpdao = DAOFactory.getDAO(DAO.MATIERE_PROG);
		matdao = DAOFactory.getDAO(DAO.MATIERE);
		rapmoydao = DAOFactory.getDAO(DAO.RAPMOY);
		profdao = DAOFactory.getDAO(DAO.AGENT);
		clsdao = DAOFactory.getDAO(DAO.CLASSE);
		niveaudao = DAOFactory.getDAO(DAO.NIVEAU);

		// les chargements ne dépendant d'aucun paramètre
		elvdao.load();
		matdao.load();
		profdao.load();
		clsdao.load();
		niveaudao.load();

		histoMng = new HistoManager();
	}

	public void valider(int tpe) {
		this.type = tpe;

		// paramètres généraux
		for (Observer obs : listObserver) {
			if (obs instanceof StaMoyTrim) {
				writer = (StaMoyTrim) obs;
			}
		}

		// ********debut de la barre de progression***************
		pFrame = new ProgressFrame();
		progress = new Progress();
		System.out.println(writer.getTitle());
		progress.getLoading(pFrame, "Chargement des données");

		conf = new ConfigEcole(trimestre, annee);
		nview = new NoteViewerBull();
		staman = new Statistician();// instantier le statisticien

		if (type == 1) {
			// creation des statistiques
			new Thread(new Runnable() {
				public void run() {
					createSta(conf.bullConfig.getTypeStaMoyTrim());
				}
			}).start();
		}
	}

	public void createSta(int type) {
		html = "";
		htmlBody = "";

		tabClasses = writer.getTableOfChoise();
		nbreClasses = tabClasses.getRowCount();

		classe = (String) tabClasses.getValueAt(0, 1);
		Classe classe1 = (Classe) clsdao.findObj(classe);
		String niveau1 = classe1.getNiveau();

		System.out
				.println("gggggggggggggggggggggggggggggggggggggg========================================>>"
						+ niveau1 + annee + classe1);
		confClasse = new ConfigClasse(classe1, annee);
		dec = confClasse.persoClasse.getDecoupage();
		dec.setSection(trimestre);

		bs = new BullFormat(trimestre, annee);// ceci pour initialiser le bs

		// ****************on crée en même temps les pages*******************
		editor = new MartEditorPane(MartEditorPane.MATHS_DOCUMENT);
		MartStyle.setPadding(0);
		MartStyle.setRowheight(Constance.STA_MOY_TRIM_ROWH);
		editor.setStyle(MartStyle.DOCUMENTS);
		// ****************************************************************

		htmlBody += "<div class='sautPaysage'>"// saut page
				+ "<div id='sautligne'></div>"
				+ "<div class='cadre'>" // le cadre

				// la table des en-têtes
				+ "<table class='tabSB' width='100%'>"
				+ "<tr>"
				+ "<td width='40%'>" + "<div>" + Constance.INITIALE
				+ "&#160;"
				+ Constance.NOM + "</div>" + "<div>"
				+ Constance.BP
				+ "&#160;/Tel:" + Constance.TEL + "</div>" + "</td>"

				+ "<td width='30%'></td>"

				+ "<td width='30%'>Année Scolaire: " + annee + "</td></tr>"

				+ "<tr>"
				+ "<td class='tdb' align='center' colspan='3'>"
				+ "<b>STATISTIQUES DES RESULTATS DU "
				+ (dec.toString()).toUpperCase() + "</b></td>"
				+ "</tr>"
				+ "</table>";// fin table des en-têtes

		// Le titre du tableau
		htmlBody += "<table class='tabStaMoyTrim' width='100%'>"
				+ "<tr>"
				+ "<td class='tdB' size='4%' height='60'>Classes</td>"
				+ "<td class='tdSBL' size='4%'>Sexe</td>"
				+ "<td class='tdSBL' size='4%'>Inscrits</td>"
				+ "<td class='tdSBL' size='4%'>Présents</td>"
				+ "<td class='tdSBL' size='9%'><latex>$16\\leqslant Moy<18$</latex></td>"
				+ "<td class='tdSBL' size='9%'><latex>$14\\leqslant Moy<16$</latex></td>"
				+ "<td class='tdSBL' size='9%'><latex>$12\\leqslant Moy<14$</latex></td>"
				+ "<td class='tdSBL' size='9%'><latex>$10\\leqslant Moy<12$</latex></td>"
				+ "<td class='tdSBL' size='9%'><latex>$8\\leqslant Moy<10$</latex></td>"
				+ "<td class='tdSBL' size='9%'><latex>$6\\leqslant Moy<8$</latex></td>"
				+ "<td class='tdSBL' size='9%'><latex>$4\\leqslant Moy<6$</latex></td>"
				+ "<td class='tdSBL' size='9%'><latex>$Moy\\geqslant 10$</latex></td>"
				+ "<td class='tdSBL' size='4%'>Pourc.</td>"
				+ "<td class='tdSBL' size='4%'>Plus forte moy.</td>"
				+ "<td class='tdSBL' size='4%'>Plus faible moy.</td>"

				+ "</tr>";

		Niveaux = new MartList<Niveau>();

		// créer la liste des niveaux
		for (int i = 0; i < nbreClasses; i++) {// POUR PARCOURRIR LES CLASSES
			classe = (String) tabClasses.getValueAt(i, 1);
			classe1 = (Classe) clsdao.findObj(classe);
			niveau1 = classe1.getNiveau();

			bs.setClasse(classe1);// pour le Bullsets

			Niveau niveau = (Niveau) niveaudao.findObj(niveau1);
			Niveaux.smartAdd(niveau);

		}// fin de création de la listes des niveau

		/* la barre de progression définitive */
		progmax = nbreClasses * Niveaux.size() + 1;
		progress.getProgress(pFrame, 0, progmax);

		for (Niveau niv : Niveaux) {
			for (int i = 0; i < nbreClasses; i++) {
				String classe = (String) tabClasses.getValueAt(i, 1);
				Classe cls1 = (Classe) clsdao.findObj(classe);
				classe = cls1.getIntitule();

				// si la classe est dans le niveau actuel
				if (cls1.getNiveau().equals(niv.getIntitule())) {
					Eleves = new ArrayList<EleveClasse>();

					elvclsdao.load(new String(), classe, trimestre, annee);

					Eleves = elvclsdao.getListObt();

					int nbreElv = Eleves.size();

					nview.load(classe, trimestre, annee);
					infocls = nview.getInfoClasse();
					effectif = infocls.getEff();
					staman.addClasse(cls1.getNiveau());

					// Ajout des moyennes générales de élève du niveau considéré
					for (EleveClasse elv : Eleves) {
						Moyenne moyG = nview.getMoyenne(elv);
						double moytrim = moyG.getMoyGen();

						StaData data = new StaData(cls1.getNiveau(), moytrim,
								elv.getSexe(), moyG.hasCompose());

						staman.setDataMoy(data);
					}

				}// fin si la classe est dans le niveau

				// *****POUR LA BARRE DE PORGRESSION***********
				progress.increment();
				// *********************FIN********************

			}// fin for classe
		}// for Niveau

		// *******************on commence par éditer**************************

		if (type == 1) {
			ArrayList<MoySta> stamoy = staman.getResultMoy();
			// String[] listNiveauCollege = { "6ème", "5ème", "4ème", "3ème" };

			for (Niveau niv : Niveaux) {
				String str = niv.getIntitule();
				// On choisi la statistique générale correspondant au niveau
				MoySta staG = null;
				for (MoySta moyst : stamoy) {
					System.out.println("*******************testé"
							+ moyst.getIntitule() + " Au lieu de: " + str);

					if ((moyst.getIntitule()).equals(str)) {
						staG = moyst;

						htmlBody += // ligne garçon
						"<tr>" + "<td class='tdB' rowspan='3'>"
								+ str
								+ "</td>"
								+ "<td class='tdBInf'>G</td>"
								+ "<td class='tdBInf'>"
								+ staG.getInscritg()
								+ "</td>"
								+ "<td class='tdBInf'>"
								+ staG.getPresentg()
								+ "</td>"
								+ "<td class='tdBInf'>"
								+ staG.getInf18g()
								+ "</td>"
								+ "<td class='tdBInf'>"
								+ staG.getInf16g()
								+ "</td>"
								+ "<td class='tdBInf'>"
								+ staG.getInf14g()
								+ "</td>"
								+ "<td class='tdBInf'>"
								+ staG.getInf12g()
								+ "</td>"
								+ "<td class='tdBInf'>"
								+ staG.getInf10g()
								+ "</td>"
								+ "<td class='tdBInf'>"
								+ staG.getInf8g()
								+ "</td>"
								+ "<td class='tdBInf'>"
								+ staG.getInf6g()
								+ "</td>"
								+ "<td class='tdBInf'>"
								+ staG.getSup10g()
								+ "</td>"
								+ "<td class='tdBInf'>"
								+ formatter.format(staG.getPerSup10g())
								+ "</td>"
								// moyenne extrêmes sans distinction de sexe
								+ "<td class='tdBInf' rowspan='3'>"
								+ staG.getHNote()
								+ "</td>"
								+ "<td class='tdBInf'  rowspan='3'>"
								+ staG.getLNote()
								+ "</td>"
								+ "</tr>"

								// ligne fille
								+ "<tr>"
								+ "<td class='tdBInf'>F</td>"
								+ "<td class='tdBInf'>"
								+ staG.getInscritf()
								+ "</td>"
								+ "<td class='tdBInf'>"
								+ staG.getPresentf()
								+ "</td>"
								+ "<td class='tdBInf'>"
								+ staG.getInf18f()
								+ "</td>"
								+ "<td class='tdBInf'>"
								+ staG.getInf16f()
								+ "</td>"
								+ "<td class='tdBInf'>"
								+ staG.getInf14f()
								+ "</td>"
								+ "<td class='tdBInf'>"
								+ staG.getInf12f()
								+ "</td>"
								+ "<td class='tdBInf'>"
								+ staG.getInf10f()
								+ "</td>"
								+ "<td class='tdBInf'>"
								+ staG.getInf8f()
								+ "</td>"
								+ "<td class='tdBInf'>"
								+ staG.getInf6f()
								+ "</td>"
								+ "<td class='tdBInf'>"
								+ staG.getSup10f()
								+ "</td>"
								+ "<td class='tdBInf'>"
								+ formatter.format(staG.getPerSup10f())
								+ "</td>"
								+ "</tr>"

								// ligne total
								+ "<tr>" + "<td class='tdBInfC'>T</td>"
								+ "<td class='tdBInfC'>" + staG.getInscrit()
								+ "</td>" + "<td class='tdBInfC'>"
								+ staG.getPresent() + "</td>"
								+ "<td class='tdBInfC'>" + staG.getInf18()
								+ "</td>" + "<td class='tdBInfC'>"
								+ staG.getInf16() + "</td>"
								+ "<td class='tdBInfC'>" + staG.getInf14()
								+ "</td>" + "<td class='tdBInfC'>"
								+ staG.getInf12() + "</td>"
								+ "<td class='tdBInfC'>" + staG.getInf10()
								+ "</td>" + "<td class='tdBInfC'>"
								+ staG.getInf8() + "</td>"
								+ "<td class='tdBInfC'>" + staG.getInf6()
								+ "</td>" + "<td class='tdBInfC'>"
								+ staG.getSup10() + "</td>"
								+ "<td class='tdBInfC'>"
								+ formatter.format(staG.getPerSup10())
								+ "</td>" + "</tr>";
					}
				}
			}

			// ligne des grands totaux
			htmlBody += "<tr>" + "<td class='tdSBT' rowspan='3'>G.T</td>"
					+ "<td class='tdBInf'>G</td>" + "<td class='tdBInf'>"
					+ staman.getTInscritg()
					+ "</td>"
					+ "<td class='tdBInf'>"
					+ staman.getTPresentg()
					+ "</td>"
					+ "<td class='tdBInf'>"
					+ staman.getTInf18g()
					+ "</td>"
					+ "<td class='tdBInf'>"
					+ staman.getTInf16g()
					+ "</td>"
					+ "<td class='tdBInf'>"
					+ staman.getTInf14g()
					+ "</td>"
					+ "<td class='tdBInf'>"
					+ staman.getTInf12g()
					+ "</td>"
					+ "<td class='tdBInf'>"
					+ staman.getTInf10g()
					+ "</td>"
					+ "<td class='tdBInf'>"
					+ staman.getTInf8g()
					+ "</td>"
					+ "<td class='tdBInf'>"
					+ staman.getTInf6g()
					+ "</td>"
					+ "<td class='tdBInf'>"
					+ staman.getTSup10g()
					+ "</td>"
					+ "<td class='tdBInf'>"
					+ formatter.format(staman.getPerTSup10g())
					+ "</td>"
					+ "<td class='tdBInf' rowspan='3'></td>"
					+ "<td class='tdBInf'  rowspan='3'></td>" + "</tr>";

			htmlBody += "<tr>" + "<td class='tdBInf'>F</td>"
					+ "<td class='tdBInf'>"
					+ staman.getTInscritf()
					+ "</td>"
					+ "<td class='tdBInf'>"
					+ staman.getTPresentf()
					+ "</td>"
					+ "<td class='tdBInf'>"
					+ staman.getTInf18f()
					+ "</td>"
					+ "<td class='tdBInf'>"
					+ staman.getTInf16f()
					+ "</td>"
					+ "<td class='tdBInf'>"
					+ staman.getTInf14f()
					+ "</td>"
					+ "<td class='tdBInf'>"
					+ staman.getTInf12f()
					+ "</td>"
					+ "<td class='tdBInf'>"
					+ staman.getTInf10f()
					+ "</td>"
					+ "<td class='tdBInf'>"
					+ staman.getTInf8f()
					+ "</td>"
					+ "<td class='tdBInf'>"
					+ staman.getTInf6f()
					+ "</td>"
					+ "<td class='tdBInf'>"
					+ staman.getTSup10f()
					+ "</td>"
					+ "<td class='tdBInf'>"
					+ formatter.format(staman.getPerTSup10g())
					+ "</td>"
					+ "</tr>";

			htmlBody += "<tr>" + "<td class='tdBInfC'>T</td>"
					+ "<td class='tdBInfC'>"
					+ staman.getTInscrit()
					+ "</td>"
					+ "<td class='tdBInfC'>"
					+ staman.getTPresent()
					+ "</td>"
					+ "<td class='tdBInfC'>"
					+ staman.getTInf18()
					+ "</td>"
					+ "<td class='tdBInfC'>"
					+ staman.getTInf16()
					+ "</td>"
					+ "<td class='tdBInfC'>"
					+ staman.getTInf14()
					+ "</td>"
					+ "<td class='tdBInfC'>"
					+ staman.getTInf12()
					+ "</td>"
					+ "<td class='tdBInfC'>"
					+ staman.getTInf10()
					+ "</td>"
					+ "<td class='tdBInfC'>"
					+ staman.getTInf8()
					+ "</td>"
					+ "<td class='tdBInfC'>"
					+ staman.getTInf6()
					+ "</td>"
					+ "<td class='tdBInfC'>"
					+ staman.getTSup10()
					+ "</td>"
					+ "<td class='tdBInfC'>"
					+ formatter.format(staman.getPerTSup10())
					+ "</td>"
					+ "</tr>";
		}

		htmlBody += "</table>";// fin table sta

		// table des signature
		bs.load();
		htmlBody += bs.getTabSignature(DocFormat.DIR, "tabSB");

		htmlBody += "</div>" // fin cadre
				+ "</div>"// saut page
				+ "<div id='sautligne'></div>"; // le saut de ligne

		html += "<html>" + "<head>" + "<meta charset='utf-8' />" + "</head>"
				+ "<body><div id='rapportsta'>" + htmlBody + "</div>"
				+ "</body></html>";

		System.out.println(html);

		// *****POUR LA BARRE DE PORGRESSION***********
		progress.increment();
		// *********************FIN********************

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				editor.setLatexSize(8);
				editor.setHtml(html);
				// on fait appelle à l'editeur
				editor.revalidate();
				editor.setOrientation(PageFormat.LANDSCAPE);
				Preview bsh = new Preview(editor);
			}
		});

		pFrame.close();

		new Thread(new Runnable() {
			public void run() {
				// Pour l'archive********************************
				String title = "Statistiques_moy_trim/ "
						+ dec.toString() + "/ " + annee;

				Histo his = new Histo(title, html, new DateTime());
				his.setOrientation(PageFormat.LANDSCAPE);
				histoMng.save(his);
				// fin archive**********************************
			}
		}).start();
	}

	public int getNbreBull() {
		return effectif;
	}

	public static void main(String[] args) {
		StaMoyTrimModel sm = new StaMoyTrimModel();
	}

	@Override
	public MartList<MartRangeable> getRessources() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void supprimer(int mode) {
		// TODO Auto-generated method stub

	}

	@Override
	public void executeTab(int type, int mode) {
		// TODO Auto-generated method stub

	}
}
