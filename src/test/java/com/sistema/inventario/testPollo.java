package com.sistema.inventario;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.sistema.inventario.models.Pollo;

class testPollo {

	@Test
	public void testPollo() {
		
		Pollo pollo= new Pollo(2L, 3);
		
		assertEquals(2L, pollo.getId());
		assertEquals(3, pollo.getDias());
		
		pollo.setId(3L);
		pollo.setDias(4);
		
		assertEquals(3L, pollo.getId());
		assertEquals(4, pollo.getDias());
	}

}
