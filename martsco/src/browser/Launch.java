package browser;

import function.Constance;
import accueil.AccueilSCO;
import progress.Welcome;

public class Launch {
	public static AccueilSCO acceuil;
	public static Welcome animation;

	public static void main(String[] args) {

		Thread thd1 = new Thread(new Runnable() {
			public void run() {
				animation = new Welcome(1, 800);
				animation.revalidate();
			}
		});

		thd1.start();

		Thread starting = new Thread(new Runnable() {
			public void run() {
				askhand();
				try {
					Constance.initialize();
					Constance.miseajour();
				} catch (Exception e) {
					System.out.println("PREMIERE LANCEMENT DE LOGICIEL");
				}
				acceuil = AccueilSCO.getInstance();
			}
		});

		starting.start();
	}

	private synchronized static void askhand() {
		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
