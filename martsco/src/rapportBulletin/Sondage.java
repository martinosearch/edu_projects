package rapportBulletin;

import eleve.Eleve;
import eleve.EleveClasse;
import examen.Examen;
import function.MartArranger;
import graphicsModel.MartFrame;
import graphicsModel.MartList;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import matiere.MatiereProg;
import note.InfoClasse;
import note.Moyenne;
import note.NoteViewer;
import progress.Avancer;
import tableComponent.MartTabModel;
import tableComponent.MartTable;
import annee.Decoupage;
import classe.Classe;
import connection.DAO;

public class Sondage extends MartFrame {
	private String annee = "", classe = "", examen = "";
	private int trimestre = 1;
	private String trimstr = "";

	private DAO clsdao, andao, matpdao, elvdao, elvclsdao, decdao, examdao,
			petsdao;
	private ArrayList<MatiereProg> matieres;
	private ArrayList<EleveClasse> eleves;

	private Decoupage dec;
	private int type = 1;
	private NoteViewer nview;

	private DecimalFormat formatter = new DecimalFormat("00.00");

	private String strEts = "";
	private String matiere;

	private MartList<String> listeEts;
	private JPanel container;
	private Classe cls;
	private JLabel lbMessage;
	protected int t = 0;

	// constructeur avec paramètre
	public Sondage() {
		this.setTitle("SONDAGE DES RESULTATS");
		this.setSize(300, 500);
		this.setResizable(true);
		initComponent();

		this.getContentPane().add(container, BorderLayout.CENTER);
	}

	// creation des composants
	public void initComponent() {
		container = new JPanel();
		container.setLayout(new BorderLayout());
	}

	public void createResutat(String cl, int trim, String an) {
		classe = cl;
		annee = an;
		trimestre = trim;
		type = DaoLoader.BULLETIN;

		DaoLoader dLoader = new DaoLoader(type);
		clsdao = dLoader.getClsDao();
		matpdao = dLoader.getMatProgDao();
		elvdao = dLoader.getElvDao();
		elvclsdao = dLoader.getElvClsDao();
		decdao = dLoader.getDecDao();
		andao = dLoader.getAnDao();

		clsdao.load();
		andao.load();
		elvdao.load();

		cls = (Classe) clsdao.findObj(classe);

		load();
		refresh();
		surveillance();

	}

	public synchronized void createResutat(final String exam) {
		examen = exam;
		type = DaoLoader.RELEVE;

		DaoLoader dLoader = new DaoLoader(type);

		clsdao = dLoader.getClsDao();
		matpdao = dLoader.getMatProgDao();
		elvdao = dLoader.getElvDao();
		elvclsdao = dLoader.getElvClsDao();
		andao = dLoader.getAnDao();
		examdao = dLoader.getExamDao();
		petsdao = dLoader.getPEtsDao();

		clsdao.load();
		andao.load();
		elvdao.load();
		examdao.load();
		petsdao.load();

		load();
		refresh();
		surveillance();

	}

	private void surveillance() {
		lbMessage = new JLabel();
		panEtat.add(lbMessage, BorderLayout.CENTER);

		new Thread(new Runnable() {
			public void run() {
				while (true) {
					try {
						setRebours();
						Thread.sleep(15000);
						refresh();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		}).start();
	}

	private void setRebours() {
		t = 0;
		new Thread(new Runnable() {
			public void run() {
				while (t < 15) {
					t++;
					lbMessage.setText("Mise à jour dans: " + (15 - t) + "s");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	@Override
	public void load() {
		// Definition de la dénomination
		if (type == DaoLoader.BULLETIN) {
			decdao.load(new String(), classe, trimestre, annee);
			dec = (Decoupage) decdao.findObj(classe);
			dec.setSection(trimestre);

			trimstr = dec.getSectionStr();
		}

		if (type == DaoLoader.RELEVE) {
			Examen exam = (Examen) examdao.findObj(examen);
			listeEts = ((EtsExamen) petsdao.findObj(examen)).toList();

			for (String str : listeEts) {
				strEts += str + ", ";
			}
		}
	}

	// nous définissons la mise ajour de la fiche
	@Override
	public void refresh() {
		// table des notes
		if (type == DaoLoader.BULLETIN) {
			matpdao.load(new String(), classe, trimestre, annee);
			elvclsdao.load(new String(), classe, trimestre, annee);
		}

		if (type == DaoLoader.RELEVE) {
			matpdao.loadExam(examen);
			elvclsdao.loadExam(examen);
		}

		eleves = elvclsdao.getListObt();
		matieres = matpdao.getListObt();

		int nbreElv = eleves.size();
		int nbreMat = matieres.size();

		// Chargement des notes
		nview = new NoteViewer();

		if (type == DaoLoader.BULLETIN) {// pour bulletin
			nview.load(classe, trimestre, annee);
		}

		if (type == DaoLoader.RELEVE) {// pour releve
			nview.load(examen);
		}

		InfoClasse infocls = nview.getInfoClasse();
		Object[][] dataSondage = new Object[eleves.size()][4];

		if (type == DaoLoader.RELEVE && listeEts.size() > 1) {
			dataSondage = new Object[eleves.size()][4];
		}

		for (int i = 0; i < eleves.size(); i++) {
			try {
				Eleve eleve = nview.getElvAuRg("std", i + 1);
				String matricule = eleve.getCodeInfo();
				Moyenne moy = nview.getMoyenne(new EleveClasse(matricule));

				// On affiche la moyenne si l'élève a composé
				if (moy.hasCompose() == true) {
					double moytrim = moy.getMoyGen();

					String moyf = formatter.format(moytrim);
					String sexe = eleve.getSexe();
					String rang = MartArranger.getOrder(i + 1, sexe);

					dataSondage[i][0] = rang;
					dataSondage[i][1] = eleve.getNom();
					dataSondage[i][2] = moyf;

					if (type == DaoLoader.RELEVE && listeEts.size() > 1) {
						dataSondage[i][3] = eleve.getEts();
					}

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		String[] title = { "Rang", "Nom", "Moyenne" };

		String[] title2 = { "Rang", "Nom", "Moy", "Ets" };

		MartTabModel mod = new MartTabModel(dataSondage, title);

		if (type == DaoLoader.RELEVE && listeEts.size() > 1) {
			mod = new MartTabModel(dataSondage, title2);
		}

		MartTable tableSondage = new MartTable(mod);
		tableSondage.setColumnSize(0, 1);
		tableSondage.setColumnSize(1, 1);
		tableSondage.setColumnSize(2, 1);

		container.removeAll();
		container.add(new JScrollPane(tableSondage));
		container.revalidate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
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

	public static void main(String[] args) {
		Sondage sond = new Sondage();
		sond.createResutat("BEPC- BLANC Serie B MAI 2016");
		sond.setVisible(true);
	}
}
