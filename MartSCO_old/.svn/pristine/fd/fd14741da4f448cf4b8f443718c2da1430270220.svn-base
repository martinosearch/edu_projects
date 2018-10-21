package componentFactory;

import graphicsModel.MartFrame;
import graphicsModel.OptionItem;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import rapportBulletin.DocumentAdmin;
import annee.ChooserAnnee;
import configurationAdmin.ConfigAdmin;

public class ItemsAdmin extends ItemsEditorPanel {

	private OptionItem doc;
	private OptionItem reglage;

	public ItemsAdmin() {
		setTitre("Administration");
		doc = new OptionItem(
				"img_rapport.png",
				"<div>Documents officiels<div><div id='explication'>Fiche exemplaire, Attestions...</div>");
		reglage = new OptionItem("img_reglage.png",
				"<div>Personnaliser</div><div id='explication'>Attribution de fonction</div>");

		addElement(doc);
		addElement(reglage);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Component source = (Component) e.getSource();
		final ChooserAnnee chooser = ChooserAnnee.getInstance();
		chooser.setTrimestreChoosing(false);
		chooser.setLocation(MartFrame.INTERNAL_FRAME_LOCATION);

		if (source == doc) {
			new DocumentAdmin().setVisible(true);
		}

		if (source == reglage) {
			chooser.setAction(new ActionListener() {
				private ConfigAdmin fr;

				public void actionPerformed(ActionEvent arg0) {
					fr = new ConfigAdmin(0, chooser.getAnnee());
					fr.setVisible(true);
					chooser.close();
				}
			});
			chooser.setVisible(true);
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
