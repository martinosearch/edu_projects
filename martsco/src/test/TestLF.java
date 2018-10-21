package test;

import java.awt.Container;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class TestLF extends JFrame {
	public TestLF() {
		super("Test L&F");
		createJMenuBar();
		createComposants();
		setSize(200, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	public Map<String, String> getLookAndFeelsMap() {
		UIManager.LookAndFeelInfo[] info = UIManager.getInstalledLookAndFeels();
		Map<String, String> map = new TreeMap<String, String>();
		for (int i = 0; i < info.length; i++) {
			String nomLF = info[i].getName();
			String nomClasse = info[i].getClassName();
			map.put(nomLF, nomClasse);
		}
		return map;
	}

	protected void createJMenuBar() {
		JMenuBar bar = new JMenuBar();
		JMenu menu = new JMenu("L&F");
		ButtonGroup bg = new ButtonGroup();
		Map<String, String> map = getLookAndFeelsMap();

		for (String clef : map.keySet()) {
			final String classe = map.get(clef);
			boolean natif = classe.equals(UIManager
					.getSystemLookAndFeelClassName());

			JRadioButtonMenuItem item = new JRadioButtonMenuItem(clef, natif);

			item.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					try {
						UIManager.setLookAndFeel(classe);
					} catch (Exception e) {
						e.printStackTrace();
					}
					System.out.println(TestLF.this);
					SwingUtilities.updateComponentTreeUI(TestLF.this);
				}
			});
			bg.add(item);
			menu.add(item);
		}
		bar.add(menu);
		setJMenuBar(bar);
	}

	protected void createComposants() {
		Container c = getContentPane();
		c.setLayout(new GridLayout(3, 2));
		c.add(new JLabel("JLabel"));
		c.add(new JButton("JButton"));
		c.add(new JTextField("JTextField"));
		c.add(new JRadioButton("JRadioButton"));
		c.add(new JComboBox(new String[] { "un", "deux", "trois", "quatre",
				"cinq", "six" }));
		JTextArea area = new JTextArea();
		for (int i = 0; i < 10; i++) {
			area.append("ligne " + i + "\n");
		}
		c.add(new JScrollPane(area));
		setContentPane(c);

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		SwingUtilities.updateComponentTreeUI(this);
	}

	public static void main(String[] args) {
		new TestLF();
	}
}