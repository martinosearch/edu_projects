package function;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class FiltreFichier extends FileFilter {
	/**
	 * Description du type de fichier
	 */
	private String description;

	/**
	 * Extension du type de fichier
	 */
	private String extension;

	/**
	 * Constructeur du filtre de fichiers
	 * 
	 * @param description
	 *            String contenant la description du type de fichier
	 * @param extension
	 *            String contenant l'extension du type de fichier
	 */
	public FiltreFichier(String description, String extension) {
		if (description == null || extension == null) {
			throw new NullPointerException(
					"La description ou l'extension ne peuvent être nulles.");
		}
		this.description = description;
		this.extension = extension;
	}

	public boolean accept(File file) {
		if (file.isDirectory()) {
			return true;
		}
		String nomFichier = file.getName().toLowerCase();
		return nomFichier.endsWith(extension);
	}

	/**
	 * Renvoie la description du type de fichier
	 * 
	 * @return Un String contenant la description du fichier suivie de
	 *         l'extension du fichier entre parenthèses.
	 */
	public String getDescription() {
		return (description + " (fichier " + extension + ")");
	}
}
