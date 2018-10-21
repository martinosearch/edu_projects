package configurationAppli;

import graphicsModel.MartCheckBox;
import graphicsModel.MartFrame;
import graphicsModel.MartList;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import componentFactory.MartButton;

public abstract class AbstractConfigPanel extends JPanel implements
		ActionListener {
	protected Dimension dimPanes = new Dimension(
			MartFrame.MEDIUM_FRAME.width - 150, 80);
	protected Dimension dimPanes2 = new Dimension(dimPanes.width, 150);
	protected Dimension dimPanes3 = new Dimension(dimPanes.width, 200);
	protected Dimension dimContainer = new Dimension(dimPanes.width,
			MartFrame.MEDIUM_FRAME.height - 20);
	protected String title;
	protected MartList<Setting> sets;
	protected MartList<MartCheckBox> checks;
	protected MartButton bRemonter = new MartButton("Remonter");
	protected MartButton bDescendre = new MartButton("Descendre");
	protected MartButton bValider = new MartButton().getValider();
	protected MartButton bAnnuler = new MartButton().getAnnuler();

	protected String annee;
	protected int trimestre;

	public AbstractConfigPanel() {
	}

	public AbstractConfigPanel(String tit) {
		title = tit;
		sets = new MartList<Setting>();
		checks = new MartList<MartCheckBox>();

		bRemonter.addActionListener(this);
		bDescendre.addActionListener(this);
	}

	public abstract MartList<Setting> getSettings();

	public abstract Object find(String id);

	public abstract void refresh();

	public abstract void save();

	public String getTitle() {
		return title;
	}

	public String getAnnee() {
		return annee;
	}

	public void setAnnee(String annee) {
		this.annee = annee;
	}

	public int getTrimestre() {
		return trimestre;
	}

	public void setTrimestre(int trimestre) {
		this.trimestre = trimestre;
	}
}
