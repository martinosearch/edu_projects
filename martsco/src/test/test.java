package test;

import java.math.BigDecimal;

import org.joda.time.DateTime;

public class test {
	public test() throws Exception {
		DateTime t1 = new DateTime();
		DateTime t2 = new DateTime();

		System.out.println(t1.isBefore(t2) + "de "
				+ (t2.getMillis() - t1.getMillis()));
	}

	private void printLocal() throws Exception {
		System.out.println(System.getProperty("user.dir"));
	}

	public static void main(String[] args) throws Exception {
		double d = (200000000 * 100);
		System.out.println(new BigDecimal(d));
	}

}
