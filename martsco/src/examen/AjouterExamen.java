package examen;

import graphicsModel.MartFrame;
import graphicsModel.MartList;
import interfacePerso.Observer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
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
import classe.Niveau;

import componentFactory.MartButton;

import connection.DAO;
import connection.DAOFactory;

public class AjouterExamen extends MartFrame implements Observer {
	private static AjouterExamen instance;
	private JPanel pan2 = new JPanel();

	private JLabel lbType = new JLabel("Type");
	private JLabel lbMois = new JLabel("Mois");
	private JLabel lbAnnee = new JLabel("Année");
	private JLabel lbNiveau = new JLabel("Série");
	private JLabel lbInfo = new JLabel("Information");
	private JTextField tfAnnee = new JTextField("2016");
	private JComboBox cbType;
	private JComboBox cbMois;
	private JComboBox cbNiveau;
	private Dimension dim1 = new Dimension(100, 30);
	private static AbstractModel model = new ExamModel();
	private static AbstractControler controler = new ExamControler(model);

	Font police1 = new Font("Times new Romam", Font.TYPE1_FONT, 12);
	private JPanel panBut;
	private MartButton bValider = new MartButton().getValider();
	private MartButton bAnnuler = new MartButton().getAnnuler();
	private Examen examen;
	private DAO nivdao;
	private MartList<Niveau> niveaux;

	public AjouterExamen() {
		setSize(600, 150);
		setLocation(MEDIUM_FRAME_CHOOSER_LOCATION);
		setTitle("Examen");
		initComponent();

		getContentPane().add(container, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		getInstance().setVisible(true);
	}

	private void initComponent() {
		// champ de saisie
		tfAnnee.setPreferredSize(dim1);
		tfAnnee.setFont(police1);
		tfAnnee.setForeground(Color.gray);
		tfAnnee.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				((JTextField) e.getSource()).setText("");
				((JTextField) e.getSource()).setForeground(Color.BLUE);
			}
		});

		String[] datacb1 = { "***", "EVALUATION MENSUELLE", "CEPD- BLANC",
				"BEPC- BLANC", "BAC I BLANC", "BAC II BLANC" };

		String[] datacb2 = { "***", "JANVIER", "FEVRIER", "MARS", "AVRIL",
				"MAI", "JUIN", "JUILLET", "AOUT", "SEPTEMBRE", "OCTOBRE",
				"NOVEMBRE", "DECEMBRE" };

		cbType = new JComboBox(datacb1);
		cbType.setPreferredSize(dim1);
		cbType.setFont(police1);

		cbMois = new JComboBox(datacb2);
		cbMois.setPreferredSize(dim1);
		cbMois.setFont(police1);

		// propri�t�s des label
		lbInfo.setFont(police1);
		lbInfo.setForeground(Color.RED);

		// ajout aux contenurs
		pan2.setLayout(new GridLayout(2, 2, 2, 2));
		pan2.setBorder(BorderFactory.createTitledBorder("Nouveau Examen"));
		pan2.setPreferredSize(new Dimension(580, 70));

		load();
		refresh();

		bValider.addActionListener(this);
		bAnnuler.addActionListener(this);

		panBut = new JPanel();
		panBut.add(bValider);
		panBut.add(bAnnuler);
		container = new JPanel(new BorderLayout());
		container.add(pan2, BorderLayout.CENTER);
		container.add(panBut, BorderLayout.SOUTH);
	}

	public static AjouterExamen getInstance() {
		if (instance == null) {
			instance = new AjouterExamen();
		}
		return instance;
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
			doValider();
		}

		if (source == bValider) {
			close();
		}

	}

	private void doValider() {
		AbstractModel model = new ExamModel();
		AbstractControler controler = new ExamControler(model);
		model.addObserver(this);

		Examen ets = new Examen(getMois(), getAnnee(), getNiveau(),
				getTypeExam());
		controler.setData(ets);
		model.setData(ets);

		controler.valider();

		close();
	}

	private String getTypeExam() {
		return (String) cbType.getSelectedItem();
	}

	private String getNiveau() {
		return (String) cbNiveau.getSelectedItem();
	}

	private String getAnnee() {
		return tfAnnee.getText();
	}

	private String getMois() {
		return (String) cbMois.getSelectedItem();
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
		nivdao = DAOFactory.getDAO(DAO.NIVEAU);
	}

	@Override
	public void refresh() {
		nivdao.load();
		niveaux = nivdao.getListObt();

		cbNiveau = new JComboBox();
		cbNiveau.setPreferredSize(dim1);
		cbNiveau.setFont(police1);

		for (Niveau niv : niveaux) {
			cbNiveau.addItem(niv.getIntitule());
		}

		pan2.removeAll();
		pan2.add(lbType);
		pan2.add(cbType);
		pan2.add(lbMois);
		pan2.add(cbMois);
		pan2.add(lbAnnee);
		pan2.add(tfAnnee);
		pan2.add(lbNiveau);
		pan2.add(cbNiveau);
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

	@Override
	public void update() {
		((Observer) parent).update();
		try {
			Thread.sleep(2000);
			lbInfo.setText("");
			// on réinitialise les champs de text
			cbType.setSelectedIndex(0);
			cbMois.setSelectedIndex(0);
			cbNiveau.setSelectedIndex(0);
			load();
			refresh();
			this.repaint();

			// fermer
			close();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void setObject(Examen exam) {
		examen = exam;

		cbType.setSelectedItem(exam.getType());
		cbMois.setSelectedItem(exam.getMois());
		cbNiveau.setSelectedItem(exam.getNiveau());
		tfAnnee.setText(exam.getAnnee());
	}
}
