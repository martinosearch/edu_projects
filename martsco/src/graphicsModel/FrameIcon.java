package graphicsModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import function.Constance;

public class FrameIcon {
	public FrameIcon() {

	}

	public JPanel getAccueil() {
		URL url = getURL("img_retour.png");
		return getIcone(url, "Accueil");
	}

	public JPanel getCompta() {
		URL url = getURL("img_billet.png");
		return getIcone(url, "Comptabilité");
	}

	public JPanel getIcone(URL url, String str) {
		JPanel pan = new JPanel(new BorderLayout());
		// pan.setBackground(Color.WHITE);

		ImageIcon image = new ImageIcon(url);
		JLabel img = new JLabel(image);
		JLabel title = new JLabel(str);
		title.setFont(new Font("cooper black", Font.PLAIN, 30));
		title.setForeground(Color.BLUE);

		// pan.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		pan.add(new JPanel(), BorderLayout.WEST);
		pan.add(img, BorderLayout.CENTER);
		pan.add(title, BorderLayout.EAST);

		return pan;
	}

	public URL getURL(String str) {
		ClassLoader loader = getClass().getClassLoader();
		URL url = loader.getResource(Constance.getImageFolder() + str);

		return url;
	}

	public JPanel getCaisse() {
		URL url = getURL("img_caisse.png");
		return getIcone(url, "Caisse");
	}

	public JPanel getSalaire() {
		URL url = getURL("img_salaire.png");
		return getIcone(url, "Salaire");
	}

	public JPanel getBilan() {
		URL url = getURL("img_rapport.png");
		return getIcone(url, "Bilan");
	}

	public JPanel getFraisScolarite() {
		URL url = getURL("img_billet.png");
		return getIcone(url, "Frais de Scolarité");
	}

	public JPanel getPrintView() {
		URL url = getURL("img_apercu_imp.png");
		return getIcone(url, "Aperçu");
	}

	public JPanel getAgent() {
		URL url = getURL("img_agent.png");
		return getIcone(url, "Agent");
	}

	public JPanel getHelp() {
		URL url = getURL("img_help.png");
		return getIcone(url, "Aide-Info");
	}

	public JPanel getEleve() {
		URL url = getURL("img_eleve.png");
		return getIcone(url, "Eleve");
	}

	public JPanel getCandidat() {
		URL url = getURL("img_eleve.png");
		return getIcone(url, "Candidat");
	}

	public JPanel getClasse() {
		URL url = getURL("img_classe.png");
		return getIcone(url, "Classe");
	}

	public JPanel getAnnee() {
		URL url = getURL("img_annee.png");
		return getIcone(url, "Année- Scolaire");
	}

	public JPanel getMatiere() {
		URL url = getURL("img_matiere.png");
		return getIcone(url, "Matière");
	}

	public JPanel getNiveau() {
		URL url = getURL("img_niveau.png");
		return getIcone(url, "Niveau");
	}

	public JPanel getIntituleClasse(String classe) {
		URL url = getURL("img_classe.png");
		return getIcone(url, classe);
	}

	public JPanel getReglage(String classe) {
		URL url = getURL("img_reglage.png");
		return getIcone(url, classe);
	}

	public JPanel getNotes(String str) {
		URL url = getURL("img_editer.png");
		return getIcone(url, str);
	}

	public JPanel getEvaluation() {
		URL url = getURL("img_rapport.png");
		return getIcone(url, "Evaluation");
	}

	public JPanel getConfig() {
		URL url = getURL("img_config.png");
		return getIcone(url, "Configurations");
	}

	public JPanel getSta() {
		URL url = getURL("img_sta.png");
		return getIcone(url, "Configurations");
	}

	public JPanel getRegister() {
		URL url = getURL("img_register.png");
		return getIcone(url, "Enrégistrer");
	}

	public JPanel getSecurite() {
		URL url = getURL("img_register.png");
		return getIcone(url, "Authentification");
	}

	public JPanel getUser() {
		URL url = getURL("img_user.png");
		return getIcone(url, "Utilisateurs");
	}

	public JPanel getEts() {
		URL url = getURL("img_ecole.png");
		return getIcone(url, "Ecoles");
	}

	public JPanel getExamen() {
		URL url = getURL("img_rapport.png");
		return getIcone(url, "Examens");
	}

	public JPanel getGestionNote() {
		URL url = getURL("img_rapport.png");
		return getIcone(url, "Gestion des notes");
	}

	public JPanel getDB() {
		URL url = getURL("img_db.png");
		return getIcone(url, "Base de données");
	}

}
