package progress;

import graphicsModel.MartList;
import interfacePerso.Observable;
import interfacePerso.Observer;

public class Avancer implements Observable {

	private int count;
	private String message = "Traitements des données en cours";
	private MartList<Observer> Observers;

	public Avancer() {
		count = 0;
		Observers = new MartList<Observer>();
	}

	public void addObserver(Observer obs) {
		Observers.add(obs);
	}

	public void increment() {
		count++;
		notifyObserver();
	}

	public void setMessage(String str) {
		this.message = str;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public void removeObserver() {
		Observers = new MartList<Observer>();
		System.out
				.println("Destruction d'un avanceur=============================>>");
	}

	@Override
	public void notifyObserver() {
		for (Observer obs : Observers) {
			obs.update();
		}
	}
}
