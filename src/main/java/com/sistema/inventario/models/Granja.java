package com.sistema.inventario.models;


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
	
	@Column(name="nombre", length=50, nullable=false)	
	private String nombre;
	
	@Column(name="telefono", length=50, nullable=false)	
	private int telefono;
	
	@Column(name="direccion", length=50, nullable=false)	
	private String direccion;
	
	@Column(name="dinero", length=50, nullable=false)	
	private double dinero;
	
	@Column(name="capacidadHuevos", length=50, nullable=false)	
	private int capacidadHuevos;
	
	@Column(name="capacidadPollos", length=50, nullable=false)	
	private int capacidadPollos;
	
	@Column(name="precioVentaPollo", length=50, nullable=false)	
	private double precioVentaPollo;
	
	@Column(name="precioVentaHuevo", length=50, nullable=false)	
	private double precioVentaHuevo;
	
	


	public Granja(Long id, String nombre, int telefono, String direccion, double dinero, int capacidadHuevos,
			int capacidadPollos, double precioVentaPollo, double precioVentaHuevo) {
		
		this.id = id;
		this.nombre = nombre;
		this.telefono = telefono;
		this.direccion = direccion;
		this.dinero = dinero;
		this.capacidadHuevos = capacidadHuevos;
		this.capacidadPollos = capacidadPollos;
		this.precioVentaPollo = precioVentaPollo;
		this.precioVentaHuevo = precioVentaHuevo;
	}

	






	public Granja(String nombre, int telefono, String direccion, double dinero, int capacidadHuevos,
			int capacidadPollos, double precioVentaPollo, double precioVentaHuevo) {
		
		this.nombre = nombre;
		this.telefono = telefono;
		this.direccion = direccion;
		this.dinero = dinero;
		this.capacidadHuevos = capacidadHuevos;
		this.capacidadPollos = capacidadPollos;
		this.precioVentaPollo = precioVentaPollo;
		this.precioVentaHuevo = precioVentaHuevo;
	}








	public Granja() {
		
	}

	
	




	public Long getId() {
		return id;
	}






	public void setId(Long id) {
		this.id = id;
	}






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






	public double getPrecioVentaPollo() {
		return precioVentaPollo;
	}






	public void setPrecioVentaPollo(double precioVentaPollo) {
		this.precioVentaPollo = precioVentaPollo;
	}






	public double getPrecioVentaHuevo() {
		return precioVentaHuevo;
	}






	public void setPrecioVentaHuevo(double precioVentaHuevo) {
		this.precioVentaHuevo = precioVentaHuevo;
	}

	




	public int getTelefono() {
		return telefono;
	}







	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}







	public String getDireccion() {
		return direccion;
	}







	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}







	@Override
	public String toString() {
		return "Granja [id=" + id + ", nombre=" + nombre + ", telefono=" + telefono + ", direccion=" + direccion
				+ ", dinero=" + dinero + ", capacidadHuevos=" + capacidadHuevos + ", capacidadPollos=" + capacidadPollos
				+ ", precioVentaPollo=" + precioVentaPollo + ", precioVentaHuevo=" + precioVentaHuevo + "]";
	}







	
	
	

	
}
