package security;

import graphicsModel.MartFrame;
import graphicsModel.MartList;
import interfacePerso.Observer;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import agent.Agent;
import componentFactory.MartButton;
import connection.DAO;
import connection.DAOFactory;

public class AjouterUser extends MartFrame {
	private JTextField tfLogin = new JTextField();
	private JTextField tfPassWord = new JTextField();
	private User user;

	private JComboBox cbType = new JComboBox(new String[] { "ADMINISTRATEUR",
			"OPS", "SECRETAIRE", "COMPTABLE" });

	private JComboBox cbNiveau = new JComboBox(new String[] { "1", "2", "3",
			"4", "5" });
	private JComboBox cbNom;

	private Dimension dim1 = new Dimension(100, 20);
	private static AjouterUser instance;
	private Font police1 = new Font("Times new Romam", Font.TYPE1_FONT, 12);
	private JPanel panChamp = new JPanel();
	private JLabel lbLogin = new JLabel("Login");
	private JLabel lbPassWord = new JLabel("Passe");
	private JLabel lbType = new JLabel("Type");
	private JLabel lbNiveau = new JLabel("Niveau");
	private JLabel lbNom = new JLabel("Nom et Prénoms");
	private DAO ensdao;
	private MartList<Agent> agents;
	private MartButton bValider = new MartButton().getValider();
	private MartButton bAnnuler = new MartButton().getAnnuler();
	private DAO userdao;

	public AjouterUser(User user) {
		setSize(400, 230);
		setTitle("Ajouter un Utilisateur");
		setLocation(MEDIUM_FRAME_CHOOSER_LOCATION);

		initComponent();
		getContentPane().add(container, BorderLayout.SOUTH);
		setVisible(true);
	}

	public AjouterUser() {
		setSize(400, 230);
		setTitle("Ajouter un Utilisateur");
		setLocation(MEDIUM_FRAME_CHOOSER_LOCATION);

		initComponent();
		getContentPane().add(container, BorderLayout.SOUTH);
		setVisible(true);
	}

	private void initComponent() {
		// champ de saisie
		tfLogin.setPreferredSize(dim1);
		tfLogin.setFont(police1);
		tfPassWord.setPreferredSize(dim1);
		tfPassWord.setFont(police1);
		cbType.setPreferredSize(dim1);
		cbType.setFont(police1);
		cbNiveau.setPreferredSize(dim1);
		cbNiveau.setFont(police1);

		// propriétés des label
		lbLogin.setFont(police1);
		lbPassWord.setFont(police1);
		lbType.setFont(police1);
		lbNiveau.setFont(police1);

		panChamp.setLayout(new GridLayout(5, 2, 2, 2));
		panChamp.setBorder(BorderFactory.createTitledBorder("Nouvelle classe"));

		load();
		refresh();

		bValider.addActionListener(this);
		bAnnuler.addActionListener(this);
		JPanel panBut = new JPanel(new FlowLayout());
		panBut.add(bValider);
		panBut.add(bAnnuler);

		container = new JPanel(new BorderLayout());
		container.add(panChamp, BorderLayout.CENTER);
		container.add(panBut, BorderLayout.SOUTH);
	}

	public void setUser(User u) {
		try {
			user = u;
			tfLogin.setText(user.getLogin());
			tfPassWord.setText(user.getPass());
			cbType.setSelectedItem(user.getType());
			System.out.println("le niveau:" + user.getNiveau());
			cbNiveau.setSelectedItem(String.valueOf(user.getNiveau()));
			Agent agent = agents.find(user.getCodeInfo());

			try {
				cbNom.setSelectedItem(agent.decrisToi());
			} catch (Exception e) {
				cbNom.setSelectedIndex(0);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new AjouterUser(new User());
	}

	@Override
	public void load() {
		ensdao = DAOFactory.getDAO(DAO.AGENT);
		userdao = DAOFactory.getDAO(DAO.USER);

		ensdao.load();
		userdao.load();
	}

	@Override
	public void refresh() {
		// ajout de nom et prénoms des utilisateur
		ensdao.load();
		agents = ensdao.getListObt();

		String[] tabAgents = new String[agents.size() + 1];
		tabAgents[0] = "";

		int i = 1;
		for (Agent agent : agents) {
			tabAgents[i] = agent.decrisToi();
			i++;
		}

		cbNom = new JComboBox(tabAgents);

		panChamp.removeAll();
		panChamp.add(lbLogin);
		panChamp.add(tfLogin);
		panChamp.add(lbPassWord);
		panChamp.add(tfPassWord);
		panChamp.add(lbType);
		panChamp.add(cbType);
		panChamp.add(lbNom);
		panChamp.add(cbNom);
		panChamp.add(lbNiveau);
		panChamp.add(cbNiveau);
		panChamp.add(lbNom);
		panChamp.add(cbNom);
	}

	private void doValider() {
		if (user == null) {
			user = new User();
		}

		user.setLogin(getLogin());
		user.setPass(getPass());
		user.setType(getType_user());
		user.setNiveau(getNiveau());
		user.setCodeInfo(getCodeInfo(cbNom.getSelectedIndex() - 1));
		userdao.update_create(user);

		((Observer) parent).update();
		close();
	}

	private String getCodeInfo(int index) {
		String matricule = agents.get(index).getCodeInfo();
		System.out.println(matricule);
		return matricule;
	}

	// les accesseurs
	public String getLogin() {
		return tfLogin.getText();
	}

	public String getPass() {
		return tfPassWord.getText();
	}

	public String getType_user() {
		return (String) cbType.getSelectedItem();
	}

	public int getNiveau() {
		return Integer.valueOf((String) cbNiveau.getSelectedItem());
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

		if (source == bAnnuler) {
			close();
		}
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
	public void mousePressed(MouseEvent e) {

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
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 10) {
			doValider();
		}
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
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public static AjouterUser getInstance() {
		if (instance == null) {
			instance = new AjouterUser();
		}
		return instance;
	}
}
