package function;

import graphicsModel.MartList;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import matiere.MatiereProg;
import note.Moyenne;
import note.Note;
import note.NoteViewer;
import abstractObject.Rapport;
import connection.DAO;

public class MartComputer {
	protected double note1;
	protected double note2;
	protected double note3;
	protected double note4;
	protected double moycls = 0, moyp = 0, ptObtenus = 0;
	protected String note1str;
	protected String note2str;
	protected String note3str;
	protected String note4str;

	boolean hasCompose = false;
	String coefConsidered = "";

	public static int FRAN = 1, ANG = 2, ALL = 3, ESP = 4;

	private int div = 0, divp = 0;
	private MatiereProg matiere2;
	private MartList<Note> liste;
	private NoteViewer nview;

	public MartComputer(int typ) {
	}

	public void setNote(Note note) {
		try {
			note1 = note.getNote1();
			note2 = note.getNote2();
			note3 = note.getNote3();
			note4 = note.getNote4();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Vous n'avez pas peut- être choisi le trimestre",
					"ERREUR DE PARAMETRAGE", JOptionPane.ERROR_MESSAGE);
		}

		// nous en avons besoin
		note1str = String.valueOf(note.getNote1()).toString();
		note2str = String.valueOf(note.getNote2()).toString();
		note3str = String.valueOf(note.getNote3()).toString();
		note4str = String.valueOf(note.getNote4()).toString();

		// par combien il faut diviser
		// ou l'élève a t-il composé dans la matière

		if (note1 != 0.001) {
			div++;
			divp++;
			hasCompose = true;
		} else {
			note1 = 0.0;
		}

		if (note2 != 0.001) {
			div++;
			divp++;
			hasCompose = true;
		} else {
			note2 = 0.0;
		}

		if (note3 != 0.001) {
			div++;
			divp++;
			hasCompose = true;
		} else {
			note3 = 0.0;
		}

		if (note4 != 0.001) {
			hasCompose = true;
			divp++;
		} else {
			note4 = 0.0;
		}

		if (div == 0)
			div = 1;

		if (divp > 2)
			divp = 2;

		try {
			moycls = (note1 + note2 + note3) / div;

			moyp = (moycls + note4) / divp;

			if (hasCompose == false) {
				moycls = 0.001;
				moyp = 0.001;
			}

		} catch (Exception e) {
			moycls = new Double(0.001);
			moyp = new Double(0.001);
			e.printStackTrace();
		}

	}

	public MartComputer() {

	}

	public double getMoyCls() {
		return this.moycls;
	}

	public double getMoyp() {
		return moyp;
	}

	public double getPtObtenus(MatiereProg matiere) {
		matiere2 = matiere;
		// on définit une stratégie pour les élèves dispensés d'un cours donné
		// définition du coefficient a afficher

		if (hasCompose == true) {
			// Dans le cas du primaire le notes sont déja coefficiées
			// Donc plus la peine de le faire encore
			if (nview.typeRapport == Rapport.EVAL_TRIMESTRIELLE) {
				if (nview.evaluation == Rapport.INTERRO1) {
					ptObtenus = note1 * matiere2.getCoef();
				} else if (nview.evaluation == Rapport.INTERRO2) {
					ptObtenus = note2 * matiere2.getCoef();
				} else if (nview.evaluation == Rapport.DEVOIR) {
					ptObtenus = note3 * matiere2.getCoef();
				} else {
					ptObtenus = note4 * matiere2.getCoef();
				}
			} else {
				ptObtenus = getMoyp() * matiere2.getCoef();
			}

			coefConsidered = String.valueOf(matiere2.getCoef());
		}// fin hasCompose
		else {
			coefConsidered = "";
		}

		return ptObtenus;
	}

	public String getAppr(double value, int type) {
		String appr = "";
		switch (type) {
		// français
		case 1:
			if (value < 2)
				appr = "Mauvais";
			else if (value < 4)
				appr = "Très-faible";
			else if (value < 6)
				appr = "Faible";
			else if (value < 8)
				appr = "Médiocre";
			else if (value < 10)
				appr = "Insuffisant";
			else if (value < 12)
				appr = "Passable";
			else if (value < 14)
				appr = "Assez-bien";
			else if (value < 16)
				appr = "Bien";
			else if (value < 18)
				appr = "Très-bien";
			else
				appr = "Excellent";
			break;

		// Anglais
		case 2:
			if (value < 2)
				appr = "Bad";
			else if (value < 4)
				appr = "Very weak";
			else if (value < 6)
				appr = "Weak";
			else if (value < 8)
				appr = "Mediocre";
			else if (value < 10)
				appr = "Insufficient";
			else if (value < 12)
				appr = "Passable";
			else if (value < 14)
				appr = "Fair good";
			else if (value < 16)
				appr = "Good";
			else if (value < 18)
				appr = "Very good";
			else
				appr = "Excellent";
			break;

		// Allemand
		case 3:
			if (value < 2)
				appr = "Mauvais";
			else if (value < 4)
				appr = "Très-faible";
			else if (value < 6)
				appr = "Faible";
			else if (value < 8)
				appr = "Médiocre";
			else if (value < 10)
				appr = "Insuffisant";
			else if (value < 12)
				appr = "Passable";
			else if (value < 14)
				appr = "Assez-bien";
			else if (value < 16)
				appr = "Bien";
			else if (value < 18)
				appr = "Très-bien";
			else
				appr = "Excellent";
			break;

		// espagnol
		case 4:
			if (value < 2)
				appr = "Mauvais";
			else if (value < 4)
				appr = "Très-faible";
			else if (value < 6)
				appr = "Faible";
			else if (value < 8)
				appr = "Médiocre";
			else if (value < 10)
				appr = "Insuffisant";
			else if (value < 12)
				appr = "Passable";
			else if (value < 14)
				appr = "Assez-bien";
			else if (value < 16)
				appr = "Bien";
			else if (value < 18)
				appr = "Très-bien";
			else
				appr = "Excellent";
			break;

		}
		return appr;
	}

	public static double round(double a, int dec) {
		return (double) ((int) (a * Math.pow(10, dec) + 0.5) / Math
				.pow(10, dec));
	}

	public String getCoefConsidered() {
		return this.coefConsidered;
	}

	public boolean getHasCompose() {
		return hasCompose;
	}

	public void setMatiereProg(MatiereProg mat) {
		this.matiere2 = mat;
	}

	public synchronized MartList<Note> getNoteDivided(Note note) {
		liste = new MartList<Note>();
		double noteX1 = 0.001;
		double noteX2 = 0.001;
		double noteX3 = 0.001;
		double noteX4 = 0.001;
		double noteY1 = 0.001;
		double noteY2 = 0.001;
		double noteY3 = 0.001;
		double noteY4 = 0.001;

		note1 = note.getNote1();
		note2 = note.getNote2();
		note3 = note.getNote3();
		note4 = note.getNote4();

		try {
			int valeurMax = (int) (note1 / 2);

			if (note1 == 0.001) {
				noteX1 = 0.001;
				noteY1 = 0.001;
			} else if (note1 > 0.001 && note1 < 2) {
				noteX1 = 0;
				noteY1 = note1 - noteX1;
			} else {
				noteX1 = (int) (Math.random() * valeurMax);
				noteY1 = note1 - noteX1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			int valeurMax = (int) (note2 / 2);

			if (note2 == 0.001) {
				noteX2 = 0.001;
				noteY2 = 0.001;
			} else if (note2 > 0.001 && note2 < 2) {
				noteX2 = 0;
				noteY2 = note2 - noteX2;
			} else {
				noteX2 = (int) (Math.random() * valeurMax);
				noteY2 = note2 - noteX2;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			int valeurMax = (int) (note3 / 2);

			if (note3 == 0.001) {
				noteX3 = 0.001;
				noteY3 = 0.001;
			} else if (note3 > 0.001 && note3 < 2) {
				noteX3 = 0;
				noteY3 = note3 - noteX3;
			} else {
				noteX3 = (int) (Math.random() * valeurMax);
				noteY3 = note3 - noteX3;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			int valeurMax = (int) (note4 / 2);

			if (note4 == 0.001) {
				noteX4 = 0.001;
				noteY4 = 0.001;
			} else if (note4 > 0.001 && note4 < 2) {
				noteX4 = 0;
				noteY4 = note4 - noteX4;
			} else {
				noteX4 = (int) (Math.random() * valeurMax);
				noteY4 = note4 - noteX4;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		Note noteX = null;
		try {
			noteX = new Note(note.getCodeInfo(), noteX1, noteX2, noteX3, noteX4);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Note noteY = null;
		try {
			noteY = new Note(note.getCodeInfo(), noteY1, noteY2, noteY3, noteY4);
		} catch (Exception e) {
			e.printStackTrace();
		}

		liste.add(noteX);
		liste.add(noteY);

		return liste;
	}

	/*
	 * public static void main(String[] args){ Constance.setAnnee("2015-2016");
	 * Constance.setClasse("3ème A");
	 * 
	 * 
	 * Note not=(Note) notedao.findObj("A0003");
	 * 
	 * martComputer mc=new martComputer(Constance.MODEL_SECOND);
	 * mc.setNote(not); System.out.println("les notes sont: "+not.getNote1()+"-"
	 * +not.getNote2()+"-"+not.getNote3()+"-"+not.getNote4());
	 * System.out.println("Moyenne de classe: "+mc.getMoyCls());
	 * System.out.println("Moyenne: "+mc.getMoyp());
	 * 
	 * MatiereProg mat=new MatiereProg(); mat.setIntitule("Anglais");
	 * mat.setCoef(2);
	 * System.out.println("Points obtenus: "+mc.getPtObtenus(mat)); }
	 */

	public void setNoteViewer(NoteViewer nv) {
		nview = nv;
	}

}
