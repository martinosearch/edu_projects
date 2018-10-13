package com.martino.martsco.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.martino.martsco.models.TypeEns;
import com.martino.martsco.repositories.TypeEnsRepository;

@Service
@Transactional
public class TypeEnsService implements MyService<TypeEns> {
	@Autowired
	private TypeEnsRepository repository;

	@Override
	public void delete(Long id) {
		TypeEns obj = findOne(id);
		repository.delete(obj);
	}

	@Override
	public List<TypeEns> findAll() {
		List<TypeEns> objs = new ArrayList<TypeEns>();
		for (TypeEns obj : repository.findAll()) {
			objs.add(obj);
		}
		return objs;
	}

	@Override
	public TypeEns findOne(Long id) {
		Optional<TypeEns> obj = repository.findById(id);
		return obj.isPresent() ? obj.get() : null;
	}

	@Override
	public TypeEns save(TypeEns obj) {
		return repository.save(obj);
	}

}
