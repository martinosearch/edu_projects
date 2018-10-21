package function;

import java.text.DecimalFormat;

public class LetterConverter {

	private static int unites = 0;
	private static double frac = 0.0;
	private static int ordre;
	static DecimalFormat formatter = new DecimalFormat("00.00");

	private static boolean isNegative = false;
	private static boolean precision = false;

	private String[] n1000 = { "", "mille", "million", "milliard" };
	private static int milliards;
	private static int millions;
	private static int milliers;

	public static String convert(double nbre) {
		String result = "";
		treat(nbre);
		result += getStringMilliard(milliards);
		result += getUnionPlusMille(milliards, millions);

		result += getStringMillions(millions);
		result += getUnionPlusMille(millions, milliers);

		result += getStringMilliers(milliers);
		result += getUnionMille(milliers, unites);

		result += getStringUnites(unites);
		return result;
	}

	private static String getStringMilliers(int nbre) {
		if (nbre < 1) {
			return "";
		} else if (nbre > 1) {
			return getStringUnites(nbre) + " mille";
		} else {
			return "mille";
		}
	}

	private static String getStringMillions(int nbre) {
		if (nbre < 1) {
			return "";
		} else if (nbre > 1) {
			return getStringUnites(nbre) + " millions";
		} else {
			return getStringUnites(nbre) + " million";
		}
	}

	private static String getStringMilliard(int nbre) {
		if (nbre < 1) {
			return "";
		} else if (nbre > 1) {
			return getStringUnites(nbre) + " milliards";
		} else {
			return getStringUnites(nbre) + " milliard";
		}
	}

	public static void main(String[] args) {
		System.out.println(convert(14231));
		// System.out.println(202 % 100);
	}

	public static String getStringUnites(int nbreCent) {
		int r = nbreCent;
		// la partie entière
		String str = "";
		int un = 0, dix = 0, cent = 0;

		cent = (int) nbreCent / 100;
		if (cent > 0) {
			nbreCent = nbreCent - (cent * 100);
		}

		dix = (int) nbreCent / 10;

		if (dix > 0) {
			nbreCent = nbreCent - (dix * 10);
		}

		un = nbreCent;

		// pour 10
		if (dix == 1) {
			dix = 0;
			un = un + 10;
		}

		// pour 70
		if (dix == 7) {
			dix = 6;
			un = un + 10;
		}

		// pour 90
		if (dix == 9) {
			dix = 8;
			un = un + 10;
		}

		str += getCentaines(cent);
		str += getUnionCent(r, dix, un);
		str += getDizaines(dix);
		str += getUnionDix(dix, un);
		str += getUnites(un);
		// **********************fin partie entière****************

		// la partie décimale
		if (precision == false && frac == 0) {

		} else {
			str += " virgule ";

			int un2 = 0, dix2 = 0, cent2 = 0, mille2 = 0;

			dix2 = (int) (frac * 10);
			un2 = (int) ((frac * 100) - (dix2 * 10));

			// pour des valeur plus petit que 10
			if (dix2 == 0) {
				str += " zéro ";
			}

			// pour 10
			if (dix2 == 1) {
				dix2 = 0;
				un2 = un2 + 10;
			}

			// pour 70
			if (dix2 == 7) {
				dix2 = 6;
				un2 = un2 + 10;
			}

			// pour 90
			if (dix2 == 9) {
				dix2 = 8;
				un2 = un2 + 10;
			}

			str += getDizaines(dix2);
			str += getUnionDix(dix2, un2);
			str += getUnites(un2);
		}

		return str;
	}

	private static void treat(double nbre) {
		double number = MartComputer.round(nbre, 2);

		// System.out.println("J'ai " + nbre);

		String nombre = formatter.format(number);
		nombre = nombre.replace(',', '.');
		nombre = nombre.replace(" ", "");

		if (nombre.charAt(0) == '-') {
			nombre = nombre.substring(1);
			isNegative = true;
		}

		int entier = (int) number;

		frac = MartComputer.round((number - entier), 2);

		// System.out.println("frac: " + frac);

		if (nombre.length() > 18) {
			nombre = "#Trop grand";
		} else {
			milliards = (int) entier / 1000000000;
			entier = entier - milliards * 1000000000;
			millions = (int) entier / 1000000;
			entier = entier - millions * 1000000;
			milliers = (int) entier / 1000;
			entier = entier - milliers * 1000;
			unites = entier;
			System.out.println(entier + " " + milliards + " " + millions + " "
					+ milliers + " " + unites);
		}
	}

	// les conversion
	public static String getUnionDix(int nbre1, int nbre2) {
		// System.out.println("je reçois: " + nbre1 + " " + nbre2);

		String trait = "";

		if (nbre1 < 1 || nbre2 < 1) {
			trait = "";
		} else {

			if (nbre1 >= 2 && nbre2 < 20)
				trait = "- ";

			if (nbre1 == 2 && nbre2 == 1)
				trait = " et ";

			if (nbre1 == 8 && nbre2 == 1)
				trait = " et ";
		}

		if (nbre1 == 8 && nbre2 == 0) {
			trait = "s";
		}

		return trait;
	}

	public static String getUnionCent(int nbre, int dix, int un) {
		System.out.println("je reçois-100: " + nbre + " " + dix + " " + un);

		String trait = "";

		if (nbre != 0 && nbre % 100 == 0 && dix == 0 && un == 0) {
			trait = "s";
		} else if (dix != 0 || un != 0) {
			trait = " ";
		} else {
			trait = "";
		}

		return trait;
	}

	public static String getUnionMille(int nbre1, int nbre2) {
		// System.out.println("je reçois: " + nbre1 + " " + nbre2);

		String trait = "";
		if (nbre1 < 1 || nbre2 < 1) {
			trait = "";
		} else {
			if (nbre2 > 0) {
				trait = " ";
			}
		}

		return trait;
	}

	public static String getUnionPlusMille(int nbre1, int nbre2) {
		// System.out.println("je reçois: " + nbre1 + " " + nbre2);

		String trait = "";

		if (nbre1 < 1 || nbre2 < 1) {
			trait = "";
		} else {
			if (nbre2 > 0) {
				trait = " ";
			} else {
				if (nbre1 > 1) {
					trait = "s";
				}
			}
		}
		return trait;
	}

	public static String getUnites(int nbre) {
		String[] n1 = { "", "un", "deux", "trois", "quatre", "cinq", "six",
				"sept", "huit", "neuf", "dix", "onze", "douze", "treize",
				"quatorze", "quinze", "seize", "dix-sept", "dix-huit",
				"dix-neuf" };
		return n1[nbre];
	}

	public static String getDizaines(int nbre) {
		String[] n10 = { "", "dix", "vingt", "trente", "quarante", "cinquante",
				"soixante", "soixante-dix", "quatre-vingt", "quatre-vingt-dix" };

		return n10[nbre];
	}

	public static String getCentaines(int nbre) {
		if (nbre < 1) {
			return "";
		} else if (nbre == 1) {
			return "cent";
		} else {
			return getUnites(nbre) + " cent";
		}
	}

	public String getMille(int nbre) {
		int rg = nbre / 1000;

		return null;
	}

	public static boolean isPrecision() {
		return precision;
	}

	public static void setPrecision(boolean precision) {
		LetterConverter.precision = precision;
	}

}
