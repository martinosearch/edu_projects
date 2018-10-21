package componentFactory;

import graphicsModel.MartList;
import graphicsModel.OptionItem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public abstract class ItemsEditorPanel extends JPanel implements MouseListener {
	protected MartList<OptionItem> listeItem;
	protected JPanel container;
	protected Dimension compDim = new Dimension(400, 50);
	protected JPanel panTitre;
	protected Font font = new Font("Arial", Font.BOLD, 30);

	public ItemsEditorPanel() {
		// setSize(SMALL_FRAME);
		setLayout(new BorderLayout());
		panTitre = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panTitre.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		container = new JPanel(new FlowLayout());
		listeItem = new MartList<OptionItem>();

		add(panTitre, BorderLayout.NORTH);
		add(container, BorderLayout.CENTER);
	}

	public void addElement(OptionItem item) {
		item.setPreferredSize(compDim);
		item.addListener(this);

		listeItem.add(item);
		container.removeAll();

		for (OptionItem it : listeItem) {
			container.add(it);
		}
		container.revalidate();
		container.setPreferredSize(new Dimension((int) compDim.getWidth(),
				(int) (compDim.getHeight() * listeItem.size()) + 40));
	}

	public void setActionListener(Object listener) {
		for (OptionItem it : listeItem) {
			it.addListener(listener);
		}
	}

	public void setTitre(String str) {
		JLabel lb = new JLabel(str);
		lb.setFont(font);
		lb.setForeground(new Color(60, 115, 150));
		panTitre.add(lb);
	}

	public void removeAll() {
		container.removeAll();
		listeItem = new MartList<OptionItem>();
	}

	public void reset() {
		for (OptionItem item : listeItem) {
			item.setSelected(false);
			item.update();
		}
	}
}
