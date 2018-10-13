package com.martino.martsco.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.martino.martsco.models.NiveauEns;
import com.martino.martsco.repositories.NiveauEnsRepository;

@Service
@Transactional
public class NiveauEnsService implements MyService<NiveauEns> {
	@Autowired
	private NiveauEnsRepository repository;

	@Override
	public void delete(Long id) {
		NiveauEns obj = findOne(id);
		repository.delete(obj);
	}

	@Override
	public List<NiveauEns> findAll() {
		List<NiveauEns> objs = new ArrayList<NiveauEns>();
		for (NiveauEns obj : repository.findAll()) {
			objs.add(obj);
		}
		return objs;
	}

	@Override
	public NiveauEns findOne(Long id) {
		Optional<NiveauEns> obj = repository.findById(id);
		return obj.isPresent() ? obj.get() : null;
	}

	@Override
	public NiveauEns save(NiveauEns obj) {
		return repository.save(obj);
	}

}
