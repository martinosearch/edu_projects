package componentFactory;

import graphicsModel.OptionItem;
import help.Help;

import java.awt.Component;
import java.awt.event.MouseEvent;

public class ItemsApropos extends ItemsEditorPanel {

	private OptionItem guide;

	public ItemsApropos() {
		setTitre("A propos");
		guide = new OptionItem("img_help.png", "Guide d'utilisation");
		addElement(guide);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Component source = (Component) e.getSource();

		if (source == guide) {
			Help help = Help.getInstance();
			help.getType(help.USER_HELP);
			help.setVisible(true);
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
