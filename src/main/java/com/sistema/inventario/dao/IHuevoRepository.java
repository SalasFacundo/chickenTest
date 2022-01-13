package com.sistema.inventario.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sistema.inventario.models.Huevo;

public interface IHuevoRepository extends JpaRepository<Huevo, Integer>{
	
	@Query("select dias, count(dias) as cantidad from Huevo group by dias order by dias")
    List<String> cantidadDias();	
	
	
	
	

}
