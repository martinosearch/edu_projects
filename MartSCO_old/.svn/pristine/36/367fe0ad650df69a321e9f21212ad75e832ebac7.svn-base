package graphicsModel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;

public class VerticalToolBar extends JPanel {
	private JPanel container;
	private MartList<Component> listeComponent;

	public VerticalToolBar() {
		container = new JPanel();
		container.setLayout(new FlowLayout());
		listeComponent = new MartList<Component>();

		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(110, 500));
		add(container, BorderLayout.CENTER);
	}

	public Component add(Component comp) {
		comp.setPreferredSize(MartFrame.TOOL_BUTTON_SIZE);
		listeComponent.add(comp);
		container.removeAll();

		for (Component c : listeComponent) {
			container.add(c);
		}

		container.revalidate();
		return comp;
	}

	public Component[] getComponents() {
		Component[] list = new Component[listeComponent.size()];
		int i = 0;
		for (Component comp : listeComponent) {
			list[i] = comp;
			i++;
		}
		return list;
	}
}
