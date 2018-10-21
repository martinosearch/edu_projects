package componentFactory;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import function.Constance;

public class MartButton extends JButton {

	public static int SIMPLE = 0;

	public MartButton() {
		initComponent();
	}

	public MartButton(String title) {
		super(title);
		initComponent();
	}

	public MartButton(String title, ImageIcon img) {
		super(title, img);

		initComponent();
	}

	public MartButton(ImageIcon img) {
		super(img);

		initComponent();
	}

	private void initComponent() {
		setFocusable(false);
		this.setHorizontalTextPosition(JButton.CENTER);
		this.setVerticalTextPosition(JButton.BOTTOM);
	}

	public void setDefaultAlignment() {
		this.setHorizontalTextPosition(JButton.RIGHT);
		this.setVerticalTextPosition(JButton.CENTER);
	}

	public void setType(int type) {
		if (type == 0) {
			this.setIcon(null);
		}

		if (this.getText().equals("Suivant")) {
			this.setText("Suivant >");
		}
	}

	private URL getURL(String str) {
		ClassLoader loader = getClass().getClassLoader();

		URL url = loader.getResource(Constance.getImageFolder() + str);

		return url;
	}

	public MartButton getAjouter() {
		URL url = getURL("img_ajouter.png");

		MartButton but = new MartButton("Ajouter");
		but.setIcon(new ImageIcon(url));

		return but;
	}

	public MartButton getValider() {
		URL url = getURL("img_valider.png");

		MartButton but = new MartButton("Valider");
		but.setDefaultAlignment();

		but.setIcon(new ImageIcon(url));

		return but;
	}

	public MartButton getBien() {
		URL url = getURL("img_valider.png");

		MartButton but = new MartButton();
		but.setDefaultAlignment();
		but.setIcon(new ImageIcon(url));

		return but;
	}

	public MartButton getQuitter() {
		URL url = getURL("img_quitter.png");

		MartButton but = new MartButton("Quitter");
		but.setIcon(new ImageIcon(url));

		return but;
	}

	public MartButton getAnnuler() {
		URL url = getURL("img_annuler.png");

		MartButton but = new MartButton("Annuler");
		but.setDefaultAlignment();
		but.setIcon(new ImageIcon(url));

		return but;
	}

	public MartButton getRetour() {
		URL url = getURL("img_retour.png");

		MartButton but = new MartButton("Retour");
		but.setIcon(new ImageIcon(url));

		return but;
	}

	public MartButton getSupprimer() {
		URL url = getURL("img_delete.png");

		MartButton but = new MartButton("Supprimer");
		but.setIcon(new ImageIcon(url));

		return but;
	}

	public MartButton getSupprimerAll() {
		URL url = getURL("img_delete.png");

		MartButton but = new MartButton("Suppr. Tout");
		but.setIcon(new ImageIcon(url));

		return but;
	}

	public MartButton getSuivant() {
		URL url = getURL("img_suivant.png");

		MartButton but = new MartButton();
		but.setIcon(new ImageIcon(url));

		return but;
	}

	public MartButton getPrecedent() {
		URL url = getURL("img_precedent.png");

		MartButton but = new MartButton();
		but.setIcon(new ImageIcon(url));

		return but;
	}

	public MartButton getModifier() {
		URL url = getURL("img_modifier.png");

		MartButton but = new MartButton("Modifier");
		but.setIcon(new ImageIcon(url));

		return but;
	}

	public MartButton getRechercher() {
		URL url = getURL("img_chercher.png");

		MartButton but = new MartButton("Chercher");
		but.setIcon(new ImageIcon(url));

		return but;
	}

	public MartButton getLeftAll() {
		return new MartButton("<<");
	}

	public MartButton getRightAll() {
		return new MartButton(">>");
	}

	public MartButton getRetirer() {
		return new MartButton("Retirer");
	}

	public MartButton getLeft() {
		return new MartButton("<");
	}

	public MartButton getRight() {
		return new MartButton(">");
	}

	public MartButton getImportListe() {
		URL url = getURL("img_importer.png");

		MartButton but = new MartButton("Importer");
		but.setIcon(new ImageIcon(url));

		return but;
	}

	public MartButton getImprimer() {
		URL url = getURL("img_imprimer.png");

		MartButton but = new MartButton("Imprimer");
		but.setIcon(new ImageIcon(url));

		return but;
	}

	public MartButton getPDF() {
		URL url = getURL("img_exportpdf.png");

		MartButton but = new MartButton("Exporter");
		but.setIcon(new ImageIcon(url));

		return but;
	}

	public MartButton getRemonter() {
		return new MartButton("Remonter");
	}

	public MartButton getDescendre() {
		return new MartButton("Descendre");
	}

	public MartButton getSectionBulletins() {
		URL url = getURL("img_sbulletins.png");

		MartButton but = new MartButton(
				"<html><div>GESTION DES BULLETINS</div><div align=center>DE NOTES</div></html>");
		but.setIcon(new ImageIcon(url));

		return but;
	}

	public MartButton getSectionReleves() {
		URL url = getURL("img_sreleves.png");

		MartButton but = new MartButton("GESTION DES EXAMENS");
		but.setIcon(new ImageIcon(url));

		return but;
	}

	public MartButton getComptabilite() {
		URL url = getURL("img_billet.png");

		MartButton but = new MartButton("COMPTABILITE");
		but.setIcon(new ImageIcon(url));

		return but;
	}

	public MartButton getCompte() {
		URL url = getURL("img_rapport.png");

		MartButton but = new MartButton("Compte");
		but.setIcon(new ImageIcon(url));

		return but;
	}

	public MartButton getFraisInscription() {
		URL url = getURL("");

		MartButton but = new MartButton("Frais d'INSCRIPTION");
		but.setIcon(new ImageIcon(url));

		return but;
	}

	public MartButton getSectionDocuments() {
		URL url = getURL("img_sdocuments.png");

		MartButton but = new MartButton("DOCUMENTS ADMINISTRATIFS");
		but.setIcon(new ImageIcon(url));

		return but;
	}

	public MartButton getSectionSorties() {
		return new MartButton("SORTIES");
	}

	public MartButton getSectionEmploiDuTemps() {
		URL url = getURL("img_semploidutemps.png");

		MartButton but = new MartButton("GESTION DU TEMPS");
		but.setIcon(new ImageIcon(url));

		return but;
	}

	public MartButton getSectionDesign() {
		URL url = getURL("img_sdesign.png");

		MartButton but = new MartButton("TRAITEMENTS D'IMAGES)");
		but.setIcon(new ImageIcon(url));

		return but;
	}

	public MartButton getEleve() {
		URL url = getURL("img_eleve.png");

		MartButton but = new MartButton("Eleve");
		but.setIcon(new ImageIcon(url));

		return but;
	}

	public MartButton getAgent() {
		URL url = getURL("img_agent.png");

		MartButton but = new MartButton("Staff");
		but.setIcon(new ImageIcon(url));

		return but;
	}

	public MartButton getClasse() {
		URL url = getURL("img_classe.png");

		MartButton but = new MartButton("Classe");
		but.setIcon(new ImageIcon(url));

		return but;
	}

	public MartButton getChoixMultiple() {
		return new MartButton("Choix multiple");
	}

	public MartButton getSauvegarder() {
		URL url = getURL("img_sauvegarder.png");

		MartButton but = new MartButton("Sauvegarder");
		but.setIcon(new ImageIcon(url));

		return but;
	}

	public MartButton getCalculatrice() {
		URL url = getURL("img_calculatrice.png");

		MartButton but = new MartButton("Calculatrice");
		but.setIcon(new ImageIcon(url));

		return but;
	}

	public MartButton getBulletin() {
		URL url = getURL("img_bulletin.png");

		MartButton but = new MartButton("Bulletin");
		but.setIcon(new ImageIcon(url));

		return but;
	}

	public MartButton getReleve() {
		URL url = getURL("img_bulletin.png");

		MartButton but = new MartButton("Releves");
		but.setIcon(new ImageIcon(url));

		return but;
	}

	public MartButton getNote() {
		URL url = getURL("img_note.png");

		MartButton but = new MartButton("Note");
		but.setIcon(new ImageIcon(url));

		return but;
	}

	public MartButton getConfiguration() {
		URL url = getURL("img_config.png");

		MartButton but = new MartButton("Configurations");
		but.setIcon(new ImageIcon(url));

		return but;
	}

	public MartButton getRedemarrer() {
		URL url = getURL("img_redemarrer.png");

		MartButton but = new MartButton("Redémarrer");
		but.setIcon(new ImageIcon(url));

		return but;
	}

	public MartButton getReinitialiser() {
		URL url = getURL("img_reinitialiser.png");

		MartButton but = new MartButton("Réinitialiser");
		but.setIcon(new ImageIcon(url));

		return but;
	}

	public MartButton getFusion() {
		URL url = getURL("img_fusion.png");

		MartButton but = new MartButton("Fusion");
		but.setIcon(new ImageIcon(url));

		return but;
	}

	public MartButton getSection() {
		URL url = getURL("img_section.png");

		MartButton but = new MartButton("Sectionner");
		but.setIcon(new ImageIcon(url));

		return but;
	}

	public MartButton getCaisseEcolage() {
		URL url = getURL("img_caisse.png");

		MartButton but = new MartButton("Caisse");
		but.setIcon(new ImageIcon(url));

		return but;
	}

	public MartButton getAudit() {
		URL url = getURL("img_saudit.png");

		MartButton but = new MartButton("Audit");
		but.setIcon(new ImageIcon(url));

		return but;
	}

	public MartButton getCDI() {
		URL url = getURL("img_cdi.png");

		MartButton but = new MartButton(
				"<html><div>CENTRE DE DOCUMENTATION</div><div align=center>ET D'INFORMATION</div></html>");
		but.setIcon(new ImageIcon(url));

		return but;
	}

	public MartButton getPayement() {
		MartButton but = new MartButton("Payer");

		URL url = getURL("img_payer.png");
		but.setIcon(new ImageIcon(url));
		return but;
	}

	public MartButton getAnnulerPayement() {
		MartButton but = new MartButton("Annuler Payement");

		URL url = getURL("img_annuler_payement.png");
		but.setIcon(new ImageIcon(url));
		return but;
	}

	public MartButton getGestionSalaire() {
		MartButton but = new MartButton("Salaires");

		URL url = getURL("img_salaire.png");
		but.setIcon(new ImageIcon(url));
		return but;
	}

	public MartButton getAccorderAvanceSalaire() {
		MartButton but = new MartButton("Avance sur Salaire");

		URL url = getURL("img_avance.png");
		but.setIcon(new ImageIcon(url));
		return but;
	}

	public MartButton getDefinirSalaire() {
		MartButton but = new MartButton("Définir le salaire");

		return but;
	}

	public MartButton getHelp() {
		MartButton but = new MartButton("Aide");

		URL url = getURL("img_help.png");
		but.setIcon(new ImageIcon(url));
		return but;
	}

	public MartButton getPhoto() {
		MartButton but = new MartButton("Photo");

		URL url = getURL("img_app_photo.png");
		but.setIcon(new ImageIcon(url));
		return but;
	}

	public MartButton getExcel() {
		MartButton but = new MartButton("EXCEL");

		URL url = getURL("img_excel.png");
		but.setIcon(new ImageIcon(url));
		return but;
	}

	public MartButton getCsv() {
		MartButton but = new MartButton("CSV");

		URL url = getURL("img_csv.png");
		but.setIcon(new ImageIcon(url));
		return but;
	}

	public MartButton getExport() {
		MartButton but = new MartButton("Export");

		URL url = getURL("img_export.png");
		but.setIcon(new ImageIcon(url));
		return but;
	}

	public MartButton getRecondure() {
		MartButton but = new MartButton("Reconduire");

		URL url = getURL("img_reporter.png");
		but.setIcon(new ImageIcon(url));
		return but;
	}

	public MartButton getDownload() {
		MartButton but = new MartButton("Charger");

		URL url = getURL("img_importer.png");
		but.setIcon(new ImageIcon(url));
		return but;
	}

	public MartButton getDiminuer() {
		MartButton but = new MartButton("Diminuer");

		URL url = getURL("img_diminuer.png");
		but.setIcon(new ImageIcon(url));
		return but;
	}

	public MartButton getZoomP() {
		MartButton but = new MartButton();

		URL url = getURL("img_zoom_p.png");
		but.setIcon(new ImageIcon(url));
		return but;
	}

	public MartButton getZoomM() {
		MartButton but = new MartButton();

		URL url = getURL("img_zoom_m.png");
		but.setIcon(new ImageIcon(url));
		return but;
	}
}
