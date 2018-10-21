package security;

import graphicsModel.MartList;

import javax.swing.JOptionPane;

public class Droits {
	static User user;
	private static boolean hasAccess;
	private static String classNiveau;

	public static boolean hasAccess(Class cl) {
		System.out.println("JE SUIS APPELE");
		classNiveau = cl.getSimpleName();
		user = CurrentUser.getInstance();
		hasAccess = false;

		MartList<String> liste = new MartList<String>();

		System.out.println("L'utilisateur actuel est:*************** "
				+ user.getLogin() + " " + user.getType() + " Niveau "
				+ user.getNiveau());

		if (user.getLogin() == null) {
			System.out.println("L'utisateur actuel est le PROGRAMMEUR");
			user = new User("adm", "alpha", "ADMINISTRATEUR", 5, "A0005");
		}

		if (!Soldier.getInstance().isRegister()) {
			user = new User("none", "none", "SYSTEME", 1, "");
			System.out.println("L'utisateur actuel est le SYSTEM");
		}

		try {
			if (user.getType().equals("SYSTEME")) {
				if (user.getNiveau() == 1) {
					liste.add("Register");
				}
			}

			if (user.getType().equals("ADMINISTRATEUR")) {
				if (user.getNiveau() == 1) {
					liste.add("AccueilSCO");
					liste.add("AccueilBull");
					liste.add("AccueilBull");
					liste.add("AccueilBull");
					liste.add("AccueilBull");
					liste.add("AccueilBull");
					liste.add("AccueilBull");
					liste.add("AccueilBull");
				}

				if (user.getNiveau() > 1) {
					liste.add("AccueilBull");
				}

				if (user.getNiveau() > 2) {
					liste.add("AccueilBull");
				}

				if (user.getNiveau() > 3) {
					liste.add("AccueilBull");
				}

				if (user.getNiveau() > 4) {
					liste.add("ALL");
				}
			}

			else if (user.getType().equals("COMPTABLE")) {
				if (user.getNiveau() == 1) {
					liste.add("AccueilBull");
				}

				if (user.getNiveau() > 1) {
					liste.add("AccueilBull");
				}

				if (user.getNiveau() > 2) {
					liste.add("AccueilBull");
				}

				if (user.getNiveau() > 3) {
					liste.add("AccueilBull");
				}

				if (user.getNiveau() > 4) {
				}
			}

			else if (user.getType().equals("SECRETAIRE")) {
				if (user.getNiveau() == 1) {
					liste.add("AccueilBull");
				}

				if (user.getNiveau() > 1) {
					liste.add("AccueilBull");
				}

				if (user.getNiveau() > 2) {
					liste.add("AccueilBull");
				}

				if (user.getNiveau() > 3) {
					liste.add("AccueilBull");
				}

				if (user.getNiveau() > 4) {
				}
			}

			else if (user.getType().equals("OPS")) {
				if (user.getNiveau() == 1) {
					liste.add("AccueilBull");
				}

				if (user.getNiveau() > 1) {
					liste.add("AccueilBull");
				}

				if (user.getNiveau() > 2) {
					liste.add("AccueilBull");
				}

				if (user.getNiveau() > 3) {
					liste.add("AccueilBull");
				}

				if (user.getNiveau() > 4) {
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// prise de décision
		for (String str : liste) {
			if (str.equals("ALL")) {
				hasAccess = true;
				break;
			} else if (str.equals(classNiveau)) {
				hasAccess = true;
				break;
			}
		}

		if (hasAccess == false) {
			JOptionPane
					.showMessageDialog(
							null,
							"DESOLE! \n Vous ne disposez pas des accréditations nécessaires pour cette action.",
							"AVERTISSEMENT", JOptionPane.WARNING_MESSAGE);
		}

		return hasAccess;
	}
}
