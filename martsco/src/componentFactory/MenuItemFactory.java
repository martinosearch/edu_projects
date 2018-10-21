package componentFactory;

import graphicsModel.OptionItem;

public class MenuItemFactory {

	public OptionItem getUtilisateur() {
		OptionItem item = new OptionItem("img_utilisateur.png", "Utilisateur");
		return item;
	}

	public OptionItem getMiseAjour() {
		OptionItem item = new OptionItem("img_annee.png", "Année");
		return item;
	}

	public OptionItem getDataBase() {
		OptionItem item = new OptionItem("img_db.png", "Base de données");
		return item;
	}

	public OptionItem getNiveau() {
		OptionItem item = new OptionItem("img_niveau.png",
				"Niveau d'Enseignement");
		return item;
	}

	public OptionItem getRegister() {
		OptionItem item = new OptionItem("img_register.png", "Enrégistrer");
		return item;
	}
}
