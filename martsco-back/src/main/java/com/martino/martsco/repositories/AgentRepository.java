package com.martino.martsco.repositories;

import org.springframework.data.repository.CrudRepository;

import com.martino.martsco.models.Matiere;

public interface AgentRepository extends CrudRepository<Matiere, Long> {

}