package com.example.main.dtos;

import java.io.Serializable;
import java.util.List;

public class HumanoDTO implements Serializable{
	
	private int id;
	private List<String> ADN;
	private boolean mutante;
	
	public HumanoDTO(List<String> ADN, boolean mutante) {
		this.ADN = ADN;
		this.mutante = mutante;
	}
	
	public HumanoDTO() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<String> getADN() {
		return ADN;
	}

	public void setADN(List<String> aDN) {
		ADN = aDN;
	}

	public boolean isMutante() {
		return mutante;
	}

	public void setMutante(boolean mutante) {
		this.mutante = mutante;
	}
	
	

}
