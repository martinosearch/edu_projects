package note;

import org.joda.time.DateTime;

import function.MartArranger;
import function.MartFormatter;
import graphicsModel.MartList;
import abstractObject.AbstractPojo;

public class SerieNote extends AbstractPojo {
	private MartList<Note> liste;
	private String classe;
	private int trimestre;
	private int numSerie;
	private String matiere;
	private DateTime date;
	private String id;
	private double bareme;

	public SerieNote(String id) {
		this.id = id;
		liste = new MartList<Note>();
	}

	public MartList<Note> getListe() {
		return liste;
	}

	public void setListe(MartList<Note> liste) {
		this.liste = liste;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	public int getTrimestre() {
		return trimestre;
	}

	public void setTrimestre(int trimestre) {
		this.trimestre = trimestre;
	}

	public void setMatiere(String mat) {
		matiere = mat;
	}

	public void add(Note note) {
		liste.smartAdd(note);
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getIntitule() {
		return null;
	}

	@Override
	public void setIntitule(String inti) {

	}

	@Override
	public int getRang() {
		return 0;
	}

	@Override
	public void setRang(int rg) {
	}

	@Override
	public double getValue() {
		return 0;
	}

	@Override
	public void setValue(double val) {

	}

	@Override
	public String getPrimaryKey() {
		return id;
	}

	@Override
	public String getInfo(int i) {
		return null;
	}

	@Override
	public String getCor() {
		return null;
	}

	public String getValueSerie() {
		String str = "";
		str += getBareme() + ";";

		for (Note note : liste) {
			String not = "''''";
			if (!note.getNote1str().equals("")) {
				not = note.getNote1str();
			}

			str += note.getCodeInfo() + ";" + not + ";";
		}

		return str;
	}

	public DateTime getDate() {
		return date;
	}

	public String getMatiere() {
		return matiere;
	}

	public void setValueSerie(String str) {
		MartList temp = MartFormatter.decomposer(str, ';');

		Note note = null;

		setBareme(Double.parseDouble((String) temp.get(0)));

		liste = new MartList<Note>();
		for (int i = 1; i < temp.size(); i++) {
			if (i % 2 != 0) {
				note = new Note();
				note.setCodeInfo((String) temp.get(i));
			} else {
				note.setNote1str((String) temp.get(i));
				liste.add(note);
			}

			/*
			 * try { Thread.sleep(100); } catch (InterruptedException e) {
			 * e.printStackTrace(); }
			 */
		}
	}

	public void setDate(DateTime dateTime) {
		date = dateTime;
	}

	public int getNumber() {
		MartList lis = MartFormatter.decomposer(getPrimaryKey(), ';');
		return Integer.parseInt((String) lis.get(1));
	}

	public double getBareme() {
		return bareme;
	}

	public void setBareme(double bareme) {
		this.bareme = bareme;
	}
}
