package com.sistema.inventario;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.sistema.inventario.models.Granja;

class testGranja {

	@Test
	void test() {

		
		
		Granja granja= new Granja(1L, "Los pollos hermanos", 2236005531L, "Calle falsa 123", 123.12, 500, 100, 100.50, 50.50, 80.00, 30.00, 4, 4, 10, 5, 1);
		
		assertEquals(1L,granja.getId());
		assertEquals("Los pollos hermanos",granja.getNombre());
		assertEquals(2236005531L,granja.getTelefono());
		assertEquals("Calle falsa 123",granja.getDireccion());
		assertEquals(123.12,granja.getDinero());
		assertEquals(500,granja.getCapacidadHuevos());
		assertEquals(100,granja.getCapacidadPollos());
		assertEquals(100.50,granja.getPrecioVentaPollo());
		assertEquals(50.50,granja.getPrecioVentaHuevo());
		assertEquals(80.00,granja.getPrecioCompraPollo());
		assertEquals(30.00,granja.getPrecioCompraHuevo());
		assertEquals(4,granja.getDiasDeCompraPollo());
		assertEquals(4,granja.getDiasDeCompraHuevo());
		assertEquals(10,granja.getDiasPolloEnMorir());
		assertEquals(5,granja.getDiasPolloEnPonerHuevo());
		assertEquals(1,granja.getDiasHuevoEnDarPollo());
		
		granja.setId(2L);
		granja.setNombre("El pollo hermano");
		granja.setTelefono(2236115531L);
		granja.setDireccion("Calle verdadera 321");
		granja.setDinero(321.21);
		granja.setCapacidadHuevos(1000);
		granja.setCapacidadPollos(200);
		granja.setPrecioVentaPollo(300.00);
		granja.setPrecioVentaHuevo(100.00);
		granja.setPrecioCompraPollo(160.00);
		granja.setPrecioCompraHuevo(60.00);
		granja.setDiasDeCompraPollo(8);
		granja.setDiasDeCompraHuevo(8);
		granja.setDiasPolloEnMorir(20);
		granja.setDiasPolloEnPonerHuevo(10);
		granja.setDiasHuevoEnDarPollo(2);
		
		assertEquals(2L,granja.getId());
		assertEquals("El pollo hermano",granja.getNombre());
		assertEquals(2236115531L,granja.getTelefono());
		assertEquals("Calle verdadera 321",granja.getDireccion());
		assertEquals(321.21,granja.getDinero());
		assertEquals(1000,granja.getCapacidadHuevos());
		assertEquals(200,granja.getCapacidadPollos());
		assertEquals(300.00,granja.getPrecioVentaPollo());
		assertEquals(100.00,granja.getPrecioVentaHuevo());
		assertEquals(160.00,granja.getPrecioCompraPollo());
		assertEquals(60.00,granja.getPrecioCompraHuevo());
		assertEquals(8,granja.getDiasDeCompraPollo());
		assertEquals(8,granja.getDiasDeCompraHuevo());
		assertEquals(20,granja.getDiasPolloEnMorir());
		assertEquals(10,granja.getDiasPolloEnPonerHuevo());
		assertEquals(2,granja.getDiasHuevoEnDarPollo());
		
		
	}

}
