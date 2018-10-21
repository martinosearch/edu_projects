package eleve;

import function.Constance;
import graphicsModel.MartFrame;
import graphicsModel.MartList;
import graphicsModel.OptionItem;
import interfacePerso.Observer;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import componentFactory.MartButton;
import progress.Avancer;
import rapportBulletin.ListeWriterModel;
import Import.ImportListeEleve;
import Import.ImportManager;
import abstractObject.AbstractControler;
import abstractObject.AbstractModel;
import connection.DAO;
import connection.DAOFactory;

public class AjouterEleveClasse extends MartFrame implements Observer {
	protected Dimension dim1 = new Dimension(120, 30);
	Font police1 = new Font("Times new Romam", Font.TYPE1_FONT, 16);
	private PanChooseEleve panSearch;
	private String classe;
	private String annee;
	private Eleve superEleve;
	private JTabbedPane panTab = new JTabbedPane();
	private JPanel panImport;
	private MartButton bExcel = new MartButton().getExcel();
	private MartButton bCsv = new MartButton().getCsv();
	private ImportManager mng;

	public AjouterEleveClasse(String cl, String an) {
		classe = cl;
		annee = an;

		setSize(SMALL_FRAME);
		setTitle("Ajouter un élèvé");
		setLocation(MEDIUM_FRAME_CHOOSER_LOCATION);
		addListeners();

		load();
		panSearch = new PanChooseEleve();
		panSearch.addListener(this);
		panTab.add("Ajouter par le nom", panSearch);

		bExcel.addActionListener(this);
		bCsv.addActionListener(this);

		panImport = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panImport.add(bExcel);
		panImport.add(bCsv);
		panTab.add("Importer une liste", panImport);

		container = new JPanel(new BorderLayout());
		container.add(panTab, BorderLayout.CENTER);

		getContentPane().add(container, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		new AjouterEleveClasse("6ème", "2016-2017").setVisible(true);
	}

	@Override
	public Avancer getAvancer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Component source = (Component) e.getSource();
		if (source == panSearch.getValider()) {
			setSearchResult();
			doValider();
		}

		if (source == bExcel) {
			// On définit l'action qui serra assignée au chooser
			ImportListeEleve imp = new ImportListeEleve();
			MartList<Eleve> liste = imp.getListeExcelClasse(classe);

			mng = new ImportManager();
			mng.addObserver(this);

			mng.setAnnee(annee);
			mng.importer(liste);
			mng.setVisible(true);
		}

		if (source == bCsv) {
			Constance.getNotSet();
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
		mng.close();
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
		Component source = (Component) e.getSource();
		if (source == panSearch.getComponent()) {
			if (panSearch.itemExists()) {
				setSearchResult();

				if (e.getKeyCode() == 10) {
					doValider();
				}
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

	private void doValider() {
		AbstractModel model = new EleveModel();
		AbstractControler controler = new EleveControler(model);
		model.addObserver((Observer) getParent());

		controler.setAnnee(annee);
		controler.setClasse(classe);
		controler.setData(superEleve);

		model.setAnnee(annee);
		model.setClasse(classe);
		model.setData(superEleve);

		controler.valider();
		panSearch.reset();
	}

	private void setSearchResult() {
		// System.out.println("listener: ok!");
		superEleve = panSearch.getSelectedItem();
	}

	@Override
	public void load() {
		DAOFactory.getDAO(DAO.ELEVE);
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		((Observer) getParent()).update();
		close();
	}
}
