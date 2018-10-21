package componentFactory;

import eleve.NouveauEleve;
import function.GeneralVoid;
import graphicsModel.OptionItem;

import java.awt.Component;
import java.awt.event.MouseEvent;

import matiere.NouvelleMatiere;
import agent.NouveauAgent;
import annee.NouvelleAnnee;
import classe.NouveauNiveau;
import classe.NouvelleClasse;

public class ItemsEcole extends ItemsEditorPanel {

	private OptionItem annee;
	private OptionItem classe;
	private OptionItem matiere;
	private OptionItem eleve;
	private OptionItem agent;
	private OptionItem niveau;
	private OptionItem config;

	public ItemsEcole() {
		setTitre("Ecole");
		annee = new OptionItem("img_annee.png", "Année");
		classe = new OptionItem("img_classe.png", "Classe");
		matiere = new OptionItem("img_matiere.png", "Matiere");
		eleve = new OptionItem("img_eleve.png", "Eleve");
		agent = new OptionItem("img_agent.png", "Agent");
		niveau = new OptionItem("img_niveau.png", "Niveau d'Enseignement");
		config = new OptionItem("img_reglage.png", "Configuration");

		addElement(annee);
		addElement(niveau);
		addElement(classe);
		addElement(agent);
		addElement(eleve);
		addElement(matiere);
		addElement(config);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Component source = (Component) e.getSource();

		if (source == annee) {
			NouvelleAnnee.getInstance().setVisible(true);
		}
		if (source == eleve) {
			NouveauEleve.getInstance().setVisible(true);
		}

		if (source == matiere) {
			NouvelleMatiere.getInstance().setVisible(true);
		}

		if (source == niveau) {
			NouveauNiveau.getInstance().setVisible(true);
		}

		if (source == classe) {
			NouvelleClasse.getInstance().setVisible(true);
		}

		if (source == agent) {
			NouveauAgent.getInstance().setVisible(true);
		}

		if (source == config) {
			new GeneralVoid().configEcole();
		}
		// pour annuler la couleur de selection
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
