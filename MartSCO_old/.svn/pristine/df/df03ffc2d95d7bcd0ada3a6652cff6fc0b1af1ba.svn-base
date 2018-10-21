package graphicsModel;

import java.util.Map;
import java.util.TreeMap;

import javax.swing.UIManager;

import configurationAppli.Setting;
import function.Constance;
import function.Fichier;

public class Design {
	public static String getCurrentLookAndFeel() {
		Fichier fPref = new Fichier(getPreferenceDir());
		String look = fPref.findParam("lookAndFeel");
		return look;
	}

	public static Map<String, String> getLookAndFeelsMap() {
		UIManager.LookAndFeelInfo[] info = UIManager.getInstalledLookAndFeels();
		Map<String, String> map = new TreeMap<String, String>();
		for (int i = 0; i < info.length; i++) {
			String nomLF = info[i].getName();
			String nomClasse = info[i].getClassName();
			map.put(nomLF, nomClasse);
		}

		return map;
	}

	public static void savePreference(Setting set) {
		Fichier fPref = new Fichier(getPreferenceDir());
		fPref.writeParam(set);
	}

	private static String getPreferenceDir() {
		return Constance.getTempDir() + "preference.martsco";
	}
}
