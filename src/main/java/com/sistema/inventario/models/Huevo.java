package com.sistema.inventario.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="huevo")
public class Huevo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")	
	private Long id;
	
	
	@Column(name="dias", length=4, nullable=false)	
	private int dias;


	public Huevo(Long id) {
		super();
		this.id = id;
	}


	

	public Huevo(Long id, int dias) {
		
		this.id = id;
		this.dias = dias;
	}
	
	public Huevo() {
		
		
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
