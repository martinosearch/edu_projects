package Import;

import eleve.Eleve;
import function.Fichier;
import function.FiltreFichier;
import graphicsModel.MartList;

import java.awt.Color;

import javax.swing.JFileChooser;

import progress.Progress;
import progress.ProgressFrame;
import abstractObject.AbstractControler;
import abstractObject.AbstractModel;
import candidat.CdtControler;
import candidat.CdtModel;
import connection.DAO;
import connection.DAOFactory;

public class ImportExamManager extends ProgressFrame {
	private MartList<Eleve> listeEleves;
	private int size, progmax, count2;
	private Progress progress;
	protected DAO cdtdao;
	protected int vagueCounter;
	private MartList<Eleve> receivedVague;
	private AbstractControler controler;
	private String annee;
	private AbstractModel model;
	private String etablissement;
	private String examen;

	public ImportExamManager() {
		model = new CdtModel();
		controler = new CdtControler(model);
	}

	public void importer() {
		Fichier fichierListe = null;
		JFileChooser fChooser = new JFileChooser();
		fChooser.setFileFilter(new FiltreFichier("fichier d'importation_",
				"csv"));

		int selection = fChooser.showOpenDialog(null);

		if (selection == JFileChooser.APPROVE_OPTION) {
			fichierListe = new Fichier(fChooser.getSelectedFile()
					.getAbsolutePath());

			listeEleves = (MartList<Eleve>) fichierListe.getListeEleves();

			int nbre = listeEleves.size();
			size = listeEleves.size();
			// ***************LA BARRE DE
			// PROGRESSION********************
			progmax = nbre;

			progress = new Progress();
			progress.getProgress(this, 0, progmax);
			progress.setColor(Color.green);
			// ********************FIN*****************************/*****
			this.setVisible(true);

			charger();
		}
	}

	public synchronized void charger() {
		System.out
				.println("La diffffffffffffffffffffffffffffffffffffffffffffff"
						+ (size - count2));
		if ((size - count2 - 1) > 0) {
			thImport = new Thread(new Runnable() {

				@Override
				public void run() {
					MartList<Eleve> listeVague = new MartList<Eleve>();
					int count = 0;
					cdtdao = DAOFactory.getDAO(DAO.ELEVE);
					cdtdao.load();

					for (int i = count2; i < (count2 + 50); i++) {
						count++;

						if (i < listeEleves.size()) {// l'incrémentation ne doit
														// pas dépasser
														// la taille de la liste

							// vérification de correspondance();
							// Attribution de l'ets
							Eleve eleve = listeEleves.get(i);
							eleve.setEts(etablissement);

							listeVague.add(eleve);
						}
					}

					vagueCounter++;

					progress.setText("Veuillez patienter, Lancement de Vague "
							+ vagueCounter);

					vaguer(listeVague);

					// remise à zéro de la liste
					listeVague = new MartList<Eleve>();

					System.out
							.println("Realise======================###########"
									+ "######## \n ############################"
									+ "#######################\n#############"
									+ "############################\n########"
									+ "#####################################"
									+ "#####\n############################"
									+ "################\n#################"
									+ "####################################"
									+ "#######\n############################"
									+ "##=======================>>");

				}
			});

			thImport.start();
		} else {
			try {
				System.out.println("Suppression du thread");
				thImport.stop();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private synchronized void vaguer(MartList<Eleve> liste) {

		try {
			receivedVague = liste;

			for (Eleve elv : receivedVague) {
				count2++;
				controler.setData(elv);
				controler.setExamen(examen);

				model.setExamen(examen);

				controler.valider();

				// *****POUR LA BARRE DE
				// PORGRESSION***********
				progress.increment();
				progress.setText("Reste à traiter: " + (size - count2));
				// *********************FIN**

				Thread.sleep(100);
			}

			charger();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String getAnnee() {
		return annee;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public void setAnnee(String annee2) {
		annee = annee2;
	}

	public void setExamen(String exam) {
		examen = exam;
	}

	public void setEts(String ets) {
		etablissement = ets;
	}

}
