package componentFactory;

import function.GeneralVoid;
import graphicsModel.OptionItem;

import java.awt.Component;
import java.awt.event.MouseEvent;

import security.NouveauUser;
import accueil.AccueilSCO;
import connection.OptionDataBase;

public class ItemsApplication extends ItemsEditorPanel {
	private OptionItem dataBase;
	private OptionItem config;
	private OptionItem user;

	public ItemsApplication() {
		setTitre("Application");
		dataBase = new OptionItem("img_db.png", "Base de données");
		user = new OptionItem("img_user.png", "Utilisateurs");
		config = new OptionItem("img_reglage.png", "Configuration");

		addElement(dataBase);
		addElement(user);
		addElement(config);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Component source = (Component) e.getSource();

		if (source == dataBase) {
			OptionDataBase db = new OptionDataBase().getInstance();
			db.setVisible(true);
			db.setParent(AccueilSCO.getInstance());
		}

		if (source == config) {
			new GeneralVoid().configApplication();
		}

		if (source == user) {
			NouveauUser.getInstance().setVisible(true);
		}
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
