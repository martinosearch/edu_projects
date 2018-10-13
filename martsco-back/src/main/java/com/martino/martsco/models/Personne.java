package com.martino.martsco.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.joda.time.LocalTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(name = "table_personne")
public abstract class Personne {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;

	// Info élève
	protected String nom;
	protected String prenom;
	protected long sexeId;
	protected String tel;
	protected byte[] photo;

	protected String adresse;
	protected LocalTime dateNaissance;
	protected String email;

	public String decrisToi() {
		return nom;
	}
}
