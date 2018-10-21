package rapportBulletin;

import eleve.EleveClasse;
import function.Constance;
import function.MartComputer;
import matiere.MatiereProg;
import note.InfoNote;

public class BullModelEditor2 extends BullWriterModel {

    private int nbrcol;

    public BullModelEditor2() {

    }

    @Override
    public void write() {
	htmlBody += "<div class='saut'><div id='sautligne'></div>" + "<div class='cadre'>"; // le cadre

	writeEntete();
	writeIdEleve();
	writeColumnTabNote();
	writeLitteraire();
	writeScientifique();
	writeFacultative();

	// saut de ligne
	htmlBody += "<tr><td id='sautligne' colspan='" + nbrcol + "'>" + "</td></tr>";
	writeGrandTotal();

	// fin de la table des notes
	writeFinTabNote();

	writeBilan();
	writeSignature();
	writePiedPage();

	htmlBody += "</div>" // fin cadre

		+ "<div id='sautligne'></div>" // le saut de ligne

		+ "</div>";// saut de page

	// *****POUR LA BARRE DE PORGRESSION***********
	progress.increment();
	// *********************FIN********************
    }

    private void writeFinTabNote() {
	htmlBody += "</table>" + "</div>";
    }

    private void writeColumnTabNote() {
	nbrcol = bs.getnbreCol();
	htmlBody += "<div id='notes'>" + tabNote + "</div>";
    }

    private void writeSignature() {
	// les signatures
	htmlBody += "<div>" + bs.getTabSignature(DocFormat.DIR_TITUL, "tabB") + "</div>";
    }

    private void writeBilan() {
	// Bloc en dessous des notes
	htmlBody += "<div width='100%'><table width='100%' class='tabB'><tr>";
	htmlBody += "<td width='45%' class='tdSB'>";
	writeMoyenne();

	htmlBody += "<td width='55%' class='tdSB' valign='top'>";

	htmlBody += "<table width='100%' class='tabSB'><tr><td  width='40%' class='tdSB' valign='top'>";
	writeTableauHonneur();

	htmlBody += "<td width='60%' class='tdSB' valign='top'>";
	writeMoyClasse();
	htmlBody += "</tr>";

	htmlBody += "<tr><td colspan='2'>";
	writeDiscipline();
	htmlBody += "</td></tr>";

	htmlBody += "<tr><td colspan='2'>";
	writeDecision();
	htmlBody += "</td></tr>";

	htmlBody += "</table>";

	htmlBody += "</td></tr>";
	htmlBody += "</table></div>";
    }

    private void writeIdEleve() {
	htmlBody += "<div id='blocidentites'>" + "<table class='tabB' width='100%'>" + "<tr>"
		+ "<td id='ideleve' width='85%'>" + "<div id='nom'><span id='italic'>Nom et Prénoms: </span>"
		+ eleve.getNom() + " &#160;" + " &#160; " + eleve.getPrenom() + "</div>"
		+ "<div><span id='italic'>Sexe: </span>" + eleve.getSexe() + "</div>"
		+ "<div><span id='italic'>Code: </span>" + eleve.getCodeInfo()
		+ "<span id='italic'>&#160; &#160; &#160; Statut: </span>&#160;" + statut + "</div></td>"

		+ "<td id='image' width='15%'>";

	if (conf.bullConfig.photoEleve() == true) {
	    htmlBody += "<div><img src='" + photo.toURI() + "'/></div>";
	}

	htmlBody += "</td></tr>" + "</table>"// fin table id eleves

		+ "</div>";
    }

    private void writeTableauHonneur() {
	htmlBody += "<table id='tabHon' width='100%'>" + "<tr><td class='tdSB' width='80%'>Tableau d'Honneur ...</td>"
		+ "<td class='tdB' width='15%'></td><td class='tdSB' width='5%'></td></tr>"

		+ "<tr><td class='tdSB' width='80%'>Félicitation ......</td>"
		+ "<td class='tdB' width='15%'></td><td class='tdSB' width='5%'></td></tr>"

		+ "<tr><td class='tdSB' width='80%'>Encouragement ......</td>"
		+ "<td class='tdB' width='15%'></td><td class='tdSB' width='5%'></td></tr>"

		+ "<tr><td class='tdSB' width='80%'>Avertissement ........</td>"
		+ "<td class='tdB' width='15%'></td><td class='tdSB' width='5%'></td></tr>"

		+ "<tr><td class='tdSB' width='80%'>Blâme ..............</td>"
		+ "<td class='tdB' width='15%'></td><td class='tdSB' width='5%'></td></tr>" + "</table>";
    }

    private void writeDiscipline() {
	htmlBody += "<div width='100%' id='discipline'>Retards ..................... "
		+ "Abscences ................... Punitions .....................</div>";
    }

    private void writeMoyClasse() {
	// saut de ligne
	htmlBody += "<table id='tabMoyClasse' width='100%'>"// table moy
		// table moyenne extremes
		+ "<tr><td id='titreCdSmall' colspan=2>MOYENNES DE LA CLASSE</td></tr>" + "<tr>"
		+ "<td class='tdB'  width='75%'>Moyenne la plus forte</td>" + "<td class='tdSBL'  width='25%'>" + Hmoy
		+ "</td></tr><tr><td class='tdSBT'>Moyenne la plus faible</td>" + "<td class='tdBInf'>" + Lmoy
		+ "</td></tr><tr>" + "<td class='tdSBT'>Moyenne de la classe</td>" + "<td class='tdBInf'>" + mCls
		+ "</td></tr>";
	htmlBody += "</table>";
    }

    private void writeDecision() {
	htmlBody += "<table id='tabSB' width='100%'>"
		+ "<tr><td id='titreCdSmall' colspan=4>OBSERVATIONS ET DECISION DU CONSEIL</td></tr>"
		+ "<tr><td class='tdSBT' colspan=4><div id='obsConseil' align='center'>" + obs + "</div></td></tr>"

		+ "</table>"// fin table moy extremes
		+ "</div>";
    }

    // la méthode qui ecrit la l'entete du bulletin
    private void writeEntete() {
	decoupage.setSection(trimestre);
	htmlBody += bs.writeEntete();
	htmlBody += bs.writeRefEts();
	htmlBody += "<table width='100%'><tr><td width='35%' id='annee' valign='bottom'>Année- Scolaire: " + annee
		+ " </td>" + "<td width='50%' id='titreBull'>BULLETIN DE NOTES</td>"
		+ "<td width='35%' id='annee' valign='bottom'>" + decoupage.toString().toUpperCase()
		+ "</td></tr></table>";
    }

    private void writePiedPage() {
	htmlBody += bs.writeCode(eleve.getCodeInfo(), Constance.DEVISE,
		"<u>NB:</u> Il n'est délivré qu'un seul bulletin,prenez-en soin");
    }

    private void writeMoyenne() {
	htmlBody += "<table id='tabMoy' width='100%'  valign='top'>"
		+ "<tr><td colspan=4 id='titreCdSmall'>MOYENNES DE L'ELEVE</td></tr><tr>"
		+ "<td class='tdBC' width='55%'><b>" + "Moyenne du " + decoupage.sectionToString() + "</b></td>"
		+ "<td class='tdSBLC' width='15%'><b>" + moyGen + "</b></td>"
		+ "<td class='tdSBLC' width='15%'>Rang</td>" + "<td class='tdSBLC' width='15%'><b>" + rangGen
		+ "</b></td>" + "</tr>"

		+ "<tr>" + "<td id='enLettres' colspan='4'>" + moyGenStr + "</td>" + "</tr>"

		+ "<tr>" + "<td id='mention' colspan='4'>Mention:  <b>" + mention + "</b></td>" + "</tr>"

		// saut de ligne
		+ "<tr id='sautligne'><td colspan='4'></td></tr>"

		+ "<tr>";

	// S'affiche pour les trimestre autres que le premier
	if (trimestre > 1) {
	    htmlBody += "<td class='tdB'>1er " + decoupage.sectionToString() + "</td>" + "<td class='tdSBL'>" + moytrim1
		    + "</td>" + "<td class='tdSBL'>" + rangtrim1 + "</td>";
	} else {
	    htmlBody += "<td class='tdSB'></td>";
	}

	htmlBody += "</tr>" + "<tr>";

	// saffiche seulement lorsque le type est trimestre
	// et qu'on soit au 3ème trimestre
	if (trimestre == 3) {
	    htmlBody += "<td class='tdSBT'>2è " + decoupage.sectionToString() + "</td>" + "<td class='tdBInf'>"
		    + moytrim2 + "</td>" + "<td class='tdBInf'>" + rangtrim2 + "</td>";
	} else {
	    htmlBody += "<td class='tdSB'></td>";
	}

	htmlBody += "</tr>" + "<tr id='sautligne'><td colspan='4'></td></tr>" + "<tr>";

	// s'affiche seulement en fin d'année
	if (trimestre == maxtrim) {
	    htmlBody += "<td class='tdBC'><b>Moyenne Annuelle</b></td>" + "<td class='tdSBL'><b>" + moyAnn + "</b></td>"
		    + "<td class='tdSBL'><b>" + rangAnn + "</b></td>";
	} else {
	    htmlBody += "<td class='tdSB'></td>";
	}

	htmlBody += "</tr></table></td>";// fin 1ère colonne
    }

    private void writeGrandTotal() {
	// grand total
	htmlBody += "<tr>" + "<td class='tdSB' colspan='" + bs.colspan1 + "'></td>"
		+ "<td class='tdBC' colspan='2'><b>Totaux</b></td>" + "<td class='tdSBLC'>" + bilan.getTotCoef()
		+ "</td>" + "<td class='tdSBLC'>" + totaux + "</td>" + "<td class='tdSB' colspan='" + bs.colspan2
		+ "'></td>" + "</tr>" + "<tr><td id='sautligne' colspan='" + nbrcol + "'></td>" + "</tr>";
    }

    private void writeFacultative() {
	// initialisation des matieres facultatives
	if (conf.bullConfig.bullBloc() == true) {
	} else {
	    htmlBody += "<tr><td class='typemat' colspan='" + nbrcol + "'>"
		    + "III. MATIERES &#160; &#160; FACULTATIVES</td></tr>";
	}

	for (MatiereProg mat : Matieres) {
	    String intitule = mat.getIntitule();

	    // on recupère les notes de l'élève
	    // on fait appel au gestionnaire da l'affichage des
	    // note
	    if ((mat.getType()).equals("Facultative")) {
		InfoNote info = nview.getNotes(mat, new EleveClasse(eleve.getCodeInfo()));

		String coef = "", charge = "", note1 = "", note2 = "", dev = "", moyCls = "", compo = "", moy = "",
			ptobt = "", rang = "", appr = "";

		if (info.hasCompose() == true) {
		    coef = info.getCoefConsidered();
		    charge = mat.getCharge();
		    note1 = info.getNote1();
		    note2 = info.getNote2();
		    dev = info.getDev();
		    moyCls = info.getmoyClsStr();
		    compo = info.getCompo();
		    moy = info.getmoyStr();
		    ptobt = info.getPtObtStr();
		    rang = info.getRang();

		    // *********************Appr�ciation************
		    appr = "";
		    MartComputer mc = new MartComputer();

		    if (intitule.equals("Anglais"))
			appr = mc.getAppr(info.getmoy(), mc.ANG);
		    else if (intitule.equals("Allemand"))
			appr = mc.getAppr(info.getmoy(), mc.ALL);
		    else if (intitule.equals("Espagnol"))
			appr = mc.getAppr(info.getmoy(), mc.ESP);
		    else
			appr = mc.getAppr(info.getmoy(), mc.FRAN);
		    // **********************************************
		} else {
		    coef = "";
		    charge = "";
		    note1 = "";
		    note2 = "";
		    dev = "";
		    moyCls = "";
		    compo = "";
		    moy = "";
		    ptobt = "";
		    rang = "";
		}

		htmlBody += "<tr>";
		if (conf.bullConfig.numOrdre() == true)
		    htmlBody += "<td class='tdtitleleftC'>" + numMat + "</td>";
		if (conf.bullConfig.matiere() == true)
		    htmlBody += "<td class='tdBInfC'>" + intitule + "</td>";
		if (conf.bullConfig.noteClasse() == true) {
		    htmlBody += "<td class='tdBInf' width='5%'>" + note1 + "</td>";
		    htmlBody += "<td class='tdBInf' width='5%'>" + note2 + "</td>";
		    htmlBody += "<td class='tdBInf' width='5%'>" + dev + "</td>";
		}
		if (conf.bullConfig.moyClasse() == true)
		    htmlBody += "<td class='tdBInf'>" + moyCls + "</td>";
		if (conf.bullConfig.noteCompo() == true)
		    htmlBody += "<td class='tdBInf'>" + compo + "</td>";
		if (conf.bullConfig.moyGenerale() == true)
		    htmlBody += "<td class='tdBInf'>" + moy + "</td>";

		// s'affiche tjrs
		htmlBody += "<td class='tdBInf'>" + coef + "</td>";

		if (conf.bullConfig.ptObtenus() == true)
		    htmlBody += "<td class='tdBInf'>" + ptobt + "</td>";

		htmlBody += "<td class='tdBInf'>" + rang + "</td>";
		htmlBody += "<td class='tdBInf'>" + appr + "</td>";

		if (conf.bullConfig.prof() == true)
		    htmlBody += "<td class='tdBInf'>" + charge + "</td>";
		if (conf.bullConfig.signature() == true)
		    htmlBody += "<td class='tdBB'></td>";

		htmlBody += "</tr>";

		numMat++;
	    }
	    // *****POUR LA BARRE DE PORGRESSION***********
	    progress.increment();
	    // *********************FIN********************
	}
	// moyenne partielle litt
	if (conf.bullConfig.bullBloc() == true) {
	} else {
	    htmlBody += "<tr>" + "<td class='tdSB' colspan='" + bs.colspan1 + "'></td>"
		    + "<td class='tdSBT' colspan='2'>Total 3</td>" + "<td class='tdBInf'>" + bilan3.getTotCoef()
		    + "</td>" + "<td class='tdBInf'>" + totaux3 + "</td>" + "<td class='tdSB' colspan='" + bs.colspan2
		    + "'>" + "Moy 3 :" + moyGen3 + "</td>" + "</tr>";
	}
    }

    private void writeScientifique() {
	// initialisation des mati�res Scientifiques
	if (conf.bullConfig.bullBloc() == true) {
	} else {
	    htmlBody += "<tr><td class='typemat' colspan='" + nbrcol + "'>"
		    + "II. MATIERES &#160; &#160; SCIENTIFIQUES</td></tr>";
	}
	for (MatiereProg mat : Matieres) {
	    String intitule = mat.getIntitule();

	    // on recupere les notes de l'�l�ve
	    // on fait appel au gestionnaire da l'affichage des
	    // note
	    if ((mat.getType()).equals("Scientifique")) {
		InfoNote info = nview.getNotes(mat, new EleveClasse(eleve.getCodeInfo()));
		String coef = "", charge = "", note1 = "", note2 = "", dev = "", moyCls = "", compo = "", moy = "",
			ptobt = "", rang = "", appr = "";

		if (info.hasCompose() == true) {
		    coef = info.getCoefConsidered();
		    charge = mat.getCharge();
		    note1 = info.getNote1();
		    note2 = info.getNote2();
		    dev = info.getDev();
		    moyCls = info.getmoyClsStr();
		    compo = info.getCompo();
		    moy = info.getmoyStr();
		    ptobt = info.getPtObtStr();
		    rang = info.getRang();

		    // *********************Appr�ciation************
		    appr = "";
		    MartComputer mc = new MartComputer();

		    if (intitule.equals("Anglais"))
			appr = mc.getAppr(info.getmoy(), mc.ANG);
		    else if (intitule.equals("Allemand"))
			appr = mc.getAppr(info.getmoy(), mc.ALL);
		    else if (intitule.equals("Espagnol"))
			appr = mc.getAppr(info.getmoy(), mc.ESP);
		    else
			appr = mc.getAppr(info.getmoy(), mc.FRAN);
		    // **********************************************
		} else {
		    coef = "";
		    charge = "";
		    note1 = "";
		    note2 = "";
		    dev = "";
		    moyCls = "";
		    compo = "";
		    moy = "";
		    ptobt = "";
		    rang = "";
		}

		htmlBody += "<tr>";
		if (conf.bullConfig.numOrdre() == true)
		    htmlBody += "<td class='tdtitleleftC'>" + numMat + "</td>";
		if (conf.bullConfig.matiere() == true)
		    htmlBody += "<td class='tdBInfC'>" + intitule + "</td>";
		if (conf.bullConfig.noteClasse() == true) {
		    htmlBody += "<td class='tdBInf' width='5%'>" + note1 + "</td>";
		    htmlBody += "<td class='tdBInf' width='5%'>" + note2 + "</td>";
		    htmlBody += "<td class='tdBInf' width='5%'>" + dev + "</td>";
		}
		if (conf.bullConfig.moyClasse() == true)
		    htmlBody += "<td class='tdBInf'>" + moyCls + "</td>";
		if (conf.bullConfig.noteCompo() == true)
		    htmlBody += "<td class='tdBInf'>" + compo + "</td>";
		if (conf.bullConfig.moyGenerale() == true)
		    htmlBody += "<td class='tdBInf'>" + moy + "</td>";

		// s'affiche tjrs
		htmlBody += "<td class='tdBInf'>" + coef + "</td>";

		if (conf.bullConfig.ptObtenus() == true)
		    htmlBody += "<td class='tdBInf'>" + ptobt + "</td>";

		htmlBody += "<td class='tdBInf'>" + rang + "</td>";
		htmlBody += "<td class='tdBInf'>" + appr + "</td>";

		if (conf.bullConfig.prof() == true)
		    htmlBody += "<td class='tdBInf'>" + charge + "</td>";
		if (conf.bullConfig.signature() == true)
		    htmlBody += "<td class='tdBB'></td>";

		htmlBody += "</tr>";

		numMat++;
	    }
	    // *****POUR LA BARRE DE PORGRESSION***********
	    progress.increment();
	    // *********************FIN********************
	}

	// moyenne partielle scien
	if (conf.bullConfig.bullBloc() == true) {
	} else {
	    htmlBody += "<tr>" + "<td class='tdSB' colspan='" + bs.colspan1 + "'></td>"
		    + "<td class='tdSBT' colspan='2'>Total 2</td>" + "<td class='tdBInf'>" + bilan2.getTotCoef()
		    + "</td>" + "<td class='tdBInf'>" + totaux2 + "</td>" + "<td class='tdSB' colspan='" + bs.colspan2
		    + "'>" + "Moy 2 :" + moyGen2 + "</td>" + "</tr>";
	}
    }

    private void writeLitteraire() {
	if (conf.bullConfig.bullBloc() == true) {
	    htmlBody += "<tr><td class='typemat' colspan='" + nbrcol + "'>" + "MATIERES &#160; OBLIGATOIRES</td></tr>";
	} else {
	    htmlBody += "<tr><td class='typemat' colspan='" + nbrcol + "'>"
		    + "I. MATIERES &#160; &#160; LITTERAIRES</td></tr>";
	}

	for (MatiereProg mat : Matieres) {
	    String intitule = mat.getIntitule();

	    // on recup�re les notes de l'éléve
	    // on fait appel au gestionnaire da l'affichage des
	    // note
	    if ((mat.getType()).equals("Littéraire")) {
		InfoNote info = nview.getNotes(mat, new EleveClasse(eleve.getCodeInfo()));

		String coef = "", charge = "", note1 = "", note2 = "", dev = "", moyCls = "", compo = "", moy = "",
			ptobt = "", rang = "", appr = "";

		if (info.hasCompose() == true) {
		    coef = info.getCoefConsidered();
		    charge = mat.getCharge();
		    note1 = info.getNote1();
		    note2 = info.getNote2();
		    dev = info.getDev();
		    moyCls = info.getmoyClsStr();
		    compo = info.getCompo();
		    moy = info.getmoyStr();
		    ptobt = info.getPtObtStr();
		    rang = info.getRang();

		    // *********************Appréciation************
		    appr = "";
		    MartComputer mc = new MartComputer();

		    if (intitule.equals("Anglais"))
			appr = mc.getAppr(info.getmoy(), mc.ANG);
		    else if (intitule.equals("Allemand"))
			appr = mc.getAppr(info.getmoy(), mc.ALL);
		    else if (intitule.equals("Espagnol"))
			appr = mc.getAppr(info.getmoy(), mc.ESP);
		    else
			appr = mc.getAppr(info.getmoy(), mc.FRAN);
		    // **********************************************
		} else {
		    coef = "";
		    charge = "";
		    note1 = "";
		    note2 = "";
		    dev = "";
		    moyCls = "";
		    compo = "";
		    moy = "";
		    ptobt = "";
		    rang = "";
		}

		// on adffiche
		htmlBody += "<tr>";
		if (conf.bullConfig.numOrdre() == true)
		    htmlBody += "<td class='tdtitleleftC'>" + numMat + "</td>";
		if (conf.bullConfig.matiere() == true)
		    htmlBody += "<td class='tdBInfC'>" + intitule + "</td>";
		if (conf.bullConfig.noteClasse() == true) {
		    htmlBody += "<td class='tdBInf' width='5%'>" + note1 + "</td>";
		    htmlBody += "<td class='tdBInf' width='5%'>" + note2 + "</td>";
		    htmlBody += "<td class='tdBInf' width='5%'>" + dev + "</td>";
		}

		if (conf.bullConfig.moyClasse() == true)
		    htmlBody += "<td class='tdBInf'>" + moyCls + "</td>";
		if (conf.bullConfig.noteCompo() == true)
		    htmlBody += "<td class='tdBInf'>" + compo + "</td>";
		if (conf.bullConfig.moyGenerale() == true)
		    htmlBody += "<td class='tdBInf'>" + moy + "</td>";

		// s'affiche tjrs
		htmlBody += "<td class='tdBInf'>" + coef + "</td>";

		if (conf.bullConfig.ptObtenus() == true)
		    htmlBody += "<td class='tdBInf'>" + ptobt + "</td>";

		// s'affiche tjrs
		htmlBody += "<td class='tdBInf'>" + rang + "</td>";
		htmlBody += "<td class='tdBInf'>" + appr + "</td>";

		if (conf.bullConfig.prof() == true)
		    htmlBody += "<td class='tdBInf'>" + charge + "</td>";

		if (conf.bullConfig.signature() == true)
		    htmlBody += "<td class='tdBB'></td>";

		htmlBody += "</tr>";

		numMat++;
	    }
	    // *****POUR LA BARRE DE PORGRESSION***********
	    progress.increment();
	    // *********************FIN********************
	}

	// moyenne partielle litt
	if (conf.bullConfig.bullBloc() == true) {
	} else {
	    htmlBody += "<tr>" + "<td class='tdSB' colspan='" + bs.colspan1 + "'></td>"
		    + "<td class='tdSBT' colspan='2'>Total 1</td>" + "<td class='tdBInf'>" + bilan1.getTotCoef()
		    + "</td>" + "<td class='tdBInf'>" + totaux1 + "</td>" + "<td class='tdSB' colspan='" + bs.colspan2
		    + "'>" + "Moy 1 :" + moyGen1 + "</td>" + "</tr>";
	}
    }

}
