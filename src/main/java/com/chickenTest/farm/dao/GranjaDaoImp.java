package com.chickenTest.farm.dao;

import java.util.List;



import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.chickenTest.farm.models.Granja;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Repository
@Transactional
public class GranjaDaoImp implements GranjaDao{

	
	
	
	
	
	@Override
	public List<Granja> datos() {
		
		SessionFactory miFactory= new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Granja.class).buildSessionFactory();
		
		Session miSession= miFactory.openSession();   
		
		
		try {
			miSession.beginTransaction();
			
			//CONSULTA DE CLIENTES
			
			List<Granja> datosGranja= miSession.createQuery("from Granja").getResultList();
			
			//MOSTRAR LOS CLIENTES
			
			for (Granja granja : datosGranja) {
				System.out.println(granja);
			}
			
			
			
			
			miSession.getTransaction().commit();
			
			//CERRAR SEESION
			miSession.close();
			//
		} catch (Exception e) {
			miFactory.close();
		}
		
		return null;
	}

	@Override
	public void editar(Granja granja) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Granja> getDatos() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
