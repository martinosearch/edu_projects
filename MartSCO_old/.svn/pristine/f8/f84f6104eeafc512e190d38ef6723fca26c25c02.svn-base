package OptionPane;

import examen.ChooserExam;
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

import abstractObject.AbstractModel;
import abstractObject.Rapport;
import progress.Avancer;
import rapportBulletin.ChooserEval;
import rapportExamen.FicheRst1Exam;
import statistique.StaMoyAn;
import statistique.StaMoyTrim;
import statistique.StaNoteCompo;
import statistiqueExamen.StaNoteExamModel;
import statistiqueExamen.StaNoteExamModelMixte;

public class OptionStatistiqueExam extends OptionEditorFrame {

	private static OptionStatistiqueExam instance;
	private OptionItem staNote;
	private int typeRapport;

	public OptionStatistiqueExam() {
		setTitle("DOCUMENTS");
		staNote = new OptionItem("img_rapport.png",
				"Statistique des notes de l'examen");

		addItem(staNote);

		setIcone(new FrameIcon().getBilan());
	}

	public static void main(String[] args) {
		new OptionStatistiqueExam().setVisible(true);

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

		if (source == staNote) {
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
								StaNoteExamModel writer = new StaNoteExamModelMixte();
								writer.setEtablissement(monChoix.getEts());
								writer.setExamen(monChoix.getExamen());
								writer.valider(AbstractModel.UPDATE_CHOISE);
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

	public static OptionStatistiqueExam getInstance() {
		if (instance == null) {
			instance = new OptionStatistiqueExam();
		}
		return instance;
	}
}
