package function;

import interfacePerso.MartRangeable;

import java.util.Comparator;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;

public class RangComparator implements Comparator<MartRangeable> {
	public static int ASCENDANT = 1, DESCENDANT = 0;
	private int order = DESCENDANT;

	public int compare(MartRangeable v1, MartRangeable v2) {
		int result = 0;
		ComparisonChain chain = ComparisonChain.start();

		if (order == DESCENDANT) {
			chain = (chain.compare(v1.getRang(), v2.getRang(), Ordering
					.natural().nullsLast())).compare(v1.getIntitule(),
					v2.getIntitule(), Ordering.natural().reverse().nullsLast());
			// System.out.println("c'''''''''''''''''''est moi" +
			// v1.getRang()
			// + " " + v2.getRang());
		} else {
			chain = (chain.compare(v1.getRang(), v2.getRang(), Ordering
					.natural().reverse().nullsLast())).compare(
					v1.getIntitule(), v2.getIntitule(), Ordering.natural()
							.reverse().nullsLast());
		}

		result = chain.result();
		return result;
	}
}