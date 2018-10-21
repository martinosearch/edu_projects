package configurationEcole;

import graphicsModel.MartImage;
import graphicsModel.MartList;
import images.RessourceFinder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import abstractObject.AbstractModel;
import configurationAppli.AbstractConfigPanel;
import configurationAppli.Setting;
import connection.DAO;
import connection.DAOFactory;

public class ConfModelBulletin extends AbstractConfigPanel {

	private JPanel container;
	private JPanel panModel;
	private JPanel panChooser;
	private MartImage editor;
	private JComboBox cbModel;
	private DAO setdao;

	@SuppressWarnings("unchecked")
	public ConfModelBulletin(String tit) {
		super(tit);

		setdao = DAOFactory.getDAO(DAO.SETTING);

		panModel = new JPanel(new BorderLayout());
		panModel.setBackground(Color.DARK_GRAY);

		panChooser = new JPanel();
		cbModel = new JComboBox();
		cbModel.addItem("MODEL 1");
		cbModel.addItem("MODEL 2");

		panChooser.add(new JLabel("Model"));
		panChooser.add(cbModel);

		setModel(cbModel.getSelectedIndex());
		cbModel.addActionListener(this);

		container = new JPanel(new BorderLayout());
		JPanel modelCont = new JPanel(new BorderLayout());
		modelCont.add(new JScrollPane(panModel), BorderLayout.CENTER);
		modelCont.setPreferredSize(new Dimension(330, 425));

		container.add(modelCont, BorderLayout.EAST);
		container.add(panChooser, BorderLayout.CENTER);

		this.add(container, BorderLayout.CENTER);
	}

	private void setModel(int type) {
		panModel.removeAll();
		if (type == 0) {
			editor = new MartImage(new RessourceFinder().getBullModel1());
		}

		if (type == 1) {
			editor = new MartImage(new RessourceFinder().getBullModel2());
		}

		panModel.add(editor, BorderLayout.CENTER);
		panModel.revalidate();
	}

	@Override
	public void save() {
		setdao.load("", "", 0, annee);

		try {
			Setting set = new Setting("modelBull", String.valueOf(cbModel
					.getSelectedIndex()));

			setdao.update_create(set);

		} catch (Exception e2) {
			e2.printStackTrace();
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Component source = (Component) e.getSource();

		if (source == cbModel) {
			setModel(cbModel.getSelectedIndex());
		}

		System.out.println("I hear you");
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
		setdao.load("", "", 0, annee);
		Setting set = (Setting) setdao.findObj("modelBull");
		int model = 0;
		try {
			model = Integer.parseInt((String) set.getAttribut());
		} catch (Exception e) {
			// e.printStackTrace();
		}

		try {
			cbModel.setSelectedIndex(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getModel() {
		return cbModel.getSelectedIndex();
	}
}
