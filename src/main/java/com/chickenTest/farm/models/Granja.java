package com.chickenTest.farm.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="granja")
public class Granja {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")	
	private Long id;
	
	@Column(name="nombre")	
	private String nombre;
	
	@Column(name="dinero")	
	private double dinero;
	
	@Column(name="capacidadHuevos")	
	private int capacidadHuevos;
	
	@Column(name="capacidadPollos")	
	private int capacidadPollos;
	
	@Column(name="precioVentaPollo")	
	private double precioVentaPollo;
	
	@Column(name="precioVentaHuevo")	
	private double precioVentaHuevo;
	
	
	
	
	public String getNombre() {
		return nombre;
	}

	

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getDinero() {
		return dinero;
	}

	public void setDinero(double dinero) {
		this.dinero = dinero;
	}

	public int getCapacidadHuevos() {
		return capacidadHuevos;
	}

	public void setCapacidadHuevos(int capacidadHuevos) {
		this.capacidadHuevos = capacidadHuevos;
	}

	public int getCapacidadPollos() {
		return capacidadPollos;
	}

	public void setCapacidadPollos(int capacidadPollos) {
		this.capacidadPollos = capacidadPollos;
	}

	
}
