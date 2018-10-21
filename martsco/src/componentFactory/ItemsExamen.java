package componentFactory;

import etablissement.NouveauEts;
import examen.ChooserExam;
import examen.NouveauExamen;
import graphicsModel.MartFrame;
import graphicsModel.OptionItem;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import rapportBulletin.DocumentAdmin;
import rapportExamen.DocumentAdminExam;
import OptionPane.OptionNoteExam;
import OptionPane.OptionSaisieNote;
import candidat.NouveauCdt;
import classe.ChooserClasse;
import configurationExamen.ConfigExamen;

public class ItemsExamen extends ItemsEditorPanel {

	private OptionItem ecole;
	private OptionItem examen;
	private OptionItem config;
	private OptionItem gestionNote;
	private OptionItem candidat;
	private OptionItem doc;

	public ItemsExamen() {
		setTitre("Examen");
		ecole = new OptionItem(
				"img_ecole.png",
				"<div>Ecoles<div><div id='explication'>Les différentes écoles qui participent à l'examen.</div>");
		examen = new OptionItem("img_rapport.png",
				"<div>Examens</div><div id='explication'>Examens et spécifications</div>");
		candidat = new OptionItem("img_eleve.png",
				"<div>Candidats</div><div id='explication'>Inscription des candidats</div>");
		doc = new OptionItem("img_rapport.png",
				"<div>Autres Documents</div><div id='explication'>Fiches pratiques</div>");
		gestionNote = new OptionItem(
				"img_rapport.png",
				"<div>Gestion des notes</div><div id='explication'>Saisie des notes/ résultat</div>");
		config = new OptionItem("img_reglage.png", "<div>Configuration</div>");

		addElement(ecole);
		addElement(examen);
		addElement(candidat);
		addElement(gestionNote);
		addElement(doc);
		addElement(config);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Component source = (Component) e.getSource();
		ChooserClasse chooser = ChooserClasse.getInstance();
		chooser.setTrimestreChoosing(false);
		chooser.setLocation(MartFrame.INTERNAL_FRAME_LOCATION);

		if (source == ecole) {
			NouveauEts.getInstance().setVisible(true);
		}

		if (source == examen) {
			NouveauExamen.getInstance().setVisible(true);
		}

		if (source == config) {
			final ChooserExam examChooser = new ChooserExam();
			examChooser.setEtsChoosing(false);

			examChooser.setAction(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					examChooser.setModal(false);
					new Thread(new Runnable() {
						public void run() {
							String exam = examChooser.getExamen();
							ConfigExamen conf = new ConfigExamen(exam);
							conf.setVisible(true);
						}
					}).start();

					examChooser.close();
				}
			});
			examChooser.setVisible(true);
		}

		if (source == gestionNote) {
			OptionNoteExam.getInstance().setVisible(true);
		}

		if (source == candidat) {
			new Thread(new Runnable() {
				private ChooserExam monChoix;

				public void run() {
					monChoix = ChooserExam.getInstance();
					monChoix.setModal(false);
					monChoix.setEtsChoosing(false);

					// On définit l'action qui serra assignée au chooser
					monChoix.setAction(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							new Thread(new Runnable() {
								public void run() {
									NouveauCdt ncdt = NouveauCdt.getInstance();
									ncdt.setExamen(monChoix.getExamen());
									ncdt.setVisible(true);
									ncdt.load();

									monChoix.close();
								}
							}).start();

						}
					});

					monChoix.setVisible(true);
					// ***********************************************************
				}

			}).start();
		}

		if (source == doc) {
			final ChooserExam examChooser = new ChooserExam();
			examChooser.setEtsChoosing(false);

			examChooser.setAction(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					examChooser.setModal(false);
					new Thread(new Runnable() {
						public void run() {
							DocumentAdminExam writer = new DocumentAdminExam();
							writer.setExamen(examChooser.getExamen());
							writer.setVisible(true);
						}
					}).start();

					examChooser.close();
				}
			});
			examChooser.setVisible(true);

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
}
