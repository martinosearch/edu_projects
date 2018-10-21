package rapportCompta;

import ecolage.EcolageComputer;
import ecolage.EleveEcolage;
import editeur.MartEditorPane;
import editeur.MartStyle;
import editeur.Preview;
import eleve.Eleve;
import graphicsModel.MartList;
import interfacePerso.MartRangeable;

import java.awt.Color;
import java.awt.print.PageFormat;

import javax.swing.SwingUtilities;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import progress.Progress;
import progress.ProgressFrame;
import rapportBulletin.BullFormat;
import rapportBulletin.DocFormat;
import rapportBulletin.HistoManager;
import abstractObject.AbstractModel;
import agent.Agent;
import connection.DAO;
import connection.DAOFactory;

public abstract class ListeCompteEntreeModel extends AbstractModel {
	protected DAO versecolagedao, elvdao, agentdao;
	protected String htmlBody;
	protected String html = "";
	protected HistoManager histoMng;
	protected Progress progress;
	protected ProgressFrame progressFrame;
	protected int progmax;
	protected MartList<EleveEcolage> listeVersEcolage;
	protected DocFormat bs;
	protected String titre;
	protected Eleve superEleve;
	protected EcolageComputer computer;
	protected Thread treat;
	protected DateTimeFormatter formatter;
	protected MartList<Agent> listeOperateur;

	// constructeur avec paramètre
	public ListeCompteEntreeModel() {
		histoMng = new HistoManager();

		initComponent();
	}

	// creation des composants
	public void initComponent() {
		versecolagedao = DAOFactory.getDAO(DAO.VERSEMENT_ECOLAGE);
		elvdao = DAOFactory.getDAO(DAO.ELEVE);
		agentdao = DAOFactory.getDAO(DAO.AGENT);
		elvdao.load();
		agentdao.load();
	}

	public void createListe() {
		// on recupere la liste des élève
		versecolagedao.load(null, null, 0, annee);
		formatter = DateTimeFormat.shortDateTime();
		formatter.withZoneUTC();

		listeVersEcolage = versecolagedao.getListObt();
		listeOperateur = agentdao.getListObt();
		titre = "FICHE DE COMPTE";

		// ********debut de la barre de progression***************
		progress = new Progress();
		progressFrame = new ProgressFrame();

		progress.getLoading(progressFrame, "Chargement des données en cours");
		// ****************fin partielle***********************

		new Thread(new Runnable() {
			public void run() {
				// ***************LA BARRE DE PROGRESSION********************
				progmax = listeVersEcolage.size() + listeOperateur.size() + 1;
				progress.getProgress(progressFrame, 0, progmax);
				progress.setColor(Color.green);
				// ********************FIN*****************************/*****

				bs = new BullFormat(0, annee);

				// ecriture du model
				html = "";
				htmlBody = "";

				// reférence de l'établissement
				htmlBody += bs.writeEntete(titre);

				// fin des en-têtes
				if (write()) {
					html += "<html><head></head><body>" + htmlBody + "</body>";

					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							MartEditorPane editor = new MartEditorPane();
							MartStyle.setPadding(0);
							MartStyle.setRowheight(5);
							editor.setStyle(MartStyle.DOCUMENTS);
							editor.setHtml(html);
							editor.setOrientation(PageFormat.LANDSCAPE);

							// on fait appelle à la visionneuse
							Preview bsh = new Preview(editor);

							// *****POUR LA BARRE DE PORGRESSION***********
							progress.increment();
							// *********************FIN********************

							progressFrame.close();
						}
					});
				}
			}
		}).start();
	}

	@Override
	public void valider(int type) {
		// TODO Auto-generated method stub

	}

	@Override
	public void supprimer(int mode) {
		// TODO Auto-generated method stub

	}

	@Override
	public void executeTab(int type, int mode) {
		// TODO Auto-generated method stub

	}

	@Override
	public MartList<MartRangeable> getRessources() {
		// TODO Auto-generated method stub
		return null;
	}

	public abstract boolean write();

	public abstract String getPeriode();
}
