package connection;

import function.Constance;
import graphicsModel.FrameIcon;
import graphicsModel.MartList;
import graphicsModel.OptionEditorFrame;
import graphicsModel.OptionItem;
import images.RessourceFinder;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import progress.Avancer;
import progress.Progress;
import progress.ProgressFrame;
import classe.Classe;

import componentFactory.MartButton;

public class DataChooser extends OptionEditorFrame {

	private static DataChooser instance;
	private MartButton bSupprimer = new MartButton().getSupprimer();
	private MartButton bSupprimerAll = new MartButton().getSupprimerAll();
	private MartButton bQuitter = new MartButton().getQuitter();

	private DAO elvclsdao, clsdao;
	private MartList<Classe> classes;
	private String annee;
	private OptionItem selectedItem;
	private Progress progress;
	private ProgressFrame pFrame;

	public DataChooser() {
		setToolBarVertical();
		setIcone(new FrameIcon().getDB());

		initComponent();
	}

	private void initComponent() {
		barreOutilsV.add(bSupprimer);
		barreOutilsV.add(bSupprimerAll);
		barreOutilsV.add(bQuitter);
		addListeners();
	}

	public void set() {
		load();
		refresh();
	}

	public static void main(String[] args) {
		getInstance().setVisible(true);
	}

	public static DataChooser getInstance() {
		if (instance == null) {
			instance = new DataChooser();
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

		if (source == bSupprimer) {
			int rep = JOptionPane.showConfirmDialog(null,
					"Etes- vous sur de supprimer: Liste "
							+ ((Classe) selectedItem.getInfo()).getIntitule()
							+ " " + annee, "CONFIRMATION",
					JOptionPane.YES_NO_OPTION);
			if (rep == JOptionPane.YES_OPTION) {
				delete(selectedItem);

				refresh();
			}
		}

		if (source == bSupprimerAll) {
			int rep = JOptionPane.showConfirmDialog(null,
					"Etes- vous sur de supprimer toutes les données",
					"CONFIRMATION", JOptionPane.YES_NO_OPTION);
			if (rep == JOptionPane.YES_OPTION) {
				for (OptionItem i : getItems()) {
					delete(i);
				}

				refresh();
			}
		}

		if (source == bQuitter) {
			close();
		}
	}

	private void delete(OptionItem item) {
		String classe = ((Classe) item.getInfo()).getIntitule();

		String query1 = "drop table " + "bull_" + Constance.getCor(classe)
				+ "_" + Constance.getCor(annee);
		String query2 = "drop table " + "archive_" + Constance.getCor(classe)
				+ "_" + Constance.getCor(annee);
		String query3 = "drop table " + "perso_" + Constance.getCor(classe)
				+ "_" + Constance.getCor(annee);

		clsdao.updateDB(query1);
		clsdao.updateDB(query2);
		clsdao.updateDB(query3);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Component source = (Component) e.getSource();

		if (source instanceof OptionItem) {
			selectedItem = (OptionItem) source;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		Component source = (Component) e.getSource();

		if (source instanceof OptionItem) {
			selectedItem = (OptionItem) source;
		}
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
		elvclsdao = DAOFactory.getDAO(DAO.ELEVE_CLASSE);
		clsdao = DAOFactory.getDAO(DAO.CLASSE);

		clsdao.load();
		classes = clsdao.getListObt();
	}

	@Override
	public void refresh() {
		progress = new Progress();
		pFrame = new ProgressFrame();
		progress.getProgress(pFrame, 0, classes.size());

		new Thread(new Runnable() {
			public void run() {
				refreshItems();

				for (Classe cl : classes) {
					System.out.println(cl.getIntitule());

					try {
						elvclsdao.load(null, cl.getIntitule(), 0, annee);
						MartList listeEleve = elvclsdao.getListObt();

						if (listeEleve.size() > 0) {
							OptionItem item = new OptionItem(
									new RessourceFinder().getDB(), "Liste "
											+ cl.getIntitule() + " " + annee);

							item.setInfo(cl);
							addItem(item);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

					// pour la barre de progression
					progress.increment();
				}

				pFrame.close();
			}
		}).start();

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

	public void setAnnee(String an) {
		annee = an;
	}

}
