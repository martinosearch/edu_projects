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

import com.martino.martsco.models.Classe;
import com.martino.martsco.services.ClasseService;

@RestController
@RequestMapping("/classe")
public class ClasseController {
	@Autowired
	private ClasseService service;

	@PostMapping("/create")
	public Classe create(@RequestBody Classe obj) {
		return service.save(obj);
	}

	@GetMapping("/{id}/delete")
	public void delete(@PathVariable long id) {
		service.delete(id);
	}

	@GetMapping("/{id}/info")
	public Classe getOne(@PathVariable long id) {
		return service.findOne(id);
	}

	@GetMapping("/liste")
	public List<Classe> liste() {
		return service.findAll();
	}

	@PutMapping("/annee/{id}/update")
	public Classe update(@RequestBody Classe obj, @PathVariable long id) {
		obj.setId(id);
		return service.save(obj);
	}
}