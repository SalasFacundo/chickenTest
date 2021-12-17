package com.chickenTest.farm.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.chickenTest.farm.models.Granja;


@Repository
@Transactional
public class GranjaDaoImp implements GranjaDao{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Granja> getDatos() {
		String query="FROM Granja"; 
		
		return entityManager.createQuery(query).getResultList();
	}

	

	@Override
	public void editar(Granja granja) {
		
		entityManager.merge(granja);
		
	}



	@Override
	public List<Granja> datos() {
		
		String query="FROM Granja"; 		
		return entityManager.createQuery(query).getResultList();
		
	}

}
