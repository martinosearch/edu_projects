package com.martino.martsco.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.martino.martsco.models.Eleve;
import com.martino.martsco.repositories.EleveRepository;

@Service
@Transactional
public class EleveService implements MyService<Eleve> {
	@Autowired
	private EleveRepository repository;

	@Override
	public void delete(Long id) {
		Eleve obj = findOne(id);
		repository.delete(obj);
	}

	@Override
	public List<Eleve> findAll() {
		List<Eleve> objs = new ArrayList<Eleve>();
		for (Eleve obj : repository.findAll()) {
			objs.add(obj);
		}
		return objs;
	}

	@Override
	public Eleve findOne(Long id) {
		Optional<Eleve> obj = repository.findById(id);
		return (obj.isPresent()) ? obj.get() : null;
	}

	@Override
	public Eleve save(Eleve obj) {
		return repository.save(obj);
	}

}
