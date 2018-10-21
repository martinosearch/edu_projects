package progress;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

public class GreatUser extends JWindow {
	protected int i = 0, max;
	protected Font police1 = new Font("Courrier", Font.TYPE1_FONT, 50);
	protected JPanel container;
	private String texte;

	public GreatUser(String t) {
		texte = "Bienvenue " + t;
		max = texte.length();
		this.setSize(800, 150);
		this.setLocationRelativeTo(null);
		this.setModalExclusionType(null);

		initComponent();

		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(container, BorderLayout.CENTER);
		this.setVisible(true);
	}

	private void initComponent() {
		container = new JPanel();
		container.setLayout(new BorderLayout());

		final JLabel lb = new JLabel();
		lb.setFont(police1);
		lb.setForeground(Color.BLUE);
		container.add(lb);

		new Thread(new Runnable() {
			public void run() {
				String aff = "";
				while (i < max) {
					try {
						aff += texte.charAt(i);
						lb.setText(aff);
						Thread.sleep(150);
						i++;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				stop();
			}
		}).start();
	}

	public boolean stop() {
		this.dispose();
		return true;
	}

	public static void main(String[] args) {
		new GreatUser("Martin");
	}

}
