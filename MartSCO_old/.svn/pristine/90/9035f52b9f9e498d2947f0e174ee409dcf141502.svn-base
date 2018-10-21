package statistique;

import tableComponent.MartTable;
import interfacePerso.Observer;
import abstractObject.AbstractChooser;
import abstractObject.AbstractControler;
import abstractObject.AbstractModel;

public abstract class AbstractStaWritter implements Observer {

	protected AbstractControler controler;
	protected AbstractModel model;
	protected ChooserSta chooser;
	protected String title;
	protected boolean isTrimestreSetting = true;

	public AbstractStaWritter(String tr) {
		title = tr;
	}

	public abstract void valider();

	public void set() {
		chooser = new ChooserSta(this);
		chooser.isTrimestreSetting(isTrimestreSetting);
		chooser.setVisible(true);
	}

	public void setTitle(String str) {
		title = str;
	}

	public String getTitle() {
		return title;
	}

	public AbstractChooser getFrame() {
		return chooser;
	}

	public MartTable getTableOfChoise() {
		return chooser.getTableOfChoise();
	}

	public void isTrimestreSetting(boolean b) {
		isTrimestreSetting = b;
	}

	public boolean isTrimestreSetting() {
		return isTrimestreSetting;
	}
}
