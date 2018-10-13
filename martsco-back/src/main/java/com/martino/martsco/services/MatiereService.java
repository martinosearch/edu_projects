package com.martino.martsco.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.martino.martsco.models.Matiere;
import com.martino.martsco.repositories.MatiereRepository;

@Service
@Transactional
public class MatiereService implements MyService<Matiere> {
	@Autowired
	private MatiereRepository repository;

	@Override
	public void delete(Long id) {
		Matiere obj = findOne(id);
		repository.delete(obj);
	}

	@Override
	public List<Matiere> findAll() {
		List<Matiere> objs = new ArrayList<Matiere>();
		for (Matiere obj : repository.findAll()) {
			objs.add(obj);
		}
		return objs;
	}

	@Override
	public Matiere findOne(Long id) {
		Optional<Matiere> obj = repository.findById(id);
		return obj.isPresent() ? obj.get() : null;
	}

	@Override
	public Matiere save(Matiere obj) {
		return repository.save(obj);
	}

}
