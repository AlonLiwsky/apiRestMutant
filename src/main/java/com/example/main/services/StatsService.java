package com.example.main.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.main.entities.Estadistica;
import com.example.main.entities.Humano;
import com.example.main.repositories.HumanoRepository;

@Service
public class StatsService {
	private HumanoRepository humanoRepository;

	public StatsService(HumanoRepository humanoRepository) {
		this.humanoRepository = humanoRepository;
	}
	
	public Estadistica getStats() {
		List<Humano> humanos = humanoRepository.findAll();
		int mutantes = 0;
		for (Humano humano : humanos) {
			if (humano.isMutant()) {
				mutantes++;
			}
		} 

		return new Estadistica(mutantes, humanos.size());
	}
}
