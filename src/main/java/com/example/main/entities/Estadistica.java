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
		if(this.humanDna == 0) {
			return 0;
		}else {
			return (float) this.mutantDna/this.humanDna;
		}				
	}
	
	
}
