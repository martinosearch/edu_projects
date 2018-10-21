package images;

import java.io.File;

import connection.MartConnection;
import function.Constance;

public class PictureFinder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public File getPhotoEleve(String code) {
		File file;

		File f1 = new File(getPhotoEleveFolder() + code + ".png");
		File f2 = new File(getPhotoEleveFolder() + code + ".jpg");

		if (f1.exists()) {
			file = f1;
		} else {
			file = f2;
		}

		return file;
	}

	public String getPhotoEleveFolder() {
		return "documents\\photos_eleves_" + MartConnection.getDataBase()
				+ "\\";
	}

	public File getPlogo() {
		File file;

		File f1 = new File(getImageFolder() + "Plogo.png");
		File f2 = new File(getImageFolder() + "Plogo.jpg");

		if (f1.exists()) {
			file = f1;
		} else {
			file = f2;
		}

		return file;
	}

	private String getImageFolder() {
		return "documents/images_" + MartConnection.getDataBase() + "/";
	}

	public File getGlogo() {
		File file;

		File f1 = new File(getImageFolder() + "Glogo.png");
		File f2 = new File(getImageFolder() + "Glogo.jpg");

		if (f1.exists()) {
			file = f1;
		} else {
			file = f2;
		}

		return file;
	}

	public File getFiligrane() {
		File file;

		File f1 = new File(getImageFolder() + "filigrane.png");
		File f2 = new File(getImageFolder() + "filigrane.jpg");

		if (f1.exists()) {
			file = f1;
		} else {
			file = f2;
		}

		return file;
	}

	public File getPhotoAgent(String code) {
		File file;

		File f1 = new File(getPhotoAgentFolder() + code + ".png");
		File f2 = new File(getPhotoAgentFolder() + code + ".jpg");

		if (f1.exists()) {
			file = f1;
		} else {
			file = f2;
		}

		return file;
	}

	public String getPhotoAgentFolder() {
		return "documents\\photos_agents_" + MartConnection.getDataBase()
				+ "\\";
	}
}
