package security;

import javax.swing.JOptionPane;

import annee.Annee;
import configurationAppli.Setting;
import connection.DAO;
import connection.DAOFactory;
import function.Constance;
import function.GeneralVoid;
import function.SerialGEN;

public class Soldier {
	protected static User user;
	protected static DAO userdao = DAOFactory.getDAO(DAO.USER);
	private boolean first = false;
	private DAO refdao;
	private String cleCor;
	private String cleCorAn;
	private DAO setdao, andao;
	private static Soldier instance;

	public Soldier() {
		setdao = DAOFactory.getDAO(DAO.SETTING);
		andao = DAOFactory.getDAO(DAO.ANNEE);
	}

	public boolean isfistTime() {
		return first;
	}

	public void setFirstTime(boolean bool) {
		this.first = bool;
		System.out.println("l'état a changé en " + first);
	}

	public boolean isRegister() {
		try {
			Constance.initialize();
			Constance.miseajour();
		} catch (Exception e) {
			e.printStackTrace();
		}

		SerialGEN serial1 = new SerialGEN(Constance.INITIALE, Constance.NOM,
				Constance.TEL);

		cleCor = serial1.getSerial();// la clef correcte
		cleCorAn = serial1.getSerialAnnuel();

		String key = Constance.MY_LIFE;

		System.out.println("la clé est :" + key + " au lieu de " + cleCor);
		System.out.println("la clé annuel est :" + key + " au lieu de "
				+ cleCorAn);

		boolean isResg = false;

		if (key.equals(cleCor)) {
			isResg = true;
			setPermenentUse(true);
		} else if (key.equals(cleCorAn)) {
			isResg = true;
			setPermenentUse(false);
		} else {
			isResg = false;
			setWarning();
		}

		return isResg;
	}

	private void setWarning() {
		setdao.load();
		Setting set = (Setting) setdao.findObj("licence_p");
		boolean b = Boolean.parseBoolean((String) set.getAttribut());

		if (!b) {
			JOptionPane
					.showMessageDialog(
							null,
							"Vous avez opter pour une licence annuelle donc vous"
									+ " devez la renouveller.\n Toutefois pouvez continuer à utiliser vos "
									+ "anciennes données après le redemarrage \n Merci d'utiliser MartSCO",
							"AVERTISSEMENT", JOptionPane.OK_OPTION);

			Annee annee = new Annee(Constance.getAnnee());
			andao.deleteObj(annee);

			GeneralVoid gv = new GeneralVoid();
			gv.setDoRestart(true);
			gv.doSmartClosing();
		}
	}

	private void setPermenentUse(boolean b) {
		Setting set = new configurationAppli.Setting("licence_p", "false");

		if (b) {
			set = new configurationAppli.Setting("licence_p", "true");
		}

		setdao.load(null, null, 1, Constance.getAnnee());
		setdao.update_create(set);
	}

	public static boolean compare(String login, String pass) {
		userdao.load();
		user = (User) userdao.findObj(login);

		if (user.getPass().equals(pass))
			return true;
		else
			return false;
	}

	public static void main(String[] args) {
		Soldier sol = new Soldier();
		System.out.println("Enrégistrer: " + sol.isRegister());
	}

	public static User getUser() {
		return user;
	}

	public static Soldier getInstance() {
		if (instance == null)
			instance = new Soldier();
		return instance;
	}

}
