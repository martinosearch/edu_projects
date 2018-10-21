package configurationPlanning;

import function.Constance;
import graphicsModel.MartFrame;
import graphicsModel.MartList;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import configurationAppli.AbstractConfigPanel;
import configurationAppli.Setting;
import connection.DAO;
import connection.DAOFactory;
import progress.Avancer;

public class ConfHeur extends AbstractConfigPanel implements ChangeListener {

	private static ConfHeur instance;
	private JPanel panDays;
	private JSpinner jspJours;
	int indice = 0;
	private MartList<Component> listeJourField;
	private String annee;
	private DAO setdao;

	public ConfHeur(String title) {
		super(title);
		setdao = DAOFactory.getDAO(DAO.SETTING);
		initComponent();
	}

	private void initComponent() {
		this.setLayout(new BorderLayout());
		panDays = new JPanel();
		panDays.setLayout(new FlowLayout());
		panDays.setPreferredSize(new Dimension(280, 330));
		listeJourField = new MartList<Component>();

		// selecteur du nombre de jours
		JPanel panSpinner = new JPanel();
		jspJours = new JSpinner();
		jspJours.setModel(new SpinnerNumberModel(9, 1, 9, 1));
		jspJours.addChangeListener(this);

		panSpinner.add(new Label("Nombre d'heures"));
		panSpinner.add(jspJours);

		this.add(panSpinner, BorderLayout.NORTH);

		final String[] jours = { "07:00 - 7h:55", "07:55 - 08:50",
				"08:50 - 09:45", "09:45 - 10:10", "10:10 - 11:05",
				"11:05 - 12:00", "12:00 - 15:00", "15:00 - 15:55",
				"15:55 - 16:50" };

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				for (int i = 0; i < 9; i++) {
					JTextField tfJour = new JTextField(jours[i]);
					JLabel lbJour = new JLabel("Heure " + (i + 1));
					tfJour.setPreferredSize(new Dimension(220, 30));
					panDays.add(lbJour);
					panDays.add(tfJour);

					listeJourField.add(lbJour);
					listeJourField.add(tfJour);

					setEnabledComponent();
				}
			}
		});

		this.add(panDays, BorderLayout.CENTER);
	}

	private void setEnabledComponent() {
		indice = (int) jspJours.getValue();
		// System.out.println("indice= " + indice);

		int i = 0;
		for (Component c : listeJourField) {
			c.setEnabled(false);
			if (i < indice * 2) {
				c.setEnabled(true);
			}
			i++;
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		setEnabledComponent();

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
	public void refresh() {
		// TODO Auto-generated method stub

	}

	@Override
	public void save() {
		setdao.load(null, null, 0, annee);

		// nbre de section
		setdao.update_create(new Setting("nbre_heure", String.valueOf(indice)));

		Setting set = null;
		for (int i = 0; i < listeJourField.size(); i++) {

			if (i % 2 == 0) {
				set = new Setting();
				set.setId("planning_"
						+ ((JLabel) listeJourField.get(i)).getText());

			} else {
				set.setAttribut(((JTextField) listeJourField.get(i)).getText());
				setdao.update_create(set);
			}

		}
	}

	public void setAnnee(String an) {
		annee = an;
	}
}
