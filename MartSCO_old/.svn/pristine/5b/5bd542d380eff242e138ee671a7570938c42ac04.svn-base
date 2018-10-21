package OptionPane;

import function.Constance;
import function.GeneralVoid;
import graphicsModel.FrameIcon;
import graphicsModel.OptionEditorFrame;
import graphicsModel.OptionItem;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import classe.ChooserClasse;
import progress.Avancer;
import rapportBulletin.BullModelEditor1;
import rapportBulletin.BullModelEditor2;
import rapportBulletin.BullWriter;
import rapportBulletin.BullWriterControler;
import rapportBulletin.FicheRst1;
import rapportBulletin.FicheRst2;
import rapportBulletin.FicheRstAn2;
import rapportBulletin.FicheRstAn2Semestre;
import rapportBulletin.FicheRstAn2Trimestre;
import rapportBulletin.RapportSaisie;
import abstractObject.AbstractChooser;
import abstractObject.AbstractControler;
import abstractObject.AbstractModel;
import abstractObject.Rapport;
import annee.Decoupage;
import configurationClasse.ConfigClasse;
import configurationEcole.ConfigEcole;
import connection.DAO;
import connection.DAOFactory;

public class OptionRapportNote extends OptionEditorFrame {

	private static OptionRapportNote instance;
	private OptionItem rapportSaisie;
	private OptionItem bulletin;
	private OptionItem rstTrim;
	private OptionItem moyMatiere;
	private AbstractChooser monChoix;
	private OptionItem moyAn;

	public OptionRapportNote() {
		setTitle("RAPPORTS TRIMESTRIELS/ SEMESTRIELS");
		bulletin = new OptionItem("img_rapport.png", "Bulletin de notes");
		rapportSaisie = new OptionItem("img_rapport.png", "Rapport de Saisie");
		rstTrim = new OptionItem("img_rapport.png",
				"Fiche de Résultat (moyenne générale)");
		moyMatiere = new OptionItem("img_rapport.png",
				"Fiche des moyennes par matière");
		moyAn = new OptionItem("img_rapport.png",
				"Fiche des moyennes annuelles");

		addItem(rapportSaisie);
		addItem(rstTrim);
		addItem(bulletin);
		addItem(moyMatiere);
		addItem(moyAn);

		setIcone(new FrameIcon().getBilan());
	}

	public static void main(String[] args) {
		Constance.initialize();
		new OptionRapportNote().setVisible(true);
	}

	@Override
	public Avancer getAvancer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Component source = (Component) e.getSource();

		// ecoute listeEleve
		if (source == bulletin) {
			// on demande le remplissage**********************************
			monChoix = ChooserClasse.getInstance();
			monChoix.setTrimestreChoosing(true);

			// On définit l'action qui serra assignée au chooser
			monChoix.setAction(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					new Thread(new Runnable() {
						ConfigEcole config;

						public void run() {
							config = new ConfigEcole(0, monChoix.getAnnee());
							AbstractModel model = null;

							int type = Rapport.BULLETIN2;

							try {
								type = config.bullConfig.getModelBull();
							} catch (Exception e) {
								e.printStackTrace();
							}

							if (type == Rapport.BULLETIN1) {
								model = new BullModelEditor1();
							}

							if (type == Rapport.BULLETIN2) {
								model = new BullModelEditor2();
							}

							model.setAnnee(monChoix.getAnnee());
							model.setTrimestre(monChoix.getTrimestre());
							model.setClasse(monChoix.getClasse().getIntitule());

							AbstractControler controler = new BullWriterControler(
									model);
							BullWriter writter = new BullWriter(controler);
							model.addObserver(writter);
							writter.setVisible(true);

						}
					}).start();

					monChoix.close();
				}
			});
			monChoix.setVisible(true);
			// ***********************************************************
		}

		if (source == rapportSaisie) {
			monChoix = ChooserClasse.getInstance();
			monChoix.setTrimestreChoosing(true);

			// On d�finit l'action qui serra assign�e au chooser
			monChoix.setAction(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					new Thread(new Runnable() {
						public void run() {
							RapportSaisie fn = new RapportSaisie();
							fn.createResutat(
									monChoix.getClasse().getIntitule(),
									monChoix.getTrimestre(),
									monChoix.getAnnee());
						}
					}).start();

					monChoix.close();
				}
			});
			monChoix.setVisible(true);
			// ***********************************************************
		}

		if (source == rstTrim) {
			// on demande le remplissage**********************************
			monChoix = ChooserClasse.getInstance();
			monChoix.setAction(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					new Thread(new Runnable() {
						public void run() {
							FicheRst2 fr2 = new FicheRst2();
							fr2.createResutat(monChoix.getClasse()
									.getIntitule(), monChoix.getTrimestre(),
									monChoix.getAnnee());
						}
					}).start();

					monChoix.close();
				}
			});
			monChoix.setVisible(true);
		}

		if (source == moyMatiere) {
			monChoix = ChooserClasse.getInstance();
			monChoix.setAction(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					new Thread(new Runnable() {
						public void run() {
							FicheRst1 fr1 = new FicheRst1();
							fr1.createResutat(monChoix.getClasse()
									.getIntitule(), monChoix.getTrimestre(),
									monChoix.getAnnee());

						}
					}).start();
					monChoix.close();
				}
			});
			monChoix.setVisible(true);
		}

		if (source == moyAn) {
			// on demande le remplissage**********************************
			monChoix = ChooserClasse.getInstance();
			monChoix.setTrimestreChoosing(false);

			// On d�finit l'action qui serra assign�e au chooser
			monChoix.setAction(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					new Thread(new Runnable() {

						private ConfigClasse conf;
						private Decoupage dec;

						public void run() {
							conf = new ConfigClasse(monChoix.getClasse(),
									monChoix.getAnnee());

							dec = conf.persoClasse.getDecoupage();

							System.out.println("==============>>"
									+ dec.getTypeDec());

							if (dec.getTypeDec() == Decoupage.TRIMESTRE) {
								FicheRstAn2 fr3 = new FicheRstAn2Trimestre();
								fr3.createResutat(monChoix.getClasse(),
										monChoix.getAnnee());
							}

							if (dec.getTypeDec() == Decoupage.SEMESTRE) {
								FicheRstAn2 fr3 = new FicheRstAn2Semestre();
								fr3.createResutat(monChoix.getClasse(),
										monChoix.getAnnee());
							}
						}
					}).start();

					monChoix.close();
				}
			});
			monChoix.setVisible(true);
		}

		reset();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void load() {
		// TODO Auto-generated method stub

	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub

	}

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	public static OptionRapportNote getInstance() {
		if (instance == null) {
			instance = new OptionRapportNote();
		}
		return instance;
	}
}
