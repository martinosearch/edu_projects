package progress;

import graphicsModel.MartDialog;
import graphicsModel.MartFrame;
import graphicsModel.MartPanel;
import interfacePerso.MartRangeable;
import interfacePerso.Observer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Window;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

import abstractObject.AbstractChooser;

public class Progress implements MartRangeable, Observer {
	public static int FIN = 1;
	private JPanel panel = new JPanel();
	private Window frame;
	private martProgressBar barre;
	private Color color = Color.GREEN;
	private int avancement = 0, n = 0;
	private int min, max;
	private Thread avanceur;
	private JLabel label = new JLabel("Veuillez patienter", JLabel.CENTER);
	private String chargement = "Chargement en cours";
	private String traitement = "Traitement en cours";
	private int rang = 0;
	private String intitule = "martProgress_Default";
	public JLabel lbpercent = new JLabel("0%", JLabel.CENTER);
	public boolean go = true;
	private Avancer avancer;
	private boolean showPercent = true;
	private MartPanel panelContainer;
	protected boolean removeAfter = true;
	private boolean writeTermine = true;

	public Progress() {
		avancer = new Avancer();
		avancer.addObserver(this);
	}

	public void getProgress(MartFrame frame, int min2, int max2) {
		go = false;
		this.frame = frame;
		min = min2;
		max = max2;

		// r�initialisation
		avancement = 0;
		n = 0;
		// *********fin************

		panel.setLayout(new GridLayout(3, 1));
		barre = new martProgressBar();
		JPanel pan1 = new JPanel();
		JPanel pan2 = new JPanel();
		JPanel pan3 = new JPanel();

		label.setForeground(Color.WHITE);
		lbpercent.setForeground(Color.WHITE);
		pan1.setBackground(Color.GRAY);
		pan3.setBackground(Color.GRAY);

		pan1.setLayout(new BorderLayout());
		pan2.setLayout(new BorderLayout());
		barre.setPreferredSize(new Dimension(frame.getWidth(), 10));
		pan1.setPreferredSize(new Dimension(frame.getWidth(), 10));
		pan2.setPreferredSize(new Dimension(frame.getWidth(), 10));
		pan3.setPreferredSize(new Dimension(frame.getWidth(), 10));

		pan1.add(label, BorderLayout.WEST);
		pan1.add(lbpercent, BorderLayout.EAST);
		pan2.add(barre, BorderLayout.CENTER);

		panel.removeAll();
		panel.add(pan1);
		panel.add(pan2);
		panel.add(pan3);

		System.out.println("LANCEMENT DE LA BARRE DE PROGRESSION " + max);
		waiter();

		frame.getPanEtat().removeAll();
		frame.getPanEtat().add(panel, BorderLayout.CENTER);
		frame.getPanEtat().revalidate();
		frame.revalidate();
		frame.repaint();
	}

	public void getLoading(MartFrame frame2, String text) {
		chargement = text;
		frame = frame2;
		((MartFrame) frame).getPanEtat().removeAll();

		((MartFrame) frame).getPanEtat().setLayout(new BorderLayout());
		final JLabel label2 = new JLabel(chargement);
		((MartFrame) frame).getPanEtat().add(label2, BorderLayout.WEST);
		((MartFrame) frame).getPanEtat().revalidate();
		((MartFrame) frame).getContentPane().revalidate();
		frame.repaint();

		new Thread(new Runnable() {
			public void run() {
				int index = 0;
				while (go == true) {
					if (index == 0)
						label2.setText(chargement);
					if (index == 1)
						label2.setText(chargement + " .  ");
					if (index == 2)
						label2.setText(chargement + " . .");
					if (index == 3)
						label2.setText(chargement + " . . .");
					index++;
					if (index > 3)
						index = 0;
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
					}
				}
			}
		}).start();
	}

	public void getProgress(MartPanel frame, int min2, int max2) {
		go = false;
		this.panelContainer = frame;
		min = min2;
		max = max2;

		// réinitialisation
		avancement = 0;
		n = 0;
		removeAfter = false;
		// *********fin************

		panel.setLayout(new GridLayout(3, 1));
		barre = new martProgressBar();
		JPanel pan1 = new JPanel();
		JPanel pan2 = new JPanel();
		JPanel pan3 = new JPanel();

		label.setForeground(Color.WHITE);
		lbpercent.setForeground(Color.WHITE);
		pan1.setBackground(Color.GRAY);
		pan3.setBackground(Color.GRAY);

		pan1.setLayout(new BorderLayout());
		pan2.setLayout(new BorderLayout());
		barre.setPreferredSize(new Dimension(frame.getWidth(), 10));
		pan1.setPreferredSize(new Dimension(frame.getWidth(), 10));
		pan2.setPreferredSize(new Dimension(frame.getWidth(), 10));
		pan3.setPreferredSize(new Dimension(frame.getWidth(), 10));

		pan1.add(label, BorderLayout.WEST);
		pan1.add(lbpercent, BorderLayout.EAST);
		pan2.add(barre, BorderLayout.CENTER);

		panel.removeAll();
		panel.add(pan1);
		panel.add(pan2);
		panel.add(pan3);

		System.out.println("LANCEMENT DE LA BARRE DE PROGRESSION " + max);
		waiter();

		panelContainer.getPanneau().removeAll();
		panelContainer.getPanneau().add(panel, BorderLayout.CENTER);
		panelContainer.getPanneau().revalidate();

		panelContainer.revalidate();
		panelContainer.repaint();
	}

	public void setColor(Color color) {
		this.color = color;
		barre.setForeground(color);
		barre.revalidate();
	}

	public void waiter() {
		new Thread(new Runnable() {
			public void run() {
				int index = 0;
				go = true;
				while (go) {
					if (index == 0)
						label.setText(traitement);
					if (index == 1)
						label.setText(traitement + " .  ");
					if (index == 2)
						label.setText(traitement + " . .");
					if (index == 3)
						label.setText(traitement + " . . .");
					index++;
					if (index > 3)
						index = 0;
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
					}
				}
			}
		}).start();
	}

	public void increment() {
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				n++;
				avancement++;
				barre.setValue(avancement);

				/*
				 * System.out
				 * .println("VALUEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE" +
				 * avancement);
				 */

				if (showPercent == true) {
					lbpercent.setText(String.valueOf((int) (barre
							.getPercentComplete() * 100)) + "%");
				} else {
					lbpercent.setText("");
				}

				panel.revalidate();

				if (avancement == max) {
					System.out
							.println("==================>>MAXXXXXXXXXXXXXXXXXXXXXXXXX "
									+ avancement);
					if (removeAfter == true)
						stop();
					if (writeTermine = true) {
						setText("terminé");
					}
				}// fin if

			}
		});
	}

	class martProgressBar extends JProgressBar {
		public martProgressBar() {
			super(min, max);
			this.setForeground(color);
		}
	}

	public int getMax() {
		return max;
	}

	public void dispose() {
		Thread stopper = new Thread(new Runnable() {
			public void run() {
				panel.removeAll();
				panel.revalidate();
				panel.repaint();
			}
		});
		if (SwingUtilities.isEventDispatchThread())
			stopper.start();
		else {
			SwingUtilities.invokeLater(stopper);
		}
	}

	public void stop() {
		avancement = 0;
		n = 0;
		go = false;
		avancer.removeObserver();
		dispose();
	}

	// ************CAS D'UN JDIALOG
	public void getProgress(MartDialog frame, int min2, int max2) {
		go = false;
		this.frame = frame;
		min = min2;
		max = max2;

		// r�initialisation
		avancement = 0;
		n = 0;
		// *********fin************

		panel.setLayout(new GridLayout(3, 1));
		barre = new martProgressBar();
		JPanel pan1 = new JPanel();
		JPanel pan2 = new JPanel();
		JPanel pan3 = new JPanel();

		label.setForeground(Color.WHITE);
		lbpercent.setForeground(Color.WHITE);
		pan1.setBackground(Color.GRAY);
		pan3.setBackground(Color.GRAY);

		pan1.setLayout(new BorderLayout());
		pan2.setLayout(new BorderLayout());
		barre.setPreferredSize(new Dimension(frame.getWidth(), 10));
		pan1.setPreferredSize(new Dimension(frame.getWidth(), 10));
		pan2.setPreferredSize(new Dimension(frame.getWidth(), 10));
		pan3.setPreferredSize(new Dimension(frame.getWidth(), 10));

		pan1.add(label, BorderLayout.WEST);
		pan1.add(lbpercent, BorderLayout.EAST);
		pan2.add(barre, BorderLayout.CENTER);

		panel.removeAll();
		panel.add(pan1);
		panel.add(pan2);
		panel.add(pan3);

		System.out.println("LANCEMENT DE LA BARRE DE PROGRESSION " + max);
		waiter();

		frame.getPanEtat().removeAll();
		frame.getPanEtat().add(panel, BorderLayout.CENTER);
		frame.getPanEtat().revalidate();
		frame.revalidate();
		frame.repaint();
	}

	public void getLoading(MartDialog frame2, String text) {
		chargement = text;
		frame = frame2;
		((MartDialog) frame).getPanEtat().removeAll();
		((MartDialog) frame).getPanEtat().setLayout(new BorderLayout());
		final JLabel label2 = new JLabel(chargement);
		((MartDialog) frame).getPanEtat().add(label2, BorderLayout.WEST);
		((MartDialog) frame).getPanEtat().revalidate();
		((MartDialog) frame).getContentPane().revalidate();

		new Thread(new Runnable() {
			public void run() {
				int index = 0;
				while (go == true) {
					if (index == 0)
						label2.setText(traitement);
					if (index == 1)
						label2.setText(traitement + " .  ");
					if (index == 2)
						label2.setText(traitement + " . .");
					if (index == 3)
						label2.setText(traitement + " . . .");
					index++;
					if (index > 3)
						index = 0;
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
					}
				}
			}
		}).start();
	}

	public void setText(String str) {
		this.traitement = str;
	}

	@Override
	public String getIntitule() {
		// TODO Auto-generated method stub
		return this.intitule;
	}

	@Override
	public void setIntitule(String inti) {
		this.intitule = inti;
	}

	@Override
	public int getRang() {
		// TODO Auto-generated method stub
		return this.rang;
	}

	@Override
	public void setRang(int rg) {
		this.rang = rg;
	}

	@Override
	public double getValue() {
		return 0;
	}

	@Override
	public void update() {
		increment();
		setText(avancer.getMessage());
	}

	public Avancer getAvancer() {
		return avancer;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setValue(double val) {
		// TODO Auto-generated method stub

	}

	public void showPercent(boolean b) {
		showPercent = b;
	}

	public void setIsTermineWriter(boolean b) {
		writeTermine = b;
	}

	public boolean isRemoveAfter() {
		return removeAfter;
	}

	public void setRemoveAfter(boolean removeAfter) {
		this.removeAfter = removeAfter;
	}

}
