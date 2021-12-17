package com.chickenTest.farm.dao;

import java.util.List;

import com.chickenTest.farm.models.Granja;

public interface GranjaDao {

	public List<Granja> datos();
	public void editar(Granja granja);
	public List<Granja> getDatos();
}
