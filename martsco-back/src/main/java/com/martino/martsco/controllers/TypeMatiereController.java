package com.martino.martsco.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.martino.martsco.models.TypeMatiere;
import com.martino.martsco.services.TypeMatiereService;

@RestController
@RequestMapping("/type-matiere")
public class TypeMatiereController {
	@Autowired
	private TypeMatiereService service;

	@PostMapping("/create")
	public TypeMatiere create(@RequestBody TypeMatiere obj) {
		return service.save(obj);
	}

	@GetMapping("/{id}/delete")
	public void delete(@PathVariable long id) {
		service.delete(id);
	}

	@GetMapping("/{id}/info")
	public TypeMatiere getOne(@PathVariable long id) {
		return service.findOne(id);
	}

	@GetMapping("/liste")
	public List<TypeMatiere> liste() {
		return service.findAll();
	}

	@PutMapping("/annee/{id}/update")
	public TypeMatiere update(@RequestBody TypeMatiere obj, @PathVariable long id) {
		obj.setId(id);
		return service.save(obj);
	}
}