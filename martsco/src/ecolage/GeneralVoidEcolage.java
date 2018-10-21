package ecolage;

import eleve.EleveClasse;
import function.Constance;
import graphicsModel.MartFrame;
import graphicsModel.MartList;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import progress.Progress;
import progress.ProgressFrame;
import rapportCompta.ListeCompteEntreeModel;
import rapportCompta.ListeCompteEntreeModelGeneral;
import rapportCompta.ListeCompteEntreeModelPeriode;
import rapportCompta.ListeEcolageClasseModel;
import rapportCompta.ListeEntreeScolariteModel;
import rapportCompta.ListeFraisInscriptionClasseModel;
import salaire.ChooserSalaireAgent;
import salaire.DefinirSalaire;
import annee.Annee;
import annee.ChooserAnnee;
import annee.ChooserPeriode;
import classe.ChooserClasse;
import classe.Classe;
import configurationEcolage.ConfigEcolage;
import connection.DAO;
import connection.DAOFactory;

public class GeneralVoidEcolage {

	private ChooserAnnee chooserAnnee;
	private ChooserClasse chooserBull;
	private String annee;
	private ConfigEcolage conf;
	private DAO andao, clsdao, elvclsdao;
	private MartList<Classe> classes;

	public GeneralVoidEcolage() {
		andao = DAOFactory.getDAO(DAO.ANNEE);
		clsdao = DAOFactory.getDAO(DAO.CLASSE);
		elvclsdao = DAOFactory.getDAO(DAO.ELEVE_CLASSE);
		DAOFactory.getDAO(DAO.VERSEMENT_ECOLAGE);
		DAOFactory.getDAO(DAO.OPERATION);
	}

	public void caisseEcolage() {
		chooserAnnee = ChooserAnnee.getInstance();

		chooserAnnee.setAction(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NouveauEcolage nEco = new NouveauEcolage();
				nEco.setAnnee(chooserAnnee.getAnnee());
				nEco.setVisible(true);
				chooserAnnee.dispose();
			}

		});

		chooserAnnee.setVisible(true);
	}

	public void gestionSalaire() {
		chooserAnnee = ChooserAnnee.getInstance();

		chooserAnnee.setAction(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefinirSalaire defSal = new DefinirSalaire();
				defSal.setAnnee(chooserAnnee.getAnnee());
				defSal.setVisible(true);
				chooserAnnee.dispose();
			}

		});

		chooserAnnee.setVisible(true);
	}

	public void listeEcolageClasse() {
		final ChooserClasse chooser = ChooserClasse.getInstance();
		chooser.setTrimestreChoosing(false);

		chooser.setAction(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ListeEcolageClasseModel model = new ListeEcolageClasseModel();
				model.setAnnee(chooser.getAnnee());
				model.setClasse(chooser.getClasse().getIntitule());

				model.createListe();

				chooser.close();
			}
		});

		chooser.setVisible(true);
	}

	public void listeFraisInscriptionClasse() {
		chooserBull = ChooserClasse.getInstance();
		chooserBull.setTrimestreChoosing(false);

		chooserBull.setAction(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ListeFraisInscriptionClasseModel model = new ListeFraisInscriptionClasseModel();
				model.setAnnee(chooserBull.getAnnee());
				model.setClasse(chooserBull.getClasse().getIntitule());

				model.createListe();

				chooserBull.close();
			}
		});

		chooserBull.setVisible(true);
	}

	public void listeEntreeEcolage() {
		chooserAnnee = ChooserAnnee.getInstance();
		chooserAnnee.setPeriodeChoosing(true);

		chooserAnnee.setAction(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (chooserAnnee.isPeriodeActivated()) {
					final ChooserPeriode ch = new ChooserPeriode();
					ch.setAction(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							ListeEntreeScolariteModel model = new ListeEntreeScolariteModel();
							model.setPeriodSelection(true);
							model.setAnnee(chooserAnnee.getAnnee());
							model.setDate1(ch.getDateDebut());
							model.setDate2(ch.getDateFin());

							model.createListe();
							chooserAnnee.close();
							ch.close();
						}
					});
				} else {
					ListeEntreeScolariteModel model = new ListeEntreeScolariteModel();
					model.setPeriodSelection(false);
					model.setAnnee(chooserAnnee.getAnnee());
					model.createListe();
					chooserAnnee.close();
				}
			}
		});

		chooserAnnee.setVisible(true);
	}

	public void listeCompteEntree() {
		chooserAnnee = ChooserAnnee.getInstance();
		chooserAnnee.setPeriodeChoosing(true);

		chooserAnnee.setAction(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (chooserAnnee.isPeriodeActivated()) {
					final ChooserPeriode ch = new ChooserPeriode();
					ch.setAction(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							ListeCompteEntreeModel model = new ListeCompteEntreeModelPeriode();
							model.setAnnee(chooserAnnee.getAnnee());
							model.setDate1(ch.getDateDebut());
							model.setDate2(ch.getDateFin());

							model.createListe();
							chooserAnnee.close();
							ch.close();
						}
					});
				} else {
					ListeCompteEntreeModel model = new ListeCompteEntreeModelGeneral();
					model.setAnnee(chooserAnnee.getAnnee());
					model.createListe();
					chooserAnnee.close();
				}
			}
		});

		chooserAnnee.setVisible(true);
	}

	public static void main(String[] args) {
		Constance.initialize();
		new GeneralVoidEcolage().listeCompteEntree();
	}

	public void general() {
		String anneestr = JOptionPane.showInputDialog(null,
				"Veuillez saisir l'annee-scolaire" + " ici",
				"PANNEAU DE CONFIGURATION***", JOptionPane.QUESTION_MESSAGE);

		andao.load();

		if (!(anneestr.equals(""))) {
			try {
				annee = ((Annee) andao.findObj(anneestr)).getIntitule();
				conf = new ConfigEcolage(annee);
				conf.setVisible(true);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public void synchroListeEleve() {
		chooserAnnee = ChooserAnnee.getInstance();
		chooserAnnee.setModal(true);

		chooserAnnee.setAction(new ActionListener() {
			private MartList<EleveClasse> eleves;
			private Progress progress;
			private MartFrame frame = new ProgressFrame();

			public void actionPerformed(ActionEvent arg0) {
				annee = chooserAnnee.getAnnee();
				clsdao.load();
				classes = clsdao.getListObt();
				progress = new Progress();
				progress.getLoading(frame,
						"Synchronisation des données avec la Comptabilité");

				new Thread(new Runnable() {
					public void run() {
						for (Classe cl : classes) {
							elvclsdao.load(null, cl.getIntitule(), 0, annee);
							eleves = elvclsdao.getListObt();

							// on remet les élèves dans leur classes
							// cela permettra de faire mise a jour au niveau de
							// la
							// comptbilité
							try {
								for (EleveClasse superEleve : eleves) {
									elvclsdao.update_create(superEleve);
									try {
										Thread.sleep(100);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						progress.setText("Terminé");
						try {
							Thread.sleep(5000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						frame.close();
					}
				}).start();
			}
		});

		chooserAnnee.setVisible(true);
	}

	public void definirSalaire() {
		chooserAnnee = ChooserAnnee.getInstance();
		chooserAnnee.setAction(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefinirSalaire fen = new DefinirSalaire();
				fen.setAnnee(chooserAnnee.getAnnee());
				fen.setVisible(true);
				chooserAnnee.dispose();
			}

		});

		chooserAnnee.setVisible(true);
	}

	public void avancerSalaire() {
		Constance.getNotSet();
	}

	public void choisirAgent() {
		chooserAnnee = ChooserAnnee.getInstance();
		chooserAnnee.setAction(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ChooserSalaireAgent fen = new ChooserSalaireAgent();
				fen.setAnnee(chooserAnnee.getAnnee());
				fen.setVisible(true);
				chooserAnnee.dispose();
			}

		});

		chooserAnnee.setVisible(true);
	}

}
