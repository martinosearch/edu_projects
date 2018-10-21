package planning;

import javax.swing.JSpinner;

public class SpinnerPlanning extends JSpinner {
	String id;

	public SpinnerPlanning(String ident) {
		super();
		id = ident;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
