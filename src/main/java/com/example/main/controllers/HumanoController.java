package com.example.main.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.main.dtos.HumanoDTO;
import com.example.main.dtos.ListadoDTO;
import com.example.main.entities.Estadistica;
import com.example.main.services.HumanoService;

@Controller
@RestController
@RequestMapping(path = "api/v1/mutant")
public class HumanoController {

	private HumanoService humanoService;

	public HumanoController(HumanoService humanoService) {
		this.humanoService = humanoService;
	}

	@CrossOrigin("*")
	@PostMapping(path = "/")
	public ResponseEntity isMutant(@RequestBody ListadoDTO dna) {
		try {
			if (humanoService.isMutant(dna)) {
				return ResponseEntity.status(200).body("{'mutant': " + true + "}");
			} else {
				return ResponseEntity.status(403).body("{'mutant': " + false + "}");
			}
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(400).body("'Error': 'La matriz no cumple con NxN'");
		}catch(NullPointerException e) {
			return ResponseEntity.status(400).body("'Error': 'El formato de la matriz no es valido, asegurese de nombrar la variable dna'");
		}
	}

	@CrossOrigin("*")
	@GetMapping(path = "/stats")
	public ResponseEntity getStats() {
		try {
			Estadistica stats = humanoService.getStats();
			return ResponseEntity.status(200).body("'count_mutant_dna': " + stats.getMutantDna() + ", 'count_human_dna':"
					+ stats.getHumanDna() + ", 'ratio': " + stats.ratio());
		} catch (Exception e) {
			return ResponseEntity.status(500).body("'Error': 'Problema en el servidor, intente nuevamente'");
		}
		
	}

}
