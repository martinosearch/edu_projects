package configurationExamen;

import eleve.EleveClasse;
import etablissement.Etablissement;
import function.Constance;
import graphicsModel.MartList;
import interfacePerso.Observer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import componentFactory.MartButton;
import tableComponent.MartTabModel;
import tableComponent.MartTable;
import agent.Agent;
import agent.Responsable;
import configurationAppli.AbstractConfigPanel;
import configurationAppli.Setting;
import connection.DAO;
import connection.DAOFactory;

/**
 * Cette classe permet de définir les répartition en salle
 * 
 * 
 * @author martino
 *
 */

public class ConfRepEnSalle extends AbstractConfigPanel implements Observer {
	DAO elvclsdao, respdao, etsdao;
	private JScrollPane sc;

	private JPanel container;
	private MartList<EleveClasse> eleves;
	private JPanel pan2;
	private int nbreSalle = 0;
	private MartTable tableSalle;
	private JPanel pan1;
	private MartButton bAjouter = new MartButton().getAjouter();
	private MartButton bDiminuer = new MartButton().getDiminuer();
	private JPanel panAffichage;
	private JLabel lbAff;
	private DAO setdao;
	private MartList<Salle> listeSalle = new MartList<Salle>();

	public ConfRepEnSalle(String tit) {
		super(tit);
		this.setLayout(new BorderLayout());
		elvclsdao = DAOFactory.getDAO(DAO.CANDIDAT_PERSO);
		setdao = DAOFactory.getDAO(DAO.SETTING);

		container = new JPanel(new GridLayout());

		initComponent();

		this.add(container, BorderLayout.CENTER);
	}

	private void initComponent() {
		pan1 = new JPanel();

		pan1.add(bDiminuer);
		pan1.add(bAjouter);
		pan1.setBorder(BorderFactory.createLineBorder(Color.gray));

		pan2 = new JPanel(new BorderLayout());

		panAffichage = new JPanel(new BorderLayout());
		// Etat
		lbAff = new JLabel();
		panAffichage.add(lbAff, BorderLayout.CENTER);

		pan1.add(panAffichage);

		container.add(pan1);
		container.add(pan2);

		pan2.setBorder(BorderFactory.createLineBorder(Color.gray));

		bAjouter.addActionListener(this);
		bDiminuer.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Component source = (Component) e.getSource();

		if (source == bAjouter) {
			nbreSalle++;
			refresh();
		}

		if (source == bDiminuer) {
			nbreSalle--;
			refresh();
		}

	}

	@Override
	public MartList<Setting> getSettings() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object find(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save() {
		setdao.load(null, null, 0, annee);

		Setting nSalle = new Setting("nbreSalle", String.valueOf(nbreSalle));
		setdao.update_create(nSalle);

		for (int i = 0; i < nbreSalle; i++) {
			try {
				String nbre = (String) tableSalle.getValueAt(i, 1);
				Setting set = new Setting("effSalle_" + (i + 1), nbre);
				setdao.update_create(set);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void refresh() {
		elvclsdao.loadExam(annee);
		eleves = elvclsdao.getListObt();

		if (nbreSalle == 0) {
			setdao.load(null, null, 0, annee);
			try {
				nbreSalle = Integer.parseInt((String) ((Setting) setdao
						.findObj("nbreSalle")).getAttribut());
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		String[] title = { "Salle", "Effectif" };
		Object[][] data = new Object[nbreSalle][2];

		for (int i = 0; i < nbreSalle; i++) {
			int effSalle = 0;

			try {
				effSalle = Integer.parseInt((String) ((Setting) setdao
						.findObj("effSalle_" + (i + 1))).getAttribut());
			} catch (Exception e) {
				e.printStackTrace();
			}
			Salle salle = new Salle(i, effSalle);

			data[i][0] = "Salle " + (i + 1);

			data[i][1] = String.valueOf(salle.getEffectif());

			listeSalle.add(salle);

		}

		MartTabModel model = new MartTabModel(data, title);
		model.setColEditable(1);
		model.addObserver(this);

		tableSalle = new MartTable(model);
		pan2.removeAll();
		pan2.add(new JScrollPane(tableSalle), BorderLayout.CENTER);
		pan2.revalidate();

		update();

	}

	@Override
	public void update() {
		int reste = eleves.size();
		for (int i = 0; i < nbreSalle; i++) {
			try {
				int nbre = Integer.parseInt((String) tableSalle
						.getValueAt(i, 1));
				reste = reste - nbre;
				// System.out.println("nous avons: " + nbre);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (reste < 0) {
			JOptionPane.showMessageDialog(null,
					"Vous avez dépassé le nombre total de candidats");
		}
		// Etat
		String text = "<html><table><tr><td>Nombre total de candidats:</td><td color='blue'>"
				+ eleves.size()
				+ "</td></tr>"
				+ "<tr><td>Nombre de canditat n'ayant pas de salle:</td><td color='blue'>"
				+ reste + "</td></tr>" + "</table></html>";
		lbAff.setText(text);
		lbAff.revalidate();
	}

	public int getNbreSalle() {
		return nbreSalle;
	}

	public int getEffSalle(int i) {
		try {
			nbreSalle = (listeSalle.get(i)).getEffectif();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nbreSalle;
	}

}
