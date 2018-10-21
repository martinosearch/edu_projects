package browser;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class testRel extends JFrame {

	public testRel() {
		this.setSize(300, 300);
		this.setTitle("Demonstraction de programmation");

		JPanel pan = new JPanel();
		pan.setLayout(new GridLayout(3, 1));
		JLabel lb = new JLabel(
				"M. AGOKPA Comment vous trouvvez cette petite démonstration?");

		lb.setFont(new Font("courrier", Font.PLAIN, 12));
		pan.add(lb);
		pan.add(new JButton("Valider"));

		this.getContentPane().add(pan);
	}

	public static void main(String[] args) {
		new testRel().setVisible(true);
	}
}
