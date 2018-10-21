package note;

import java.text.DecimalFormat;

import abstractObject.Rapport;
import eleve.EleveClasse;
import function.MartArranger;
import function.MartComputer;
import function.MartFormatter;
import function.StaData;
import function.Statistician.MoySta;
import graphicsModel.MartList;
import interfacePerso.MartRangeable;
import interfacePerso.SuperDAO;
import matiere.Matiere;
import matiere.MatiereProg;

public class InfoClasseLoader {
	private boolean hasCompose;
	private double moy;
	private double compo;
	private double dev;
	private double note1;
	private double note2;
	private DecimalFormat formatter = new DecimalFormat("00.00");

	public void treat(NoteViewer noteViewer) {
		// définition des variables
		noteViewer.infocls = new InfoClasse();
		noteViewer.Moyennes = new MartList<MartRangeable>();

		long time4 = System.currentTimeMillis(); // traceur de temps

		for (EleveClasse elv : noteViewer.elevesC) {
			double totCoef = 0;
			double grdTotal = 0;
			double totCoefLitt = 0, totCoefSci = 0, totCoefFac = 0;
			double grdTotalLitt = 0, grdTotalSci = 0, grdTotalFac = 0;
			noteViewer.eleve = elv;

			for (MatiereProg mat : noteViewer.matieres) {
				noteViewer.matProg = mat;
				Matiere matiere = (Matiere) noteViewer.matdao.findObj(mat
						.getIntitule());

				long time5 = System.currentTimeMillis(); // traceur de

				InfoNote infonote = noteViewer.getNotes(mat, elv);
				hasCompose = infonote.hasCompose();

				// System.out.println("====================================>> infonote: "
				// + infonote.getCoefConsidered()+" "
				// + hasComposeCompo);

				try {
					note1 = Double.parseDouble(infonote.getNote1());
				} catch (Exception e) {
					// e.printStackTrace();
				}

				try {
					note2 = Double.parseDouble(infonote.getNote2());
				} catch (Exception e) {
					// e.printStackTrace();
				}
				try {
					dev = Double.parseDouble(infonote.getDev());
				} catch (Exception e) {
					// e.printStackTrace();
				}
				try {
					compo = Double.parseDouble(infonote.getCompo());
				} catch (Exception e) {
					// e.printStackTrace();
				}

				try {
					moy = infonote.getmoy();
				} catch (Exception e) {
					// e.printStackTrace();
				}

				if (noteViewer.typeRapport == Rapport.EVAL_TRIMESTRIELLE) {
					if (noteViewer.evaluation == Rapport.INTERRO1) {
						noteViewer.staman.setDataMat(new StaData(mat
								.getIntitule(), note1, elv.getSexe(),
								hasCompose));
					} else if (noteViewer.evaluation == Rapport.INTERRO2) {
						noteViewer.staman.setDataMat(new StaData(mat
								.getIntitule(), note2, elv.getSexe(),
								hasCompose));
					} else if (noteViewer.evaluation == Rapport.DEVOIR) {
						noteViewer.staman
								.setDataMat(new StaData(mat.getIntitule(), dev,
										elv.getSexe(), hasCompose));
					} else {
						noteViewer.staman.setDataMat(new StaData(mat
								.getIntitule(), compo, elv.getSexe(),
								hasCompose));
					}
				} else {
					noteViewer.staman.setDataMat(new StaData(mat.getIntitule(),
							moy, elv.getSexe(), hasCompose));
				}

				// note coefficiée
				String coef = MartFormatter.correctDecimal(infonote
						.getCoefConsidered());

				try {
					note1 = note1 * Double.parseDouble(coef);
				} catch (Exception e) {
					// e.printStackTrace();
				}
				try {
					note2 = note2 * Double.parseDouble(coef);
				} catch (Exception e) {
					// e.printStackTrace();
				}
				try {
					dev = dev * Double.parseDouble(coef);
				} catch (Exception e) {
					// e.printStackTrace();
				}
				try {
					compo = compo * Double.parseDouble(coef);
				} catch (Exception e) {
					// e.printStackTrace();
				}
				try {
					moy = moy * Double.parseDouble(coef);
				} catch (Exception e) {
					// e.printStackTrace();
				}

				// totaliser les
				// notes********************************************************************
				if (hasCompose == true) {
					double value = 0;
					if (noteViewer.typeRapport == Rapport.EVAL_TRIMESTRIELLE) {
						if (noteViewer.evaluation == Rapport.INTERRO1) {
							value = note1;
						} else if (noteViewer.evaluation == Rapport.INTERRO2) {
							value = note2;
						} else if (noteViewer.evaluation == Rapport.DEVOIR) {
							value = dev;
						} else {
							value = compo;
						}
					} else {
						value = moy;
					}

					totCoef = totCoef + mat.getCoef();
					grdTotal = grdTotal + value;

					if (matiere.getType().equals("Littéraire")) {
						totCoefLitt = totCoefLitt + mat.getCoef();
						grdTotalLitt = grdTotalLitt + value;
					}

					if (matiere.getType().equals("Scientifique")) {
						totCoefSci = totCoefSci + mat.getCoef();
						grdTotalSci = grdTotalSci + value;
					}

					// ceci pour calculer la moyenne des mati�res Facult
					if (matiere.getType().equals("Facultative")) {
						totCoefFac = totCoefFac + mat.getCoef();
						grdTotalFac = grdTotalFac + value;
					}
				}// fin total des notes

				// ************pour la barre de progression ****************
				try {
					noteViewer.incrementAvancers();
				} catch (Exception e) {
					e.printStackTrace();
				}
				// ***********************FIN*******************************

			}// fin for matière########################

			double moyGen = MartComputer.round(grdTotal / totCoef, 2);
			double moyLitt = MartComputer.round(grdTotalLitt / totCoefLitt, 2);
			double moySci = MartComputer.round(grdTotalSci / totCoefSci, 2);
			double moyFac = MartComputer.round(grdTotalFac / totCoefFac, 2);

			// si le grand total est nul alors l'élève n'a pas compose
			boolean hasComposeGen = false;
			if (grdTotal != 0) {
				hasComposeGen = true;
			}

			// ajout de la moyenne de l'élève aux statistiques
			noteViewer.staman.setDataMoy(new StaData(noteViewer.classe, moyGen,
					elv.getSexe(), hasComposeGen));

			// System.out.println("====================================>>moy1: "
			// + moy1 + "moy2: " + moy2 + "moy3: " + dev + "moy4: "
			// + compo + "classe: " + noteViewer.classe + hasComposeCompo);

			// les moyennes générales de l'élève
			Moyenne superMoyLitt = new Moyenne(grdTotalLitt, totCoefLitt,
					moyLitt, noteViewer.model);
			Moyenne superMoySci = new Moyenne(grdTotalSci, totCoefSci, moySci,
					noteViewer.model);
			Moyenne superMoyFac = new Moyenne(grdTotalFac, totCoefFac, moyFac,
					noteViewer.model);

			Moyenne superMoyGen = new Moyenne(grdTotal, totCoef, moyGen,
					noteViewer.model);

			// definition des données lit sc fac
			superMoyGen.setLit(superMoyLitt);
			superMoyGen.setScien(superMoySci);
			superMoyGen.setFac(superMoyFac);
			superMoyGen.setHasComposeGen(hasComposeGen);

			// On attribut le nom à la moyenne
			superMoyGen.setId(elv.getCodeInfo());

			// ajout de la moyenne de l'élève
			noteViewer.Moyennes.add(superMoyGen);
		}// fin des traitement par élève

		// TRAITEMENTS DE LA CLASSE
		// On range les moyennes trimestrielles et on les ajoute au
		// classeur
		noteViewer.Moyennes = MartArranger.arrange(noteViewer.Moyennes);
		noteViewer.Moyennes.setId("std");
		noteViewer.Classeurs.add(noteViewer.Moyennes);

		if (noteViewer.type == noteViewer.BULLETIN) {
			// on fais la même chose au moyenne annuelle
			noteViewer.rapmoydao.load(new String(), noteViewer.classe,
					noteViewer.trimestre, noteViewer.annee);

			@SuppressWarnings("unchecked")
			MartList<MartRangeable> dataAn = noteViewer.rapmoydao.getListObt();

			noteViewer.MoyennesAn = MartArranger.arrange(dataAn);
			noteViewer.MoyennesAn.setId("moyAn");
			noteViewer.Classeurs.add(noteViewer.MoyennesAn);
		}

		// On recuperre ici les statistiques
		MartList<MoySta> staResult = noteViewer.staman.getResultMoy();
		MoySta staMoyGen = null;

		if (noteViewer.type == NoteViewer.RELEVE) {
			if (noteViewer.typeRapport == Rapport.EVAL_TRIMESTRIELLE) {
				staMoyGen = staResult.find(noteViewer.classe);
			} else {
				staMoyGen = staResult.find(noteViewer.examen);
			}
		}

		if (noteViewer.type == NoteViewer.BULLETIN) {
			staMoyGen = staResult.find(noteViewer.classe);
		}

		// paramétrage de l'objet infoclasse qui contient les information sur
		// une classe
		try {
			noteViewer.infocls.setEff(staMoyGen.getInscrit());
			noteViewer.infocls.setHmoy(staMoyGen.getHNote());
			noteViewer.infocls.setLMoy(staMoyGen.getLNote());
			noteViewer.infocls.setMoyCls(staMoyGen.getMoyCls());
			noteViewer.infocls.setOntComp(staMoyGen.getPresent());
			noteViewer.infocls.setNMoy(staMoyGen.getSup10());
			noteViewer.infocls.setPercent(formatter.format(staMoyGen
					.getPerSup10()) + " %");
		} catch (Exception e) {
			e.printStackTrace();
		}

		// suvegarde des moyenne de l'élève (cas des bulletins cumulatif)
		if (noteViewer.type == noteViewer.BULLETIN) {
			for (EleveClasse elv : noteViewer.elevesC) {
				Moyenne moyenne = (Moyenne) noteViewer.Moyennes.find(elv
						.getId());
				noteViewer.doArchive(elv, moyenne);

				String rg = noteViewer.getRang(new MatiereProg("moyAn"), elv);
				((SuperDAO) noteViewer.rapmoydao).saveRgAn(elv.getId(), rg);
			}
		}
	}
}
