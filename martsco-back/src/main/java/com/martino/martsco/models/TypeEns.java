package com.martino.martsco.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "table_type_ens")
@Data
@NoArgsConstructor
public class TypeEns {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String dim;
	private String intitule;
}
