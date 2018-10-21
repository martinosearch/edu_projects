package configurationEcole;

import graphicsModel.MartCheckBox;
import graphicsModel.MartList;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import tableComponent.MartTabModel;
import tableComponent.MartTable;
import matiere.Matiere;
import configurationAppli.AbstractConfigPanel;
import configurationAppli.Setting;
import connection.DAO;
import connection.DAOFactory;

public class ConfOrdreMatieres extends AbstractConfigPanel {
	private DAO<Matiere> matdao;
	private MartTabModel modTabOrdre;
	private MartTable tabOrd;

	@SuppressWarnings("unchecked")
	public ConfOrdreMatieres(String tit) {
		super(tit);
		matdao = DAOFactory.getDAO(DAO.MATIERE);
		this.setLayout(new BorderLayout());

		// Matieres
		JPanel panOrd = new JPanel();
		panOrd.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.BLUE),
				"Matières par défaut" + "(Réorganisables)", 0, 0));

		panOrd.setLayout(new BorderLayout());

		// table de la liste par défaut
		ArrayList<Matiere> listMat = matdao.getList();
		String[][] temp = new String[listMat.size()][1];

		int i3 = 0;
		for (Matiere mat : listMat) {
			temp[i3][0] = mat.getIntitule();
			i3++;
		}

		String[] title = { "Matières par défaut" };
		modTabOrdre = new MartTabModel(temp, title);
		tabOrd = new MartTable(modTabOrdre);
		tabOrd.addMouseListener(modTabOrdre);

		// Button
		JPanel panBut = new JPanel();
		panBut.setLayout(new GridLayout(5, 1));

		panBut.add(bRemonter);
		panBut.add(bDescendre);

		panOrd.add(new JScrollPane(tabOrd), BorderLayout.CENTER);
		panOrd.add(panBut, BorderLayout.EAST);

		this.add(panOrd, BorderLayout.CENTER);
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

	}

	@Override
	public void save() {
		int heightOrd = tabOrd.getRowCount();
		for (int i = 0; i < heightOrd; i++) {
			try {
				// la matiere en cours
				String matiere = (String) tabOrd.getValueAt(i, 0);

				int rang = i;
				Matiere mat = (Matiere) matdao.findObj(matiere);
				mat.setRang(rang);

				matdao.setTypeUpdate(matdao.SIMPLE_UPDATE);
				matdao.update_create(mat);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Component source = (Component) e.getSource();

		if (source == bRemonter) {
			// table de la liste par défaut

			int taille = tabOrd.getRowCount();
			ArrayList<String> listeAct = new ArrayList<String>();
			for (int i1 = 0; i1 < taille; i1++) {
				listeAct.add((String) tabOrd.getValueAt(i1, 0));
			}

			String[][] temp = new String[taille][1];

			final int index = modTabOrdre.getSelectedRow();

			for (int i = 0; i < taille; i++) {
				try {
					if (i == index) {
						temp[i][0] = (String) listeAct.get(index - 1);
					} else {
						temp[i][0] = (String) listeAct.get(i);
					}

					temp[index - 1][0] = (String) listeAct.get(index);
				} catch (Exception e4) {
					e4.printStackTrace();
				}

			}
			modTabOrdre.removeAll();
			for (String[] str : temp) {
				modTabOrdre.addRow(str);
			}
			modTabOrdre.setSelectedRow(index - 1);
		}

		if (source == bDescendre) {
			// table de la liste par défaut
			int taille = tabOrd.getRowCount();
			ArrayList<String> listeAct = new ArrayList<String>();

			for (int i1 = 0; i1 < taille; i1++) {
				listeAct.add((String) tabOrd.getValueAt(i1, 0));
			}

			String[][] temp = new String[taille][1];

			final int index = modTabOrdre.getSelectedRow();

			for (int i = 0; i < taille; i++) {

				if (i == index) {
					temp[i][0] = (String) listeAct.get(index + 1);
				} else {
					temp[i][0] = (String) listeAct.get(i);
				}

				temp[index + 1][0] = (String) listeAct.get(index);
			}

			modTabOrdre.removeAll();
			for (String[] str : temp) {
				modTabOrdre.addRow(str);
			}
			modTabOrdre.setSelectedRow(index + 1);
		}

	}
}
