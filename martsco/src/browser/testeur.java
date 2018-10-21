package browser;

import java.awt.Component;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JTextField;

import connection.DAO;

public class testeur extends JFrame {
	static DAO matdao, matpdao, elvdao, elvclsdao, promodao;
	private JTextField tfRemise;

	public testeur() {
		this.setSize(400, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		initComponent();
		this.setVisible(true);
	}

	private void initComponent() {
		tfRemise = new JTextField("tu es fort");

		GroupLayout layout = new GroupLayout(this.getContentPane());
		getContentPane().setLayout(layout);

		layout.setHorizontalGroup(layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addComponent(tfRemise));

		layout.setVerticalGroup(layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addComponent(tfRemise));
	}

	public static void main(String[] args) {
		String str1 = "b ";
		String str2 = "Dictée- questions";

		System.out.println(str1.trim().length());

	}

}
