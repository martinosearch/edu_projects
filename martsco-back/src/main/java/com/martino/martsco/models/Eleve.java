package com.martino.martsco.models;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.joda.time.DateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "table_eleve")
@PrimaryKeyJoinColumn(name = "id")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Eleve extends Personne {
	// info classe
	private long classeActuelleId;

	// info inscription
	private DateTime dateEntree;
	private DateTime dateSortie;

	// info mère
	private long mereId;

	// info père
	private long pereId;

	// info Personne à prévenir
	private long tuteurId;

	@Override
	public String decrisToi() {
		return nom + " " + prenom;
	}
}
