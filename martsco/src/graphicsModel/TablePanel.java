package graphicsModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import org.joda.time.DateTime;

public class TablePanel extends JPanel implements MouseListener {
	private Dimension compDim = MartFrame.SMALL_FRAME;
	private MartList<OptionItem> listeItem = new MartList<OptionItem>();
	private JPanel panItem;
	private JScrollPane scroll;

	public TablePanel(Dimension dimFrame) {
		panItem = new JPanel(new FlowLayout());
		setLayout(new BorderLayout());
		panItem.setBackground(getBackground());

		compDim = dimFrame;

		scroll = new JScrollPane(panItem);

		// scroll = new JScrollPane(table);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		add(scroll, BorderLayout.CENTER);
	}

	public void addItem(OptionItem item) {
		item.setBackground(panItem.getBackground());
		item.addMouseListener(this);
		// item.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		listeItem.add(item);
		item.setPreferredSize(new Dimension((int) compDim.getWidth(), 60));
		panItem.setPreferredSize(new Dimension(compDim.width, 70 * listeItem
				.size()));

		// panItem.removeAll();

		/*
		 * Object[][] data = new Object[listeItem.size()][1]; String[] title =
		 * new String[1];
		 * 
		 * int i = 0;
		 */
		// for (OptionItem it : listeItem) {
		panItem.add(item);
		// data[i][0] = it;
		// i++;
		// System.out.println("taille: " + it.toString());
		// }

		/*
		 * MartTabModel mod = new MartTabModel(data, title); MartTable table =
		 * new MartTable(mod); table.getColumnModel().getColumn(0)
		 * .setCellRenderer(new OptionItemRenderer()); table.setRowHeight(40);
		 */

		panItem.revalidate();

	}

	public MartList<OptionItem> getListeItem() {
		return listeItem;
	}

	public JScrollPane getScroll() {
		return scroll;
	}

	public void setScroll(JScrollPane scroll) {
		this.scroll = scroll;
	}

	public void setListeItem(MartList<OptionItem> listeItem) {
		this.listeItem = listeItem;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Component source = (Component) e.getSource();
		for (OptionItem item : listeItem) {
			if (item == source) {
				// le traitement se fait déja dans item
			} else {
				item.setSelected(false);
			}
			item.update();
			item.revalidate();
		}
		panItem.revalidate();
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
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void removeAll() {
		super.removeAll();
		listeItem = new MartList<OptionItem>();
		revalidate();
	}

	public void setBarPosition(int i) {
		JScrollBar bar = scroll.getVerticalScrollBar();
		bar.setValue(i);
	}

	public void setSelectedItem(int num) {
		int i = 0;
		for (OptionItem item : listeItem) {
			if (i == num) {
				item.setSelected(true);
			} else {
				item.setSelected(false);
			}
			item.update();
			i++;
		}
		panItem.revalidate();
	}

	public void setActionListener(Object listener) {
		for (OptionItem it : listeItem) {
			it.addListener(listener);
		}
	}

	public void reset() {
		System.out.println("Réinitialisation des composants");
		for (OptionItem item : listeItem) {
			item.setSelected(false);
			item.update();
		}
		panItem.revalidate();
	}
}
