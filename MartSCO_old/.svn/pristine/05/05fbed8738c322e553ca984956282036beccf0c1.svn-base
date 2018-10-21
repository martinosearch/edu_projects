package configurationPlanning;

import graphicsModel.MartList;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import configurationAppli.AbstractConfigPanel;
import configurationAppli.Setting;
import connection.DAO;
import connection.DAOFactory;

public class ConfJour extends AbstractConfigPanel implements ChangeListener {

	private static ConfJour instance;
	private JPanel panDays;
	private JSpinner jspJours;
	int indice = 0;
	private MartList<Component> listeJourField;
	private String annee;
	private DAO setdao;

	public ConfJour(String title) {
		super(title);
		setdao = DAOFactory.getDAO(DAO.SETTING);
		initComponent();
	}

	private void initComponent() {
		this.setLayout(new BorderLayout());
		panDays = new JPanel();
		panDays.setLayout(new FlowLayout());
		panDays.setPreferredSize(new Dimension(280, 300));
		listeJourField = new MartList<Component>();

		// selecteur du nombre de jours
		JPanel panSpinner = new JPanel();
		jspJours = new JSpinner();
		jspJours.setModel(new SpinnerNumberModel(5, 1, 7, 1));
		jspJours.addChangeListener(this);

		panSpinner.add(new Label("Nombre de Jours"));
		panSpinner.add(jspJours);

		this.add(panSpinner, BorderLayout.NORTH);

		final String[] jours = { "Lundi", "Mardi", "Mercredi", "Jeudi",
				"Vendredi", "Samedi", "Dimanche" };

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				for (int i = 0; i < 7; i++) {
					JTextField tfJour = new JTextField(jours[i]);
					JLabel lbJour = new JLabel("Jour " + (i + 1));
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

	@Override
	public void stateChanged(ChangeEvent arg0) {
		setEnabledComponent();
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

		// nbre de section
		setdao.update_create(new Setting("nbre_jour", String.valueOf(indice)));

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

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub

	}

	public void setAnnee(String an) {
		annee = an;
	}
}
