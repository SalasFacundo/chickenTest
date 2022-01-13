package com.sistema.inventario.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sistema.inventario.models.Pollo;

public interface IPolloRepository extends JpaRepository<Pollo, Integer>{

	  @Query("select dias, count(dias) as cantidad from Pollo group by dias order by dias")
	    List<String> cantidadDias();
}
