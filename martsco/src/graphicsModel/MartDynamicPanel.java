package graphicsModel;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;

/**
 * Classe panel dynamique. C'est un panel qui modifie sa propre taille en
 * fonction des sous panel que l'on y ajoute.
 * 
 * @author martino
 *
 */
public class MartDynamicPanel extends JPanel {
	MartList<Component> liste = new MartList<Component>();

	public MartDynamicPanel() {
		this.setLayout(new FlowLayout());
	}

	public void addContenu(JPanel pan) {
		liste.add(pan);

		this.removeAll();

		System.out.println("Je contient de composant: " + liste.size());
		this.setLayout(new FlowLayout());
		this.setPreferredSize(new Dimension(this.getWidth(), (150 + liste
				.size() * 75)));

		for (Component comp : liste) {
			this.add(comp);
		}

		this.revalidate();
		this.repaint();
	}

	public void removeContenu(JPanel pan) {
		MartList<Component> temp = new MartList<Component>();

		for (Component comp : liste) {
			if (comp != pan) {
				temp.add(comp);
			}
		}
		liste = temp;

		System.out.println("Je contient de composant: " + liste.size());
		this.removeAll();
		this.setLayout(new FlowLayout());
		this.setPreferredSize(new Dimension(this.getWidth(), (150 + liste
				.size() * 75)));

		for (Component comp : liste) {
			this.add(comp);
		}

		this.revalidate();
		this.repaint();
	}
}
