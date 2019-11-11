package com.example.main.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "apirest_humano")
public class Humano {

	@Id
	@Column(name = "humano_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ElementCollection(targetClass=String.class)
	@JoinColumn(name="humano_fk_ADN")
	private List<String> ADN;	
	
	@Column(name = "humano_mutante")
	private boolean mutante;	
	
	public Humano() {
		
	}
	
	public Humano(List<String> ADN, boolean mutante) {
		this.ADN = ADN;
		this.mutante = mutante;
	}	

	public boolean isMutant() {
		return this.mutante;
	}
	
	public void setMutante(boolean mutante) {
		this.mutante = mutante;
	}
	
		
}
