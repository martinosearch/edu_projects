package configurationAppli;

import graphicsModel.FrameIcon;
import graphicsModel.MartFrame;
import graphicsModel.MartList;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import componentFactory.MartButton;

/**
 * La classe qui gère les configurations générales
 * 
 * @author Administrateur
 *
 */

public abstract class AbstractConfig extends MartFrame {
	protected JTabbedPane tp;
	protected JPanel container;

	protected String annee = "";
	protected String classe = "";
	protected int trimestre = 0;
	protected int type;
	protected MartButton bSave = new MartButton().getSauvegarder();
	protected MartButton bRecondure = new MartButton().getRecondure();
	protected MartButton bQuitter = new MartButton().getQuitter();
	private MartList<AbstractConfigPanel> listeConfigs = new MartList<AbstractConfigPanel>();

	// constructeur
	public AbstractConfig() {
		this.setSize(MEDIUM_FRAME);
		this.setLocation(INTERNAL_FRAME_LOCATION);
		this.setResizable(false);
		this.addWindowListener(this);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setPub();
		setToolBar();
		setToolBarVertical();
		setIcone(new FrameIcon().getConfig());

		tp = new JTabbedPane();
		container = new JPanel();
		container.setLayout(new BorderLayout());

		// ajout au conteneurs
		container.add(tp, BorderLayout.CENTER);
	}

	/**
	 * Methode qui instantie les différents composants
	 * 
	 */
	public abstract void initComponent();

	public void save() {
		for (AbstractConfigPanel set : listeConfigs) {
			set.setAnnee(annee);
			set.save();
		}
		this.showMessage("Mise à jour efféctué");
	}

	public void refresh() {
		for (AbstractConfigPanel set : listeConfigs) {
			set.refresh();
		}
	}

	public void addConfig(MartList<AbstractConfigPanel> configs) {
		for (AbstractConfigPanel set : configs) {
			listeConfigs.add(set);
			tp.add(set.getTitle(), set);
		}
	}

	public void close() {
		this.dispose();
	}

	public String getAnnee() {
		return annee;
	}

	public void setAnnee(String annee2) {
		annee = annee2;
	}

	public int getTrimestre() {
		return trimestre;
	}

	public void setTrimestre(int trimestre) {
		this.trimestre = trimestre;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

}
