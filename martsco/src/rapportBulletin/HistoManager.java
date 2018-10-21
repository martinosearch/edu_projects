package rapportBulletin;

import graphicsModel.MartList;
import connection.DAO;
import connection.DAOFactory;

public class HistoManager {
	public String title;
	public String text;
	DAO histodao = DAOFactory.getDAO(DAO.HISTORIQUE);
	private MartList<Histo> listeHisto;
	private int count = 0;
	private int deleteObj;

	public HistoManager() {
		histodao.load();
	}

	public void save(Histo histo) {
		this.title = histo.getIntitule();
		this.text = histo.getHisto();

		histodao.update_create(histo);

		// on ne veux que 30 historiques
		// on passe donc à la mise ajour
		getList();

		for (Histo his : listeHisto) {
			count = (his.getRang() > count) ? his.getRang() : count;
		}

		for (Histo his : listeHisto) {
			if (his.getRang() < count - 30) {
				histodao.deleteObj(his);
				deleteObj++;
			}
		}

		// System.out.println("Suppression de ====================>>" +
		// deleteObj
		// + " historiques");
	}

	public MartList<Histo> getList() {
		histodao.load();
		listeHisto = histodao.getListObt();

		return listeHisto;
	}

	public static void main(String[] args) {

	}

}
