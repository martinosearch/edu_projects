package images;

import java.net.URL;

import function.Constance;

public class RessourceFinder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public String getBillet() {
		return "img_billet.png";
	}

	public String getDB() {
		return "img_db.png";
	}

	private URL getURL(String str) {
		ClassLoader loader = getClass().getClassLoader();
		URL url = loader.getResource(Constance.getImageFolder() + str);

		return url;
	}

	public URL getBullModel2() {
		return getURL("img_bullModel2.png");
	}

	public URL getBullModel1() {
		return getURL("img_bullModel1.jpg");
	}
}
