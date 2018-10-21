package function;

import graphicsModel.MartList;

public class MartFormatter {
	private static Character separator = '-';
	private static MartList<String> resultSep;
	private static String chaine;
	private static int index;

	public static MartList decomposer(String str, Character separator1) {
		separator = separator1;
		resultSep = new MartList<String>();
		chaine = str;
		try {
			do {
				index = chaine.indexOf(separator);
				if (index != -1) {
					String element = chaine.substring(0, index);
					if (!element.equals("")) {
						resultSep.add(element);
					}

					// System.out.println("j'ai==================>>" + chaine);

					chaine = chaine.substring(index + 1);

				} else {
					if (!chaine.equals("")) {
						resultSep.add(chaine);
					}
					chaine = null;
				}

			} while (chaine != null);
		} catch (Exception e) {

		}

		return resultSep;
	}

	public static String getDecomp(int pos) {
		String str = "";
		try {
			str = resultSep.get(pos - 1);
		} catch (Exception e) {

		}

		return str;
	}

	public static void main(String[] args) {
		MartList liste = decomposer("as_2015_2016", '_');
		System.out.println(getDecomp(1) + " " + getDecomp(2) + " "
				+ getDecomp(3));
		System.out.println("Taille: " + liste.size());
	}

	public static String correctDecimal(String note) {
		MartList liste = decomposer(note, '.');
		String noteC = note;

		if (liste.size() > 1) {
			double dec = Double.parseDouble((String) liste.get(1));
			if (dec == 0) {
				noteC = String.valueOf(liste.get(0));
			}
		}

		return noteC;
	}
}
