package graphicsModel;

import function.MartArranger;
import interfacePerso.MartRangeable;

import java.util.ArrayList;

import configurationAppli.AbstractConfigPanel;
import note.Note;
import note.RgValue;

public class MartList<T> extends ArrayList<T> implements MartRangeable {
	public static int ASCENDANT = 1, DESCENDANT = 0;
	private String id = "";
	private int rang = 0;
	private String search = "";
	private MartArranger ma = new MartArranger();

	public MartList(String nom1) {
		super();
		this.id = nom1;
	}

	public MartList() {
		super();
	}

	public String getId() {
		return this.id;
	}

	public void setId(String str) {
		this.id = str;
	}

	public boolean contains(String ident) {
		if (find(ident) != null)
			return true;
		else
			return false;
	}

	public synchronized T find(String identifiant) {
		T cherche = null;
		search = identifiant;
		try {
			for (T gv : this) {
				/*
				 * System.out.println("J'ai tester:" + ((MartRangeable)
				 * gv).getId() + " Mais il faut " + identifiant);
				 */

				if (((MartRangeable) gv).getId().equals(search)) {
					cherche = gv;
				}
			}
		} catch (Exception e) {

		}
		return cherche;
	}

	public T find(int rg) {
		T cherche = null;
		for (T gv : this) {
			if (((MartRangeable) gv).getRang() == rg) {
				cherche = gv;
			}
		}
		return cherche;
	}

	public T find(String id, String inti) {
		T cherche = null;
		for (T gv : this) {
			if (((MartRangeable) gv).getId().equals(id)
					&& ((MartRangeable) gv).getIntitule().equals(inti)) {
				cherche = gv;
			}
		}
		return cherche;
	}

	public int getRang() {
		return rang;
	}

	@Override
	public void setRang(int rg) {
		this.rang = rg;
	}

	@Override
	public double getValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getIntitule() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setIntitule(String inti) {
		// TODO Auto-generated method stub

	}

	// Ajout intélligen c-à-d ajouter sans dupliquer
	public void smartAdd(T obj) {
		// System.out.println("Tentative d'ajout intélligemment:");

		T test = null;
		try {
			test = this.find(((MartRangeable) obj).getId());
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (test == null) {
			this.add(obj);
		} else {
			// System.out.println("L'insertion existe déja looooooool!");
		}
	}

	@Override
	public void setValue(double val) {
		// TODO Auto-generated method stub

	}

	public MartList set() {
		return null;
	}
}
