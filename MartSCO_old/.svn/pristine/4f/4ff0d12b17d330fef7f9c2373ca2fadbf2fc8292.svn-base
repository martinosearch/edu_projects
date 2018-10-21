package function;

public class MartConverter {

	public static String convertLetters(double nbre) {
		return LetterConverter.convert(nbre);
	}

	// Converti une mesure en centimètre vers point
	public static int getPtValue(double mes) {
		double mpouce = mes / 2.54;
		double mpt = mpouce * 72;

		int mesapproxi = (int) mpt;
		// System.out.println("La mesure en point: " + mes
		// + " cm -----------------" + "------------->>" + mesapproxi);

		return mesapproxi;
	}

	public static void main(String[] args) {
		System.out.println(convertLetters(200));
	}

}
