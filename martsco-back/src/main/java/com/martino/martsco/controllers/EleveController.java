package com.martino.martsco.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.martino.martsco.models.Eleve;
import com.martino.martsco.services.EleveService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/eleve")
public class EleveController {
	@Autowired
	private EleveService service;

	@PostMapping("/create")
	public Eleve create(@RequestBody Eleve obj) {
		return service.save(obj);
	}

	@GetMapping("/{id}/delete")
	public void delete(@PathVariable long id) {
		service.delete(id);
	}

	@GetMapping("/{id}/info")
	public Eleve getOne(@PathVariable long id) {
		return service.findOne(id);
	}

	@GetMapping("/liste")
	public List<Eleve> liste() {
		return service.findAll();
	}
}