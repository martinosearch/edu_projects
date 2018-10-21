package ecolage;

import eleve.Eleve;
import eleve.EleveClasse;
import graphicsModel.MartList;

import javax.swing.JOptionPane;

import org.joda.time.DateTime;

import abstractObject.AbstractModel;
import classe.Classe;
import configurationAppli.Setting;
import connection.DAO;
import connection.DAOFactory;

public class EcoViewer {
	private DAO elvdao, setdao, clsdao, versecolagedao, elvclsdao;
	private String annee;
	private MartList<EleveEcolage> listeVersEcolage;
	private MartList<EleveClasse> eleves;
	private EcolageComputer computer;
	private Eleve superEleve;
	private double ecolage = 0;
	private Classe superClasse;
	private double totalEcoClasse;
	private double totalPayeClasse;
	private MartList<InfoEcoEleve> listeInfoEleve;
	private MartList<Operation> listInfoVersement;
	private AbstractModel listener;
	private MartList<Classe> classes;
	private InfoEcoEts infoEcoEts;

	public EcoViewer() {
		clsdao = DAOFactory.getDAO(DAO.CLASSE);
		elvdao = DAOFactory.getDAO(DAO.ELEVE);
		setdao = DAOFactory.getDAO(DAO.SETTING);
		versecolagedao = DAOFactory.getDAO(DAO.VERSEMENT_ECOLAGE);
		elvclsdao = DAOFactory.getDAO(DAO.ELEVE_CLASSE);
		elvdao.load();
		clsdao.load();
	}

	public void load(String an) {
		annee = an;
		versecolagedao.load(null, null, 0, annee);
		setdao.load(null, null, 0, annee);

		listeVersEcolage = versecolagedao.getListObt();
	}

	public void loadInfoClasse(String classe) {
		totalEcoClasse = 0;
		totalPayeClasse = 0;

		// on recupère les élèves de la classe
		// System.out.println("lancement dans ecoviwer " + classe + annee);
		superClasse = (Classe) clsdao.findObj(classe);
		elvclsdao.load(null, classe, 0, annee);
		eleves = elvclsdao.getListObt();

		computer = new EcolageComputer();
		computer.setAnnee(annee);

		Setting setEco = (Setting) setdao.findObj("ecolage_"
				+ superClasse.getNiveau());

		// on recupère l'écolage de la classe
		try {
			ecolage = Double.parseDouble((String) setEco.getAttribut());
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,
					"Vous n'avez pas défini d'écolage de cette classe");
		}

		totalEcoClasse = eleves.size() * ecolage;
		System.out.println("##################lancement dans ecoviwer "
				+ eleves.size() + "eco" + ecolage);
		// on calcule les infos ecolage de la classe
		listeInfoEleve = new MartList<InfoEcoEleve>();
		for (EleveClasse eleve : eleves) {
			superEleve = (Eleve) elvdao.findObj(eleve.getCodeInfo());

			EleveEcolage elveco = (EleveEcolage) versecolagedao.findObj(eleve
					.getCodeInfo());

			computer.setEleveEcolage(elveco);
			totalPayeClasse += computer.getRegle();

			InfoEcoEleve info = new InfoEcoEleve(eleve, computer);
			listeInfoEleve.add(info);
		}

	}

	public double getTotalEcoClasse() {
		return totalEcoClasse;
	}

	public void setTotalEcoClasse(double totalEcoClasse) {
		this.totalEcoClasse = totalEcoClasse;
	}

	public double getTotalPayeClasse() {
		return totalPayeClasse;
	}

	public void setTotalPayeClasse(double totalPayeClasse) {
		this.totalPayeClasse = totalPayeClasse;
	}

	public double getTotalRestantClasse() {
		return totalEcoClasse - totalPayeClasse;
	}

	public double getTotalClasse() {
		return totalEcoClasse;
	}

	public double getTauxRecEcolageClasse() {
		double taux = getTotalPayeClasse() / getTotalClasse() * 100;
		return taux;
	}

	public InfoEcoEleve findInfo(String code) {
		System.out.println("liste info: " + listeInfoEleve.size());
		return listeInfoEleve.find(code);
	}

	public double getEcolageClasse() {
		// TODO Auto-generated method stub
		return ecolage;
	}

	// bilan
	public void loadInfoEts() {
		listInfoVersement = new MartList<Operation>();
		infoEcoEts = new InfoEcoEts();

		for (EleveEcolage el : listeVersEcolage) {
			superEleve = (Eleve) elvdao.findObj(el.getCodeInfo());
			MartList<Operation> listOperation = el.getlisteOperation();
			boolean consider = true;
			for (Operation op : listOperation) {
				DateTime date = op.getDate();
				if (listener.isPeriodSelection()) {
					if (listener.getDate1().isBefore(date.getMillis())
							&& listener.getDate2().isAfter(date.getMillis())) {
						consider = true;
					} else {
						consider = false;
					}
				}
				if (consider == true) {
					op.setNomEleve(superEleve.getNom());
					op.setPrenomEleve(superEleve.getPrenom());
					op.setSexeEleve(superEleve.getSexe());
					op.setClasseEleve(superEleve.getClasseAnnee(annee));
					listInfoVersement.add(op);

					infoEcoEts.add(op);
				}
			}
		}
	}

	public void loadInfoStaEts() {
		classes = clsdao.getListObt();
		for (Classe cl : classes) {
			try {
				loadInfoClasse(cl.getIntitule());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public MartList<Operation> getListeOperation() {
		// TODO Auto-generated method stub
		return listInfoVersement;
	}

	public void setListener(AbstractModel lis) {
		listener = lis;
	}

	public InfoEcoEts getInfoEcoEts() {
		// TODO Auto-generated method stub
		return infoEcoEts;
	}
}
