package componentFactory;

import function.GeneralVoid;
import graphicsModel.MartFrame;
import graphicsModel.OptionItem;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import OptionPane.OptionGestionNote;
import classe.ChooserClasse;
import classe.ListeEleveClasse;
import configurationClasse.ConfigClasse;

public class ItemsClasse extends ItemsEditorPanel {

	private OptionItem eleve;
	private OptionItem reglage;
	private OptionItem gestionNote;
	private OptionItem fusion;
	private OptionItem statut;
	private OptionItem test;

	public ItemsClasse() {
		setTitre("Classe");
		eleve = new OptionItem(
				"img_eleve.png",
				"<div>Eleve<div><div id='explication'>Elèves inscrits dans une classe donnée</div>");
		reglage = new OptionItem(
				"img_reglage.png",
				"<div>Personnaliser</div><div id='explication'>Matières au programme/ chargés / titulaire, etc.</div>");
		gestionNote = new OptionItem(
				"img_rapport.png",
				"<div>Gestion des notes</div><div id='explication'>"
						+ "Différents traitement relatif au notes des élèves</div>");
		fusion = new OptionItem(
				"img_fusion.png",
				"<div>Fusionner des classes</div><div id='explication'>Fusion des classes d'un même niveau</div>");
		statut = new OptionItem(
				"img_eleve.png",
				"<div>Statut des élèves</div><div id='explication'>On définit ici si l'élève est Nouveau ou Redoublant.</div>");

		addElement(eleve);
		addElement(gestionNote);
		// addElement(test);
		addElement(fusion);
		addElement(statut);
		addElement(reglage);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Component source = (Component) e.getSource();
		final ChooserClasse chooser = ChooserClasse.getInstance();
		chooser.setTrimestreChoosing(false);
		chooser.setLocation(MartFrame.INTERNAL_FRAME_LOCATION);

		if (source == eleve) {
			chooser.setAction(new ActionListener() {
				private ListeEleveClasse fr;

				public void actionPerformed(ActionEvent arg0) {
					fr = new ListeEleveClasse(chooser.getClasse(), chooser
							.getAnnee());
					fr.setVisible(true);
					chooser.close();
				}
			});
			chooser.setVisible(true);
		}

		if (source == reglage) {
			chooser.setAction(new ActionListener() {
				private ConfigClasse fr;

				public void actionPerformed(ActionEvent arg0) {
					fr = new ConfigClasse(chooser.getClasse(), chooser
							.getAnnee());

					fr.setVisible(true);
					chooser.close();
				}
			});
			chooser.setVisible(true);
		}

		if (source == gestionNote) {
			OptionGestionNote.getInstance().setVisible(true);
		}

		if (source == fusion) {
			new GeneralVoid().fusionClasse();
		}

		if (source == statut) {
			new GeneralVoid().promoEleve();
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
