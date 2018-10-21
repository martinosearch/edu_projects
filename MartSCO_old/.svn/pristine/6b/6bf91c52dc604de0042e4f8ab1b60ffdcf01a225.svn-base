package function;

import ecolage.Operation;
import eleve.Eleve;
import graphicsModel.MartList;

import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.joda.time.DateTime;

import planning.Activite;
import agent.Agent;
import connection.DAO;
import connection.DAOFactory;

public class MatriGEN {
	protected String matri;
	protected static DAO elvdao, profdao, cdtdao, serialdao,
			operationdao, actdao;
	protected static String matricule;
	protected MartList<Eleve> eleves;
	protected MartList<Agent> profs;
	protected MartList<Eleve> candidats;
	protected MartList<Operation> listNumOp;
	private MartList<Activite> listNumActivite;
	protected MartList<Serial> serials;
	protected String codeObj = "";

	protected static int type = 0;

	public static int ELEVE = 0, AGENT = 1, CANDIDAT = 2, COMPTABILITE = 3,
			ACTIVITE = 4;
	private static DecimalFormat formatter = new DecimalFormat("0000");
	private int numElv = 0, numProf = 0, numCdt = 0, numCompta = 0,
			numSalaire = 0;
	private String annee;
	private int numActivite;

	public String getAnnee() {
		return annee;
	}

	public void setAnnee(String annee) {
		this.annee = annee;
	}

	public MatriGEN(int tpe) {
		this.type = tpe;
		elvdao = DAOFactory.getDAO(DAO.ELEVE);
		profdao = DAOFactory.getDAO(DAO.AGENT);
		cdtdao = DAOFactory.getDAO(DAO.CANDIDAT);
		serialdao = DAOFactory.getDAO(DAO.SERIAL);
		operationdao = DAOFactory.getDAO(DAO.OPERATION);
		actdao = DAOFactory.getDAO(DAO.ACTIVITE);

		// on charge pour avoir les serial
		serialdao.load();
		numElv = ((Serial) serialdao.findObj("numElv")).getSerialValue();
		numProf = ((Serial) serialdao.findObj("numprof")).getSerialValue();
		numCdt = ((Serial) serialdao.findObj("numCdt")).getSerialValue();
		numCompta = ((Serial) serialdao.findObj("numCompta_"
				+ Constance.getCor(annee))).getSerialValue();
		numActivite = ((Serial) serialdao.findObj("numActivite_"
				+ Constance.getCor(annee))).getSerialValue();

		System.out.println("Eleve " + numElv + "Prof " + numProf + "Cdt "
				+ numCdt + " numCompta_= " + numCompta);
	}

	public String getMatri() {
		// code eleve
		if (type == ELEVE) {
			if (numElv == 0) {
				eleves = elvdao.getList();
				int max = eleves.size();
				numElv = max + 1;
			} else
				numElv++;

			Serial act = new Serial("numElv");
			act.setSerialValue(numElv);

			serialdao.update_create(act);

			matricule = "ELV" + getCode(numElv);
		}

		// code enseignant
		if (type == AGENT) {
			if (numProf == 0) {
				profs = profdao.getList();
				int max = profs.size();
				numProf = max + 1;
			} else
				numProf++;

			Serial act = new Serial("numProf");
			act.setSerialValue(numProf);
			serialdao.update_create(act);

			matricule = "AGT" + getCode(numProf);
		}

		// code candidat à l'examen
		if (type == CANDIDAT) {
			ArrayList<Eleve> elvInternes = elvdao.getList();
			boolean exist = false;
			String matriTemp = "";

			// on vérifie d'abord si l'élève n'est pas interne
			for (Eleve elv : elvInternes) {
				if (elv.getPrimaryKey().equals(codeObj)) {
					String nom = elv.getNom() + " " + elv.getPrenom();

					int option = JOptionPane.showConfirmDialog(null,
							"MartSCO a détecté dans la base \n"
									+ "un élève du nom: " + nom + " \n"
									+ "S'agit-il de ce dernier?", "Info",
							JOptionPane.YES_NO_OPTION);

					if (option == JOptionPane.YES_OPTION) {
						exist = true;
					}

					matriTemp = elv.getCodeInfo();
				}
			}

			if (exist == true) {
				matricule = matriTemp;
			}

			else {
				if (numCdt == 0) {
					candidats = cdtdao.getList();
					int max = candidats.size();
					numCdt = max + 1;
				} else {
					numCdt++;
				}

				Serial act = new Serial("numCdt");
				act.setSerialValue(numCdt);
				serialdao.update_create(act);

				matricule = "ELV" + getCode(numCdt) + "EX";
			}
		}

		// code comptablité
		if (type == COMPTABILITE) {
			if (numCompta == 0) {
				operationdao.load(null, null, 0, annee);
				listNumOp = operationdao.getListObt();
				int max = listNumOp.size();
				numCompta = max + 1;
			} else
				numCompta++;

			Serial act = new Serial("numCompta_" + Constance.getCor(annee));
			act.setSerialValue(numCompta);
			serialdao.update_create(act);

			DateTime date = new DateTime();
			matricule = "COMPT" + date.getYear() + getCode(numCompta);
		}

		// code activite
		if (type == ACTIVITE) {
			if (numActivite == 0) {
				actdao.load(null, null, 0, annee);
				listNumActivite = actdao.getListObt();
				int max = listNumActivite.size();
				numActivite = max + 1;
			} else
				numActivite++;

			Serial act = new Serial("numActivite_" + Constance.getCor(annee));
			act.setSerialValue(numActivite);
			serialdao.update_create(act);

			matricule = "ACT" + getCode(numActivite);
		}

		return matricule;
	}

	private String getCode(int num) {
		String prefixe1 = "";
		String prefixe2 = "";
		String[] alpha2 = { "", "A", "B", "C", "D", "E", "F", "G", "H", "I",
				"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
				"V", "W", "X", "Y", "Z" };
		String[] alpha1 = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
				"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
				"W", "X", "Y", "Z" };

		int matri = (num % 10000);
		int rg1 = (int) (num - matri) / 10000;
		int rg2 = (int) (num - matri) / 10000 / 26;

		String matristr = formatter.format(matri);
		System.out.println(rg1 + "  " + rg2);

		prefixe1 += alpha1[rg1];
		prefixe2 += alpha2[rg2];

		String code = prefixe2 + prefixe1 + matristr;

		return code;
	}

	private void setCodeObj(String cod) {
		this.codeObj = cod;
	}

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			System.out.println();
		}
	}
}
