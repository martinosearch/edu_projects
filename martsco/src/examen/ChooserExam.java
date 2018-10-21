package examen;

import interfacePerso.MartRangeable;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import progress.Avancer;
import abstractObject.AbstractChooser;
import abstractObject.MartObjet;
import annee.Annee;
import classe.Classe;
import configurationExamen.ConfigExamen;
import connection.DAO;
import connection.DAOFactory;
import function.MartArranger;
import function.MartFormatter;
import function.NoteComparator;
import graphicsModel.MartList;

public class ChooserExam extends AbstractChooser {
	protected ActionListener validerListener;
	protected static ChooserExam instance;
	protected Dimension dim1 = new Dimension(100, 30);
	protected Dimension dim2 = new Dimension(300, 30);
	protected Dimension dim3 = new Dimension(290, 60);
	DAO examdao, andao, matdao, elvdao, elvclsdao, decdao;
	String ins = "", eName = "";
	protected MartFormatter decomposer;
	protected MartArranger ma = new MartArranger();

	private ConfigExamen conf;

	public ChooserExam() {
		this.setSize(400, 200);
		this.setLocationRelativeTo(null);
		this.setModal(true);

		initComponent();
	}

	private void initComponent() {
		panAnnee = new JPanel();
		panButton = new JPanel();
		panExamen = new JPanel();
		panEts = new JPanel();
		container = new JPanel();

		panButton.add(bValider);

		load();

		panAnnee.setLayout(new FlowLayout());
		panButton.setLayout(new FlowLayout());
		panExamen.setLayout(new FlowLayout());

		container.setLayout(new GridLayout(4, 1));
		container.add(panAnnee);
		container.add(panExamen);
		container.add(panEts);
		container.add(panButton);

		this.getContentPane().add(container);
	}

	public static ChooserExam getInstance() {
		if (instance == null)
			instance = new ChooserExam();

		instance.load();
		return instance;
	}

	public void load() {
		decomposer = new MartFormatter();
		andao = DAOFactory.getDAO(DAO.ANNEE);
		examdao = DAOFactory.getDAO(DAO.EXAMEN);
		matdao = DAOFactory.getDAO(DAO.MATIERE);
		elvdao = DAOFactory.getDAO(DAO.CANDIDAT);
		elvclsdao = DAOFactory.getDAO(DAO.CANDIDAT_PERSO);

		andao.load();
		examdao.load();

		// on ajoute la liste des annees
		annees = andao.getListObt();
		examens = examdao.getListObt();

		// on initialise l'année et l'examen
		// valeur par défaut
		int indexAn = annees.size();
		int indexExam = examens.size();

		// permet de remplir la combobox annee scolaire
		String[] anneeTemp = new String[annees.size() + 1];
		MartList<MartRangeable> listeAnTemp = new MartList<MartRangeable>();

		int i = 0;
		decomposer.decomposer(annees.get(0).getIntitule(), '-');
		MartObjet obj = new MartObjet(decomposer.getDecomp(2),
				decomposer.getDecomp(2));
		obj.setRang(i);
		listeAnTemp.smartAdd(obj);

		for (Annee an : annees) {
			i++;
			decomposer.decomposer(an.getIntitule(), '-');
			MartObjet obj2 = new MartObjet(decomposer.getDecomp(1),
					decomposer.getDecomp(1));
			obj2.setRang(i);
			listeAnTemp.smartAdd(obj2);

		}

		MartList<MartRangeable> listeAnOrdonne = ma.ordonner(listeAnTemp,
				NoteComparator.DESCENDANT);

		int j = 0;
		for (MartRangeable obj3 : listeAnOrdonne) {
			String data = (String) (((MartObjet) obj3).getObject());
			anneeTemp[j] = data;
			// System.out.println("============>>" + data);
			j++;
		}

		cbAnnee = new JComboBox(anneeTemp);
		cbAnnee.setPreferredSize(dim2);
		cbAnnee.addActionListener(this);

		panAnnee.removeAll();
		panAnnee.add(new JLabel("Année"));
		panAnnee.add(cbAnnee);
		panAnnee.revalidate();

		// permet de remplir la combobox examen
		setComboExamen();
	}

	public void setComboExamen() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

				String[] extemp = new String[examens.size()];
				int i2 = 0;
				System.out
						.println("*****************************" + getAnnee());
				for (Examen exam : examens) {
					if (exam.getAnnee().equals(getAnnee())) {
						extemp[i2] = exam.getIntitule();
						i2++;
					}
				}

				cbExamen = new JComboBox(extemp);
				cbExamen.setPreferredSize(dim2);
				cbExamen.addActionListener(ChooserExam.this);

				panExamen.removeAll();
				panExamen.add(cbExamen);
				panExamen.revalidate();

				if (isEtsChoosing()) {
					setComboEts();
				}
			}
		});
	}

	public void setComboEts() {
		SwingUtilities.invokeLater(new Runnable() {
			private Object isEtsChoosing;

			public void run() {
				conf = new ConfigExamen(getExamen());
				MartList<String> listeEts = conf.relConfig.getEtsPerso();

				System.out.println(listeEts.size());
				// combo ets
				String[] tabEts = new String[listeEts.size() + 1];

				tabEts[0] = "Tous les Ets.";
				for (int i = 0; i < listeEts.size(); i++) {
					tabEts[i + 1] = listeEts.get(i);
				}

				cbEts = new JComboBox(tabEts);
				cbEts.setPreferredSize(dim2);
				cbEts.setForeground(Color.BLUE);

				panEts.removeAll();
				panEts.add(lbEts);
				panEts.add(cbEts);

				cbEts.setVisible(isEtsChoosing());
				lbEts.setVisible(isEtsChoosing());

				panEts.revalidate();
			}
		});
	}

	// Ecouteurs
	public void actionPerformed(ActionEvent e) {
		Component source = (Component) e.getSource();
		// System.out.println("J'écoute" + source);

		if (source == cbAnnee) {
			// System.out.println("J'écoute"
			// + ((JComboBox) source).getSelectedItem());

			setComboExamen();
		}

		if (source == cbExamen) {
			// System.out.println("J'écoute"
			// + ((JComboBox) source).getSelectedItem());

			setComboEts();
		}
	}

	public String getAnnee() {
		return (String) cbAnnee.getSelectedItem();
	}

	public String getExamen() {
		return (String) cbExamen.getSelectedItem();
	}

	public void setAction(ActionListener action) {
		bValider.removeActionListener(validerListener);
		this.validerListener = action;
		bValider.addActionListener(validerListener);
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

	public static void main(String[] args) {
		getInstance().setVisible(true);
	}

	public String getEts() {
		// TODO Auto-generated method stub
		return (String) cbEts.getSelectedItem();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public Classe getClasse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getFirstOption() {
		// TODO Auto-generated method stub
		return false;
	}
}
