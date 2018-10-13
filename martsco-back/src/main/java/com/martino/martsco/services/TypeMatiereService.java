package com.martino.martsco.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.martino.martsco.models.TypeMatiere;
import com.martino.martsco.repositories.TypeMatiereRepository;

@Service
@Transactional
public class TypeMatiereService implements MyService<TypeMatiere> {
	@Autowired
	private TypeMatiereRepository repository;

	@Override
	public void delete(Long id) {
		TypeMatiere obj = findOne(id);
		repository.delete(obj);
	}

	@Override
	public List<TypeMatiere> findAll() {
		List<TypeMatiere> objs = new ArrayList<TypeMatiere>();
		for (TypeMatiere obj : repository.findAll()) {
			objs.add(obj);
		}
		return objs;
	}

	@Override
	public TypeMatiere findOne(Long id) {
		Optional<TypeMatiere> obj = repository.findById(id);
		return obj.isPresent() ? obj.get() : null;
	}

	@Override
	public TypeMatiere save(TypeMatiere obj) {
		return repository.save(obj);
	}

}
