package progress;

public class Lanceur {

	public static void effectuerTraitementLong() {
		for (int i = 0; i < 500; i++) {
			System.out.println(i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		LanceurAttente attente = new LanceurAttente();
		attente.start();
		attente.arreter();
	}

}
