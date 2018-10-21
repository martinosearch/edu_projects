package function;

public class SerialGEN {
	protected String intitule = "";
	protected String nom = "";
	protected String contact = "";
	protected String code = "";

	public SerialGEN(String inti, String nom, String contact) {
		this.intitule = inti;
		this.nom = nom;
		this.contact = contact;
	}

	public String getSerial() {
		String initiale = "MT";
		String suffixe = "RE";
		int nbre1 = intitule.length();
		int nbre2 = nom.length();
		int nbre3 = contact.length();
		int nbre4 = 3 * ((((nbre2 + 13 + nbre1) * nbre3 - 2) + 1989) - 11);

		code = (nbre1 + initiale + nbre4 + suffixe);
		try {
			code = code.replace('2', nom.charAt(1));
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("le code généré est: " + code);

		return code;
	}

	public String getSerialAnnuel() {
		String initiale = "MT";
		String suffixe = "RE";
		int nbre1 = intitule.length();
		int nbre2 = nom.length();
		int nbre3 = contact.length();
		int nbre4 = 3 * ((((nbre2 + 13 + nbre1) * nbre3 - 2) + Constance
				.getAnGrande(Constance.getAnnee())) - 11);

		code = (nbre1 + initiale + nbre4 + suffixe);
		try {
			code = code.replace('2', nom.charAt(1));
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("le code généré est: " + code);

		return code;
	}
}
