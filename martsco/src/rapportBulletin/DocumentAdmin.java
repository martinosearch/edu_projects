package rapportBulletin;

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

import progress.Avancer;
import classe.ChooserClasse;

public class DocumentAdmin extends OptionEditorFrame {

	private OptionItem attestionAgent;
	private OptionItem ficheNoteExemplaire;
	private OptionItem AttestationEleve;
	private OptionItem listeEleve;

	public DocumentAdmin() {
		setIcone(new FrameIcon().getBilan());
		setTitle("DOCUMENTS ADMINISTRATIFS");
		listeEleve = new OptionItem("img_rapport.png", "Liste de classe");
		ficheNoteExemplaire = new OptionItem("img_rapport.png",
				"Fiche de notes (Exemplaire)");
		attestionAgent = new OptionItem("img_rapport.png",
				"Attestation de travail");
		AttestationEleve = new OptionItem("img_rapport.png",
				"Attestation de scolarité");

		addItem(listeEleve);
		addItem(ficheNoteExemplaire);
		addItem(attestionAgent);
		addItem(AttestationEleve);
	}

	public static void main(String[] args) {
		new DocumentAdmin().setVisible(true);

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
		if (source == ficheNoteExemplaire) {
			// on demande le remplissage**********************************
			final ChooserClasse monChoix = ChooserClasse.getInstance();
			monChoix.setTrimestreChoosing(false);
			monChoix.setListChoosing(false);

			// On définit l'action qui serra assignée au chooser
			monChoix.setAction(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					new Thread(new Runnable() {
						public void run() {
							FicheNoteWriterModel model = new FicheNoteWriterModel();

							model.setAnnee(monChoix.getAnnee());
							model.setTrimestre(monChoix.getTrimestre());
							model.setClasse(monChoix.getClasse().getIntitule());

							model.valider(model.DEFAULT);
						}
					}).start();

					monChoix.close();
				}
			});
			monChoix.setVisible(true);
			// ***********************************************************
		}

		if (source == listeEleve) {
			new GeneralVoid().createListeEleve();
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
}
