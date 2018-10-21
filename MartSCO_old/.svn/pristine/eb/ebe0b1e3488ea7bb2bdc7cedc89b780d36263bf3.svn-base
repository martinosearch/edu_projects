package graphicsModel;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public abstract class OptionEditorFrame extends MartFrame {
	private Dimension compDim;
	protected TablePanel panItem;

	public OptionEditorFrame() {
		setSize(MEDIUM_FRAME);
		setToolBar();

		compDim = new Dimension(getWidth() - 30, 60);

		container = new JPanel(new BorderLayout());

		refreshItems();

		setLocation(MartFrame.INTERNAL_FRAME_LOCATION);
		// setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		addWindowListener(this);

		getContentPane().add(container, BorderLayout.CENTER);
	}

	public MartList<OptionItem> getItems() {
		return panItem.getListeItem();
	}

	public void addItem(final OptionItem item) {
		item.setPreferredSize(compDim);
		item.addListener(this);

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				panItem.addItem(item);
			}
		});

		panItem.revalidate();
	}

	public void refreshItems() {
		container.removeAll();
		panItem = new TablePanel(MEDIUM_FRAME);
		panItem.setPreferredSize(compDim);
		container.add(panItem, BorderLayout.CENTER);
	}

	public void setActionListener(Object listener) {
		panItem.setActionListener(listener);
	}

	public void reset() {
		panItem.reset();
	}
}
