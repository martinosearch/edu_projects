package etablissement;

import graphicsModel.MartFrame;
import interfacePerso.Observer;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import progress.Avancer;
import abstractObject.AbstractControler;
import abstractObject.AbstractModel;

import componentFactory.MartButton;

public class AjouterEts extends MartFrame implements Observer {

	Font police1 = new Font("Times new Romam", Font.TYPE1_FONT, 16);
	private JPanel panBut;
	private MartButton bValider = new MartButton().getValider();
	private MartButton bAnnuler = new MartButton().getAnnuler();
	private Etablissement etablissement;
	private JPanel pan2 = new JPanel();
	private JPanel panbut = new JPanel();

	private JLabel lbIntitule = new JLabel("Ets");
	private JLabel lbNom = new JLabel("Nom");
	private JLabel lbContact = new JLabel("Contact");

	private static JTextField tfNom = new JTextField();
	private JComboBox cbIntitule;
	private JTextField tfContact = new JTextField();

	private static AjouterEts instance;
	private Dimension dim1 = new Dimension(100, 30);

	public AjouterEts() {
		setSize(400, 200);
		setLocation(MEDIUM_FRAME_CHOOSER_LOCATION);
		setTitle("Nouveau Etablissement");

		initComponent();

		getContentPane().add(container, BorderLayout.CENTER);
	}

	private void initComponent() {
		// champ de saisie
		tfNom.setPreferredSize(dim1);
		tfNom.setFont(police1);

		String[] datacb = { "***", "CEG", "LYCEE", "CPL", "EPP", "EPC", "EPE",
				"EPB", "EPL" };
		cbIntitule = new JComboBox(datacb);
		cbIntitule.setPreferredSize(dim1);
		cbIntitule.setFont(police1);
		tfContact.setPreferredSize(dim1);
		tfContact.setFont(police1);

		// ajout aux contenurs
		pan2.setLayout(new GridLayout(3, 2, 2, 2));
		pan2.setBorder(BorderFactory
				.createTitledBorder("Nouveau Etablissement"));
		panbut.setLayout(new GridLayout(1, 3, 2, 2));
		panbut.setPreferredSize(new Dimension(578, 30));

		load();
		refresh();

		pan2.add(lbIntitule);
		pan2.add(cbIntitule);
		pan2.add(lbNom);
		pan2.add(tfNom);
		pan2.add(lbContact);
		pan2.add(tfContact);

		bValider.addActionListener(this);
		bAnnuler.addActionListener(this);

		panBut = new JPanel();
		panBut.add(bValider);
		panBut.add(bAnnuler);
		container = new JPanel(new BorderLayout());
		container.add(pan2, BorderLayout.CENTER);
		container.add(panBut, BorderLayout.SOUTH);
	}

	public static void main(String[] args) {
		getInstance().setVisible(true);
	}

	@Override
	public Avancer getAvancer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Component source = (Component) e.getSource();
		if (source == bValider) {
			AbstractModel model = new EtsModel();
			AbstractControler controler = new EtsControler(model);
			model.addObserver(this);

			Etablissement ets = new Etablissement(getIntitule(), getNom(),
					getContact());
			controler.setData(ets);
			model.setData(ets);

			controler.valider();

			close();
		}

		if (source == bAnnuler) {
			close();
		}
	}

	public static String getNom() {
		return tfNom.getText();
	}

	public String getIntitule() {
		return (String) cbIntitule.getSelectedItem();
	}

	public String getContact() {
		return tfContact.getText();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

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
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void load() {
		// TODO Auto-generated method stub

	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub

	}

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	public static AjouterEts getInstance() {
		if (instance == null) {
			instance = new AjouterEts();
		}

		return instance;
	}

	public void setObject(Etablissement ets) {
		// initialisation
		etablissement = ets;
		tfNom.setText(etablissement.getNom());
		cbIntitule.setSelectedItem(etablissement.getIntitule());
		tfContact.setText(etablissement.getContact());
	}

	@Override
	public void update() {
		((Observer) parent).update();
	}
}
