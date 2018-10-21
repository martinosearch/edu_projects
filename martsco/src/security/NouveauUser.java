package security;

import graphicsModel.FrameIcon;
import graphicsModel.MartList;
import graphicsModel.OptionEditorFrame;
import graphicsModel.OptionItem;
import interfacePerso.Observer;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import progress.Avancer;

import componentFactory.MartButton;

import connection.DAO;
import connection.DAOFactory;

public class NouveauUser extends OptionEditorFrame implements Observer {
	private static NouveauUser instance;
	private DAO userdao;
	private MartList<User> utilisateurs;
	private MartButton bAjouter = new MartButton().getAjouter();
	private MartButton bModifier = new MartButton().getModifier();
	private MartButton bSupprimer = new MartButton().getSupprimer();
	private MartButton bQuitter = new MartButton().getQuitter();
	private User user;

	// constructeur
	public NouveauUser() {
		super();
		setTitle("Compte d'utilisateur");
		setToolBar();
		setToolBarVertical();
		setIcone(new FrameIcon().getUser());

		initComponent();
	}

	// initialisaton des contenus
	public void initComponent() {
		barreOutilsV.add(bAjouter);
		barreOutilsV.add(bModifier);
		barreOutilsV.add(bSupprimer);
		barreOutilsV.add(bQuitter);
		addListeners();

		load();
	}

	// *********************************************************
	// cree une instance
	// permet donc de ne instancier qu'une seul fois (pattener singleton)
	public static NouveauUser getInstance() {
		if (instance == null)
			instance = new NouveauUser();

		return instance;
	}

	public void actionPerformed(ActionEvent e) {
		Component source = (Component) e.getSource();
		if (source == bAjouter) {
			AjouterUser ajouter = AjouterUser.getInstance();
			ajouter.setParent(this);
			ajouter.setUser(new User());
			ajouter.setVisible(true);
		}

		if (source == bModifier) {
			doModifier();
		}

		if (source == bQuitter) {
			close();
		}

		if (source == bSupprimer) {
			int confirm = JOptionPane.showConfirmDialog(null,
					"Voulez vous vraiment supprimer: " + user.getLogin(),
					"CONFIRMATION" + " ?", JOptionPane.YES_NO_OPTION);
			if (confirm == JOptionPane.YES_OPTION) {
				userdao.deleteObj(user);
				refresh();
			}
		}
	}

	private void doModifier() {
		AjouterUser ajouter = AjouterUser.getInstance();
		ajouter.setParent(this);

		if (user == null) {
			JOptionPane.showMessageDialog(null,
					"Veuillez selectionner un compte");
		} else {
			ajouter.setUser(user);
			ajouter.setVisible(true);
		}
	}

	public void load() {
		userdao = DAOFactory.getDAO(DAO.USER);
		refresh();
	}

	public static void main(String[] args) {
		getInstance().setVisible(true);
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
	}

	@Override
	public void refresh() {
		userdao.load();
		utilisateurs = userdao.getListObt();

		refreshItems();

		int i = 0;
		for (User obj : utilisateurs) {
			OptionItem item = new OptionItem("img_user.png", "<div>"
					+ obj.getLogin() + "</div><div id='explication'> Sexe: "
					+ obj.getNiveau() + " Droit: " + obj.getType()
					+ " Niveau: " + obj.getNiveau() + "</div>");
			item.setInfo(obj);
			item.addListener(this);
			addItem(item);

			// System.out.println(obj.decrisToi().toUpperCase());
			i++;
		}

		revalidate();
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
	public Avancer getAvancer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Component source = (Component) e.getSource();
		user = (User) ((OptionItem) source).getInfo();
		if (e.getClickCount() > 1) {
			doModifier();
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
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		if (Droits.hasAccess(this.getClass())) {

		} else {
			dispose();
		}
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
	public void update() {
		refresh();
		showMessage("Succès");
	}
}
