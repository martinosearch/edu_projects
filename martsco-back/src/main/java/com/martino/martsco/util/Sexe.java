package com.martino.martsco.util;

public class Sexe {
    private long id;

    public Sexe(long id) {
	this.id = id;
    }

    public String getIntitule() {
	if (id == 0)
	    return "Masculin";
	else
	    return "Féminin";
    }
}
