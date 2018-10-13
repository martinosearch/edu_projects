package com.martino.martsco.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.martino.martsco.models.Classe;
import com.martino.martsco.repositories.ClasseRepository;

@Service
@Transactional
public class ClasseService implements MyService<Classe> {
	@Autowired
	private NiveauEnsService niveauEnsService;

	@Autowired
	private ClasseRepository repository;

	@Override
	public void delete(Long id) {
		Classe obj = findOne(id);
		repository.delete(obj);
	}

	@Override
	public List<Classe> findAll() {
		List<Classe> objs = new ArrayList<Classe>();
		for (Classe obj : repository.findAll()) {
			objs.add(obj);
		}
		return objs;
	}

	@Override
	public Classe findOne(Long id) {
		Optional<Classe> obj = repository.findById(id);
		return obj.isPresent() ? obj.get() : null;
	}

	@Override
	public Classe save(Classe obj) {
		return repository.save(obj);
	}

}
