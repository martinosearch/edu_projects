package classe;

import graphicsModel.MartFocusManager;
import graphicsModel.MartFrame;
import interfacePerso.Observer;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import org.joda.time.DateTime;

import componentFactory.MartButton;

import classe.Classe;
import connection.DAO;
import connection.DAOFactory;
import progress.Avancer;
import abstractObject.AbstractControler;
import abstractObject.AbstractModel;
import annee.Annee;
import annee.ChooserDate;

public class AjouterNiveau extends MartFrame {
	private Font police1 = new Font("Times new Romam", Font.TYPE1_FONT, 16);
	private Font police2 = new Font("courrier new", Font.BOLD, 14);

	private JTabbedPane tabPan = new JTabbedPane();
	private JPanel panInfo = new JPanel();

	private JLabel lbNiveau = new JLabel("Intitulé");
	private JLabel lbTypeEns = new JLabel("Type");
	private JLabel lbDim = new JLabel("Diminutif");

	private JTextField tfNiveau = new ThisTextField();
	private JComboBox cbTypeEns;

	private MartButton bSave = new MartButton().getSauvegarder();
	private Niveau niveau;
	private DAO dao;

	public AjouterNiveau() {
		setSize(SMALL_FRAME);
		setTitle("Niveau");
		setLocation(MEDIUM_FRAME_CHOOSER_LOCATION);
		setToolBarVertical();
		initComponent();

		getContentPane().add(container, BorderLayout.CENTER);
	}

	private void initComponent() {
		// pour la barre d'outils
		barreOutilsV.add(bSave);
		addListeners();

		// on remplit les cambobox
		load();
		refresh();

		// ajout aux conteneur
		panInfo.setLayout(new FlowLayout(FlowLayout.LEFT));
		panInfo.add(lbNiveau);
		panInfo.add(tfNiveau);
		panInfo.add(lbTypeEns);
		panInfo.add(cbTypeEns);

		tabPan.add("Information", panInfo);

		container = new JPanel();
		container.setLayout(new BorderLayout());
		container.add(tabPan, BorderLayout.CENTER);
		container.revalidate();
	}

	public static void main(String[] args) {
		new AjouterNiveau().setVisible(true);
	}

	@Override
	public Avancer getAvancer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Component source = (Component) e.getSource();

		if (source == bSave) {
			doValider();
		}
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
		dao = DAOFactory.getDAO(DAO.MATIERE);
		dao.load();

		String[] datacb = { "***", "PRIM", "COL", "LEG", "LET" };
		cbTypeEns = new ThisComboBox(datacb);
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

	private void doValider() {
		if (niveau == null) {
			niveau = new Niveau();
		}

		niveau.setIntitule(getNiveau());
		niveau.setTypeEns(getTypeEns());

		AbstractModel model = new NiveauModel();
		AbstractControler controler = new NiveauControler(model);
		model.addObserver((Observer) getParent());

		model.setData(niveau);
		controler.setData(niveau);

		controler.valider();
		close();
	}

	public String getNiveau() {
		return tfNiveau.getText();
	}

	public String getTypeEns() {
		return (String) cbTypeEns.getSelectedItem();
	}

	public void loadNiveau(Niveau obj) {
		niveau = obj;
		tfNiveau.setText(niveau.getIntitule());
		cbTypeEns.setSelectedItem(niveau.getTypeEns());
	}

	// classe interne
	class ThisTextField extends JTextField {
		protected Dimension dim1 = new Dimension(SMALL_FRAME.width - 150, 30);

		public ThisTextField() {
			super();
			setPreferredSize(dim1);
			setFont(police1);
		}
	}

	class ThisComboBox extends JComboBox {
		protected Dimension dim1 = new Dimension(SMALL_FRAME.width - 150, 30);

		public ThisComboBox() {
			super();
			setPreferredSize(dim1);
			setFont(police1);
		}

		public ThisComboBox(Object[] obj) {
			super(obj);
			setPreferredSize(dim1);
			setFont(police1);
		}
	}

	class ThisChooserDate extends ChooserDate {
		protected Dimension dim1 = new Dimension(SMALL_FRAME.width - 150, 30);

		public ThisChooserDate() {
			super();
			setPreferredSize(dim1);
			setFont(police1);
			setEmpty();
		}
	}
}
