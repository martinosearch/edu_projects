package rapportBulletin;

import javax.swing.JOptionPane;

import abstractObject.Rapport;
import agent.Responsable;
import configurationEcole.ConfigEcole;
import connection.DAO;
import connection.DAOFactory;
import function.Constance;
import images.PictureFinder;

public class BullFormat extends DocFormat {

    public BullFormat(int trim, String an) {
	setdao = DAOFactory.getDAO(DAO.SETTING);
	respdao = DAOFactory.getDAO(DAO.RESPONSABLE);
	Plogo = new PictureFinder().getPlogo();
	nbreColBull = 3;
	pcent = 0;
	serie = 3;
	colspan1 = 0;
	colspan2 = 2;
	annee = an;
	trimestre = trim;

	conf = new ConfigEcole(trimestre, annee);

	Constance.setAnnee(annee);
	try {
	    Constance.miseajour();
	} catch (Exception e) {
	    e.printStackTrace();
	}

	if (conf.bullConfig.numOrdre() == true) {
	    nbreColBull++;
	    pcent = pcent + 4;
	    colspan1 = colspan1 + 1;
	}
	if (conf.bullConfig.matiere() == true) {
	    nbreColBull++;
	    pcent = pcent + 15;
	    colspan1 = colspan1 + 1;
	}

	if (conf.bullConfig.noteClasse() == true) {
	    for (int i = 0; i < serie; i++) {
		nbreColBull++;

	    }

	    colspan1 = colspan1 + 3;
	    pcent = pcent + 15;
	}

	if (conf.bullConfig.moyClasse() == true) {
	    nbreColBull++;
	    pcent = pcent + 6;
	    colspan1 = colspan1 + 1;
	}

	if (conf.bullConfig.noteCompo() == true) {
	    nbreColBull++;
	    pcent = pcent + 5;
	} else {
	    colspan2 = colspan2 - 1;
	}

	if (conf.bullConfig.moyGenerale() == true) {
	    nbreColBull++;
	    pcent = pcent + 6;
	} else {
	    colspan2 = colspan2 - 1;
	}

	// pour coef
	pcent = pcent + 4;

	if (conf.bullConfig.ptObtenus() == true) {
	    nbreColBull++;
	    pcent = pcent + 5;
	} else {
	    colspan2 = colspan2 - 1;
	}

	// on ajoute les colonne pr�sente d'office
	pcent = pcent + 18;

	if (conf.bullConfig.prof() == true) {
	    nbreColBull++;
	    pcent = pcent + 13;
	    colspan2 = colspan2 + 1;
	}

	if (conf.bullConfig.signature() == true) {
	    nbreColBull++;
	    pcent = pcent + 9;
	    colspan2 = colspan2 + 1;
	}

	pcentrest = 100 - getPcent();
	System.out.println(pcentrest);

    }

    public BullFormat() {
	// TODO Auto-generated constructor stub
    }

    @Override
    public String getTabNote() {
	html = "<table class='tabB' width='100%'><tr>";

	if (typeRapport == Rapport.EVAL_TRIMESTRIELLE) {
	    if (model == Rapport.MODEL_SECOND) {
		html += "<td class='tdtitletop' width='4%' v-align='top'>N°</td>"
			+ "<td class='tdtitletop' width='60%' v-align='top'>Matière</td>"
			+ "<td class='tdtitletop' width='15%' v-align='top'>Note</td>"
			+ "<td class='tdtitletop' width='15%' v-align='top'>Sur</td>";
	    }

	    if (model == Rapport.MODEL_PRIM) {
		html += "<td class='tdtitletop' width='4%' v-align='top'>N°</td>"
			+ "<td class='tdtitletop' width='60%' v-align='top'>Matière</td>"
			+ "<td class='tdtitletop' width='15%' v-align='top'>Note</td>"
			+ "<td class='tdtitletop' width='15%' v-align='top'>Sur</td>";
	    }
	} else {
	    if (conf.bullConfig.numOrdre() == true) {
		html += "<td class='tdtitletopC' width='3%' v-align='top'>N°</td>";
	    }
	    if (conf.bullConfig.matiere() == true) {
		html += "<td class='tdtitletopC' width='29%' v-align='top'>Matière</td>";
	    }
	    if (conf.bullConfig.noteClasse() == true) {
		for (int i = 0; i < serie; i++) {
		}
		html += "<td class='tdtitletopC' colspan='" + serie + "' width='12%'" + " v-align='top'>Note "
			+ "de Classe</td>";
	    }

	    if (conf.bullConfig.moyClasse() == true) {
		html += "<td class='tdtitletopC' width='5%' v-align='top'>" + "Moy. de Classe</td>";
	    }

	    if (conf.bullConfig.noteCompo() == true) {
		html += "<td class='tdtitletopC' width='5%' v-align='top'>Note Compo</td>";
	    }
	    if (conf.bullConfig.moyGenerale() == true) {
		html += "<td class='tdtitletopC' width='5%' v-align='top'>Moy. Gle</td>";
	    }

	    // pour coef
	    html += "<td class='tdtitletopC' width='4%' v-align='top'>Coef</td>";

	    if (conf.bullConfig.ptObtenus() == true) {
		html += "<td class='tdtitletopC' width='5%' v-align='top'>Points Obtenus</td>";
	    }
	    // on ajoute les colonne pr�sente d'office
	    html += "<td class='tdtitletopC' width='5%' v-align='top'>Rang</td>"
		    + "<td class='tdtitletopC' width='8%'>Appréciation</td>";

	    if (conf.bullConfig.prof() == true) {
		html += "<td class='tdtitletopC' width='12%' v-align='top'>Professeur</td>";
	    }
	    if (conf.bullConfig.signature() == true) {
		html += "<td class='tdtitlerightC' width='7%' v-align='top'>Signature</td>";
	    }
	}
	html += "</tr>";

	return html;
    }

    @Override
    public String getTabSignature(int dIR2, String clsTable) {
	String html = "";
	if (dIR2 == DIR) {
	    html = "<table width='100%' class=" + clsTable + ">" + "<tr>" + "<td width='55%'></td>" + "<td width='45%'>"
		    + "<p>Fait à " + Constance.VILLE + ", le __________________</p>" + "<div align='center'><b><u>"
		    + DIRECTEUR.getFonction() + "</u><b></div>" + "<p></p>" + "<p></p>" + "<div align='center'>"
		    + DIRECTEUR.decrisToi(Responsable.REVERSE) + "</div>" + "</td>" + "</tr>" + "</table>";
	}

	if (dIR2 == CHEF) {
	    html = "<table width='100%' class=" + clsTable + ">" + "<tr>" + "<td width='60%'></td>" + "<td width='40%'>"
		    + "<div><b><u>Le Chef d'Etablissement</u><b></div>" + "</td>" + "</tr>" + "</table>";
	}

	if (dIR2 == DIR_TITUL) {
	    html = "<table class=" + clsTable + " width='100%'>" + "<tr>"

		    + "<td width='100%' class='tdSB' align='center' colspan='3'>" + "<span class='textNormal'>Fait à "
		    + Constance.VILLE + ", " + "le_________________________</span></td>" + "</tr>"

		    + "<tr>" + "<td class='tdSB' width=40% align=center>" + "<div><u><b>Le Titulaire</b></u></div>";

	    if (conf.bullConfig.titSign() == true) {
		try {
		    html += "<div></div>" + "<div></div>";
		    if (conf.bullConfig.titNom() == true)
			html += "<div><b>" + TITULAIRE.decrisToi(Responsable.REVERSE) + "</b></div>";
		} catch (Exception e) {
		    e.printStackTrace();
		    TitulaireManque();
		}
	    } else {
		if (conf.bullConfig.titNom() == true) {
		    try {
			html += "<div><b>" + TITULAIRE.decrisToi(Responsable.REVERSE) + "</b></div>";
			html += "<div></div><div></div>";
		    } catch (Exception e) {
			TitulaireManque();
		    }
		}
	    }

	    html += "</td>" + "<td class='tdSB' width=20%></td>"

		    + "<td class='tdSB' width=40% align=center>";

	    try {
		if (conf.bullConfig.dirSign() == true) {
		    html += "<div><u><b>" + DIRECTEUR.getFonction() + "</b></u></div>" + "<div></div>" + "<div></div>";

		    if (conf.bullConfig.titNom() == true)
			html += "<div><b>" + (DIRECTEUR).decrisToi(Responsable.REVERSE) + "</b></div>";
		} else {
		    html += "<div><u><b>" + DIRECTEUR.getFonction() + "</b></u></div>";

		    if (conf.bullConfig.titNom() == true)
			html += "<div><b>" + DIRECTEUR.decrisToi(Responsable.REVERSE) + "</b></div>";

		    html += "<div></div>" + "<div></div>";
		}
	    } catch (Exception e) {
		DirManque();
	    }
	    html += "</td>" + "</tr>" + "</table>";
	}
	return html;
    }

    @Override
    public void load() {
	if (typeEns.equals("PRIM"))
	    DIRECTEUR = Constance.DIRECTEUR_PRIM;
	if (typeEns.equals("COL"))
	    DIRECTEUR = Constance.DIRECTEUR_COL;
	if (typeEns.equals("LEG"))
	    DIRECTEUR = Constance.DIRECTEUR_LEG;
	if (typeEns.equals("LET"))
	    DIRECTEUR = Constance.DIRECTEUR_LET;

	if (DIRECTEUR == null) {
	    JOptionPane.showMessageDialog(null,
		    "Des infomations manque dans les configurations\n"
			    + "Régardez du côté 'Niveau' dans les configurations générales et aussi \n du "
			    + "côté du nom des responsable");
	}

	respdao.load();

	TITULAIRE = new Responsable();
	try {
	    TITULAIRE = (Responsable) respdao
		    .findObj("titulaire_" + Constance.getCor(classe) + "_" + Constance.getCor(annee));
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

    @Override
    public void refresh() {
    }

    @Override
    public String writeEntete(String title) {
	String str = "<table width='100%'>"// table ref ets
		+ "<tr>";
	if (conf.bullConfig.logoEtt() == true) {
	    str += "<td width='5%' rowspan=2><div id='image'><img src='" + Plogo.toURI() + "'/></div></td>";
	}

	str += "<td class='tdSB' width='45%' align=top><div id='titre'>" + Constance.INITIALE + "</div><div id='titre'>"
		+ Constance.NOM + "</div><div id='adresse2'>" + Constance.QUARTIER + "</div><div id='adresse2'>Tel: "
		+ Constance.TEL + "</div><div id='adresse2'>" + Constance.BP + "</div></td>"

		+ "<td class='tdSB' width='50%'><div id='ministere'>" + Constance.MINISTERE
		+ "</div><div></div><div id='titreCd'>" + title + "<div id='adresse2' align=center>Année- scolaire: "
		+ annee + "</div></div></td></tr></table>";
	return str;
    }

}
