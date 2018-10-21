package function;

import graphicsModel.MartList;
import interfacePerso.MartRangeable;

import java.util.List;

import com.google.common.collect.Ordering;

public class MartArranger {

	public MartArranger() {

	}

	// methode fessant le rangement
	public static MartList<MartRangeable> arrange(
			MartList<MartRangeable> listeVal) {
		NoteComparator nc = new NoteComparator();
		Ordering<MartRangeable> ordering = Ordering.from(nc).reverse()
				.nullsLast();

		List<MartRangeable> listeR = listeVal;
		List<MartRangeable> result = ordering.sortedCopy(listeR);

		MartList<MartRangeable> rst = new MartList<MartRangeable>();

		for (int i = 0; i < result.size(); i++) {
			MartRangeable rv = result.get(i);
			rv.setRang(i + 1);
			rst.add(rv);
		}

		return rst;
	}

	// methode pour ranger n'importe quoi
	public MartList<MartRangeable> ordonner(List<MartRangeable> listeVal) {
		RangComparator rgc = new RangComparator();
		Ordering<MartRangeable> ordering = Ordering.from(rgc).nullsLast();
		List<MartRangeable> result = ordering.sortedCopy(listeVal);

		MartList<MartRangeable> listefinale = new MartList<MartRangeable>();

		for (int i = 0; i < result.size(); i++) {
			listefinale.add(result.get(i));
			// System.out.println("j'ai fais le boulot" +
			// result.get(i).getId());
		}

		return listefinale;
	}

	// methode pour ranger n'importe quoi
	public MartList<MartRangeable> ordonner(List<MartRangeable> listeVal,
			int order) {
		RangComparator rgc = new RangComparator();
		Ordering<MartRangeable> ordering = Ordering.from(rgc).nullsLast();
		List<MartRangeable> result = ordering.sortedCopy(listeVal);

		MartList<MartRangeable> listefinale = new MartList<MartRangeable>();

		for (int i = 0; i < result.size(); i++) {
			listefinale.add(result.get(i));
		}
		return listefinale;
	}

	// ************************************************
	public static String getOrder(int ord, String sexe) {
		String order = "";
		if (ord == 1 && sexe.equals("M"))
			order = ord + "er";
		else if (ord == 1 && sexe.equals("F"))
			order = ord + "ère";
		else
			order = ord + "è";

		return order;
	}

	public static String getOrderStr(int ord, String sexe) {
		String order = "";
		if (ord == 1 && sexe.equals("M"))
			order = "PREMIER";
		else if (ord == 1 && sexe.equals("F"))
			order = "PREMIERE";
		else if (ord == 2)
			order = "DEUXIEME";
		else if (ord == 3)
			order = "TROISIEME";

		return order;
	}
}
