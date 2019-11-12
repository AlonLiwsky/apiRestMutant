package com.example.main.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.main.entities.Estadistica;
import com.example.main.services.StatsService;

@Controller
@RestController
@RequestMapping(path = "api/v1/stats")
public class StatsController {

	private StatsService statsService;
	
	public StatsController(StatsService statsService) {
		this.statsService = statsService;
	}
	
	@CrossOrigin("*")
	@GetMapping(path = "")
	public ResponseEntity getStats() {
		try {
			Estadistica stats = statsService.getStats();
			return ResponseEntity.status(200).body("'count_mutant_dna': " + stats.getMutantDna() + ", 'count_human_dna':"
					+ stats.getHumanDna() + ", 'ratio': " + stats.ratio());
		} catch (Exception e) {
			return ResponseEntity.status(500).body("'Error': 'Problema en el servidor, intente nuevamente'");
		}
		
	}
	
}
