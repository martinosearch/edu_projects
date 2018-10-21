package function;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class XmlManager {
	private File fichier;
	private Document document;
	private Element racine;

	// constructeur
	public XmlManager(File f) {
		fichier = f;
		SAXBuilder builder = new SAXBuilder();
		try {
			document = builder.build(fichier);
		} catch (JDOMException | IOException e) {
			e.printStackTrace();
		}

		racine = document.getRootElement();
	}

	public Element getRacine() {
		return racine;
	}

	public void setRacine(Element racine) {
		this.racine = racine;
	}

	public ArrayList<String> getListe(String nom) {
		return null;
	}

	public static void main(String[] args) {
		XmlManager manager = new XmlManager(new File("help.martsco"));
		Element racine = manager.getRacine();

		List themes = racine.getChildren("rubrique");

		Iterator i = themes.iterator();

		while (i.hasNext()) {
			Element element = (Element) i.next();
			System.out.println(element.getName());
			System.out.println(element.getText());
		}
	}

	// Ajouter ces deux méthodes à notre classe JDOM1
	public void afficher() {
		try {
			// On utilise ici un affichage classique avec getPrettyFormat()
			XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
			sortie.output(document, System.out);
		} catch (java.io.IOException e) {
		}
	}

	public void enregistrer(String fichier) {
		try {
			// On utilise ici un affichage classique avec getPrettyFormat()
			XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
			// Remarquez qu'il suffit simplement de créer une instance de
			// FileOutputStream
			// avec en argument le nom du fichier pour effectuer la
			// sérialisation.
			sortie.output(document, new FileOutputStream(fichier));
		} catch (java.io.IOException e) {
		}
	}
}
