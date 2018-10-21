package classe;

import eleve.AjouterEleve;
import eleve.AjouterEleveClasse;
import eleve.Eleve;
import eleve.EleveClasse;
import eleve.EleveControler;
import eleve.EleveModel;
import function.GeneralVoid;
import graphicsModel.FrameIcon;
import graphicsModel.MartFrame;
import graphicsModel.MartList;
import graphicsModel.OptionEditorFrame;
import graphicsModel.OptionItem;
import graphicsModel.TablePanel;
import interfacePerso.Observer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.JPanel;

import componentFactory.MartButton;
import progress.Avancer;
import rapportBulletin.ListeWriterModel;
import abstractObject.AbstractControler;
import abstractObject.AbstractModel;
import connection.DAO;
import connection.DAOFactory;

public class ListeEleveClasse extends OptionEditorFrame implements Observer {
	private DAO dao, elvdao;

	private MartList<EleveClasse> liste;
	private EleveClasse eleve;

	private MartButton bAjouter = new MartButton().getAjouter();
	private MartButton bSupprimer = new MartButton().getSupprimer();
	private MartButton bQuitter = new MartButton().getQuitter();
	private MartButton bModifier = new MartButton().getModifier();
	private MartButton bImprimer = new MartButton().getImprimer();

	AbstractModel model = new EleveModel();
	AbstractControler controler = new EleveControler(model);

	private PanChooseEleveClasse panSearch;
	private AjouterEleveClasse fr;
	private Classe classe;
	private String annee;
	private Eleve superEleve;
	private AjouterEleve fr2;

	public ListeEleveClasse(Classe classe2, String an) {
		classe = classe2;
		annee = an;
		this.setTitle("Liste de la classe de: " + classe.getIntitule() + "  "
				+ annee);
		setToolBarVertical();
		setIcone(new FrameIcon().getIntituleClasse(classe.getIntitule()));

		initComponent();
	}

	public void initComponent() {
		panSearch = new PanChooseEleveClasse(classe.getIntitule(), annee);
		panSearch.addListener(this);

		barreOutils.add(panSearch);

		barreOutilsV.add(bAjouter);
		barreOutilsV.add(bModifier);
		barreOutilsV.add(bSupprimer);
		barreOutilsV.add(bImprimer);
		barreOutilsV.add(bQuitter);

		addListeners();
		model.addObserver(this);

		load();
		refresh();
	}

	public void update() {
		try {
			Thread.sleep(1000);
			refresh();
			showMessage("Succès!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void load() {
		elvdao = DAOFactory.getDAO(DAO.ELEVE);
		elvdao.load();
		dao = DAOFactory.getDAO(DAO.ELEVE_CLASSE);
	}

	public void refresh() {
		// la Liste des Enseignants existants
		dao.load(null, classe.getIntitule(), 0, annee);
		liste = dao.getListObt();

		refreshItems();

		new Thread(new Runnable() {
			public void run() {
				int i = 0;
				for (EleveClasse obj : liste) {
					String sexe = null;
					String code = null;
					String statut = null;
					try {
						sexe = obj.getSexe();
					} catch (Exception e) {
						e.printStackTrace();
					}

					try {
						code = obj.getCodeInfo();
					} catch (Exception e) {
						e.printStackTrace();
					}

					try {
						statut = obj.getStatut().toString();
					} catch (Exception e) {
						e.printStackTrace();
					}

					OptionItem item = new OptionItem("img_eleve.png", "<div>"
							+ (i + 1) + "-  " + obj.decrisToi()
							+ "</div><div id='explication'> Sexe: " + sexe
							+ " Code: " + code + " Statut: " + statut
							+ "</div>"
							+ "</div><div id='explication'> Photo défini: "
							+ obj.isPhoto() + "</div>");
					item.setInfo(obj);
					item.addListener(ListeEleveClasse.this);
					addItem(item);
					System.out.println(obj.decrisToi().toUpperCase() + "sexe: "
							+ obj.getSexe());

					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					i++;
				}
			}
		}).start();

		panSearch.setListe(liste);
		panSearch.addListener(this);
	}

	public static void main(String[] args) {
		new ListeEleveClasse(new Classe("3ème", "3ème"), "2017-2018")
				.setVisible(true);
	}

	@Override
	public Avancer getAvancer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Component source = (Component) e.getSource();
		if (source == bAjouter) {
			fr = new AjouterEleveClasse(classe.getIntitule(), annee);
			fr.setParent(this);
			fr.setVisible(true);
		}

		if (source == bSupprimer) {
			model.setClasse(classe.getIntitule());
			model.setAnnee(annee);

			controler.setData(superEleve);
			model.setData(superEleve);
			controler.supprimer(model.DELETE_IN_CURRENTLIST);
		}

		if (source == bModifier) {
			doModifier();
		}

		if (source == panSearch.getValider()) {
			setSearchResult();
		}

		if (source == bQuitter) {
			close();
		}

		if (source == bImprimer) {
			ListeWriterModel writer = new ListeWriterModel();
			writer.setClasse(classe.getIntitule());
			writer.setAnnee(annee);
			writer.setAfficherPhoto(false);
			writer.valider(model.DEFAULT);
		}
	}

	private void doModifier() {
		fr2 = AjouterEleve.getInstance();
		fr2.loadEleve(superEleve);
		fr2.setParent(this);
		fr2.setVisible(true);
	}

	private void setSearchResult() {
		// System.out.println("listener: ok!");
		eleve = panSearch.getSelectedItem();
		superEleve = (Eleve) elvdao.findObj(eleve.getCodeInfo());

		int i = 0;
		MartList<OptionItem> listeItem = panItem.getListeItem();
		for (OptionItem item : listeItem) {
			if (((EleveClasse) item.getInfo()).getCodeInfo().equals(
					eleve.getCodeInfo())) {
				panItem.setBarPosition(i * 64);
				panItem.setSelectedItem(i);
			}
			i++;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Component source = (Component) e.getSource();
		eleve = (EleveClasse) ((OptionItem) source).getInfo();
		superEleve = (Eleve) elvdao.findObj(eleve.getCodeInfo());

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
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		try {
			panItem.reset();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			fr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		if (e.getKeyCode() == 10) {
			if (source == panSearch.getComponent()) {
				if (panSearch.itemExists()) {
					setSearchResult();
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

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
