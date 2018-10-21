package com.martino.martsco.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.martino.martsco.models.Annee;
import com.martino.martsco.repositories.AnneeRepository;

@Service
@Transactional
public class AnneeService implements MyService<Annee> {
	@Autowired
	private AnneeRepository repository;

	@Override
	public void delete(Long id) {
		Annee annee = findOne(id);
		repository.delete(annee);
	}

	@Override
	public List<Annee> findAll() {
		List<Annee> objs = new ArrayList<>();
		int i = 0;
		for (Annee an : repository.findAll()) {
			objs.add(an);

			i++;
		}
		return objs;
	}

	@Override
	public Annee findOne(Long id) {
		Optional<Annee> obj = repository.findById(id);
		return obj.isPresent() ? obj.get() : null;
	}

	@Override
	public Annee save(Annee obj) {
		return repository.save(obj);
	}
}
