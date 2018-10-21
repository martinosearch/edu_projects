package security;

import editeur.MartEditorPane;
import function.Constance;
import graphicsModel.FrameIcon;
import graphicsModel.MartDialog;
import graphicsModel.MartFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import componentFactory.MartButton;
import progress.Avancer;
import connection.DAO;
import connection.DAOFactory;

public class Login extends MartFrame {
	JLabel lbUsername;
	JLabel lbPassword;
	JPanel pan3, pan2, container;
	private JTextField tfLogin;
	private JPasswordField tfPass;
	private DAO userdao;
	private User user;
	public static Login instance;
	Font police1 = new Font("courrier", Font.TYPE1_FONT, 26);
	Font police2 = new Font("courrier", Font.TYPE1_FONT, 26);
	Font police3 = new Font("forte", Font.TYPE1_FONT, 20);
	private Color color = new Color(226, 169, 229);
	private MartButton bValider = new MartButton().getValider();
	private MartButton bAnnuler = new MartButton().getAnnuler();
	private Thread traitement;

	public Login() {
		setTitle("Authentification");
		setSize(MartFrame.SMALL_FRAME);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		addWindowListener(this);
		setToolBar();
		setIcone(new FrameIcon().getSecurite());

		initComponent();

		this.getContentPane().setBackground(color);
		this.getContentPane().add(container);
	}

	private void initComponent() {
		userdao = DAOFactory.getDAO(DAO.USER);
		lbUsername = new JLabel("LOGIN");
		lbPassword = new JLabel("MOT DE PASSE");
		lbUsername.setFont(police3);
		lbPassword.setFont(police3);
		lbUsername.setPreferredSize(new Dimension(180, 40));
		lbPassword.setPreferredSize(new Dimension(180, 40));

		tfLogin = new JTextField();
		tfPass = new JPasswordField();
		tfLogin.setPreferredSize(new Dimension(200, 40));
		tfPass.setPreferredSize(new Dimension(200, 40));

		tfLogin.addFocusListener(this);
		tfPass.addFocusListener(this);

		tfLogin.addKeyListener(this);
		tfPass.addKeyListener(this);

		tfPass.setEchoChar('*');
		tfLogin.requestFocusInWindow();

		tfLogin.setForeground(Color.BLUE);
		tfPass.setForeground(Color.BLUE);
		tfLogin.setFont(police1);
		tfPass.setFont(police1);

		MartEditorPane lbEts = new MartEditorPane();
		lbEts.setBackground(new Color(238, 172, 71));
		lbEts.setPreferredSize(new Dimension(250, 100));

		lbEts.setHtml("<html><head><style>#titre{font-family:'arial';font-size:30pt;font-weight:bold;text-align:center}</style></head><body><div id='titre'>"
				+ Constance.INITIALE
				+ " "
				+ Constance.NOM
				+ "</div><body></html>");

		pan2 = new JPanel();
		pan2.setLayout(new FlowLayout());
		pan2.setPreferredSize(new Dimension(250, 80));

		pan2.add(lbUsername);
		pan2.add(tfLogin);
		pan2.add(lbPassword);
		pan2.add(tfPass);

		pan3 = new JPanel(new FlowLayout());
		pan3.setBackground(color);

		bValider.addActionListener(this);
		bAnnuler.addActionListener(this);
		pan3.add(bValider);
		pan3.add(bAnnuler);

		// ajout au conteneur principal
		container = new JPanel();
		container.setBackground(color);
		container.setLayout(new BorderLayout());

		container.add(lbEts, BorderLayout.NORTH);
		container.add(pan2, BorderLayout.CENTER);
		container.add(pan3, BorderLayout.SOUTH);

		pan2.setBackground(color);
	}

	public static void main(String[] args) {
		getInstance().setVisible(true);
	}

	private boolean authentifier() {
		String login = getLogin();
		String pass = getPass();

		if (login.equals("") || pass.equals("")) {
			JOptionPane.showMessageDialog(this,
					"Nom d'utilisateur ou mot de passe" + " incorrects",
					"Authentification", JOptionPane.ERROR_MESSAGE);
			setVisible(true);
			return false;
		}

		else {
			boolean hasAccess = Soldier.compare(login, pass);
			if (hasAccess == true) {
				user = Soldier.getUser();
				CurrentUser.getInstance().setHasAccess(true);
				CurrentUser.getInstance().setLogin(user.getLogin());
				CurrentUser.getInstance().setType(user.getType());
				CurrentUser.getInstance().setNiveau(user.getNiveau());
				CurrentUser.getInstance().setCodeInfo(user.getCodeInfo());
				this.dispose();
			}

			else {
				JOptionPane.showMessageDialog(this,
						"Nom d'utilisateur ou mot de passe" + " incorrects",
						"Authentification", JOptionPane.ERROR_MESSAGE);
			}

			return hasAccess;
		}
	}

	private String getLogin() {
		return tfLogin.getText();
	}

	private String getPass() {
		return tfPass.getText();
	}

	public static Login getInstance() {
		if (instance == null) {
			instance = new Login();
		}
		return instance;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Component source = (Component) e.getSource();
		if (source == bValider) {
			if (authentifier()) {
				traitement.start();
			}
		}

		if (source == bAnnuler) {
			System.exit(0);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 10) {
			if (authentifier()) {
				traitement.start();
			}
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
	public Avancer getAvancer() {
		// TODO Auto-generated method stub
		return null;
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
		tfLogin.requestFocusInWindow();
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
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
	public void focusGained(FocusEvent e) {
		Component obj = (Component) e.getSource();
		if (obj instanceof JTextField) {
			((JTextField) obj).selectAll();
		}
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void setAction(Thread treat) {
		traitement = treat;
	}

}
