package rapportExamen;

import images.PictureFinder;

import javax.swing.JOptionPane;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import rapportBulletin.DocFormat;
import abstractObject.Rapport;
import agent.Responsable;
import configurationExamen.ConfigExamen;
import connection.DAO;
import connection.DAOFactory;
import examen.Examen;
import function.Constance;

public class RelFormat extends DocFormat {

	static String html;
	private String examen;
	private DAO examdao;
	private Examen superExamen;
	private ConfigExamen confExam;

	public RelFormat(String exam) {
		examen = exam;
		setdao = DAOFactory.getDAO(DAO.SETTING);
		examdao = DAOFactory.getDAO(DAO.EXAMEN);
		respdao = DAOFactory.getDAO(DAO.RESPONSABLE);
		examdao.load();
		respdao.load();
		superExamen = (Examen) examdao.findObj(examen);

		Plogo = new PictureFinder().getPlogo();

		confExam = new ConfigExamen(examen);
		nbreColBull = 3;
		pcent = 0;
		serie = 3;
		colspan1 = 0;
		colspan2 = 2;

		if (confExam.relConfig.numOrdre() == true) {
			nbreColBull++;
			pcent = pcent + 4;
			colspan1 = colspan1 + 1;
		}

		if (confExam.relConfig.matiere() == true) {
			nbreColBull++;
			pcent = pcent + 15;
			colspan1 = colspan1 + 1;
		}

		if (confExam.relConfig.noteClasse() == true) {
			for (int i = 0; i < serie; i++) {
				nbreColBull++;

			}

			colspan1 = colspan1 + 3;
			pcent = pcent + 15;
		}

		if (confExam.relConfig.moyClasse() == true) {
			nbreColBull++;
			pcent = pcent + 6;
			colspan1 = colspan1 + 1;
		}

		if (confExam.relConfig.noteCompo() == true) {
			nbreColBull++;
			pcent = pcent + 5;
		} else {
			colspan2 = colspan2 - 1;
		}

		if (confExam.relConfig.moyGenerale() == true) {
			nbreColBull++;
			pcent = pcent + 6;
		} else {
			colspan2 = colspan2 - 1;
		}

		// pour coef
		pcent = pcent + 4;

		if (confExam.relConfig.ptObtenus() == true) {
			nbreColBull++;
			pcent = pcent + 5;
		} else {
			colspan2 = colspan2 - 1;
		}

		// on ajoute les colonne pr�sente d'office
		pcent = pcent + 18;

		if (confExam.relConfig.prof() == true) {
			nbreColBull++;
			pcent = pcent + 13;
			colspan2 = colspan2 + 1;
		}

		if (confExam.relConfig.signature() == true) {
			nbreColBull++;
			pcent = pcent + 9;
			colspan2 = colspan2 + 1;
		}

		pcentrest = 100 - getPcent();
		System.out.println(pcentrest);

	}

	public int getPcent() {
		return pcent;
	}

	public static void main(String[] args) {
		RelFormat bs = new RelFormat("exam");
		System.out.println("Le nombre de colonne est:" + bs.getnbreCol());
		System.out.println("Le pourcentage:" + bs.getPcent() + "" + colspan1
				+ "  " + colspan2);
	}

	public int getnbreCol() {
		return nbreColBull;
	}

	// Ecrit le mode de table
	public String getTabNote() {
		html = "<table class='tabB' width='100%'><tr>";

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
		html += "</tr>";
		return html;
	}

	public String getTabSignature(int dIR2, String clsTable) {
		String html = "";

		DateTimeFormatter formatter = DateTimeFormat.mediumDate();
		formatter.withZoneUTC();

		DateTime date = new DateTime();

		if (dIR2 == PRESI) {
			try {
				html = "<table width='100%' class=" + clsTable + ">" + "<tr>"
						+ "<td width='40%'></td>" + "<td width='60%'>"
						+ "<p>Fait à " + Constance.VILLE + ", le "
						+ formatter.print(date) + "</p>"
						+ "<div align='center'><b><u>"
						+ PRESIDENT.getFonction() + "</u><b></div>" + "<p></p>"
						+ "<p></p>" + "<div align='center'>"
						+ PRESIDENT.decrisToi(Responsable.REVERSE) + "</div>"
						+ "</td>" + "</tr>" + "</table>";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (dIR2 == CHEF) {
			html = "<table width='100%' class=" + clsTable + ">" + "<tr>"
					+ "<td width='60%'></td>" + "<td width='40%'>"
					+ "<div><b><u>Le Chef d'Etablissement</u><b></div>"
					+ "</td>" + "</tr>" + "</table>";
		}

		if (dIR2 == DIR_TITUL) {
			html = "<table class=" + clsTable + " width='100%'>" + "<tr>"

			+ "<td width='100%' class='tdSB' align='center' colspan='3'>"
					+ "<span class='textNormal'>Fait à " + Constance.VILLE
					+ ", " + "le_________________________</span></td>"
					+ "</tr>"

					+ "<tr>" + "<td class='tdSB' width=40% align=center>"
					+ "<div><u><b>Le Titulaire</b></u></div>";

			if (confExam.relConfig.titSign() == true) {
				html += "<div></div>";
				if (confExam.relConfig.titNom() == true)
					html += "<div><b>"
							+ TITULAIRE.decrisToi(Responsable.REVERSE)
							+ "</b></div>";
			} else {
				if (confExam.relConfig.titNom() == true)
					html += "<div><b>"
							+ TITULAIRE.decrisToi(Responsable.REVERSE)
							+ "</b></div>";

				html += "<div></div>";
			}

			html += "</td>" + "<td class='tdSB' width=20%></td>"

			+ "<td class='tdSB' width=40% align=center>";

			if (confExam.relConfig.dirSign() == true) {
				html += "<div><u><b>" + PRESIDENT.getFonction()
						+ "</b></u></div>" + "<div></div>";

				if (confExam.relConfig.titNom() == true)
					html += "<div><b>"
							+ (PRESIDENT).decrisToi(Responsable.REVERSE)
							+ "</b></div>";
			} else {
				html += "<div><u><b>" + PRESIDENT.getFonction()
						+ "</b></u></div>";

				if (confExam.relConfig.titNom() == true)
					html += "<div><b>"
							+ PRESIDENT.decrisToi(Responsable.REVERSE)
							+ "</b></div>";

				html += "<div></div>";
			}

			html += "</td>" + "</tr>" + "</table>";
		}
		return html;
	}

	public void load() {
		Constance.setExamen(examen);
		try {
			Constance.miseajour();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PRESIDENT = new Responsable();

		try {
			PRESIDENT = (Responsable) respdao.findObj("president_"
					+ Constance.getCor(examen));
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (PRESIDENT == null) {
			JOptionPane.showMessageDialog(null,
					"Des infomations manque dans les configurations\n"
							+ "Régardez du côté 'Niveau' dans les conf"
							+ "igurations générales et aussi \n du "
							+ "côté du nom des responsable");
		}

	}

	public String writeEntete(String title) {
		String str = "<table width='100%'>"// table ref ets
				+ "<tr>";
		if (confExam.relConfig.logoEtt() == true) {
			str += "<td width='5%' rowspan=2><div id='image'><img src='"
					+ Plogo.toURI() + "'/></div></td>";
		}

		str += "<td class='tdSB' width='45%' align=top><div id='titre'>"
				+ Constance.INITIALE + "</div><div id='titre'>" + Constance.NOM
				+ "</div><div id='adresse2'>" + Constance.QUARTIER
				+ "</div><div id='adresse2'>Tel: " + Constance.TEL
				+ "</div><div id='adresse2'>" + Constance.BP + "</div></td>"

				+ "<td class='tdSB' width='50%'><div id='ministere'>"
				+ Constance.MINISTERE + "</div><div></div><div id='titreCd'>"
				+ title + "<div id='adresse2' align=center>" + examen
				+ "</div></div></td></tr></table>";
		return str;
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub

	}
}
