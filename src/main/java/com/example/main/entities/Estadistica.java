package com.example.main.entities;

public class Estadistica {
	private long mutantDna;
	private long humanDna;
	
	
	public Estadistica(long mutantDna, long humanDna) {
		this.mutantDna = mutantDna;
		this.humanDna = humanDna;
	}
	public long getMutantDna() {
		return mutantDna;
	}
	
	public long getHumanDna() {
		return humanDna;
	}
	
	public float ratio() {
		return (float)this.mutantDna/this.humanDna;		
	}
	
	
}
