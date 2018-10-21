package ecolage;

import editeur.MartEditorPane;
import eleve.Eleve;
import eleve.PanChooseEleve;
import examen.ChooserExam;
import function.Constance;
import graphicsModel.FrameIcon;
import graphicsModel.MartFrame;
import graphicsModel.MartImage;
import graphicsModel.MartList;
import graphicsModel.OptionEditorFrame;
import graphicsModel.OptionItem;
import images.PictureFinder;
import images.RessourceFinder;
import interfacePerso.Observer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.plaf.OptionPaneUI;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.google.common.collect.BiMap;

import progress.Avancer;
import rapportCompta.RecuModel;
import abstractObject.AbstractControler;
import abstractObject.AbstractModel;
import agent.Agent;
import componentFactory.MartButton;
import connection.DAO;
import connection.DAOFactory;

public class NouveauEcolage extends OptionEditorFrame implements Observer {
	private DAO elvdao, versecolagedao;
	private JPanel panIdEleve;
	private JPanel panPhoto;
	private MartEditorPane panText;
	private String annee;
	private Eleve superEleve;
	private Font police = new Font("courrier new", Font.BOLD, 16);
	private AbstractControler controler;
	private AbstractModel model;
	private MartList<EleveEcolage> listEleveEcolage;
	private Color BACK_COLOR = Color.WHITE;
	private EleveEcolage eleveEco;
	private PanChooseEleve panSearch;
	private String[] liste;
	private AjouterSco chooser;
	private MartButton bAnnulerPayement = new MartButton().getAnnulerPayement();
	private MartButton bReinitialiser = new MartButton().getReinitialiser();
	private MartButton bQuitter = new MartButton().getQuitter();
	private MartButton bAjouter = new MartButton().getAjouter();
	private MartButton bImprimer = new MartButton().getImprimer();
	private JPanel panSituation;
	private Operation operation;
	private EcolageComputer computer;

	public NouveauEcolage() {
		this.setTitle("ECOLAGE");
		this.setSize(MartFrame.MEDIUM_FRAME);
		this.setLocation(MartFrame.INTERNAL_FRAME_LOCATION);
		setResizable(false);
		setToolBar();
		setToolBarVertical();
		setIcone(new FrameIcon().getFraisScolarite());

		initComponent();
	}

	private void initComponent() {
		panSearch = new PanChooseEleve();
		panSearch.addListener(this);

		barreOutils.add(panSearch);

		barreOutilsV.add(bAjouter);
		barreOutilsV.add(bAnnulerPayement);
		barreOutilsV.add(bImprimer);
		barreOutilsV.add(bReinitialiser);
		barreOutilsV.add(bQuitter);

		addListeners();

		model = new EcolageModel();
		controler = new EcolageControler(model);
		model.addObserver(this);

		load();

		panIdEleve = new JPanel();
		panPhoto = new JPanel();
		panText = new MartEditorPane();

		panIdEleve.setLayout(new BorderLayout());
		panPhoto.setLayout(new GridLayout());

		panPhoto.setPreferredSize(new Dimension(150, 150));

		panPhoto.setBackground(BACK_COLOR);

		panIdEleve.add(panText, BorderLayout.CENTER);
		panIdEleve.add(panPhoto, BorderLayout.EAST);

		panSituation = new JPanel(new BorderLayout());
		panSituation.setBackground(Color.BLUE);
	}

	public static void main(String[] args) {
		Constance.initialize();
		new GeneralVoidEcolage().caisseEcolage();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Component source = (Component) e.getSource();

		if (source == bAjouter) {
			liste = new String[2];
			liste[0] = "ECOLAGE";
			liste[1] = "INSCRIPTION";
			chooser = new AjouterSco(AjouterSco.VERSEMENT, liste);
			chooser.setEditable(true);
			chooser.setParent(this);
			setAction();
		}

		if (source == bImprimer) {
			try {
				operation.setEleveEco(eleveEco);
				operation.setComputer(computer);
				doImprimer();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null,
						"Aucune opération n'est choisie");
			}
		}

		if (source == bAnnulerPayement) {
			liste = new String[1];
			if (operation.getJustification().equals(Operation.ECOLAGE)) {
				liste[0] = "ANNULATION ECO.";
			}
			if (operation.getJustification().equals(Operation.INSCRIPTION)) {
				liste[0] = "ANNULATION INS.";
			}
			chooser = new AjouterSco(AjouterSco.ANNULATION, liste);
			chooser.setEditable(false);
			chooser.setOperation(operation);

			setAction();
		}

		if (source == bReinitialiser) {
			reset();
		}

		if (source == bQuitter) {
			close();
		}

		if (source == panSearch.getValider()) {
			setSearchResult();
		}
	}

	public void reset() {
		super.reset();

		panSearch.reset();
		superEleve = new Eleve();
		eleveEco = new EleveEcolage();
		operation = null;
		refreshItems();
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				System.out.println("ok");
				panPhoto.removeAll();
				panText.setHtml("");
				panPhoto.revalidate();
				panText.revalidate();
				panIdEleve.repaint();
			}
		});
	}

	private void doImprimer() {
		if (operation != null) {
			RecuModel writer = new RecuModel();
			writer.setAnnee(annee);
			writer.writeRecu(operation);
		} else {
			JOptionPane.showMessageDialog(null,
					"Aucune opération n'est choisie");
		}
	}

	public String getAnnee() {
		return annee;
	}

	public void setAnnee(String annee) {
		this.annee = annee;
	}

	// définit l'action au cours de la validation de ajouterSCO
	private void setAction() {
		if (superEleve != null) {
			chooser.setAction(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					valider();
				}
			});

			chooser.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(null, "Aucun élève n'est chargé!");
		}
	}

	public void valider() {
		Operation vers = new VersementEcolage(superEleve.getCodeInfo(),
				chooser.getSomme());
		vers.setJustification(chooser.getJustification());

		eleveEco.setCodeInfo(superEleve.getCodeInfo());
		eleveEco.setNom(superEleve.getNom());
		eleveEco.setPrenom(superEleve.getPrenom());
		eleveEco.setSexe(superEleve.getSexe());
		eleveEco.setClasse(superEleve.getClasseAnnee(annee));
		eleveEco.setOperationActuel(vers);

		controler.setData(eleveEco);
		model.setData(eleveEco);
		model.setAnnee(annee);

		controler.valider();

		chooser.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void load() {
		elvdao = DAOFactory.getDAO(DAO.ELEVE);
		versecolagedao = DAOFactory.getDAO(DAO.VERSEMENT_ECOLAGE);
		elvdao.load();
	}

	@Override
	public void refresh() {
		refreshItems();
		container.add(panIdEleve, BorderLayout.NORTH);
		afficherEleve();

		String text = "<html><head></head><body><div>Nom: " + eleveEco.getNom()
				+ "</div>" + "<div>Prénoms: " + eleveEco.getPrenom() + "</div>"
				+ "<div>Sexe: " + eleveEco.getSexe() + "</div>"
				+ "<div>N° Mle: " + eleveEco.getCodeInfo() + "</div>"
				+ "<div>Classe actuelle: " + eleveEco.getClasse()
				+ "</div></body>" + "</html>";

		panText.setHtml(text);

		// définition de la photo
		File p = new PictureFinder().getPhotoEleve(superEleve.getCodeInfo());
		MartImage photo = new MartImage(p);

		panPhoto.removeAll();
		panPhoto.add(photo);
		panPhoto.revalidate();

	}

	public void afficherEleve() {
		// affichage des operation antérieures
		versecolagedao.load("", "", 0, annee);
		listEleveEcolage = versecolagedao.getListObt();
		eleveEco = listEleveEcolage.find(superEleve.getCodeInfo());

		if (eleveEco == null) {
			eleveEco = new EleveEcolage(superEleve.getCodeInfo());
		}

		try {
			String classe = superEleve.getClasseAnnee(annee);
			eleveEco.setNom(superEleve.getNom());
			eleveEco.setPrenom(superEleve.getPrenom());
			eleveEco.setSexe(superEleve.getSexe());
			eleveEco.setClasse(classe);

			// l'affichage n'est faite que si l'élève existe
			setOperationAnterieures(eleveEco);

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,
					"L'élève choisi n'est pas inscrit cette année");
			// l'affichage n'est faite que si l'élève existe
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					panText.setText("");
					panPhoto.removeAll();
					panPhoto.setBackground(BACK_COLOR);
					panIdEleve.repaint();

					reset();
				}
			});

		}

		// System.out.println("=================>>" + eleveEco.getSexe());
		panSearch.reset();
	}

	// la methode qui crée le tableau des opération antérieures
	private void setOperationAnterieures(EleveEcolage eleveEco) {
		MartList<Operation> listOp = new MartList<Operation>();
		try {
			System.out.println("Code eleve: " + eleveEco.getCodeInfo());
			listOp = eleveEco.getlisteOperation();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// calculs
		computer = new EcolageComputer();
		computer.setAnnee(annee);
		computer.setEleveEcolage(eleveEco);

		Object[][] data = new Object[listOp.size()][4];
		String[] title = new String[4];

		int i = 0;
		for (Operation op : listOp) {
			DateTime date = op.getDate();
			System.out.println("la date: " + date);
			DateTimeFormatter formatter = DateTimeFormat.mediumDateTime();
			formatter.withZoneUTC();

			OptionItem item = new OptionItem(new RessourceFinder().getBillet(),
					"<html>" + "<div>Date: " + formatter.print(date) + "</div>"
							+ "<div>Montant: " + op.getMontant() + "</div>"
							+ "<div>Justification: " + op.getJustification()
							+ "</div>" + "</html>");

			item.setInfo(op);
			addItem(item);
			i++;
		}

		JLabel lbSituation = new JLabel("Etat du payement");
		lbSituation.setFont(police);
		lbSituation.setForeground(Color.WHITE);

		try {
			lbSituation.setText("<html><table><tr><td>Reglé</td><td> "
					+ computer.getRegle()
					+ "</td></tr><tr><td>Reste à payer</td><td>"
					+ computer.getReste() + "</td></tr></table></html>");
		} catch (Exception e) {
			e.printStackTrace();
		}

		panSituation.removeAll();
		panSituation.add(lbSituation, BorderLayout.CENTER);

		container.add(panSituation, BorderLayout.SOUTH);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Component source = (Component) e.getSource();
		if (source == panSearch.getComponent()) {
			if (panSearch.itemExists()) {
				setSearchResult();
			}
		}
	}

	private void setSearchResult() {
		// reset();
		superEleve = panSearch.getSelectedItem();
		refresh();
	}

	@Override
	public void update() {
		showMessage("Versement effectué");
		refresh();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Component source = (Component) e.getSource();
		operation = (Operation) ((OptionItem) source).getInfo();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Component source = (Component) e.getSource();
		operation = (Operation) ((OptionItem) source).getInfo();
	}

	@Override
	public Avancer getAvancer() {
		// TODO Auto-generated method stub
		return null;
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
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
