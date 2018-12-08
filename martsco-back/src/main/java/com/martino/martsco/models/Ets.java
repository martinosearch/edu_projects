package com.martino.martsco.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.swing.ImageIcon;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "table_ets")
@Data
@NoArgsConstructor
public class Ets {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String initiale;
	private String nom;
	private String quartier;
	private String ville;
	private String bp;
	private String tel;
	private String email;
	private String devise;
	private ImageIcon logo;
	private int typeEnsId;
}
