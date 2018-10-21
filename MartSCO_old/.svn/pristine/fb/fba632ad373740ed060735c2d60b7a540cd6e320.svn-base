package configurationAppli;

import function.Constance;
import function.Fichier;
import graphicsModel.MartCheckBox;
import graphicsModel.MartList;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import tableComponent.MartTabModel;
import tableComponent.MartTable;
import componentFactory.MartButton;
import matiere.Matiere;
import connection.DAO;

public class ConfCommandes extends AbstractConfigPanel {
	private Dimension dimTf = new Dimension((int) (dimPanes.getWidth() * 0.65),
			30);

	private JTextField tfPhotoFiltre;
	private JButton bPhotoFiltre;
	private JTextField tfHote;
	private JTextField tfFet;
	private MartButton bPathFet;
	private Fichier fParams;

	public ConfCommandes(String tit) {
		super(tit);
		this.setLayout(new FlowLayout());
		fParams = new Fichier(Constance.getTempDir() + "params"
				+ Constance.getParamExtension());

		this.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.BLUE),
				"Commande Externes", 0, 0));

		// photoFiltre
		tfPhotoFiltre = new JTextField();
		tfPhotoFiltre.setPreferredSize(dimTf);
		tfPhotoFiltre.setForeground(Color.BLUE);

		bPhotoFiltre = new JButton();
		bPhotoFiltre.setPreferredSize(new Dimension(30, 30));
		bPhotoFiltre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fChooser = new JFileChooser();
				int selection = fChooser.showOpenDialog(null);

				if (selection == JFileChooser.APPROVE_OPTION) {
					tfPhotoFiltre.setText(fChooser.getSelectedFile()
							.getAbsolutePath());
				}
			}
		});

		this.add(new JLabel("PhotoFiltre         "));
		this.add(tfPhotoFiltre);
		this.add(bPhotoFiltre);

		// fet
		tfFet = new JTextField();
		tfFet.setPreferredSize(dimTf);
		tfFet.setForeground(Color.BLUE);

		bPathFet = new MartButton();
		bPathFet.setPreferredSize(new Dimension(30, 30));

		bPathFet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fChooser = new JFileChooser();
				int selection = fChooser.showOpenDialog(null);

				if (selection == JFileChooser.APPROVE_OPTION) {
					tfFet.setText(fChooser.getSelectedFile().getAbsolutePath());
				}
			}
		});

		// hote de la base de données
		tfHote = new JTextField();
		tfHote.setPreferredSize(dimTf);
		tfHote.setForeground(Color.BLUE);

		this.add(new JLabel("Fet(Emploi du temps)"));
		this.add(tfFet);
		this.add(bPathFet);
		this.add(new JLabel("Adresse ip Hôte        "));
		this.add(tfHote);
	}

	public MartList<Setting> getSettings() {
		sets = new MartList<Setting>();

		for (MartCheckBox ck : checks) {
			boolean choise = ck.isSelected();
			Setting set = new Setting(ck.getIntitule(), choise);
			sets.add(set);
		}

		return sets;
	}

	@Override
	public Object find(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void refresh() {
		// affichage des commandes
		tfPhotoFiltre.setText(fParams.findParam("cheminPhotoFiltre"));
		tfFet.setText(fParams.findParam("cheminFet"));
		tfHote.setText(fParams.findParam("ipHote"));
	}

	public void save() {
		// Pour les commandes
		fParams = new Fichier(Constance.getTempDir() + "params"
				+ Constance.getParamExtension());
		fParams.writeParam(new Setting("cheminPhotoFiltre", tfPhotoFiltre
				.getText()));
		fParams.writeParam(new Setting("cheminFet", tfFet.getText()));
		fParams.writeParam(new Setting("ipHote", tfHote.getText()));
		fParams.writeParam(new Setting("urlJar", new File(Constance
				.getJarName()).getPath()));
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
}
