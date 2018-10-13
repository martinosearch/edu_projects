package com.martino.martsco.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.martino.martsco.util.MyRangable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "table_annee")
@Data
@NoArgsConstructor
public class Annee implements MyRangable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(unique = true)
	private String intitule;
	private int isCurrent;

	@Transient
	private int num;
}
