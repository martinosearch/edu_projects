package function;

import eleve.PromoEleve;
import eleve.PromoEleveControler;
import eleve.PromoEleveModel;
import examen.ChooserExam;
import graphicsModel.MartFrame;
import graphicsModel.MartImage;
import graphicsModel.MyFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import matiere.SectionMatiere;
import progress.Progress;
import rapportBulletin.BullModelEditor1;
import rapportBulletin.BullWriter;
import rapportBulletin.BullWriterControler;
import rapportBulletin.DocFormat;
import rapportBulletin.ListeWriterModel;
import rapportBulletin.ScoWriterModel;
import rapportExamen.ListeWriterExamModel;
import rapportExamen.RelWriter;
import rapportExamen.RelWriterControler;
import rapportExamen.RelWriterModelDouble;
import rapportExamen.RelWriterModelSingle;
import Import.ImportExamManager;
import abstractObject.AbstractChooser;
import abstractObject.AbstractControler;
import abstractObject.AbstractModel;
import annee.ChooserAnnee;
import classe.ChooserClasse;
import classe.FusionClasse;
import complements.MyFen;
import configurationAppli.ConfigApplication;
import configurationEcole.ConfigEcole;
import configurationExamen.ConfigExamen;
import connection.DAO;
import connection.MartConnection;

public class GeneralVoid {

	private DAO andao;
	private String annee;
	private ConfigEcole conf;
	private int trimestre;
	private ChooserAnnee anneeChooser;
	protected boolean doRestore = false;
	private boolean doMiseAjour = false;
	private boolean doRestart = false;
	private ConfigApplication confAppli;
	private static AbstractChooser monChoix;
	private static ChooserExam chooser;

	public static void main(String[] args) {
		new GeneralVoid().calculatrice();
	}

	public boolean isDoRestore() {
		return doRestore;
	}

	public void setDoRestore(boolean doRestore) {
		this.doRestore = doRestore;
	}

	public void calculatrice() {
		try {
			Runtime.getRuntime().exec("calc.exe");

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void createListeEleve() {
		// on demande le remplissage**********************************
		monChoix = ChooserClasse.getInstance();
		monChoix.setListChoosing(true);

		// On définit l'action qui serra assignée au chooser
		monChoix.setAction(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Thread(new Runnable() {
					public void run() {
						ListeWriterModel model = new ListeWriterModel();

						model.setAnnee(monChoix.getAnnee());
						model.setAfficherPhoto(monChoix.getFirstOption());
						model.setClasse(monChoix.getClasse().getIntitule());

						System.out
								.println("===================>> PhotosOption: "
										+ ((ChooserClasse) monChoix)
												.getFirstOption());
						model.valider(model.DEFAULT);
					}
				}).start();

				monChoix.close();
			}
		});
		monChoix.setVisible(true);
		// ***********************************************************
	}

	public void createImportFile() {
		Fichier fImport = null;
		JFileChooser fChooser = new JFileChooser();
		fChooser.setFileFilter(new FiltreFichier("fichier d'importation_",
				"csv"));
		int selection = fChooser.showOpenDialog(null);

		if (selection == JFileChooser.APPROVE_OPTION) {
			fImport = new Fichier(fChooser.getSelectedFile().getAbsolutePath());

			fImport.create();
		}
	}

	public void doSauvegardeBD() {
		FichierEditor.writeSauvegarde();
		ProcessBuilder pb = new ProcessBuilder(Constance.getTempDir()
				+ "db_dump.bat");
		Process process = null;
		try {
			process = pb.start();
			System.out.println("En principe, je suis executé");
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			process.waitFor();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void doSmartClosing() {
		if (MartConnection.isServer()) {
			new Thread(new Runnable() {
				public void run() {
					doSauvegardeBD();
				}
			}).start();
		}

		doAnimation();
	}

	private void doAnimation() {
		final MyFrame fr = new MyFrame();
		fr.setTitle("Alert Sauvegarde");
		fr.setSize(600, 600);
		fr.setVisible(true);
		fr.setLocationRelativeTo(null);
		JPanel container = new JPanel();

		container.setLayout(new BorderLayout());
		container
				.add(new MartImage(getClass().getClassLoader().getResource(
						Constance.getImageFolder() + "img_sauvegarderbase.png")),
						BorderLayout.CENTER);

		JLabel lb = new JLabel();
		lb.setText("<html><h2 font-size='20pt'>Martsco effectue des sauvegardes "
				+ "automatiques à chaque ouverture et fermeture"
				+ " du serveur. Vous devez de temps en temps copier ces fichiers de"
				+ " sauvegarde sur un support externe.</h2>"
				+ "<h2 color='blue'>Emplacement: D:/BackUp</h2></html>");
		container.add(lb, BorderLayout.SOUTH);

		fr.getContentPane().add(container, BorderLayout.CENTER);
		final Progress progress = new Progress();
		progress.getProgress(fr, 0, 100);
		progress.showPercent(false);
		progress.setColor(Color.GREEN);

		new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 100; i++) {
					try {
						progress.increment();
						progress.setText("Fermeture dans: "
								+ (int) (10 - i * 0.1) + " sec.");
						Thread.sleep(100);
					} catch (Exception e) {
						e.printStackTrace();
					}

					// System.out.println(isDoRestart() + " " + i);

					if (isDoRestart() == true && i == 95) {
						System.out.println("REDEMARRAGE");
						FichierEditor.writeRestart();
						try {
							Runtime.getRuntime().exec(
									Constance.getTempDir() + "rst.bat");
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}

				// si la restoration de la base de données est demandées
				if (isDoRestore()) {
					if (MartConnection.isServer()) {
						System.out.println("RESTORATION DE LA BD");
						FichierEditor.writeRestore();
						try {
							Runtime.getRuntime().exec(
									Constance.getTempDir() + "db_restore.bat");

						} catch (IOException e1) {
							e1.printStackTrace();
						}

					} else {
						JOptionPane
								.showMessageDialog(null,
										"Cette action n'est possible que sur le serveur.");
					}
					fr.dispose();
					System.out.println("LIBERATION DE MEMOIRE");
					System.exit(0);
				}

				// si la mise à jour de la base de données est demandées
				else if (isMiseAjour()) {
					doMiseAjour();
					fr.dispose();
				}

				else {
					System.exit(0);
				}

				// suppression des fichier inutile
				// On libère également la mémoire
				new GeneralVoid().doFreeMemory();

			}
		}).start();

	}

	public static void promoEleve() {
		// on demande le remplissage**********************************
		monChoix = ChooserClasse.getInstance();
		monChoix.setTrimestreChoosing(false);

		// On d�finit l'action qui serra assignée au chooser
		monChoix.setAction(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Thread(new Runnable() {
					public void run() {

						AbstractModel model = new PromoEleveModel();

						model.setAnnee(monChoix.getAnnee());
						model.setTrimestre(monChoix.getTrimestre());
						model.setClasse(monChoix.getClasse().getIntitule());

						AbstractControler controler = new PromoEleveControler(
								model);
						PromoEleve pEleve = new PromoEleve(controler);
						model.addObserver(pEleve);
						pEleve.setVisible(true);

					}
				}).start();

				monChoix.close();
			}
		});
		monChoix.setVisible(true);
	}

	public void createScolariteEleve() {
		// on demande le remplissage**********************************
		monChoix = ChooserClasse.getInstance();
		monChoix.setListChoosing(true);

		// On définit l'action qui serra assignée au chooser
		monChoix.setAction(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Thread(new Runnable() {
					public void run() {

						ScoWriterModel model = new ScoWriterModel();

						model.setAnnee(monChoix.getAnnee());
						model.setClasse(monChoix.getClasse().getIntitule());
						model.setAfficherPhoto(((ChooserClasse) monChoix)
								.getFirstOption());
						model.valider(model.DEFAULT);
					}
				}).start();

				monChoix.close();
			}
		});
		monChoix.setVisible(true);
		// ***********************************************************
	}

	public void writeReleve() {

	}

	public static void ImpListeEleveExam() {
		chooser = ChooserExam.getInstance();

		chooser.setAction(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ImportExamManager mng = new ImportExamManager();
				mng.setExamen(chooser.getExamen());
				mng.setEts(chooser.getEts());
				mng.importer();
				chooser.dispose();
			}
		});

		chooser.setVisible(true);
	}

	public static void createListeEleveExam() {
		chooser = ChooserExam.getInstance();

		chooser.setAction(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ListeWriterExamModel model = new ListeWriterExamModel();
				model.setExamen(chooser.getExamen());
				model.setEts(chooser.getEts());

				model.valider(model.DEFAULT);
				chooser.dispose();
			}
		});

		chooser.setVisible(true);
	}

	public void fusionClasse() {
		monChoix = ChooserAnnee.getInstance();
		((ChooserAnnee) monChoix).setValiderTitle("Valider");

		annee = monChoix.getAnnee();

		monChoix.setAction(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FusionClasse fc = new FusionClasse(annee);
				fc.setVisible(true);
				monChoix.close();
			}
		});

		monChoix.setVisible(true);
	}

	public void lunchBD() {
		try {
			Runtime.getRuntime().exec(
					"C:\\Program Files\\PostgreSQL\\9.4\\bin\\PgAdmin3.exe");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void doFreeMemory() {
		System.out.println("SUPPRESSION DES FICHIERS INUTILES");
		FichierEditor.writeFreeMemory();
		try {
			Runtime.getRuntime()
					.exec(Constance.getTempDir() + "freeMemory.bat");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void makeDir() {
		System.out.println("CREATION DES DOSSIERS NECESSAIRES");
		File f = new File(Constance.getTempDir() + "\\temp_images\\");
		f.mkdir();
	}

	public void sectionMatiere() {
		anneeChooser = ChooserAnnee.getInstance();

		anneeChooser.setAction(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new SectionMatiere(anneeChooser.getAnnee()).setVisible(true);
			}
		});

		anneeChooser.setVisible(true);
	}

	public void setDoMiseAjour(boolean b) {
		doMiseAjour = b;
	}

	public boolean isMiseAjour() {
		return doMiseAjour;
	}

	public boolean isDoRestart() {
		return doRestart;
	}

	public void setDoRestart(boolean doRestart) {
		this.doRestart = doRestart;
	}

	public void doMiseAjour() {
		if (MartConnection.isServer()) {
			System.out.println("MISE A JOUR DE LA BD");
			FichierEditor.writeMiseAJour();
			try {
				Runtime.getRuntime().exec(Constance.getTempDir() + "maj.bat");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(null,
					"Cette action n'est possible que sur le serveur.");
		}

		System.exit(0);
	}

	public void configApplication() {
		confAppli = new ConfigApplication();
		confAppli.setVisible(true);
	}

	public void configEcole() {
		monChoix = ChooserAnnee.getInstance();
		monChoix.setTrimestreChoosing(false);
		monChoix.setLocation(MartFrame.INTERNAL_FRAME_LOCATION);

		monChoix.setAction(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				conf = new ConfigEcole(trimestre, monChoix.getAnnee());
				conf.setVisible(true);
				monChoix.dispose();
			}
		});

		monChoix.setVisible(true);
	}
}
