package planning;

import graphicsModel.MartFrame;
import graphicsModel.MartList;
import interfacePerso.Observer;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import connection.DAO;
import connection.DAOFactory;
import progress.Avancer;
import tableComponent.MartTabModel;
import tableComponent.MartTable;

public class CreateActivite extends MartFrame implements Observer {
	private MartList<Activite> listeActivite;
	private JPanel panListe;
	private String annee;
	DAO actdao;

	public CreateActivite(String an) {
		setTitle("Activités");
		setSize(600, 600);
		setLocationRelativeTo(null);
		setToolBar();
		annee = an;

		initComponent();

		getContentPane().add(container, BorderLayout.CENTER);
		setVisible(true);
	}

	private void initComponent() {
		barreOutils.add(bAjouter);
		barreOutils.add(bSupprimer);
		barreOutils.add(bQuitter);

		load();

		panListe = new JPanel();
		panListe.setLayout(new BorderLayout());

		container = new JPanel();
		container.setLayout(new BorderLayout());

		container.add(panListe, BorderLayout.CENTER);

		refresh();
	}

	public static void main(String[] args) {

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
			NouveauActivite nAct = new NouveauActivite(annee);
			nAct.addObserver(this);
		}

		if (source == bQuitter) {
			close();
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
		actdao = DAOFactory.getDAO(DAO.ACTIVITE);
		actdao.load(null, null, 0, annee);
		listeActivite = actdao.getListObt();
	}

	@Override
	public void refresh() {
		actdao.load(null, null, 0, annee);
		String[] title = { "Code Activité", "Matière", "Classe", "Enseignant",
				"Nombre d'heure" };
		Object[][] data = new Object[listeActivite.size()][5];

		int i = 0;
		for (Activite act : listeActivite) {
			data[i][0] = act.getCodeInfo();
			data[i][1] = act.getMatiere();
			data[i][2] = act.getClasse();
			data[i][3] = act.getEnseignant();
			data[i][4] = act.getNbreHeure();
			i++;
		}

		MartTabModel model = new MartTabModel(data, title);
		MartTable tabActivite = new MartTable(model);
		panListe.removeAll();
		panListe.add(new JScrollPane(tabActivite), BorderLayout.CENTER);
		panListe.revalidate();
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
		showMessage("Succès");
		refresh();
	}
}
