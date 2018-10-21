package Import;

import eleve.Eleve;
import eleve.EleveControler;
import eleve.EleveModel;
import graphicsModel.MartList;
import interfacePerso.Observable;
import interfacePerso.Observer;

import java.awt.Color;

import progress.Progress;
import progress.ProgressFrame;
import abstractObject.AbstractControler;
import abstractObject.AbstractModel;
import connection.DAO;
import connection.DAOFactory;

public class ImportManager extends ProgressFrame implements Observable {
	private int size, progmax, count2;
	private Progress progress;
	protected DAO elvdao;
	protected int vagueCounter;
	private MartList<Eleve> receivedVague;
	private AbstractControler controler;
	private String annee;
	private AbstractModel model;
	private MartList<Eleve> listeImport;
	private MartList<Observer> listeObserver = new MartList<Observer>();

	public ImportManager() {
		model = new EleveModel();
		controler = new EleveControler(model);
	}

	public void importer(MartList<Eleve> liste) {
		listeImport = liste;
		int nbre = listeImport.size();
		size = listeImport.size();

		// ***************LA BARRE DE PROGRESSION********************
		progmax = nbre;

		progress = new Progress();
		progress.getProgress(this, 0, progmax);
		progress.setColor(Color.green);
		// ********************FIN*****************************

		charger();
	}

	public void charger() {
		System.out.println("La différence" + (size - count2));

		if ((size - count2 - 1) > 0) {
			thImport = new Thread(new Runnable() {
				@Override
				public void run() {
					MartList<Eleve> listeVague = new MartList<Eleve>();
					elvdao = DAOFactory.getDAO(DAO.ELEVE);
					elvdao.load();
					MartList<Eleve> eleves = elvdao.getListObt();

					int max = count2 + 10;

					for (int i = count2; i < max; i++) {

						if (i < listeImport.size()) {// l'incrémentation ne doit
							// pas dépasser
							// la taille de la liste

							// vérification de correspondance();
							Eleve eleve = listeImport.get(i);

							for (Eleve elv : eleves) {
								if (elv.getPrimaryKey().equals(
										eleve.getPrimaryKey())) {
									Eleve eleveTemp = elv;
									eleveTemp.setClasse(eleve.getClasse());
									eleve = eleveTemp;

									break;
								}
							}

							listeVague.add(eleve);
						}
					}

					count2 = max;

					vagueCounter++;

					progress.setText("Veuillez patienter, Lancement de Vague "
							+ vagueCounter);

					lancerVague(listeVague);

					// remise à zéro de la liste
					listeVague = new MartList<Eleve>();

					System.out
							.println("Realise======================###########"
									+ "######## \n ############################"
									+ "#######\n############################"
									+ "##=======================>>");
				}
			});

			thImport.start();
		} else {
			try {
				notifyObserver();
				thImport.stop();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	private synchronized void lancerVague(MartList<Eleve> liste) {

		try {
			receivedVague = liste;

			for (Eleve elv : receivedVague) {
				controler.setData(elv);
				controler.setAnnee(getAnnee());
				controler.setClasse(elv.getClasse());

				model.setData(elv);
				model.setClasse(elv.getClasse());
				model.setAnnee(getAnnee());

				controler.valider();

				// *****POUR LA BARRE DE
				// PORGRESSION***********
				progress.increment();
				// *********************FIN**

				Thread.sleep(200);
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

	@Override
	public void addObserver(Observer obs) {
		listeObserver.add(obs);
	}

	@Override
	public void removeObserver() {
		listeObserver = new MartList<Observer>();
	}

	@Override
	public void notifyObserver() {
		for (Observer obs : listeObserver) {
			obs.update();
		}
	}
}
