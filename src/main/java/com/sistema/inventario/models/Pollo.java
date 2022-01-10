package com.sistema.inventario.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pollo")
public class Pollo {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")	
	private Long id;
	
	
	@Column(name="dias", length=4, nullable=false)	
	private int dias;

	
	
	


	public Pollo() {
		
	}


	public Pollo(Long id, int dias) {
		this.id = id;
		this.dias = dias;
	}


	public Pollo(Long id) {
		this.id = id;
	}


	public Pollo(int dias) {
		this.dias = dias;
	}
	
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public int getDias() {
		return dias;
	}


	public void setDias(int dias) {
		this.dias = dias;
	}
	
}
