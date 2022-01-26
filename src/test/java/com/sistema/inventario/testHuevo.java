package com.sistema.inventario;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.sistema.inventario.models.Huevo;

class testHuevo {

	@Test
	public void testHuevo() {
		
		Huevo huevo= new Huevo(1L, 2);
		
		assertEquals(1L, huevo.getId());
		assertEquals(2, huevo.getDias());
		
		huevo.setId(2L);
		huevo.setDias(3);
		
		assertEquals(2L, huevo.getId());
		assertEquals(3, huevo.getDias());
	}

}
