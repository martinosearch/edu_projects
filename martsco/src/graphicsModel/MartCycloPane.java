package graphicsModel;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JButton;

public class MartCycloPane extends MartImagePane implements MouseWheelListener {

	Component[] listComp;
	private boolean isCercle;

	public MartCycloPane(boolean cercle) {
		isCercle = cercle;
		this.setLayout(new CircleLayout(cercle));
		this.addMouseWheelListener(this);
	}

	public void paint(Graphics g) {
		super.paint(g);
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if (e.getWheelRotation() == 1) {
			EventQueue.invokeLater(new Runnable() {

				public void run() {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					turnIndirect();
				}
			});
		} else {
			EventQueue.invokeLater(new Runnable() {

				public void run() {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					turnDirect();
				}
			});
		}

	}

	protected synchronized void turnIndirect() {
		listComp = this.getComponents();
		int size = listComp.length;
		Component[] temp = new Component[size];
		int pos = 0;

		for (Component c : listComp) {
			if (pos > 0) {
				temp[pos] = listComp[pos - 1];
			}
			pos++;
		}

		temp[0] = listComp[size - 1];

		try {
			this.removeAll();
		} catch (Exception e2) {
			e2.printStackTrace();
		}

		try {
			for (Component c : temp) {
				this.add(c);
				c.setEnabled(true);
			}
		} catch (Exception e) {

		}

		this.revalidate();
		this.repaint();
	}

	protected synchronized void turnDirect() {
		listComp = this.getComponents();
		int size = listComp.length;
		Component[] temp = new Component[size];
		int pos = 0;

		for (Component c : listComp) {
			if (pos < size - 1) {
				temp[pos] = listComp[pos + 1];
			}
			pos++;
		}

		temp[size - 1] = listComp[0];

		try {
			this.removeAll();
		} catch (Exception e2) {
			e2.printStackTrace();
		}

		try {
			for (Component c : temp) {
				this.add(c);
				c.setEnabled(true);
			}
		} catch (Exception e) {

		}

		this.revalidate();
		this.repaint();
	}

}
