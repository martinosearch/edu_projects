package security;

import function.Constance;
import function.SerialGEN;
import graphicsModel.FrameIcon;
import graphicsModel.MartFrame;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import progress.Avancer;
import reference.Reference;
import componentFactory.MartButton;
import connection.DAO;
import connection.DAOFactory;
import connection.PostInstall;

public class Register extends MartFrame {
	String cleCor;
	private JPanel container;
	private JTextField tfInitiale, tfNom, tfTel, tfSerial;
	private SerialGEN serial1;
	private DAO refDao;
	private MartButton bSave = new MartButton().getSauvegarder();
	private Dimension dimTf = new Dimension(350, 30);

	public Register() {
		this.setSize(SMALL_FRAME);
		this.setLocation(INTERNAL_FRAME_LOCATION);
		setToolBar();
		setToolBarVertical();
		setIcone(new FrameIcon().getRegister());

		initComponent();

		refDao = DAOFactory.getDAO(DAO.REFERENCE);
		refDao.load();

		this.getContentPane().add(container, BorderLayout.CENTER);
	}

	private void initComponent() {
		barreOutilsV.add(bSave);

		addListeners();

		container = new JPanel();
		container.setLayout(new FlowLayout());

		tfInitiale = new JTextField();
		tfNom = new JTextField();
		tfTel = new JTextField();
		tfSerial = new JTextField();

		tfInitiale.setPreferredSize(dimTf);
		tfNom.setPreferredSize(dimTf);
		tfTel.setPreferredSize(dimTf);
		tfSerial.setPreferredSize(dimTf);

		container
				.add(new JLabel(
						"Entrer ici l'initiale de l'établissement. (CEG, LYCEE , Complexe Scolaire)"));
		container.add(tfInitiale);
		container.add(new JLabel("Entrer ici le nom de l'école"));
		container.add(tfNom);
		container.add(new JLabel("Entrer le ou les numéro(s) de téléphone"));
		container.add(tfTel);
		container.add(new JLabel(
				"Entrer le sérial que vous avez obtenu auprès de l'éditeur"));
		container.add(tfSerial);
	}

	public void update() {
		if (Constance.MY_LIFE.equals(cleCor)) {
			PostInstall pins = new PostInstall();
			pins.doInstall();
		}
	}

	public static void main(String[] args) {
		Register reg = new Register();
		reg.setVisible(true);
	}

	@Override
	public Avancer getAvancer() {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent e) {
		Component source = (Component) e.getSource();

		if (source == bSave) {
			serial1 = new SerialGEN(getInitiale(), getNom(), getTel());
			cleCor = serial1.getSerial();

			if (getSerial().equals(cleCor) && !cleCor.equals("")) {
				refDao.update_create(new Reference("register_key", getSerial()));
				refDao.update_create(new Reference("initiale", getInitiale()));
				refDao.update_create(new Reference("nom", getNom()));
				refDao.update_create(new Reference("tel", getTel()));

				JOptionPane.showMessageDialog(null,
						"Enrégistrement réussi!\n Vous pouvez "
								+ "maintenant utiliser MartSCO",
						"ENREGISTREMENT", JOptionPane.INFORMATION_MESSAGE);

				close();
			}

			else {
				JOptionPane.showMessageDialog(null,
						"Enrégistrement echoué!\n Merci de "
								+ "vérifier votre clef",
						"ERREUR D'ENREGISTREMENT",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}

	}

	private String getSerial() {
		return tfSerial.getText();
	}

	private String getTel() {
		return tfTel.getText();
	}

	private String getNom() {
		return tfNom.getText();
	}

	private String getInitiale() {
		return tfInitiale.getText();
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
}
