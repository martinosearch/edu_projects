package progress;

import function.Constance;
import graphicsModel.MartImage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;
import java.net.URISyntaxException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;

public class Welcome extends JWindow {
	JProgressBar barre;
	JPanel panb = new JPanel();
	static int i = 0;
	Font police1 = new Font("mistral", Font.TYPE1_FONT, 200);
	private MartImage container;

	public Welcome(int type, int max) {
		this.setSize(600, 150);
		this.setLocationRelativeTo(null);
		this.setModalExclusionType(null);

		initComponent();

		go(type, max);
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(container, BorderLayout.CENTER);
		this.setVisible(true);
	}

	private void initComponent() {
		try {
			container = new MartImage(getClass().getClassLoader().getResource(
					Constance.getImageFolder() + "img_welcome.jpg"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		container.setLayout(new BorderLayout());

	}

	public void go(int type, final int max) {
		if (type == 1) {
			new Thread(new Runnable() {
				public void run() {
					barre = new JProgressBar(0, max);
					barre.setForeground(Color.GREEN);
					barre.setPreferredSize(new Dimension(600, 7));
					panb.setLayout(new GridLayout(1, 1));
					panb.add(barre);
					container.add(panb, BorderLayout.SOUTH);
					container.revalidate();

					while (i <= max) {
						try {
							Thread.sleep(17);
							barre.setValue(i);
							i++;
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					stop();
				}
			}).start();
		}

		// 1ère animation martSCO
		if (type == 2) {
			panb.setLayout(new GridLayout(1, 1));
			final JLabel lb = new JLabel();
			lb.setFont(police1);
			lb.setForeground(Color.BLUE);
			panb.add(lb);

			new Thread(new Runnable() {
				public void run() {
					while (i <= max) {
						try {
							Thread.sleep(1000);
							if (i == 1)
								lb.setText("M");
							if (i == 2)
								lb.setText("Ma");
							if (i == 3)
								lb.setText("Mar");
							if (i == 4)
								lb.setText("Mart");
							if (i == 5)
								lb.setText("MartS");
							if (i == 6)
								lb.setText("MartSC");
							if (i == 7)
								lb.setText("MartSCO");
							panb.revalidate();
							i++;
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					stop();
				}
			}).start();
		}
	}

	public boolean stop() {
		this.dispose();
		return true;
	}

	public static void main(String[] args) {
		new Welcome(2, 8);
	}

}
