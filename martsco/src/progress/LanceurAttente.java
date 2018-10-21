package progress;

import javax.swing.JWindow;

public class LanceurAttente extends Thread {
	
	private BarreAttente barreAttente;
	private JWindow window;
	private boolean actif;
	
	public LanceurAttente(){
		actif=true;
		barreAttente = new BarreAttente();
		window = new JWindow();
		window.add(barreAttente);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}
	
	public void arreter(){
		actif=false;
		window.dispose();
	}
	
	@Override
	public void run(){
		while(actif){
			try {
				Thread.sleep(10);
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			barreAttente.tourner(5);
			barreAttente.repaint();
		}
	}

}
