package connection;

import java.sql.Connection;

import matiere.MatProgExamDAO;
import matiere.MatiereDAO;
import matiere.MatiereProgDAO;
import note.CoefDAO;
import note.NoteDAO;
import note.RapMoyenneDAO;
import note.SerieNoteDAO;
import planning.ActiviteDAO;
import rapportBulletin.HistoDAO;
import reference.ReferenceDAO;
import responsable.RespDAO;
import salaire.PayerSalaireDAO;
import salaire.SalaireDAO;
import security.SerialDAO;
import security.UserDAO;
import agent.AgentDAO;
import annee.AnneeDAO;
import candidat.CandidatDAO;
import candidat.CdtPersoDAO;
import classe.ClasseDAO;
import classe.NiveauDAO;
import configurationAppli.SetDAO;
import ecolage.OperationDAO;
import ecolage.VersementEcolageDAO;
import eleve.CursusDAO;
import eleve.EleveClasseDAO;
import eleve.EleveDAO;
import eleve.PromoEleveDAO;
import etablissement.EtsDAO;
import examen.ExamDAO;
import examen.NoteExamDAO;

public class DAOFactory {
	protected static Connection connexion = MartConnection.getInstance();
	protected static DAO dao = null;
	protected static int type = 0;

	public static DAO getDAO(int tpe) {
		type = tpe;

		switch (type) {

		case 0:
			dao = new EleveDAO(connexion);
			break;
		case 1:
			dao = new ClasseDAO(connexion);
			break;
		case 2:
			dao = new AnneeDAO(connexion);
			break;
		case 3:
			dao = new MatiereDAO(connexion);
			break;
		case 4:
			dao = new MatiereProgDAO(connexion);
			break;
		case 5:
			dao = new NoteDAO(connexion);
			break;
		case 6:
			dao = new EleveClasseDAO(connexion);
			break;
		case 8:
			dao = new CursusDAO(connexion);
			break;
		case 9:
			dao = new AgentDAO(connexion);
			break;
		case 10:
			dao = new ReferenceDAO(connexion);
			break;
		case 11:
			dao = new NiveauDAO(connexion);
			break;
		case 12:
			dao = new CoefDAO(connexion);
			break;

		case 13:
			dao = new UserDAO(connexion);
			break;

		case 14:
			dao = new SetDAO(connexion);
			break;

		case 15:
			dao = new RespDAO(connexion);
			break;
		case 16:
			dao = new RapMoyenneDAO(connexion);
			break;
		case 17:
			dao = new HistoDAO(connexion);
			break;
		case 18:
			dao = new ExamDAO(connexion);
			break;
		case 19:
			dao = new MatProgExamDAO(connexion);
			break;
		case 20:
			dao = new EtsDAO(connexion);
			break;
		case 21:
			dao = new CandidatDAO(connexion);
			break;
		case 22:
			break;
		case 23:
			dao = new PromoEleveDAO(connexion);
			break;
		case 24:
			dao = new SerialDAO(connexion);
			break;
		case 25:
			dao = new CdtPersoDAO(connexion);
			break;
		case 26:
			dao = new NoteExamDAO(connexion);
			break;
		case 28:
			dao = new VersementEcolageDAO(connexion);
			break;
		case 29:
			dao = new OperationDAO(connexion);
			break;

		case 30:
			dao = new SalaireDAO(connexion);
			break;

		case 31:
			dao = new ActiviteDAO(connexion);
			break;

		case 32:
			dao = new PayerSalaireDAO(connexion);
			break;

		case 33:
			dao = new SerieNoteDAO(connexion);
			break;
		}

		return dao;
	}

	public static void refresh() {
		MartConnection.refresh();
	}

}
