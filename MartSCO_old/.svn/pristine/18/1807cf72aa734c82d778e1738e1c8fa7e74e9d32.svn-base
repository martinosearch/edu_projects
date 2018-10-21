package OptionPane;

import examen.ChooserExam;
import function.Constance;
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

import progress.Avancer;
import rapportBulletin.ChooserEval;
import rapportBulletin.DocFormat;
import rapportExamen.FicheRst1Exam;
import rapportExamen.FicheRst2Exam;
import rapportExamen.RelWriter;
import rapportExamen.RelWriterControler;
import rapportExamen.RelWriterModelDouble;
import rapportExamen.RelWriterModelSingle;
import abstractObject.AbstractControler;
import abstractObject.AbstractModel;
import abstractObject.Rapport;
import configurationExamen.ConfigExamen;

public class OptionRapportNoteExam extends OptionEditorFrame {

	private static OptionRapportNoteExam instance;
	private OptionItem rapportSaisie;
	private OptionItem bulletin;
	private OptionItem rstGen;
	private OptionItem moyMatiere;
	private int typeRapport = Rapport.EXAMEN;

	public OptionRapportNoteExam() {
		setTitle("RAPPORTS DES EVALUATIONS");
		bulletin = new OptionItem("img_rapport.png", "Releve de notes");
		rapportSaisie = new OptionItem("img_rapport.png", "Rapport de Saisie");
		rstGen = new OptionItem("img_rapport.png",
				"Fiche de Résultat (moyenne générale)");
		moyMatiere = new OptionItem("img_rapport.png",
				"Fiche des moyennes par matière");

		addItem(moyMatiere);
		addItem(rstGen);
		addItem(bulletin);

		setIcone(new FrameIcon().getBilan());
	}

	public static void main(String[] args) {
		Constance.initialize();
		new OptionRapportNoteExam().setVisible(true);
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
			if (typeRapport == Rapport.EVAL_TRIMESTRIELLE) {
				final ChooserEval monChoix = ChooserEval.getInstance();
				monChoix.setEtsChoosing(false);

				monChoix.setAction(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						new Thread(new Runnable() {
							public void run() {

								AbstractModel model = null;

								// ConfigExamen conf = new ConfigExamen(monChoix
								// .getExamen());

								// if (conf.relConfig.getTypeReleve() ==
								// DocFormat.BULLSINGLE) {
								// model = new RelWriterModelSingle();
								// }

								// if (conf.relConfig.getTypeReleve() ==
								// DocFormat.BULLDOUBLE) {
								model = new RelWriterModelDouble();
								// }

								model.setAnnee(monChoix.getAnnee());
								model.setClasse(monChoix.getClasse()
										.getIntitule());
								model.setTrimestre(monChoix.getTrimestre());
								model.setEvaluation(monChoix.getEvaluation());
								model.setTypeRapport(typeRapport);

								AbstractControler controler = new RelWriterControler(
										model);
								RelWriter writter = new RelWriter(controler);

								model.addObserver(writter);
								writter.setVisible(true);
							}
						}).start();

						monChoix.close();
					}
				});

				monChoix.setVisible(true);
			} else {
				// on demande le remplissage**********************************
				final ChooserExam monChoix = ChooserExam.getInstance();
				monChoix.setEtsChoosing(true);

				// On définit l'action qui serra assignée au chooser
				monChoix.setAction(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						new Thread(new Runnable() {
							public void run() {
								AbstractModel model = null;
								ConfigExamen conf = new ConfigExamen(monChoix
										.getExamen());

								if (conf.relConfig.getTypeReleve() == DocFormat.BULLSINGLE) {
									model = new RelWriterModelSingle();
								}

								if (conf.relConfig.getTypeReleve() == DocFormat.BULLDOUBLE) {
									model = new RelWriterModelDouble();
								}

								model.setAnnee(monChoix.getAnnee());
								model.setExamen(monChoix.getExamen());

								AbstractControler controler = new RelWriterControler(
										model);
								RelWriter writter = new RelWriter(controler,
										monChoix.getEts());

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
		}

		if (source == rapportSaisie) {
			Constance.getNotSet();
		}

		if (source == moyMatiere) {
			// on demande le remplissage
			if (typeRapport == Rapport.EVAL_TRIMESTRIELLE) {
				final ChooserEval monChoix = ChooserEval.getInstance();
				monChoix.setEtsChoosing(false);

				monChoix.setAction(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						new Thread(new Runnable() {
							public void run() {
								FicheRst1Exam fr2 = new FicheRst1Exam();
								fr2.createResutat(monChoix.getClasse(),
										monChoix.getEvaluation(),
										monChoix.getTrimestre(),
										monChoix.getAnnee());
							}
						}).start();

						monChoix.close();
					}
				});

				monChoix.setVisible(true);
			} else {
				final ChooserExam monChoix = ChooserExam.getInstance();
				monChoix.setEtsChoosing(true);

				monChoix.setAction(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						new Thread(new Runnable() {
							public void run() {
								FicheRst1Exam fr1 = new FicheRst1Exam();
								fr1.setEtablissement(monChoix.getEts());
								fr1.createResutat(monChoix.getExamen());
							}
						}).start();

						monChoix.close();
					}
				});

				monChoix.setVisible(true);
			}

		}

		if (source == rstGen) {
			// on demande le remplissage
			if (typeRapport == Rapport.EVAL_TRIMESTRIELLE) {
				final ChooserEval monChoix = ChooserEval.getInstance();
				monChoix.setEtsChoosing(false);

				monChoix.setAction(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						new Thread(new Runnable() {
							public void run() {
								FicheRst2Exam fr2 = new FicheRst2Exam();
								fr2.createResutat(monChoix.getClasse(),
										monChoix.getEvaluation(),
										monChoix.getTrimestre(),
										monChoix.getAnnee());
							}
						}).start();

						monChoix.close();
					}
				});

				monChoix.setVisible(true);
			} else {
				final ChooserExam monChoix = ChooserExam.getInstance();
				monChoix.setEtsChoosing(true);

				monChoix.setAction(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						new Thread(new Runnable() {
							public void run() {
								FicheRst2Exam fr2 = new FicheRst2Exam();
								fr2.setEtablissement(monChoix.getEts());
								fr2.createResutat(monChoix.getExamen());
							}
						}).start();

						monChoix.close();
					}
				});

				monChoix.setVisible(true);
			}

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

	public static OptionRapportNoteExam getInstance() {
		if (instance == null) {
			instance = new OptionRapportNoteExam();
		}
		return instance;
	}

	public void setTypeRapport(int typ) {
		typeRapport = typ;
	}
}
