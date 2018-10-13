package com.martino.martsco.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.martino.martsco.models.Annee;
import com.martino.martsco.services.AnneeService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/annee")
public class AnneeController {
	@Autowired
	private AnneeService service;

	@PostMapping("/save")
	public Annee save(@RequestBody Annee obj) {
		System.out.println("====================================>>" + obj);
		return service.save(obj);
	}

	@DeleteMapping("/{id}/delete")
	public void delete(@PathVariable long id) {
		System.out.println("====================================>>" + id);
		service.delete(id);
	}

	@GetMapping("/{id}/info")
	public Annee getOne(@PathVariable long id) {
		return service.findOne(id);
	}

	@GetMapping("/liste")
	public List<Annee> liste() {
		return service.findAll();
	}
}
